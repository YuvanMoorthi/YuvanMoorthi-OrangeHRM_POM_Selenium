package com.orangehrm.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.qa.base.SeleniumBase;

public class TestUtil extends SeleniumBase {
	public long implicityWait = 30;
	public long webdriverwait = 20;

	// ------------------Wait for element to be click able-----------------------
	public static void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Waiting for element to be clickable: " + element);
	}

	public void waitForvisibilityOfElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Waiting for element to be visibility: " + element);

	}

	/**
	 * This method is used for switching between windows using index value
	 * 
	 * @index - this index is used to switch between the windows
	 */
	public static void switchToWindowByIndex(WebDriver driver, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			wait.until((ExpectedCondition<Boolean>) d -> driver.getWindowHandles().size() > index);
			List<String> windowHandles = List.copyOf(driver.getWindowHandles());
			if (index >= 0 && index < windowHandles.size()) {
				driver.switchTo().window(windowHandles.get(index));
				log.info("Switched to window with index: {}", index);
			} else {
				log.error("Invalid index: {}", index);
			}
		} catch (Exception e) {
			log.error("Unable to switch to window at index: {}", index, e);
		}
	}

	/**
	 * 
	 * @param driver   -
	 * @param testName - means method name to saves
	 * @return
	 */
	// Method to capture screenshot
	public static String captureScreenshot(WebDriver driver, String testName) {
		// Get the current date and time to create unique file names
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("Screenshots/" + testName + "_" + timestamp + ".png");
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Screenshot saved at: {}", destination.getAbsolutePath());
		return testName;
	}

}
