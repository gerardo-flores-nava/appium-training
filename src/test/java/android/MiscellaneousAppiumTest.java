package android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MiscellaneousAppiumTest extends BaseTest {

    @Test
    public void landscapeTest(){
       /* App package -> Package in the apk
        App activities -> Every page is an activity
        Find activity -> adb shell dumpsys window | grep -E 'mCurrentFocus'*/
        Activity activity = new Activity("io.appium.android.apis",
                "io.appium.android.apis.preference.PreferenceDependencies");
        driver.startActivity(activity);
        driver.findElement(By.id("android:id/checkbox")).click();
        //Landscape view
        DeviceRotation landScape = new DeviceRotation(0, 0, 90);
        driver.rotate(landScape);

        driver.findElement(By.xpath(
                        "//android.widget.LinearLayout[2]"))
                .click();
        //Assert
        assertEquals(driver.findElement(
                        By.id("android:id/alertTitle"))
                .getText(), "WiFi settings");
        // Set text in clipboard
        driver.setClipboardText("Gerardo WiFi");
        // Use clipboard text
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();
        // Key events
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
