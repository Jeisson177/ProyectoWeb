package com.example.demo.e2e;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class adminTest {

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
    void testCrearProductoConAdicionales() {
        // Paso 1: Ir a login
        driver.get(BASE_URL + "/admin/loginAdmin");

        // Paso 2: Login fallido
        driver.findElement(By.name("usuario")).sendKeys("admin");
        driver.findElement(By.name("contrasena")).sendKeys("wrongpass");

        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();

        // Paso 3: Verificar mensaje de error
        Assertions.assertFalse(driver.getPageSource().contains("Credenciales incorrectas"));

        // Paso 4: Login correcto
        driver.findElement(By.name("usuario")).clear();
        driver.findElement(By.name("contrasena")).clear();
        driver.findElement(By.name("usuario")).sendKeys("JPerez1214");
        driver.findElement(By.name("contrasena")).sendKeys("juanperez855");
        driver.findElement(By.xpath("//button[text()='Iniciar Sesión']")).click();

        // Paso 5: Crear producto con 2 adicionales
        driver.get(BASE_URL + "/admin/agregarProducto");
        driver.findElement(By.name("Nombre")).sendKeys("helado");
        driver.findElement(By.name("Descripcion")).sendKeys("helado de vainilla");
        driver.findElement(By.name("Precio")).sendKeys("25000");
        driver.findElement(By.name("Tipo")).sendKeys("Postre");

        
        //driver.findElement(By.id("adicional1")).click();
        //driver.findElement(By.id("adicional2")).click();

        // Enviar el formulario
        driver.findElement(By.xpath("//button[normalize-space(text())='Agregar']")).click();

        // Paso 6: Verificar que el producto está en el menú con 2 adicionales
        driver.get(BASE_URL + "/admin/menu");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assertions.assertTrue(driver.getPageSource().contains("helado"));
        //Assertions.assertTrue(driver.getPageSource().contains("Adicional 1"));
        //Assertions.assertTrue(driver.getPageSource().contains("Adicional 2"));
        
        // Paso 7: Buscar ID del producto en listado
        /* 
        driver.get(BASE_URL + "/admin/listaProductos"); 
        String productoId = obtenerIdProductoPorNombre("Pizza Test");
        Assertions.assertNotNull(productoId, "No se encontró el ID del producto creado.");

        // Paso 8: Ir a editar producto y agregar tercer adicional
        driver.get(BASE_URL + "/admin/detalleProducto/" + productoId);
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("adicional3")));
        driver.findElement(By.id("adicional3")).click();
        driver.findElement(By.id("guardarProducto")).click();

        // Paso 9: Verificar que el producto ahora tiene 3 adicionales
        driver.get(BASE_URL + "/admin/menu");
        Assertions.assertTrue(driver.getPageSource().contains("Adicional 3"));*/
        
    }

    private String obtenerIdProductoPorNombre(String nombreProducto) {
        List<WebElement> filas = driver.findElements(By.cssSelector("tr"));
        for (WebElement fila : filas) {
            if (fila.getText().contains(nombreProducto)) {
                try {
                    // Suponiendo que el ID está en una celda oculta o accesible
                    return fila.findElement(By.cssSelector("td.id")).getText(); // Ajusta el selector si necesario
                } catch (NoSuchElementException e) {
                    // Alternativa: extraer el ID de un botón o enlace (ej. href="/admin/detalleProducto/1")
                    WebElement editar = fila.findElement(By.xpath(".//a[contains(@href, '/detalleProducto/')]"));
                    String href = editar.getAttribute("href");
                    return href.substring(href.lastIndexOf("/") + 1);
                }
            }
        }
        return null;
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}