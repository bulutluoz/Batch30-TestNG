package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_MouseActions extends TestBase {

    @Test
    public void test() throws InterruptedException {

        //Yeni bir class olusturalim: C05_MouseActions3
        //1- https://www.amazon.com/ adresine gidelim
        driver.get("https://www.amazon.com");
        //2- Sag ust bolumde bulunan “Account & Lists” menusunun acilmasi icin mouse’u bu menunun ustune getirelim
        WebElement liste=driver.findElement(By.xpath("//span[.='Hello, Sign in']"));
        Actions actions=new Actions(driver);

        actions.moveToElement(liste).perform();

        //3- “Create a list” butonuna basalim
        driver.findElement(By.xpath("//span[.='Create a List']")).click();

        //4- Acilan sayfada “Your Lists” yazisi oldugunu test edelim
        WebElement yourListYaziElementi= driver.findElement(By.xpath("//li[@class='a-tab-heading a-active a-size-large']"));
        Assert.assertTrue(yourListYaziElementi.isEnabled());
    }


}
