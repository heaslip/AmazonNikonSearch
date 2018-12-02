import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import org.junit.matchers.JUnitMatchers;
import static org.hamcrest.CoreMatchers.containsString;
import org.openqa.selenium.support.ui.Select;

public class Amazon {
	public static void main(String[] args) {
		searchWebsite("http://www.amazon.com");
    }
	
	private static void searchWebsite(String url){
		// Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();

        // Use this to visit website
        driver.get(url);

        // Find the text input element by its name
        WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
        
        // Search Nikon 
        element.sendKeys("Nikon");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        
        // Wait for results page to load
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
        		  .until(ExpectedConditions.presenceOfElementLocated(By.id("result_1")));
        
        // Sort prices from highest to lowest
        element = driver.findElement(By.id("sort"));
        Select dropdown= new Select(element);
        dropdown.selectByVisibleText("Price: High to Low");
        
        
        // Select second product and click it for details.
        // Locate second element in search results using CSS selector
        element = driver.findElement(By.cssSelector("#result_1 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        
        // Find URL inside the list element of second result
        driver.get(element.getAttribute("href"));

        
        // From details check (verify with assert) that product topic contains text “Nikon D3X”
        // Assign item title to variable
        String topic = driver.findElement(By.id("productTitle")).getText();

        // Check that the title contains string "Nikon D3X"
        Assert.assertThat(topic, containsString("Nikon D3X"));

   
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
                
        //Close the browser
        driver.quit();
	   }
}
