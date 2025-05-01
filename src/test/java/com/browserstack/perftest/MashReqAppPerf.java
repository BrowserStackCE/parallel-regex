package com.browserstack.perftest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;


public class MashReqAppPerf {

    public AndroidDriver driver;

    @DataProvider(name = "testData", parallel = true)
    public Object[][] createTestData() {
        Object[][] data = new Object[50][1];
        for (int i = 0; i < 50; i++) {
            data[i][0] = i + 1; // Numbers 1 to 50
        }
        return data;
    }


    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {

        AppiumClientConfig clientConfig = AppiumClientConfig.defaultConfig()
                .baseUrl(new URL("http://localhost:4444/wd/hub"))
                .connectionTimeout(Duration.ofSeconds(180))
                .readTimeout(Duration.ofSeconds(300)); // Example: 300 seconds

        MutableCapabilities capabilities = new UiAutomator2Options();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:platformVersion", "12.0");
        capabilities.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        capabilities.setCapability("appium:app", "bs://9956628e64b1fe6243e24fafd2e3dec3b741a774");
        driver = new AndroidDriver(clientConfig,capabilities);
    }

    //@Test(dataProvider = "testData")
    @Test
    public void testApp() throws Exception {
        int data=1;
        System.out.println("Test run "+ data);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement searchElement = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
