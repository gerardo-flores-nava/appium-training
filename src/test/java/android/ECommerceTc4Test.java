package android;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceTc4Test extends BaseTest {

    @Test
    public void selectItems_success() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gerardo Flores");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        scrollIntoViewAction("Argentina");
        driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElements(By.xpath(
                "//android.widget.TextView[@text= 'ADD TO CART']"))
                .get(0)
                .click();
        driver.findElements(By.xpath(
                        "//android.widget.TextView[@text= 'ADD TO CART']"))
                .get(0)
                .click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        //wait until the page is fully loaded
        wait.until(ExpectedConditions
                .attributeContains(driver.findElement(By.id(
                                "com.androidsample.generalstore:id/toolbar_title")),
                        "text",
                        "Cart"));
        ImmutableList<WebElement> itemInCart = ImmutableList.copyOf(
                driver.findElements(By.id(
                        "com.androidsample.generalstore:id/productPrice")));

        double totalSum = 0;
        for(WebElement item : itemInCart){
            totalSum = totalSum +formatedAmmount(item.getText());
        }
        Assert.assertEquals(totalSum, formatedAmmount(driver
                .findElement(By.id(
                        "com.androidsample.generalstore:id/totalAmountLbl"))
                .getText()));

        longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);
        //Hybrid - Google page ->
        ImmutableSet<String> context = ImmutableSet.copyOf(driver.getContextHandles());

        driver.context("WEBVIEW_com.androidsample.generalstore"); //Chrome driver
        driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
