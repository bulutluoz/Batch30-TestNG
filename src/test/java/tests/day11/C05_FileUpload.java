package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_FileUpload extends TestBase {
    @Test
    public void test() throws InterruptedException {
        //Tests packagenin altina bir class oluşturun : C05_UploadFile
        //https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");
        //chooseFile butonuna basalim

        //Yuklemek istediginiz dosyayi secelim.

        String dosyaYolu=System.getProperty("user.home")+ "\\Desktop\\picture.jpg";
        WebElement dosyaYukle= driver.findElement(By.id("file-upload"));

        dosyaYukle.sendKeys(dosyaYolu);
        //Upload butonuna basalim.

        driver.findElement(By.id("file-submit")).click();
        //“File Uploaded!” textinin goruntulendigini test edelim.
        Thread.sleep(3000);
        WebElement sonucYazisiElementi= driver.findElement(By.tagName("h3"));
        Assert.assertTrue(sonucYazisiElementi.isDisplayed());
    }
}
