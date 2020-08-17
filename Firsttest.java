package test;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.Iterator; 

public class Firsttest {

public static void main(String[] args) throws 
InterruptedException  {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		System.setProperty("webdriver.chrome.driver", "/Users/ojas_rahate/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("https://quantra.quantinsti.com/");
		WebElement loginLink = driver.findElement(By.cssSelector(".item.signup-link"));
		loginLink.click();
		Thread.sleep(5000);
		driver.findElement(By.name("email")).sendKeys("PLACEHOLDER");
		driver.findElement(By.name("password")).sendKeys("PLACEHOLDER");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		Thread.sleep(5000);		
		driver.findElement(By.cssSelector(".item.menu__link--toggle")).click();
	
		List<WebElement> course_list = driver.findElements(By.cssSelector(".subnav-type__list-item"));
		
		for(int i=0; i < course_list.size(); i++) {
			 if(course_list.get(i).getText().equals("Sentiment Analysis in Trading")) course_list.get(i).click(); 
//			 System.out.println(course_list.get(i).getText().equals("Sentiment Analysis in Trading"));
//			 System.out.println(course_list.get(i).getText());
//			 System.out.println(i);
	      }
		
		String price_text = driver.findElement(By.cssSelector(".course-detail__data-unit.price-data-unit")).getText();
		String[] arrOfStr = price_text.split(" ");
		price_text = arrOfStr[arrOfStr.length - 1];
		System.out.println("******************************************************");
		System.out.println("Effective Price:"+price_text);
		System.out.println("******************************************************");
		
		Thread.sleep(5000);	
		List<WebElement> pop_up = driver.findElements(By.cssSelector(".web-push-btn.remind-btn"));
		if(pop_up.size() != 0) ((WebElement) pop_up).click();
		
	        try{
	        	driver.findElement(By.cssSelector(".web-push-btn.remind-btn")).click();;
	        }
	        catch(org.openqa.selenium.NoSuchElementException	 e){
	        	System.out.println("False Alarm");
	        }
	        
	        catch( org.openqa.selenium.ElementClickInterceptedException	 e){
	        	System.out.println("False Alarm");
	        }
	  
		
		driver.findElement(By.xpath("//span[@class=\"tab-list-item__content\"]//span[@class=\"default-slot\"]")).click();
		Thread.sleep(5000);
		
		List<WebElement> cart_items = driver.findElements(By.className("cart-item-title"));
		
		for(int i=0; i < cart_items.size(); i++) {
			 System.out.println(cart_items.get(i).getText());
	      }
		
		int cart_count =Integer.parseInt(driver.findElement(By.cssSelector(".cart-count")).getText());
		System.out.println("******************************************************");
		System.out.println("Cart Items Count:"+cart_items.size());
		System.out.println("Effective Price:"+cart_count);
		System.out.println("******************************************************");
		
		assert cart_count == cart_items.size() : " Mismatch In Cart Items vs Cart Count"; 
		Thread.sleep(8000);
		
		List<WebElement> cart_summary_amount = driver.findElements(By.cssSelector(".cart-summary-left"));
		List<WebElement> cart_summary_cost_num = driver.findElements(By.cssSelector(".cart-summary-right"));
		String base_cost = "",amount_payable_cost = "";

		
		for(int i=0; i < cart_summary_amount.size(); i++) 
		{
			System.out.println(cart_summary_amount.get(i).getText());
			
			 if(cart_summary_amount.get(i).getText().equals("Base Amount"))  
			 {
				 base_cost = cart_summary_cost_num.get(i).getText();
			 }
			 
			 else if(cart_summary_amount.get(i).getText().equals("Amount Payable")) 
			 {
				 amount_payable_cost = cart_summary_cost_num.get(i).getText();
			 }
	     }
		
		System.out.println("******************************************************");
		System.out.println("Base Amount:"+base_cost);
		System.out.println("Amount Payable:"+amount_payable_cost);
		System.out.println("******************************************************");
		
		
		 try{
	        	driver.findElement(By.cssSelector(".web-push-btn.remind-btn")).click();;
	        }
	        catch(org.openqa.selenium.NoSuchElementException	 e){
	        	System.out.println("False Alarm");
	        }
	        
	        catch( org.openqa.selenium.ElementClickInterceptedException	 e){
	        	System.out.println("False Alarm");
	        }
		 
		driver.findElement(By.cssSelector(".cart-item-cta")).click();
	
		
		
		String parent=driver.getWindowHandle();
		
		Set<String> s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		System.out.println(s);
		
		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);
		Thread.sleep(2000);

		System.out.println(driver.switchTo().window(child_window).getTitle());

		driver.close();
		}

		}
		driver.switchTo().window(parent);
		
		driver.findElement(By.xpath("//div[contains(@class, 'cart-item-cta')]//a[@href='javascript:void(0);']")).click();
		Thread.sleep(2000);
		
		String delete_message = driver.findElement(By.cssSelector(".toasted.toasted-primary.info")).getText();
		System.out.println("******************************************************");
		System.out.println("Deleted Message:"+delete_message);
		System.out.println("******************************************************");
		
		driver.findElement(By.cssSelector(".default-slot")).click();
		driver.findElement(By.cssSelector(".form-control.text-uppercase.personal-coupon-input")).sendKeys("ABC");
		
		driver.findElement(By.xpath("//div[contains(@class, 'coupon-form__button')]//span[contains(@class, 'default-slot')]")).click();
		Thread.sleep(1000);
		String coupon_alert_text = driver.findElement(By.cssSelector(".coupon-alert-box")).getText();
		System.out.println("******************************************************");
		System.out.println("Coupon Alert:"+coupon_alert_text);
		System.out.println("******************************************************");
		
		driver.findElement(By.cssSelector(".close")).click();
		driver.findElement(By.cssSelector(".profile-pic-initials")).click();
		
		
		List<WebElement> sign_out =driver.findElements(By.xpath("//li[contains(@class, 'logout')]//a[@href='javascript:void(0);']"));
		sign_out.get(1).click();
		
		Thread.sleep(1000);
		
		driver.close();
		//driver.quit();
		
	}
	
}
