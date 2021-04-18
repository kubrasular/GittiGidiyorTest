package com.gittigidiyor;
import Log4j.Log4j;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {
    WebDriver driver;
    //loginUrl değişkeninin içine gittigidiyor linki ekleniyor.
    public static String loginUrl = "https://www.gittigidiyor.com/";

    @Before
    public void setUp() {
        Log4j.startLog("Test Başladı"); //Log4j kütüphanesi kullanılarak test başladı mesajı verildi.
        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");//Chrome tarayıcısı için Driver kuruluyor.
        driver = new ChromeDriver();
        driver.manage().window().maximize();// Ekranı büyütüyor.
    }

    @After
    public void tearDown() {

        driver.quit();//Test bittikten sonra tarayıcı kapatılır
        Log4j.endLog("Test sonlandı");
    }
}
