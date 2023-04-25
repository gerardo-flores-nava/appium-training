package ios;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSTouchAction;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GesturesTest extends BaseTest {

    @Test
    public void longPressTest(){
        driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
        WebElement element = driver.findElement(AppiumBy.iOSClassChain(
                "**/XCUIElementTypeButton[`label == 'Increment'`][3]"));

        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement)element).getId());
        params.put("duration", 5);
        driver.executeScript("mobile: touchAndHold", params);
    }
}
