package modules.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Web test of banco")
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    void beforeEach() {
        System.setProperty("webdrive.chrome.driver", "C:\\drivers\\chrome-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.driver.get("http://localhost:4000/");
    }

    @Test
    @DisplayName("Login with valid data")
    public void createATransactionWithValidData() {
        //Act
        driver.findElement(By.cssSelector("label[for='username']")).click();
        driver.findElement(By.id("username")).sendKeys("julio.lima");

        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("123456");

        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        //Assert
        String message = driver.findElement(By.xpath("(//h4)[2]")).getText();
        Assertions.assertEquals("Realizar Transferência", message);

    }

    @Test
    @DisplayName("Login with invalid data")
    public void createATransactionWithInvalidData() {
        //Act
        driver.findElement(By.cssSelector("label[for='username']")).click();
        driver.findElement(By.id("username")).sendKeys("julio.lima");

        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("654321");

        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        //Assert
        String message = driver.findElement(By.cssSelector(".toast.red")).getText();
        Assertions.assertEquals("Erro no login. Tente novamente.", message);

    }


}
