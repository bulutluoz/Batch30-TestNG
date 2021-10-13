package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class C01_Allerts {

    // Her Allert JS Allert degildir
    // Allert ciktiginda sag click yapip incele diyebiliyorsak bu bir HTML alert'dir
    // HTML alert'ler siradan webelement'ler olarak locate edilip kullanilabilir
    // Sag click yapamiyorsak alert bir JS Allert'dur ve switchTo() kullanilarak handle edilebilir

    //● Bir class olusturun: C01_Alerts
    //● https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
    //● Bir metod olusturun: acceptAlert
    //		○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
    //		“You successfully clicked an alert” oldugunu test edin.
    //● Bir metod olusturun: dismissAlert
    //		○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    //		“successfuly” icermedigini test edin.
    //● Bir metod olusturun: sendKeysAlert
    //		○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin,
    //		OK butonuna tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.

    WebDriver driver;


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void acceptAlert(){
        //		○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
        //		“You successfully clicked an alert” oldugunu test edin.
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();

        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String expectedResult="You successfully clicked an alert";
        String actualResult= resultYaziElementi.getText();

        assertEquals(actualResult,expectedResult,"sonuc yazisi istenen ile ayni degil");


    }
    @Test
    public void dismissAlert(){
        //		○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
        //		“successfuly” icermedigini test edin.

        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        String istenmeyenKelime="successfuly";
        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String actualResult=resultYaziElementi.getText();
        assertFalse(actualResult.contains(istenmeyenKelime),"result yazisi istenmeyen kelimeyi iceriyor");


    }
    @Test
    public void sendKeysAlert(){
        //		○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin,
        //		OK butonuna tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.

        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();

        String isim="Mehmet";
        driver.switchTo().alert().sendKeys(isim);
        driver.switchTo().alert().accept();

        WebElement resultYaziElementi=driver.findElement(By.id("result"));

        String actualResult=resultYaziElementi.getText();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(actualResult.contains(isim));
        softAssert.assertAll();

    }


    @AfterClass
    public void tearDown(){

        driver.close();
    }
}
