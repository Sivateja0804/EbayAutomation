package com.ebay;

import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;

import Pages.CheckOutPage;
import Pages.DesiredCapabilities;
import Pages.LoginPage;
import Pages.ProductPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Testcase extends DesiredCapabilities {

    WebDriverWait webDriverWait = null;
    WebElement webElement = null;
    WebDriver driver = null;
    AndroidDriver<MobileElement>androidDriver=null;
    LoginPage loginPage=new LoginPage();
    ProductPage productPage=new ProductPage();
    CheckOutPage checkOutPage=new CheckOutPage();

    @Before
    public  void initialization() {
        androidDriver=super.getDriver();
        Assert.assertNotNull(String.valueOf(androidDriver) , "Asertion Error : Unable to get the initialized Driver for automation");
    }

    @Test
    public void execution() throws Exception{
        //sign in to EBay App
        Assert.assertTrue("Assertion Error : Error Navigating to Login Page", loginPage.ValidateSigninPage(androidDriver));
        Assert.assertTrue("Assertion Error : Error Validating the Sign in functionality", loginPage.validateSignInFunctionality(androidDriver));

        //Buying a product
        Assert.assertTrue("Assertion Error : Error Validating the Search item Page",productPage.searchItem(androidDriver));
        Assert.assertTrue("Assertion Error : Error Buying the item",productPage.buyItem(androidDriver));

        //validating checkout page
        Assert.assertTrue("Assertion Error : Error validating the checkout page",checkOutPage.validationCheckoutPage(androidDriver));
        Assert.assertTrue("Assertion Error : Error validating the checkout page item details",checkOutPage.validateItemDetails());
    }

    @After
    public void cleanup(){
        try{
            androidDriver.quit();
        }catch (Exception ex){
            System.out.println("Exception occured in cleanup" + ex);
        }
    }
}