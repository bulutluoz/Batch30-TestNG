package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_KeyboardActions extends TestBase {

    @Test
    public void test() throws InterruptedException {
        //1- Bir Class olusturalim C02_KeyboardActions2
        //2- https://html.com/tags/iframe/ sayfasina gidelim

        driver.get("https://html.com/tags/iframe/");

        //3- video’yu gorecek kadar asagi inin

        Actions actions= new Actions(driver);
        actions.
                sendKeys(Keys.PAGE_DOWN).
                sendKeys(Keys.PAGE_DOWN).
                perform();
        Thread.sleep(3000);

        //4- videoyu izlemek icin Play tusuna basin
        WebElement iFrame=driver.findElement(By.className("lazy-loaded"));
        driver.switchTo().frame(iFrame);


       WebElement playTusu= driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']"));
       actions.click(playTusu).perform();
        Thread.sleep(3000);
        //5- videoyu calistirdiginizi test edin

        Assert.assertFalse(playTusu.isDisplayed());

    }
}
