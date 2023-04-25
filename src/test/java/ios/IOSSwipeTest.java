package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSSwipeTest extends BaseTest{

    @Test
    public void iosSwipeTest(){
        //Identify by the Bundle id
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.apple.mobileslideshow");
        driver.executeScript("mobile: launchApp", params);
        driver.findElement(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeButton[`label == 'All Photos'`]"))
                .click();
        List<WebElement> photos = driver.findElements(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeCell"));
        Assert.assertEquals(photos.size(), 6);
        driver.findElement(By.xpath("//XCUIElementTypeCell[1]")).click();
        params.clear();
        params.put("direction", "left");
        for(int i =0; i < photos.size(); i++){
            System.out.println(driver.findElement(By.xpath(
                    "//XCUIElementTypeNavigationBar"))
                    .getAttribute("name"));
            driver.executeScript("mobile: swipe", params);
        }
        driver.navigate().back();
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Albums'")).click();
    }
}
