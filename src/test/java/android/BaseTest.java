package android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    @BeforeClass
    public void setup() throws Exception{
        //Code to start server
        service = new AppiumServiceBuilder().
                withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js")).
                withIPAddress("127.0.0.1").
                usingPort(4723).build();
        service.start();

        //AndroidDriver, IOSDriver
        //Appium Code -> Appium Server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("GerardoEmulator");
        options.setChromedriverExecutable("//Users//gerardoalbertofloresnava//Documents//chromedriver");
        //options.setApp("//Users//gerardoalbertofloresnava//IdeaProjects//AppiumTraining//src//test//resources//ApiDemos-debug.apk");
        options.setApp("//Users//gerardoalbertofloresnava//IdeaProjects//AppiumTraining//src//test//resources//General-Store.apk");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        //Stop driver
        driver.quit();
        //Stop server
        service.stop();
    }

    public void longPressAction(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000
                ));
    }

    public void scrollIntoViewAction(String text){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void swipeAction(WebElement element, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public void dragAndDropAction(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 838,
                "endY", 787
        ));
    }

    public Double formatedAmmount(String amount){
        return Double.parseDouble(amount.substring(1));
    }
}
