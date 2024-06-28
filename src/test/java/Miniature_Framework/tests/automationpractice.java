package Miniature_Framework.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Miniature_Framework.locators.AfterRegistartionPage_Locators;
import Miniature_Framework.locators.LandingPage_Locators;
import Miniature_Framework.locators.ProductDetails;
import Miniature_Framework.locators.RegisterUserDetailsPage_Locators;
import Miniature_Framework.locators.SignInPage_Locators;
import Miniature_Framework.locators.SocialMediaPage_Locators;
import Miniature_Framework.utilities.ElementActions;
import Miniature_Framework.utilities.JavaScriptUtil;
import Miniature_Framework.utilities.Utils;
import Miniature_Framework.variables.LandingPage_Variables;
import Miniature_Framework.variables.ProductDetails_Variables;
import Miniature_Framework.variables.RegisterUserDetailsPage_Variables;
import Miniature_Framework.variables.SignInPage_Variables;
import Miniature_Framework.variables.SocialMediaPage_Variables;
import junit.framework.Assert;

public class automationpractice {

	
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

	// 1. Url Redirection Test
	@Test
	public void _1_urlTest()
	{
		Assert.assertEquals("Url redirection is not as expected",LandingPage_Variables.ExpectedRedirectedURL, elemementActions.doGetcurrentURL());
		System.out.println("Test -> 1. Url Redirection Test Passed");
	}

	// 2. Landing Page Title Test
	@Test
	public void _2_pageTitleTest()
	{

		Assert.assertEquals("Page title is incorrect",LandingPage_Variables.LandingPageTitle, elemementActions.doGetPageTitle());
		System.out.println("Test -> 2. Landing Page Title Test passed");
	}

	// 3. Validate for all categories is displayed
	@Test
	public void _3_allCategoriesDisplayedTest()
	{
		ArrayList<String> expectedCategoryList = LandingPage_Variables.ExpectedMainProductCategories();
		
		List<WebElement> actualProductCategoryList = elemementActions.getElementsList(LandingPage_Locators.ProductCatoegoryList);
		
		for (int i = 0; i < expectedCategoryList.size(); i++) 
		{
			Assert.assertEquals("Product index no " + (i+1) + " is not matching with expected",expectedCategoryList.get(i), actualProductCategoryList.get(i).getText());
		}
		
		System.out.println("Test -> 3. Validate for all categories is displayed passed");
	}
	
	// 4. Validate Application Logo on landing page is displayed
	@Test
	public void _4_validateApplicationLogoOnLandingPage()
	{
		Assert.assertEquals("Application Logo on Landing Page is not Displayed",true, elemementActions.doIsDisplayed(LandingPage_Locators.landingPageLogoImage));
		System.out.println("Test -> 4. Validate Application Logo on landing page is displayed passed");
	}
	
	// 5. Validate Application Logo Height on landing page
	@Test
	public void _5_validateApplicationLogoHeightOnLandingPage()
	{
		Assert.assertEquals("Apllication Logo height on Landing Page is not matching",LandingPage_Variables.ApplicationLogoHeight, elemementActions.getAttributeValue(LandingPage_Locators.landingPageLogoImage, "height", driver));
		System.out.println("Test -> 5. Validate Application Logo Height on landing page passed");
	}
	
	// 6. Validate Application Logo Width on landing page
	@Test
	public void _6_validateApplicationLogowidthOnLandingPage()
	{
		Assert.assertEquals("Application Logo width on Landing Page is not matching", LandingPage_Variables.ApplicationLogoWidth, elemementActions.getAttributeValue(LandingPage_Locators.landingPageLogoImage, "width", driver));
		System.out.println("Test -> 6. Validate Application Logo Width on landing page passed");
	}

	// 7. SignIn page title validation test
	@Test
	public void _7_SignInPageTitleValidationTest()
	{
		elemementActions.doClick(LandingPage_Locators.SigninButtonElement);
		
		Assert.assertEquals("Signin Page Title is not matching", SignInPage_Variables.SignInPageTitle, elemementActions.doGetPageTitle());
		System.out.println("Test -> 7. SignIn page title validation test passed");
	}
	
