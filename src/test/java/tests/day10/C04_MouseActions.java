package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C04_MouseActions extends TestBase {

    @Test
    public void test(){
        //Yeni bir class olusturalim: D14_MouseActions2
        //1- https://demoqa.com/droppable adresine gidelim
        driver.get("https://demoqa.com/droppable");
        //2- “Drag me” butonunu tutup “Drop here” kutusunun ustune birakalim

        Actions actions=new Actions(driver);
        WebElement dragElementi=driver.findElement(By.id("draggable"));
        WebElement dropAlani=driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));

        actions.dragAndDrop(dragElementi,dropAlani).perform();
        //3- “Drop here” yazisi yerine “Dropped!” oldugunu test edin

        WebElement droppedYazisi= driver.findElement(By.xpath("//*[text()='Dropped!']"));

        String actualDroppedyazisi=droppedYazisi.getText();
        String expectedDroppedYazisi="Dropped!";

        Assert.assertEquals(actualDroppedyazisi,expectedDroppedYazisi,"dropped yazisi beklenenden farkli");

    }

}
