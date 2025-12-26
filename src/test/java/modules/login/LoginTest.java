package modules.login;

import config.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@DisplayName("Web test of banco")
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    void beforeEach() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        this.driver = new ChromeDriver(options);
        //this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Configuration configuration = ConfigFactory.create(Configuration.class);
        this.driver.get(configuration.baseURL());
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
        WebElement labelToken = driver.findElement(By.cssSelector("label[for='token']"));
        String message = labelToken.getAttribute("textContent");
        Assertions.assertEquals("Token (Valores maiores que R$ 5.000,00)", message);

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

    @AfterEach
    void afterEach() {
        driver.quit();
    }


}
