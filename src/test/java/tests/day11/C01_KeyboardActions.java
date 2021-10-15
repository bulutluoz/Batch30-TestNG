package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C01_KeyboardActions extends TestBase {

    @Test
    public void test() throws InterruptedException {
        // 1- Bir Class olusturalim C01_KeyboardActions1
        //2- https://www.amazon.com sayfasina gidelim
            driver.get("https://www.amazon.com");
        //3- Arama kutusuna actions method’larine kullanarak samsung A71 yazdirin
        //   ve Enter’a basarak arama yaptirin
        WebElement aramaKutu = driver.findElement(By.id("twotabsearchtextbox"));

        Actions actions=new Actions(driver);
        /*
        actions.click(aramaKutu).perform();
        actions.sendKeys("samsung ").perform();
        actions.keyDown(Keys.SHIFT).perform();
        actions.sendKeys("a").perform();
        actions.keyUp(Keys.SHIFT).perform();
        actions.sendKeys("71").perform();
        actions.sendKeys(Keys.ENTER).perform();
        */

        // simdiye kadar once locate edip. o webelement uzerinden sendKeys yapiyorduk
        // aramaKutu.sendKeys("samsung A71");

        actions.click(aramaKutu)
                .sendKeys("samsung ")
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .sendKeys("71")
                .perform();
        Thread.sleep(3000);

        actions.sendKeys(Keys.ENTER).perform();

        //4- aramanin gerceklestigini test edin

        String arananKelime="samsung A71";
        String actuaalTitle=driver.getTitle();
        Assert.assertTrue(actuaalTitle.contains(arananKelime),"arama yapilamadi");
    }


}