	// 8. Register User With New Email Id on SignIn Page
	@Test
	public void _8_RegisterNewUserTest()
	{
		_7_SignInPageTitleValidationTest();
		
		elemementActions.doSendKeys(SignInPage_Locators.emailIdInputFieldElement, SignInPage_Variables.NewUserEmailID);
		
		elemementActions.doClick(SignInPage_Locators.createAnAccountButtonElement);
		
		Assert.assertEquals("\"Create an account\" text is not displayed on register user details page",RegisterUserDetailsPage_Variables.crateAnAccountExpectedText, elemementActions.doGetText(RegisterUserDetailsPage_Locators.CreateAnAccountTextElement));
		
		elemementActions.doClick(RegisterUserDetailsPage_Locators.maleGenderRadioButtonElement);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.userFirstNameTextboxElement, RegisterUserDetailsPage_Variables.userFirstName);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.userLastNameTextboxElement, RegisterUserDetailsPage_Variables.userLastName);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.userPasswordTextboxElement, RegisterUserDetailsPage_Variables.userPassword);

		elemementActions.selectByValueFromDropDown(RegisterUserDetailsPage_Locators.userDOBDayDropDownElement, RegisterUserDetailsPage_Variables.DOBDayValue);

		elemementActions.selectByValueFromDropDown(RegisterUserDetailsPage_Locators.userDOBMonthDropDownElement, RegisterUserDetailsPage_Variables.DOBMonthValue);

		elemementActions.selectByValueFromDropDown(RegisterUserDetailsPage_Locators.userDOBYearDropDownElement, RegisterUserDetailsPage_Variables.DOBYearValue);
		
		if (RegisterUserDetailsPage_Variables.IsSignUpForNewsLetter) 
		{
			elemementActions.doClick(RegisterUserDetailsPage_Locators.signUpForNewsLetterCheckBoxElement);
		}
		
		if (RegisterUserDetailsPage_Variables.IsspecialOfferEmailRec) 
		{
			elemementActions.doClick(RegisterUserDetailsPage_Locators.specialOfferEmailRecCheckBoxElement);
		}
			
		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressComanyNameTextBoxElement, RegisterUserDetailsPage_Variables.AddressComanyName);
		
		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressLine1TextBoxElement, RegisterUserDetailsPage_Variables.AddressLine1Name);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressLine2TextBoxElement, RegisterUserDetailsPage_Variables.AddressLine2Name);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressCityTextBoxElement, RegisterUserDetailsPage_Variables.AddressCity);

		elemementActions.selectByVisibleTextFromDropDown(RegisterUserDetailsPage_Locators.AddressStateDropDownElement, RegisterUserDetailsPage_Variables.AddressState);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressZipCodeTextBoxElement, RegisterUserDetailsPage_Variables.AddressZipCode);

		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressAdditionalInfoTextBoxElement, RegisterUserDetailsPage_Variables.AddressAdditionalInfo);
		
		elemementActions.doSendKeys(RegisterUserDetailsPage_Locators.AddressMobilePhoneTextBoxElement, RegisterUserDetailsPage_Variables.AddressMobilePhone);

		elemementActions.doClick(RegisterUserDetailsPage_Locators.registerFormButtonElement);

		elemementActions.waitForElementVisible(AfterRegistartionPage_Locators.registeredUserNameDisplayElement);
		Assert.assertEquals("Registered User First Name And Last Name is not displayed correctly after registration", RegisterUserDetailsPage_Variables.userFirstName + " " + RegisterUserDetailsPage_Variables.userLastName,
				elemementActions.doGetText(AfterRegistartionPage_Locators.registeredUserNameDisplayElement));
		
		System.out.println("User Registered Successfully as -> " + RegisterUserDetailsPage_Variables.userFirstName + " " + RegisterUserDetailsPage_Variables.userLastName);
		
		System.out.println("Test -> 8. Register User With New Email Id on SignIn Page passed");
		
	}
	
	// 9. Search by a keyword in the product search box and validate how many products are matching with the name
	@Test
	public void _9_SearchProduct()
	{
		elemementActions.doSendKeys(LandingPage_Locators.searchBox, "Dress");
		elemementActions.waitForElementPresent(LandingPage_Locators.searchBoxResultList);
		
		List<WebElement> elementsList = elemementActions.getElementsList(LandingPage_Locators.searchBoxResultList);
		
		int countOfProductsWithKeyword = 0;
		
		for (int i = 0; i < elementsList.size(); i++) 
		{
			if (elementsList.get(i).getText().contains("Dress")) 
			{
				countOfProductsWithKeyword = countOfProductsWithKeyword + 1;
			}
		} 
		if (countOfProductsWithKeyword == 5) {
			Assert.assertTrue(true);
		}
		
	}
	
	// 10. Fetch the all product price and get its total
	@Test
	public void _10_GetProductPriceTotal()
	{
		elemementActions.doClick(LandingPage_Locators.topMenuDressesCategory);
		List<WebElement> productPriceListElement = elemementActions.getElementsList(ProductDetails.DressedPriceList);
		double priceTotal = 0;
		double expectedTotalPrice = 152.87000000000003;
		for (int i = 0; i < productPriceListElement.size(); i++) 
		{
			priceTotal = priceTotal + Double.parseDouble(productPriceListElement.get(i).getText().trim().substring(1));
			System.out.println(productPriceListElement.get(i).getText().trim().substring(1));
		}System.out.println(priceTotal);

		if (priceTotal == expectedTotalPrice) {
			Assert.assertTrue(true );
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	// 11. Send a Friend Feature
	@Test
	public void _11_RecomendToFriend()
	{
		elemementActions.doClick(LandingPage_Locators.topMenuTShirtCategory);
		elemementActions.doMoveToElement(ProductDetails.optionsToBuyProduct);
		elemementActions.doActionsClick(ProductDetails.MoreOptionButton);
		
		elemementActions.waitForElementClickable(ProductDetails.SendFriendButton);
		elemementActions.doClick(ProductDetails.SendFriendButton);
		
		elemementActions.waitForElementClickable(ProductDetails.FrinedNameTextBox);
		elemementActions.doSendKeys(ProductDetails.FrinedNameTextBox, ProductDetails_Variables.FriendNameForRecommendation);
		elemementActions.doSendKeys(ProductDetails.FrinedEmailIdTextBox, ProductDetails_Variables.FriendEmailIdForRecommendation);
		
		elemementActions.waitForElementClickable(ProductDetails.FrinedMailSendButton);
		elemementActions.doActionsClick(ProductDetails.FrinedMailSendButton);
		
		elemementActions.waitForElementVisible(ProductDetails.emailConfirmationMessageText);
		System.out.println(elemementActions.doGetText(ProductDetails.emailConfirmationMessageText));
		
		Assert.assertEquals("Email Confirmation message is not machtching with the expected string", ProductDetails_Variables.recomendationEmailConformationText, elemementActions.doGetText(ProductDetails.emailConfirmationMessageText));
		
	}
	
	// 12. Change in the image using Color Feature
	@Test
	public void _12_productColourSelection()
	{
		elemementActions.doClick(LandingPage_Locators.topMenuTShirtCategory);
		String orangeColourImageSourceLink = elemementActions.getAttributeValue(ProductDetails.FadedTShritOrgangeColourImage, "src", driver);
		System.out.println("Orange Colour Tshirt product Image Source Link : " + orangeColourImageSourceLink);
		
		javaScriptUtil.clickElementByJS(elemementActions.getElement(ProductDetails.FadedTShritBlueColourIcon));
		//elemementActions.doActionsClick(ProductDetails.FadedTShritBlueColourIcon);
		elemementActions.waitForElementVisible(ProductDetails.FadedTShritBlueColourImage);
		String blueColourImageSourceLink = elemementActions.getAttributeValue(ProductDetails.FadedTShritBlueColourImage, "src", driver);
		System.out.println("Blue Colour Tshirt product Image Source Link : " + blueColourImageSourceLink);
		
		if (!(orangeColourImageSourceLink.equals(blueColourImageSourceLink))) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
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
