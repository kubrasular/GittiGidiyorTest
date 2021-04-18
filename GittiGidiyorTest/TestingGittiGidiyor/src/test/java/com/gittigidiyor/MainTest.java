package com.gittigidiyor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import Log4j.Log4j;
import static org.junit.Assert.*;

public class MainTest extends MainPage{

    @Test
    public void testing ()
    {
        driver.get(loginUrl);
        Log4j.info("Anasayfa açıldı. : " + loginUrl);  //Log4j kütüphanesi kullanılarak anasayfa açıldı bilgisi verildi.
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@title='Giriş Yap']"))).build().perform();// Giriş yep butonuna geliniyor.
        WebDriverWait wait = new WebDriverWait(driver,10);  // 10 saniye bekletiyor
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div")));
        driver.findElement(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div")).click(); // Pop-up'daki giriş yapa tıklanır.

        driver.findElement(By.name("kullanici")).sendKeys("kubrasular601449");
        driver.findElement(By.name("sifre")).sendKeys("kubra1907");
        driver.findElement(By.xpath("//*[@title=\"Giriş Yap\"]")).click();  //Bilgiler girildikten sonra login olunur.

        String Uyegiris = driver.getCurrentUrl();
        assertEquals("Başarısız",Uyegiris,"https://www.gittigidiyor.com/"); // Login olunduğu kontrol edilir.

        driver.findElement(By.xpath("//*[@class='sc-4995aq-0 sc-14oyvky-0 itMXHg']")).sendKeys("Bilgisayar");
        driver.findElement(By.xpath("//*[@class='qjixn8-0 sc-1bydi5r-0 hKfdXF']")).click();  //Arama butonuna Bilgisayar yazılır ve aratılır.

        driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL,Keys.END);  //2 butonuna görmesi için sayfa sonuna gelinir.

        driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[2]/a")).click();  // 2 butonuna basılır.
        String sayfa2url = driver.getCurrentUrl();
        assertEquals("Başarısız",sayfa2url,"https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"); // 2 numaralı sayfaya gittiği kontrol edilir.

        Random rnd = new Random();
        int randomsayi = rnd.nextInt(48);  // Random bir sayı üretilir.

        driver.findElement(By.xpath("//*[@product-index="+randomsayi+"]")).click();  // 2. Sayfadan random bir ürün seçilir.
        driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL,Keys.END);
        String fiyat = driver.findElement(By.xpath("//*[@id='sp-price-discountPrice']")).getText(); //Seçilen ürün fiyatı sepetteki fiyat ile karşılaştırmak için önceden kaydedilir.
        driver.findElement(By.xpath("//*[@id='add-to-basket']")).click();  //Sepete ekle tıklandı.

        driver.findElement(By.xpath("//*[@class='basket-container robot-header-iconContainer-cart']/a")).click();   // Sağ üstteki sepetime tıklandı.
        String sepetfiyat = driver.findElement(By.xpath("//*[@class='new-price']")).getText(); // Sepetteki ürün fiyatı değişkene kaydedildi.
        assertEquals("fiyatlar eşit değil",sepetfiyat,fiyat); // 2 fiyatın eşit olup olmadıkları karşılaştırılır.
        driver.findElement(By.xpath("//*[@class='amount']")).click();   // Adeti arttırmak için Dropdown a tıklandı.
        driver.findElement(By.xpath("//*[@class='amount']/option[2]")).click();   // Dropdown içinden 2 yi seçildi.
        String secilidrop =  driver.findElement(By.xpath("//*[@class='amount']")).getText();

        assertEquals("Stokta ürün yok.",secilidrop,2);  //Dropdown daki sayı 2 ye eşit olup olmadığı kontrol edilir. Eşit değil ise stokta ürün olmadığı için adet artırılamamıştır.
        driver.findElement(By.xpath("//*[@class='gg-icon gg-icon-bin-medium']")).click(); // Silme butonuna tıklandı.
        driver.findElement(By.xpath("//*[@title='Alışveriş Sitesi']")).click(); // Sepetin boş olduğunu kontrol etmek için anasayfaya tıklandı.

        driver.get(loginUrl); //Sepetin boş olduğunu kontrol etmek için anasayfaya gidilir.
        Actions action2 = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@class='sc-84am1q-0 sc-1r48nyr-0 gpYIaK']"))).build().perform();  // Sepetim butonunun üstüne gelinir.
        WebDriverWait wait2 = new WebDriverWait(driver,10); // Pop-up görünür olması için 10 saniye beklenir.
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='sc-1tdlrg-0 yf6n83-0 rpwBO ciohle-1 hyotRr']")));

        String urunsayisi = driver.findElement(By.xpath("//*[@class='sc-1tdlrg-0 yf6n83-0 rpwBO ciohle-1 hyotRr']")).getText();  //Sepetteki ürün bilgisi urunsayısı değişkenine eklenir.
        String bosurun="0 Ürün";
        assertEquals("Sepet Boş Değil",urunsayisi,bosurun);  // Sepette ürün olup olmadığı kontrol edilir.

    }
}
