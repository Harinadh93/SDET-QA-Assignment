package automationpackage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Blazedemo {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://blazedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//select[@name='fromPort']//option[@value='Boston']")).click();
		driver.findElement(By.xpath("//select[@name='toPort']//option[@value='London']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		List<WebElement> Price = driver.findElements(By.xpath("//tbody/tr/td[6]"));
		System.out.println("Prices: "+Price.size());
		
		// Extract prices as strings
        List<String> prices = new ArrayList<>();
        for (WebElement element : Price) {
            prices.add(element.getText());
        }

        // Sort the prices lexicographically using a custom comparator
        Collections.sort(prices, new Comparator<String>() {
            @Override
            public int compare(String price1, String price2) {
                // Remove the '$' symbol for comparison but do not modify the original strings
                Double value1 = Double.parseDouble(price1.replace("$", "").trim());
                Double value2 = Double.parseDouble(price2.replace("$", "").trim());
                return value1.compareTo(value2); // Numerical comparison
            }
        });
		
        // Print the sorted prices
        System.out.println("Sorted Prices:");
        for (String price : prices) {
            System.out.println(price);
        }
	}
}
