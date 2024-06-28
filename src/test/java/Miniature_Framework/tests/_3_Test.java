package Miniature_Framework.tests;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Miniature_Framework.locators.LandingPage_Locators;
import Miniature_Framework.locators.SocialMediaPage_Locators;
import Miniature_Framework.utilities.ElementActions;
import Miniature_Framework.utilities.JavaScriptUtil;
import Miniature_Framework.utilities.Utils;
import Miniature_Framework.variables.LandingPage_Variables;
import Miniature_Framework.variables.SignInPage_Variables;
import Miniature_Framework.variables.SocialMediaPage_Variables;
import junit.framework.Assert;

public class _3_Test {

	
	WebDriver driver;
	WebDriverWait wait;
	Properties prop;
	Utils utils;
	ElementActions elemementActions;
	JavaScriptUtil javaScriptUtil;

	@Before
	public void setUp()
	{
		utils = new Utils();
		prop = utils.init_prop();
		driver = utils.init_driver(prop);
		elemementActions = new ElementActions(driver);
		wait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("WebDriverWaitTimeout")));
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	
	// Validate Social Media Handles
	// 13. Facebook Social Media Page Redirection & Validation
	@Test
	public void _13_FaceBookMediaHandlesValidation()
	{
		elemementActions.doClick(LandingPage_Locators.faceBookLink);
		elemementActions.switchToWindowId(driver, 1);
		elemementActions.waitForElementVisible(SocialMediaPage_Locators.youtubeChannelNameElement);
		String facebookChannelName = elemementActions.doGetText(SocialMediaPage_Locators.youtubeChannelNameElement);
		Assert.assertEquals("Facebook channel name is not matching", SocialMediaPage_Variables.facebookChannelNameText, facebookChannelName);
	}
	
	// 14. Twitter Social Media Page Redirection & Validation
	@Test
	public void _14_TwitterMediaHandlesValidation()
	{
		elemementActions.doClick(LandingPage_Locators.twitterLink);
		elemementActions.switchToWindowId(driver, 1);
		elemementActions.waitForElementVisible(SocialMediaPage_Locators.twitterChannelNameElement);
		String twitterChannelName = elemementActions.doGetText(SocialMediaPage_Locators.twitterChannelNameElement);
		Assert.assertEquals("Facebook channel name is not matching", SocialMediaPage_Variables.twitterChannelNameText, twitterChannelName);
	}
	
	// 15. Youtube Social Media Page Redirection & Validation
	@Test
	public void _15_YoutubeMediaHandlesValidation()
	{
		elemementActions.doClick(LandingPage_Locators.youTubeLink);
		elemementActions.switchToWindowId(driver, 1);
		elemementActions.waitForElementVisible(SocialMediaPage_Locators.youtubeChannelNameElement);
		String youtubeChannelName = elemementActions.doGetText(SocialMediaPage_Locators.youtubeChannelNameElement);
		Assert.assertEquals("Facebook channel name is not matching", SocialMediaPage_Variables.youtubeChanneNameText, youtubeChannelName);
	}
	
	
	// 16. Newsletter subscription
	@Test
	public void _16_NewsletterSubscription()
	{
		elemementActions.doSendKeys(LandingPage_Locators.newsLetterEmailTextBoxElement, SignInPage_Variables.NewUserEmailID);
		elemementActions.doClick(LandingPage_Locators.newsLetterEmailSendButtonElement);
		elemementActions.waitForElementVisible(LandingPage_Locators.newsLetterSubscriptionSuccessAlertElement);
		Assert.assertEquals("News Letter Subscription Message is not matching with expected value", LandingPage_Variables.newsLetterSubscriptionSuccessAlertText, 
		elemementActions.doGetText(LandingPage_Locators.newsLetterSubscriptionSuccessAlertElement));	
	}
	
	@After
	public void teardown()
	{
		driver.quit();
	}
}
