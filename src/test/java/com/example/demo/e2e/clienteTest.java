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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class clienteTest {

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
    void testflujoPedidoCliente() {
        // Paso 1: Ir a login
        driver.get(BASE_URL + "/login");
        esperar(1000);

        // Paso 2: Login fallido
        driver.findElement(By.name("correo")).sendKeys("usuario");
        driver.findElement(By.name("contrasena")).sendKeys("wrongpass");
        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();
        esperar(1500);

        // Paso 3: Verificar mensaje de error
        Assertions.assertFalse(driver.getPageSource().contains("Credenciales incorrectas"));
        esperar(1000);

        // Paso 4: Login correcto
        driver.findElement(By.name("correo")).clear();
        driver.findElement(By.name("contrasena")).clear();
        driver.findElement(By.name("correo")).sendKeys("mgomez@example.com");
        driver.findElement(By.name("contrasena")).sendKeys("5678");
        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();
        esperar(2000);

        // Paso 5: Abrir home de cliente 
        driver.get(BASE_URL + "/homeCliente");
        esperar(2000);

        // Paso 6: Ingresar al menu
        WebElement ordenar= driver.findElement(By.id("ordenar"));
        ordenar.click();
        driver.get(BASE_URL + "/menu");
        esperar(2000);

        // Paso 7: Seleccionar primer producto 
        String pathPlato1 = "//*[@id=\"platoprincipal\"]/div/div[2]/div/img";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pathPlato1)));
        WebElement plato1 = driver.findElement(By.xpath(pathPlato1));
        plato1.click();
        esperar(2000);
        
        // Paso 8: Agregar 2 adicionales

        String editarAdicional = "//*[@id=\"detalle-producto\"]/div/div/div[2]/div/form/div/div[2]/div[2]/input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editarAdicional)));
        WebElement editarAd = driver.findElement(By.xpath(editarAdicional));
        editarAd.click();

        WebElement btnAgregarCarrito= driver.findElement(By.id("btnAgregarCarrito"));
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregarCarrito));
        btnAgregarCarrito.click();
        esperar(2000);

        // Paso 9: Agregar el segundo producto al carrito
        String pathPlato2 = "//*[@id=\"platoprincipal\"]/div/div[9]/div/img";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pathPlato2)));
        WebElement plato2 = driver.findElement(By.xpath(pathPlato2));
        plato2.click();
        esperar(2000);

        
        WebElement btnAgregarCarrito2= driver.findElement(By.xpath("//*[@id=\"btnAgregarCarrito\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregarCarrito2));
        btnAgregarCarrito2.click();

        // Paso 10: Ir al carrito y pagar
        WebElement carrito= driver.findElement(By.id("icono-carrito"));
        wait.until(ExpectedConditions.elementToBeClickable(carrito));
        carrito.click();
        esperar(2000);

        WebElement btnPagar= driver.findElement(By.id("btnPagar"));
        wait.until(ExpectedConditions.elementToBeClickable(btnPagar));
        btnPagar.click();
        esperar(3000);

        // Paso 11: Seleccionar la dirección de envío
        WebElement inputDireccion = driver.findElement(By.id("direccionEnvio"));
        inputDireccion.sendKeys("Calle 88#50-20");
        esperar(3000);

        // Paso 12: Confirmar el pedido
        WebElement btnConfirmar= driver.findElement(By.id("btnConfirmarPedido"));
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar));
        btnConfirmar.click();
        esperar(5000);

        driver.get(BASE_URL + "/homeCliente");
        esperar(2000);

        // Paso 13: Ver pedidos activos
        WebElement pedidosActivos= driver.findElement(By.id("pedidosActivos"));
        pedidosActivos.click();
        esperar(5000);

        //Paso 14: Ver detalle del pedido
        WebElement btnDetallePedido = driver.findElement(By.id("btnDetallePedido"));
        wait.until(ExpectedConditions.elementToBeClickable(btnDetallePedido));
        btnDetallePedido.click();
        esperar(5000);

        // Paso 15: Volver a pedidos
        WebElement btnVolver= driver.findElement(By.id("btnVolver"));
        wait.until(ExpectedConditions.elementToBeClickable(btnVolver));
        btnVolver.click();
        esperar(6000);

        // Paso 16: Ver historial de pedidos
        WebElement historial= driver.findElement(By.xpath("/html/body/app-root/app-pedidos-activos/app-header-cliente/header/div/nav/div/a[4]"));
        wait.until(ExpectedConditions.elementToBeClickable(historial));
        historial.click();
        esperar(8000);

    }

     @AfterEach
    void tearDown() {
        driver.quit();
    }

    private void esperar(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

}

}
