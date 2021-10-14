package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.security.Key;

public class C02_Actions extends TestBase {



    @Test
    public void test() throws InterruptedException {
        // amazon anasayfaya gidin
        driver.get("https://amazon.com");
        // nutella icin arama yapin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("nutella"+ Keys.ENTER);

        // 9.urunu tiklayin
        Thread.sleep(3000);
        driver.findElement(By.xpath("//img[@data-image-index='9']")).click();

    }

}
