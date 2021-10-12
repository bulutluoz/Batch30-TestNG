package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C02_Softassertion {

    // amazon sayfasina gidin
    // url'in amazon icerdigini test edin
    // title'in amazon icerdigini test edin
    // java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigini test edin
    // testleri soft assertion ile yapin

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
        SoftAssert softAssert =new SoftAssert();

        driver.get("https://www.amazon.com");

        // url'in amazon icerdigini test edin
        softAssert.assertTrue(driver.getCurrentUrl().contains("amazon"),"url amazon icermiyor");

        // title'in amazon icerdigini test edin
        softAssert.assertTrue(driver.getTitle().contains("amazon"),"title amazon icermiyor");
        System.out.println("assertion failed oldugu halde bu satir calisir");

        // java kelimesi aratin ve listedeki ilk urunun java kelimesi icerdigini test edin

        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("java"+ Keys.ENTER);
        WebElement ilkUrun=driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]"));
        softAssert.assertTrue(ilkUrun.getText().contains("java"),"ilk urun java icermiyor");

        softAssert.assertAll();
        // assertion'lar pass olsa da olmasa da assertAll'a kadar tum satirlar calisir
        // ama eger testlerden bir tanesi bile failed ise assertAll'dan sonra execution stops
        System.out.println("assertion failed oldugunda bu satir calismaz");
    }


    @AfterClass
    public void tearDown(){

        driver.close();
    }
}
