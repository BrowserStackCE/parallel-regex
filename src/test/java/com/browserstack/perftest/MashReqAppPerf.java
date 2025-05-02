package com.browserstack.perftest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;


public class MashReqAppPerf implements ITest {

    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    private int data;

    private String testName;

    public AndroidDriver getDriver() {
        return driver.get();
    }

    public MashReqAppPerf(int data)
    {
        this.data=data;
        this.testName = "MashReq API limit test "+data;
    }



    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new UiAutomator2Options();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        bstackOptions.put("sessionName", testName);
        capabilities.setCapability("bstack:options", bstackOptions);

        AndroidDriver driverInstance = new AndroidDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.set(driverInstance);


    }


    @Test
    public void testApp() throws Exception {
        //int data=1;
        System.out.println("Test run "+ data);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
        WebElement searchElement = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text=\"Sign In\"]/parent::android.widget.Button")));

        searchElement.isDisplayed();

        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\": \""+testName+"\"}}");

    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        getDriver().quit();
    }

    @Override
    public String getTestName() {
        return testName;
    }
}
