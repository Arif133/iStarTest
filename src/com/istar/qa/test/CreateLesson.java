package com.istar.qa.test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateLesson {
	
	static WebDriver driver;
	
	
	@BeforeTest
	public static void LaunchURL()
	{
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://talentify.in:8999/content/");
		
		
		
	}
	
	@Test(priority = 1)
	public static void Login()
	{
		WebElement Username = driver.findElement(By.xpath("//input[@name='email']"));
		
		Username.clear();
		
		Username.sendKeys("abhinav1@istarindia.com");
		
		WebElement Password = driver.findElement(By.xpath("//input[@name='password']"));
		
		Password.clear();
		
		Password.sendKeys("test123");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String ActualTitle = driver.getTitle();
		
		String ExpectedTitle = "Istar | Dashboard";
		
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		
		
	}
	
	@Test(priority = 2)
	public void CreateLesson1() throws InterruptedException
	{
		WebElement CourceAdminLink = driver.findElement(By.className("dropdown-toggle"));
		
		CourceAdminLink.click();
		
		driver.findElement(By.linkText("Lesson(s)")).click();
		
		driver.findElement(By.id("create_lessonzz")).click();
		 
		 Set handles = driver.getWindowHandles();
		  
		 String firstWinHandle = driver.getWindowHandle(); 
		 
		 handles.remove(firstWinHandle);
		  
		 Object winHandle=handles.iterator().next();
		  
		 if (winHandle!=firstWinHandle){
		  
		 //To retrieve the handle of second window, extracting the handle which does not match to first window handle
		  
		 Object secondWinHandle=winHandle; //Storing handle of second window handle
		  
		 //Switch control to new window
		  
		 driver.switchTo().window((String) secondWinHandle);
		
		WebElement FieldLessonType = driver.findElement(By.xpath(".//*[@id='lesson_type_idd']"));
		
		Select LessionTypeDropdown = new Select(FieldLessonType);
		
		LessionTypeDropdown.selectByVisibleText("ELT Lesson");
		
		String ActualLessonName = "Test Lesson 1234";
		
		WebElement LessonName = driver.findElement(By.xpath(".//*[@id='lesson_name_idd']"));
		
		LessonName.sendKeys(ActualLessonName);
		
		String LessonDescription = "Test Vedio22";
		
		WebElement LessonDesc = driver.findElement(By.name("lesson_desc"));
		
		LessonDesc.sendKeys(LessonDescription);
		
		driver.findElement(By.id("lesson_duration_idd")).sendKeys("5");
		
		WebElement NextButton = driver.findElement(By.linkText("Next"));
		
		NextButton.click();
		
		NextButton.click();
		
		NextButton.click();

		driver.findElement(By.linkText("Finish")).click();
		
        Thread.sleep(2000);
		
		driver.findElement(By.linkText("Dashboard")).click();
		
		WebElement ExpectedLesson = driver.findElement(By.linkText(ActualLessonName));

		String ExpectedLessonName = ExpectedLesson.getText();
		
		Assert.assertEquals(ExpectedLessonName, ActualLessonName);
		
		System.out.println("Lesson "+ActualLessonName+" has been created successfully");

	}
		
	}
	
	
	
}
