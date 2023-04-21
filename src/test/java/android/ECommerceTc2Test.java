package android;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceTc2Test extends BaseTest {

    @Test
    public void selectItems_success() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gerardo Flores");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        scrollIntoViewAction("Argentina");
        driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollIntoViewAction("Jordan 6 Rings");
        ImmutableList<WebElement> productCount = ImmutableList.copyOf(driver.findElements(By
                .id("com.androidsample.generalstore:id/productName")));
        for(WebElement element : productCount){
            if(element.getText().equals("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"))
                        .get(productCount.indexOf(element)).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //wait until the page is fully loaded
        wait.until(ExpectedConditions
                .attributeContains(driver.findElement(By.id(
                        "com.androidsample.generalstore:id/toolbar_title")),
                        "text",
                        "Cart"));
        String productName = driver.findElement(By.id(
                "com.androidsample.generalstore:id/productName"))
                .getText();
        Assert.assertEquals(productName, "Jordan 6 Rings");
    }
}
