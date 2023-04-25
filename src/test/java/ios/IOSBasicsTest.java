package ios;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
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

        driver.findElement(AppiumBy.iOSNsPredicateString(
                "value == 'Confirm / Cancel' AND type == 'XCUIElementTypeStaticText'")).click();
        //Can use regular expressions for PredicateString
        /* driver.findElement(AppiumBy.iOSNsPredicateString(
                "value BEGINSWITH[c] 'Confirm' AND type == 'XCUIElementTypeStaticText'")).click();*/
        String text = driver.findElement(AppiumBy.iOSNsPredicateString(
                "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'A message'")).getText();
        driver.findElement(AppiumBy.iOSNsPredicateString(
                "label == 'Confirm'"))
                .click();
        Assert.assertEquals(text, "A message should be a short, complete sentence.");
    }
}
