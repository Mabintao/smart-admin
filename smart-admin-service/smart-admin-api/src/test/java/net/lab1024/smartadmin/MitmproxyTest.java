package net.lab1024.smartadmin;

import com.deep007.mitmproxyjava.filter.FlowFilter;
import com.deep007.mitmproxyjava.mitmproxy.RemoteMitmproxy;
import com.deep007.mitmproxyjava.modle.FlowRequest;
import com.deep007.mitmproxyjava.modle.FlowResponse;
import org.junit.Test;

/**
 * @className: MitmproxyTest
 * @description: TODO 类描述
 * @author: mattbtma
 * @date: 2022/2/15
 **/
public class MitmproxyTest {
    @Test
    public void firstDemo() throws InterruptedException {
        //在遠程機器上的8866端口上啟動一個mitmproxy
        RemoteMitmproxy remoteMitmproxy = new RemoteMitmproxy("127.0.0.1", 60051, "127.0.0.1", 8866);
        remoteMitmproxy.addFlowFilter(new FlowFilter() {

            @Override
            public void filterRequest(FlowRequest flowRequest) {
                System.out.println(flowRequest.getUrl());
            }

            @Override
            public void filterResponse(FlowResponse flowResponse) {
                FlowRequest flowRequest = flowResponse.getRequest();
                if(flowRequest.getUrl().contains(".js")){
                   String inject_text = "try{Object.defineProperties(navigator,{webdriver:{get:() => false}});}catch(e){console.log(e);}";
                   flowResponse.setContentAsString(inject_text+flowResponse.getContentAsString());
                   System.out.println("inject success");
                }
            }

        });
        remoteMitmproxy.start();
        Thread.sleep(1000 * 60 * 5);
        remoteMitmproxy.stop();

    }
}
