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
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class C02_IFrame {
    //    ● Bir class olusturun: C02_IframeTest
    //    ● https://the-internet.herokuapp.com/iframe adresine gidin.
    //    ● Bir metod olusturun: iframeTest
    //		○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda 	yazdirin.
    //		○ Text Box’a “Merhaba Dunya!” yazin.
    //		○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
    //		dogrulayin ve  konsolda yazdirin.

    WebDriver driver;


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/iframe");
    }


    @Test
    public void test() throws InterruptedException {
        // ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda 	yazdirin.
        WebElement baslikYazielemneti= driver.findElement(By.tagName("h3"));
        Assert.assertTrue(baslikYazielemneti.isEnabled(),"baslik yazisi erisilebilir degil");
        System.out.println(baslikYazielemneti.getText());

        //		○ Text Box’a “Merhaba Dunya!” yazin.

        WebElement iFrame=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));

        driver.switchTo().frame(iFrame);
        WebElement yaziKutusu= driver.findElement(By.xpath("//*[@id='tinymce']"));

        yaziKutusu.clear();
        yaziKutusu.sendKeys("Merhaba Dunya");
        //bir iFrame'e gecis yaptiktan sonra yeniden ana sayfa ile ilgili islem yapmak isterseniz
        // gecis yaptigimiz iFrame'den geri donmeliyiz
        driver.switchTo().defaultContent();

        //		○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
        //		dogrulayin ve  konsolda yazdirin.

        WebElement elementalLinki=driver.findElement(By.linkText("Elemental Selenium"));

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(elementalLinki.isDisplayed());

        softAssert.assertAll();

        System.out.println(elementalLinki.getText());



    }

    @AfterClass
    public void tearDown(){

        driver.close();
    }
}
