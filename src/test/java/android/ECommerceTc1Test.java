package android;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ECommerceTc1Test extends BaseTest {

    @Test
    public void fillForm_success() throws Exception{
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gerardo Flores");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        scrollIntoViewAction("Argentina");
        driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
    }

    @Test
    public void fillForm_withEmptyName_fail(){
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]"))
                .getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void selectItems_success(){

    }
}
