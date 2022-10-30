package com.assignment;

import org.testng.Assert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MovieExtract {
	String movieName="Pushpa: The Rise";
	//String movieName="Dhadkan";
	 SimpleDateFormat  simpleformat = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) throws ParseException, InterruptedException {
		// TODO Auto-generated method stub
		
				System.setProperty("webdriver.chrome.driver", "F:\\Akshu\\Learning\\Drivers\\chromedriver.exe");
				WebDriver driver=new ChromeDriver();
		MovieExtract movieExtract=new MovieExtract();
	ArrayList<String> imdb=	movieExtract.imdbDetails(driver);
		Thread.sleep(1000);
	ArrayList<String> wiki=	movieExtract.wikiDetails(driver);
	//	AssertJUnit.assertEquals(imdb.get(0), wiki.get(0));
		//AssertJUnit.assertEquals(imdb.get(1),wiki.get(1));
	Assert.assertEquals(imdb.get(0), wiki.get(0));
	Assert.assertEquals(imdb.get(1),wiki.get(1));
	

				

			
			
					
			//System.out.println(driver.findElement(By.xpath("//div[contains(text(),'Release date')]")).getText());
				//div[data-testid='title-details-section'] li[data-testid='title-details-releasedate']
		/*
		 * for(WebElement option:options) {
		 * if(option.getText().equalsIgnoreCase(movieName)) { option.click(); break; } }
		 */

			}

public ArrayList<String> imdbDetails(WebDriver driver) throws InterruptedException, ParseException {
	ArrayList<String> imdbDetails=new ArrayList();
	  driver.get("https://www.imdb.com/"); 
	  driver.findElement(By.id("suggestion-search")).sendKeys(movieName);
	  Thread.sleep(1000); //List<WebElement> options=
	  driver.findElement(By.xpath("//ul[@role='listbox']/li[1]/a")).click();
	  //div[data-testid='title-details-section'] 
	  WebElement release=driver.findElement(By.cssSelector("div[data-testid='title-details-section'] li[data-testid='title-details-releasedate']")); 
	  System.out.println(release.getText().replace("Release date\n", "").split("\\(")[0]); 
	  WebElement country=driver.findElement(By.cssSelector("li[data-testid='title-details-origin']"));
	   String imdbReleaseDate=release.getText().replace("Release date\n", "").split("\\(")[0];
	  String imdbCountry=country.getText().replace("Country of origin\n", "");
	  System.out.println(country.getText().replace("Country of origin\n", "")); 
	  Date date1=new SimpleDateFormat("MMMM dd, yyyy").parse(imdbReleaseDate.trim());
	  System.out.println(date1);
	 
	String  imdbDate = simpleformat.format(date1);
	  System.out.println(imdbDate);
	  imdbDetails.add(imdbCountry);
	  imdbDetails.add(imdbDate);
return imdbDetails;
	  
}

public ArrayList<String> wikiDetails(WebDriver driver) throws ParseException {
	ArrayList<String> wikiDetails=new ArrayList();
	driver.get("https://en.wikipedia.org/wiki/Main_Page");
	driver.findElement(By.cssSelector("input[name='search']")).sendKeys(movieName);
	WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(20));
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='suggestions-results'] a[title='"+movieName+"']")));
	driver.findElement(By.cssSelector("div[class='suggestions-results'] a[title='"+movieName+"']")).click();
	List<WebElement> options=driver.findElements(By.cssSelector("table[class='infobox vevent'] tr"));
	String wikiReleaseDate="";
	String wikiCountry="";
	for(WebElement row:options)
	{
		if(row.getText().contains("Release date")) {
			System.out.println(row.getText().replace("Release date\n", ""));
			wikiReleaseDate=row.getText().replace("Release date\n", "");
		}
		if(row.getText().contains("Country")) {
			System.out.println(row.getText().replace("Country ",""));
			wikiCountry=row.getText().replace("Country ","");
		}
			//option.findElement(By.cssSelector("td div")).getText();
	}
	Date date2=new SimpleDateFormat("dd MMMM yyyy").parse(wikiReleaseDate);
	System.out.println(date2);
			String wikiDate=simpleformat.format(date2);
			System.out.println(wikiDate);
			wikiDetails.add(wikiCountry);
			wikiDetails.add(wikiDate);
return wikiDetails;
			
			
			
}
		}

	
