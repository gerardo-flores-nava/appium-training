package ios;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    public AppiumDriverLocalService service;
    public IOSDriver driver;

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
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14 Pro Max");
        /*options.setApp(
                "//Users//gerardoalbertofloresnava//IdeaProjects//AppiumTraining//src//test//resources//UIKitCatalog.app");*/
        options.setApp(
                "//Users//gerardoalbertofloresnava//IdeaProjects//AppiumTraining//src//test//resources//TestApp 3.app");
        options.setPlatformVersion("16.4");
        options.setUdid("CA751901-10CA-4BEC-86F8-2C875CB2403B");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
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
