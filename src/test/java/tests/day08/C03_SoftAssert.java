package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class C03_SoftAssert {

    //Yeni bir Class Olusturun : D08_SoftAssertTest
    //1. “http://zero.webappsecurity.com/” Adresine gidin
    // 2. Sign in butonuna basin
    // 3. Login kutusuna “username” yazin
    // 4. Password kutusuna “password” yazin
    // 5. Sign in tusuna basin
    // 6. Pay Bills sayfasina gidin
    // 7. “Purchase Foreign Currency” tusuna basin
    // 8. “Currency” drop down menusunden Eurozone’u secin
    // 9. soft assert kullanarak "Eurozone (Euro)" secildigini test edin
    // 10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
    // "Select One", "Australia (dollar)", "Canada (dollar)","Switzerland (franc)","China (yuan)","Denmark (krone)",
    // "Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)","Japan (yen)","Mexico (peso)","Norway (krone)",
    // "New Zealand (dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)"

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
        driver.get("http://zero.webappsecurity.com/");
        driver.findElement(By.id("signin_button")).click();
        driver.findElement(By.id("user_login")).sendKeys("username");
        driver.findElement(By.id("user_password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();
        driver.get("http://zero.webappsecurity.com/");

        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        driver.findElement(By.id("pay_bills_link")).click();
        driver.findElement(By.linkText("Purchase Foreign Currency")).click();
        // 8. “Currency” drop down menusunden Eurozone’u secin
        WebElement dropDown= driver.findElement(By.id("pc_currency"));
        Select select=new Select(dropDown);
        select.selectByValue("EUR");
        // 9. soft assert kullanarak "Eurozone (euro)" secildigini test edin
        String actualData=select.getFirstSelectedOption().getText();
        String expectedValue= "Eurozone (euro)";

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actualData,expectedValue,"seilen option Euro Zone degil");


        // 10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
        List<WebElement> tumOpsiyonlar= select.getOptions();
        // option listesi webelementlerden olusyor
        // expected liste ise String, bunun icin opsiyon listyesini String yapmaliyiz

        List<String> tumOpsiyonlarString=new ArrayList<>();
        for (WebElement each:tumOpsiyonlar
             ) {
            tumOpsiyonlarString.add(each.getText());
        }

        List<String> expectedOptionsList= Arrays.asList("Select One", "Australia (dollar)", "Canada (dollar)","Switzerland (franc)","China (yuan)","Denmark (krone)","Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)","Japan (yen)","Mexico (peso)","Norway (krone)","New Zealand (dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)");

        softAssert.assertEquals(tumOpsiyonlarString,expectedOptionsList,"liste farkli");

        softAssert.assertAll();

    }

    @AfterClass
    public void tearDown(){

        driver.close();
    }
}
