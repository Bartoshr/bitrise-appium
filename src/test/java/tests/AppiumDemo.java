package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDemo {
    public static AppiumDriver driver;

    @BeforeClass
    public void Android_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "espresso");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("showGradleLog",true);
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apps/app-debug.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void calcDemo(){

        WebElement addButton = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Add Button\"]");
        WebElement inputField = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText");
        WebElement item = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.EditText");

        // Paste input.
        inputField.click();
        inputField.sendKeys("Hello world");

        // Add new item.
        addButton.click();
        driver.navigate().back();

        // Check item.
        Assert.assertEquals(item.getText(), "Hello world");
    }


    @AfterClass
    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
