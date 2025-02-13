package id.ac.ui.cs.advprogid.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        // Bentuk URL dasar untuk testing
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProductAndViewInList(ChromeDriver driver) {
        // 1. Buka halaman Create Product
        driver.get(baseUrl + "/product/create");

        // 2. Isi form pembuatan produk
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));

        String productName = "Functional Test Product";
        String productQuantity = "42";

        nameInput.sendKeys(productName);
        quantityInput.sendKeys(productQuantity);

        // 3. Submit form (klik tombol submit)
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        // 4. Tunggu hingga halaman Product List muncul dan produk baru terlihat
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Tunggu sampai elemen yang mengandung productName muncul di halaman
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), '" + productName + "')]")
        ));

        // 5. Verifikasi bahwa halaman Product List memuat produk baru
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(productName),
                "Produk baru harus terlihat di halaman Product List");
    }
}
