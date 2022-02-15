package net.lab1024.smartadmin.util;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SmartSeleniumUtil {
    public static String getRandomUA() {
        List<String> uaInfos = Arrays.asList(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36"
        );

        Random random = new Random();
        int i = random.nextInt(uaInfos.size());
        return uaInfos.get(i);
    }

    public static ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless" );
        options.addArguments("user-agent=" + getRandomUA());
//        options.addArguments("--user-data-dir=" + "Users/mabintao/Library/Application Support/Google/Chrome/Default" );
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation" ));
        options.setExperimentalOption("useAutomationExtension", false);
        ChromeDriver driver = new ChromeDriver(options);

        return driver;
    }

    public static void jumpPage(ChromeDriver driver, String url) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String script = "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})";
        driver.executeScript(script);
        driver.get(url);
        driver.executeScript(script);
    }

    public static void taoBaoLogin(ChromeDriver driver) {
        String loginUrl = "https://login.taobao.com/";
        String loginName = "18148542996";
        String password = "Matt960912";

        SmartSeleniumUtil.jumpPage(driver, loginUrl);
        driver.findElement(By.id("fm-login-id" )).sendKeys(loginName);
        driver.findElement(By.id("fm-login-password" )).sendKeys(password);
        driver.findElement(By.className("password-login" )).click();
    }
}
