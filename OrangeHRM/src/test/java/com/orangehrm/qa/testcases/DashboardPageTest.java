package com.orangehrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.SeleniumBase;
import com.orangehrm.qa.pages.AboutPage;
import com.orangehrm.qa.pages.AttendancePage;
import com.orangehrm.qa.pages.ChangePasswordPage;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.pages.SupportPage;

public class DashboardPageTest extends SeleniumBase {
	LoginPage loginpage;
	DashboardPage dashboardpage;
	AboutPage aboutpage;
	SupportPage supportpage;
	ChangePasswordPage changepasswordpage;
	AttendancePage attendancepage;

	public DashboardPageTest() {
		super();
	}

	// ------------------To Initiate browser-----------------
	@BeforeMethod
	public void SetUp() {
		log.info("Initializing the browser and launching the application");
		initialization();
		loginpage = new LoginPage();
		log.info("Logging in with username: " + prop.getProperty("username"));
		dashboardpage = loginpage.enterLoginCredentials(prop.getProperty("username"), prop.getProperty("password"));
		aboutpage = new AboutPage();
		supportpage = new SupportPage();
		changepasswordpage = new ChangePasswordPage();
		attendancepage = new AttendancePage();
		log.info("Login successful, navigating to the Dashboard page");
	}

	// ------------------------------Header------------------------------
	@Test(priority = 1)
	public void upgradeLinkTest() {
		log.info("Clicking on the 'Upgrade' link");
		dashboardpage.upgradeLink();
		testutil.switchToWindowByIndex(driver, 1);
		boolean mainWebsiteLogo = dashboardpage.mainWebsiteLogo();
		Assert.assertTrue(mainWebsiteLogo,
				"After clicking the upgrade link, it does not navigate to the main OrangeHRM website");
		log.info("Upgrade link navigation verified, closing the new window");
		driver.close();
		testutil.switchToWindowByIndex(driver, 0);
		boolean labelUsername = dashboardpage.labelUsername();
		Assert.assertTrue(labelUsername,
				"After closing the new window, the driver does not navigate back to the dashboard page");
		log.info("Back to the dashboard page after closing the new window");
	}

	@Test(priority = 2)
	public void labelUsernameTest() {
		log.info("Verifying if the username label is displayed on the dashboard");
		boolean labelUsername = dashboardpage.labelUsername();
		Assert.assertTrue(labelUsername, "Username label on the dashboard is not displayed");
		log.info("Username label visibility: " + labelUsername);
	}

	@Test(priority = 3)
	public void labelUsernameImageTest() {
		log.info("Verifying if the username image is displayed on the dashboard");
		boolean labelUsernameImage = dashboardpage.labelUsernameImage();
		Assert.assertTrue(labelUsernameImage, "Username image on the dashboard is not displayed");
		log.info("Username image visibility: " + labelUsernameImage);
	}

	@Test(priority = 4)
	public void labelUsernameDropdownIconTest() {
		log.info("Clicking the username dropdown icon");
		dashboardpage.labelUsernameDropdownIcon();
		boolean headerDropdownMenu = dashboardpage.headerDropdownMenu();
		Assert.assertTrue(headerDropdownMenu, "Dropdown menu is not displayed after clicking the dropdown icon");
		log.info("Dropdown menu visibility: " + headerDropdownMenu);
	}

	@Test(priority = 5)
	public void helpButtonTest() {
		log.info("Clicking on the Help button in the header");
		dashboardpage.headerHelp();
		testutil.switchToWindowByIndex(driver, 1);
		boolean headerHelpMain = dashboardpage.headerHelpMain();
		Assert.assertTrue(headerHelpMain,
				"The driver does not switch to the new window after clicking the Help button");
		log.info("Help window opened, closing the window");
		driver.close();
		testutil.switchToWindowByIndex(driver, 0);
		boolean labelUsername = dashboardpage.labelUsername();
		Assert.assertTrue(labelUsername,
				"The driver does not switch back to the parent window after closing the Help window");
		log.info("Switched back to the parent window after closing the Help window");
	}

	// ------------------------------Header DropDown------------------------------
	@Test(priority = 6)
	public void headerDropdownAboutTest() {
		log.info("Clicking on the 'About' option from the header dropdown");
		dashboardpage.headerDropdownAbout();
		boolean aboutHeader = aboutpage.aboutHeader();
		Assert.assertTrue(aboutHeader, "Clicking on 'About' does not navigate to the About page");
		log.info("Navigated to About page, verifying header visibility");
	}

