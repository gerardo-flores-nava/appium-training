package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class IOSScrollTest extends BaseTest{

    @Test
    public void iosScrollTest()throws Exception{
        WebElement element = driver.findElement(AppiumBy.accessibilityId("Web View"));
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement) element).getId());
        params.put("direction", "down");
        driver.executeScript("mobile: scroll", params);
        driver.findElement(AppiumBy.accessibilityId("Web View")).click();
        driver.findElement(By.xpath(
                "//XCUIElementTypeButton[@name='UIKitCatalog']")).click();
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
        WebElement blueElement = driver.findElement(AppiumBy.iOSNsPredicateString(
                "label == 'Blue color component value'"));
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
        blueElement.sendKeys("105");
        Assert.assertEquals(blueElement.getText(), "105");
    }
}
