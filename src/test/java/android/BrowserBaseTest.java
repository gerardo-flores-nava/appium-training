package android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;
import java.time.Duration;


public class BrowserBaseTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    //Code to start server
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
        options.setCapability("browserName", "Chrome");

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
}
