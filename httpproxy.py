import re
INJECT_TEXT = 'try{Object.defineProperties(navigator,{webdriver:{get:() => undefined}});}catch(e){console.log(e);}'
def response(flow):
    match  = re.search(r'\.js',flow.request.url)
    if match:
    	# 屏蔽selenium检测
    	flow.response.text = INJECT_TEXT + flow.response.text
    	print('注入成功')
        #flow.response.text = flow.response.text + INJECT_TEXT
