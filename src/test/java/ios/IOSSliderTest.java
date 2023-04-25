package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSSliderTest extends BaseTest{

    @Test
    public void iosSliderTest()throws Exception{
        WebElement slider = driver.findElement(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeSlider[`label == 'AppElem'`]"));
        slider.sendKeys("1");
        Thread.sleep(2000);
        Assert.assertEquals(slider.getAttribute("value"), "100%");
        slider.sendKeys("0");
        Thread.sleep(2000);
        Assert.assertEquals(slider.getAttribute("value"), "0%");
    }
}
