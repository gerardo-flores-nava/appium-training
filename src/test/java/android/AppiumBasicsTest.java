package android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AppiumBasicsTest extends BaseTest{

    @Test
    public void wifiSettingName_success(){
        //Actual Automation
        //Locators allow xpath, id, accessibilityId, classname, androidAutomator
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        //How build xpath //tagName[@attribute=value]
        driver.findElement(By.xpath(
                "//android.widget.TextView[@content-desc='3. Preference dependencies']"))
                .click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath(
                "//android.widget.LinearLayout[2]"))
                .click();
        //Assert
        assertEquals(driver.findElement(
                By.id("android:id/alertTitle"))
                .getText(), "WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("Gerardo Wifi");
        driver.findElement(By.id("android:id/button1")).click();
    }

    @Test
    public void longPress_success() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(By.xpath(
                "//android.widget.TextView[@content-desc='Expandable Lists']"))
                .click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        longPressAction(driver.findElement(By.xpath(
                "//android.widget.TextView[@text= 'People Names']")));
        WebElement menuText = driver.findElement(By.id("android:id/title"));
        assertTrue(menuText.isDisplayed());
        assertEquals(menuText.getText(), /*Text*/"Sample menu");
    }

    @Test
    public void scrollTest() throws Exception{
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //UIAutomator Methods
        scrollIntoViewAction(/*Text=*/"WebView");
        Thread.sleep(2000);
    }

    @Test
    public void swipeTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Photos']"))
                .click();
        WebElement firstImage = driver.findElement(By.xpath(
                "(//android.widget.ImageView)[1]"));
        assertEquals(firstImage.getAttribute("focusable"), "true");
        //After swipe
        swipeAction(firstImage, "left");
        assertEquals(firstImage.getAttribute("focusable"), "false");

    }

    @Test
    public void dragAndDropTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        dragAndDropAction(source);
        assertEquals(driver.findElement(By.id(
                "io.appium.android.apis:id/drag_result_text"))
                .getText()
                ,"Dropped!");
    }
}
