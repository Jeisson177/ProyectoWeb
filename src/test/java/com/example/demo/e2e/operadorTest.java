package com.example.demo.e2e;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class operadorTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "http://localhost:4200";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); 
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--disable-extensions");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void testManejoDePedidos() {
    driver.get(BASE_URL + "/operador/loginOperador");

    // Intento de login fallido
    driver.findElement(By.name("usuario")).sendKeys("operador");
    driver.findElement(By.name("contrasena")).sendKeys("wrongpass");
    driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();

    Assertions.assertFalse(driver.getPageSource().contains("Credenciales incorrectas"));

    // Login correcto
    driver.findElement(By.name("usuario")).clear();
    driver.findElement(By.name("contrasena")).clear();
    driver.findElement(By.name("usuario")).sendKeys("carlosg");
    driver.findElement(By.name("contrasena")).sendKeys("clave123");
    driver.findElement(By.xpath("/html/body/app-root/app-login-operador/div/div/div[2]/form/button")).click();

    // Ir a la lista de pedidos
    driver.get(BASE_URL + "/operador/ver-pedidos");

    // Espera breve para asegurar carga del contenido
    try {
        Thread.sleep(1000); // puedes usar WebDriverWait también para mejorar esto
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Esperar que el <select> esté presente en el último tr
    WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//table/tbody/tr[last()]/td[3]//select")
    ));

    // Usar el elemento
    Select select = new Select(selectElement);
    select.selectByVisibleText("Mario Rossi");
}

    @Test
    void testFinalizarPedido() {
        driver.get(BASE_URL + "/operador/loginOperador");

        driver.findElement(By.name("usuario")).sendKeys("carlosg");
        driver.findElement(By.name("contrasena")).sendKeys("clave123");
        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();

        driver.get(BASE_URL + "/operador/ver-pedidos");

        // Espera hasta que haya al menos un botón de "Finalizar Pedido"
        List<WebElement> botonesFinalizar = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//table/tbody/tr/td/button[contains(text(), 'Finalizar Pedido')]")
        ));

        if (botonesFinalizar.isEmpty()) {
            fail("No hay pedidos disponibles para finalizar.");
        }

        // Click en el último botón "Finalizar Pedido"
        WebElement btnFinalizar = botonesFinalizar.get(botonesFinalizar.size() - 1);
        btnFinalizar.click();
    }


 @AfterEach
    void tearDown() {
        driver.quit();
    }

}