package com.example.demo.e2e;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        // Paso 1: Ir a login
        driver.get(BASE_URL + "/operador/loginOperador");

    
        // Paso 2: Login fallido
        driver.findElement(By.name("usuario")).sendKeys("operador");
        driver.findElement(By.name("contrasena")).sendKeys("wrongpass");
        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();

        // Paso 3: Verificar mensaje de error
        Assertions.assertFalse(driver.getPageSource().contains("Credenciales incorrectas"));

        // Paso 4: Login correcto
        driver.findElement(By.name("usuario")).clear();
        driver.findElement(By.name("contrasena")).clear();
        driver.findElement(By.name("usuario")).sendKeys("carlosg");
        driver.findElement(By.name("contrasena")).sendKeys("clave123");
        driver.findElement(By.xpath("/html/body/app-root/app-login-operador/div/div/div[2]/form/button")).click();

        // Paso 5: Abrir home de operador
        driver.get(BASE_URL + "/operador/ver-pedidos");

        // Paso 6: Cambiar de estado un pedido
        WebElement btnAsignarDom = driver.findElement(By.xpath("/html/body/app-root/app-ver-pedidos/table/tbody/tr[6]/td[3]/div/select"));
        btnAsignarDom.click();

        //Asignar un domiciliario

}

 @AfterEach
    void tearDown() {
        driver.quit();
    }

}