package net.lab1024.smartadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.lab1024.smartadmin.util.SmartSeleniumUtil;
import net.lab1024.smartadmin.util.SmartStringUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SeleniumTest extends BaseTest {
    @Test
    public void test() throws InterruptedException {

        List<String> ids = Arrays.asList("631501014973",
                "634542275732",
                "645030986300",
                "538309588036",
                "531801217795",
                "639238825497",
                "653884247890",
                "591511142030",
                "619405906420" );

        List<PageInfo> pageInfo = new ArrayList<>();
        ChromeDriver driver = SmartSeleniumUtil.getChromeDriver();
        SmartSeleniumUtil.taoBaoLogin(driver);

        Actions action = new Actions(driver);

        for (int i = 0; i < 1000; i++) {
            action.moveByOffset(new Random().nextInt(100), new Random().nextInt(1000));

        }

        for (String id : ids) {
            String url = "https://detail.1688.com/offer/" + id + ".html";
            SmartSeleniumUtil.jumpPage(driver, url);

            if (hasGoodName(driver)) {
                pageInfo.add(new PageInfo(id, getGoodName(driver)));
            } else {
                Thread.sleep(30000);
                while (hasGoodName(driver)) {
                    pageInfo.add(new PageInfo(id, getGoodName(driver)));
                    break;
                }
            }

        }

//        // 5.退出浏览器
////        driver.quit();
    }

    private String getGoodName(ChromeDriver driver) {
        return driver.findElement(By.cssSelector(".title-first-column .title-text" )).getText();
    }

    private boolean hasGoodName(ChromeDriver driver) {
        return SmartStringUtil.isEmpty(getGoodName(driver));
    }

    @Data
    @AllArgsConstructor
    public class PageInfo {
        private String id;
        private String name;
    }

}
