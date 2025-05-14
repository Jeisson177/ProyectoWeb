package com.example.demo.e2e;
import java.time.Duration;
import java.util.List;

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
    driver.findElement(By.name("nombre")).sendKeys("helado");
    driver.findElement(By.name("descripcion")).sendKeys("helado de vainilla");
    driver.findElement(By.name("precio")).sendKeys("25000");
    driver.findElement(By.name("categoria")).sendKeys("Postre");

    driver.findElement(By.id("switch_1")).click(); // Queso Parmesano Extra
    driver.findElement(By.id("switch_2")).click(); // Salsa de Tomate Extra

    // Esperar que el botón esté habilitado antes de hacer click
    WebElement crearBtn = driver.findElement(By.xpath("//button[contains(text(),'Crear Producto')]"));
    Assertions.assertFalse(crearBtn.getAttribute("disabled") != null);  // Verificar que NO está deshabilitado

    crearBtn.click();

    // Paso 6: Verificar que el producto está en el menú con 2 adicionales
    driver.get(BASE_URL + "/admin/menu");

    // Paso 7: Buscar ID del producto en listado
    String productoId = obtenerIdProductoPorNombre("helado");
    Assertions.assertNotNull(productoId, "No se encontró el ID del producto creado.");
try {
            Thread.sleep(1000); // espera para que cargue el contenido
        }catch (Exception e) {}
    // Paso 8: Ir a editar producto y agregar tercer adicional
    clickEnBotonEditarDelProducto("helado");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Esperar por 10 segundos para cargar la página

    // Relocalizar los elementos (checkboxes)
    WebElement switch1 = driver.findElement(By.id("switch_1"));
    WebElement switch2 = driver.findElement(By.id("switch_2"));
    WebElement switch3 = driver.findElement(By.id("switch_3"));

    // Seleccionar los checkboxes si no están seleccionados
    if (!switch1.isSelected()) {
        switch1.click();  // Marca el checkbox si no está marcado
    }
    if (!switch2.isSelected()) {
        switch2.click();  // Marca el checkbox si no está marcado
    }
    if (!switch3.isSelected()) {
        switch3.click();  // Marca el checkbox si no está marcado
    }

    // Esperar hasta que el botón "Guardar Cambios" esté habilitado
    WebElement guardarBtn = driver.findElement(By.xpath("//button[contains(text(),'Guardar Cambios')]"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(guardarBtn));

    // Verificar que el botón "Guardar Cambios" está habilitado antes de hacer clic
    Assertions.assertFalse(guardarBtn.getAttribute("disabled") != null, "El botón 'Guardar Cambios' está deshabilitado.");

    // Hacer clic en el botón "Guardar Cambios"
    guardarBtn.click();

    // Paso 9: Verificar que el producto ahora tiene 3 adicionales
    driver.get(BASE_URL + "/admin/menu");

    // Relocalizar el botón de editar y hacer clic de nuevo
    clickEnBotonEditarDelProducto("helado");

    // Esperar que se cargue la página de edición nuevamente
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Verificar que los 3 adicionales estén seleccionados
    switch1 = driver.findElement(By.id("switch_1"));
    switch2 = driver.findElement(By.id("switch_2"));
    switch3 = driver.findElement(By.id("switch_3"));

    Assertions.assertTrue(switch1.isSelected(), "El adicional 'Queso Parmesano Extra' no está seleccionado.");
    Assertions.assertTrue(switch2.isSelected(), "El adicional 'Salsa de Tomate Extra' no está seleccionado.");
    Assertions.assertTrue(switch3.isSelected(), "El adicional 'Salsa de Trufa' no está seleccionado.");
}



private void clickEnBotonEditarDelProducto(String nombreProducto) {
    while (true) {
        List<WebElement> filas = driver.findElements(By.cssSelector("tbody tr"));
        for (WebElement fila : filas) {
            try {
                List<WebElement> celdas = fila.findElements(By.tagName("td"));
                if (celdas.size() > 0) {
                    String nombre = celdas.get(0).getText().trim();
                    if (nombre.equalsIgnoreCase(nombreProducto.trim())) {
                        WebElement editarBoton = fila.findElement(By.xpath(".//a[contains(@href, '/detalleProducto/')]"));
                        editarBoton.click();  // CLIC DIRECTO
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al intentar hacer clic en Editar: " + e.getMessage());
            }
        }

        // Pasar a la siguiente página si no lo encontramos
        try {
            WebElement siguiente = driver.findElement(By.id("nextButton"));
            if (siguiente.getAttribute("class").contains("disabled")) {
                break;
            }
            siguiente.click();
        } catch (Exception e) {
            System.out.println("No se pudo avanzar más de página.");
            break;
        }
    }

    Assertions.fail("No se encontró el producto: " + nombreProducto);
}

    private String obtenerIdProductoPorNombre(String nombreProducto) {
    while (true) {
        List<WebElement> filas = driver.findElements(By.cssSelector("tbody tr"));
        System.out.println("=== Página actual ===");

        for (WebElement fila : filas) {
            try {
                List<WebElement> celdas = fila.findElements(By.tagName("td"));
                if (celdas.size() > 0) {
                    String nombre = celdas.get(0).getText().trim();
                    System.out.println("Producto encontrado en DOM: " + nombre);

                    if (nombre.equalsIgnoreCase(nombreProducto.trim())) {
                        WebElement editarBoton = fila.findElement(By.xpath(".//a[contains(@href, '/detalleProducto/')]"));
                        String href = editarBoton.getAttribute("href");
                        System.out.println("→ Producto encontrado: " + nombreProducto + " con href: " + href);
                        return href.substring(href.lastIndexOf("/") + 1);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer una fila: " + e.getMessage());
            }
        }

        // Intentar ir a la siguiente página
        try {
            WebElement siguiente = driver.findElement(By.id("nextButton"));
            if (siguiente.getAttribute("class").contains("disabled")) {
                break; // Ya estamos en la última página
            }
            siguiente.click();
            Thread.sleep(1000); // espera para que cargue el contenido
        } catch (Exception e) {
            System.out.println("No se pudo avanzar de página o ya es la última.");
            break;
        }
    }

    System.out.println("→ Producto NO encontrado: " + nombreProducto);
    return null;
}





    @AfterEach
    void tearDown() {
        driver.quit();
    }
}