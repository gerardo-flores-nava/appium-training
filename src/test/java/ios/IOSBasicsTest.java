package ios;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class IOSBasicsTest extends BaseTest{

    @Test
    public void IOSBasics(){
        //iosClassChain, IOSPredicateString
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]"))
                .click();
        driver.findElement(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeCell"))
                .sendKeys("Hello World");
        driver.findElement(AppiumBy.accessibilityId("OK")).click();
    }
}