	@Test(priority = 7)
	public void headerDropdownSupportTest() {
		log.info("Clicking on the 'Support' option from the header dropdown");
		dashboardpage.headerDropdownSupport();
		boolean supportDescription = supportpage.supportDescription();
		Assert.assertTrue(supportDescription, "Clicking on 'Support' does not navigate to the Support page");
		log.info("Navigated to Support page, verifying description visibility");
	}

	@Test(priority = 8)
	public void headerDropdownChangePasswordTest() {
		log.info("Clicking on 'Change Password' from the header dropdown");
		dashboardpage.headerDropdownChangePassword();
		boolean changeHeaderUsername = changepasswordpage.changeHeaderUsername();
		Assert.assertTrue(changeHeaderUsername,
				"Clicking on 'Change Password' does not navigate to the Change Password page");
		log.info("Navigated to Change Password page, verifying username header visibility");
	}

	@Test(priority = 9)
	public void headerDropdownLogout() {
		log.info("Clicking on 'Logout' from the header dropdown");
		dashboardpage.headerDropdownLogout();
		boolean validateLogoHeader = loginpage.validateLogoHeader();
		Assert.assertTrue(validateLogoHeader,
				"Clicking 'Logout' does not log out the account and navigate to the login page");
		log.info("Successfully logged out and navigated to the login page");
	}

	// ---------------------------Dashboard Widget-----------------------------
	// ------------------------------Time at Work------------------------------
	@Test(priority = 10)
	public void timeAtWorkclockIconTest() {
		log.info("verify the clock icon is displayed on time at work widget");
		boolean result = dashboardpage.clockIcon();
		Assert.assertTrue(result, "time at work is not displayed on time at work widget");
		log.info("clock icon visibility: " + result);
	}

	@Test(priority = 11)
	public void timeAtWorkLabelTest() {
		log.info("verify the time at work is displayed on time at work widget");
		boolean result = dashboardpage.timeAtWork();
		Assert.assertTrue(result, "time at work label is not displayed on time at work widget");
		log.info("time at work visibility: " + result);
	}

	@Test(priority = 12)
	public void imageUserTimeAtWorkTest() {
		log.info("verify the user image is displayed on time at work widget");
		boolean result = dashboardpage.imageUserTimeAtWork();
		Assert.assertTrue(result, "user image is not displayed on time at work widget");
		log.info("user image visibility: " + result);
	}

	@Test(priority = 13)
	public void punchedOutLabelTest() {
		log.info("verify the punched out label is displayed on time at work widget");
		boolean result = dashboardpage.punchedOut();
		Assert.assertTrue(result, "punched out label is not displayed on time at work widget");
		log.info("punched out label visibility: " + result);
	}

	@Test(priority = 14)
	public void punchedOutTimeTest() {
		log.info("verify the punched out time is displayed on time at work widget");
		boolean result = dashboardpage.punchedOutTime();
		Assert.assertTrue(result, "punched out time is not displayed on time at work widget");
		log.info("punched out time visibility: " + result);
	}

	@Test(priority = 15)
	public void timeTodayTest() {
		log.info("verify the time today is displayed on time at work widget");
		boolean result = dashboardpage.timeToday();
		Assert.assertTrue(result, "time today is not displayed on time at work widget");
		log.info("time today visibility: " + result);
	}

	@Test(priority = 16)
	public void thisWeekTest() {
		log.info("verify the this week label is displayed on time at work widget");
		boolean result = dashboardpage.thisWeek();
		Assert.assertTrue(result, "this week label is not displayed on time at work widget");
		log.info("this week label visibility: " + result);
	}

	@Test(priority = 17)
	public void employeeAttendanceChartTest() {
		log.info("verify the employee attendance chart is displayed on time at work widget");
		boolean result = dashboardpage.employeeAttendanceChart();
		Assert.assertTrue(result, "employee attendance chart is not displayed on time at work widget");
		log.info("employee attendance chart visibility: " + result);
	}

	@Test(priority = 18)
	public void stopWatchTest() {
		log.info("verify the clock icon is displayed on time at work widget");
		dashboardpage.stopWatch();
		log.info("Successsfully navigate to attendance page");
		boolean result = attendancepage.punchInHeader();
		Assert.assertTrue(result, "punch in header is not displayed on attendance page");

	}

	// ------------------------------To Quit Browser------------------------------
	@AfterMethod
	public void tearDown() {
		log.info("Test execution complete. Closing the browser");
		try {
			if (driver != null) {
				driver.quit();
				log.info("Browser closed successfully");
			}
		} catch (Exception e) {
			log.error("Error while closing the browser: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
