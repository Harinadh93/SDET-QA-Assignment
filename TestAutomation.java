package automationpackage;

/*URL: https://testautomationpractice.blogspot.com/
1. Provide some string search for it
2. count number of links
3. click on each link using for loop
4. get window ID's for every browser window
5. close specific browser window*/

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomation {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("#Wikipedia1_wikipedia-search-input")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		List<WebElement> links = driver.findElements(By.cssSelector(".wikipedia-search-results a"));
		
		// Count and print the number of links
        System.out.println("Total number of links: " + links.size());
		
        //Click on each link
		for (int i = 0; i < links.size(); i++) {
            WebElement link = links.get(i);

            // Print the link text and href for clarity
            //System.out.println("Clicking link: " + link.getText() + " | URL: " + link.getAttribute("href"));

            // Click on the link (open in new tab)
            link.click();
		}
		
		// Step 6: Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            System.out.println("Window ID are: "+handle);
        }
		
		// Handle new tab/window (switch back to main window after opening)
        String mainWindow = driver.getWindowHandle(); // Store main window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close(); // Close new tab
            }
        }
        driver.switchTo().window(mainWindow); // Switch back to main window

        // Refresh the list of links because DOM changes after clicking
        links = driver.findElements(By.cssSelector(".wikipedia-search-results a"));
    }
}


