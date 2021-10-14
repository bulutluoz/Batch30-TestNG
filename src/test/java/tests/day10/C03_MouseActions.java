package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.Set;

public class C03_MouseActions extends TestBase {

    @Test
    public void test(){

        //1- Yeni bir class olusturalim: C03_MouseActions1
        //2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");
        //3- Cizili alan uzerinde sag click yapalim
        Actions actions=new Actions(driver);
        WebElement ciziliAlan= driver.findElement(By.id("hot-spot"));

        actions.contextClick(ciziliAlan).perform();

        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu
        //    test edelim.

        String expectedAllertYazisi="You selected a context menu";
        String actualAllertYazisi=driver.switchTo().alert().getText();
        Assert.assertEquals(actualAllertYazisi,expectedAllertYazisi,"allert yazisi beklenenden farkli");
        //5- Tamam diyerek alert’I kapatalim
        driver.switchTo().alert().accept();
        //6- Elemental Selenium linkine tiklayalim
        String ilkSayfaHandle=driver.getWindowHandle();
        driver.findElement(By.linkText("Elemental Selenium")).click();
        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim

        Set<String> windowTumHandlelar=driver.getWindowHandles();

        String ikinciSayfaHandle="";

        for (String each:windowTumHandlelar
             ) {
            if (!each.equals(ilkSayfaHandle)){
                ikinciSayfaHandle=each;
            }
        }

        driver.switchTo().window(ikinciSayfaHandle);
        String expectedIkinciSayfaYazi="Elemental Selenium";
        String actualIkinciSayfaYazisi=driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualIkinciSayfaYazisi,expectedIkinciSayfaYazi,"ikinci sayfadaki yai istenenden farkli");
    }

}
