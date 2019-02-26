package com.ebay;

import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
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
public class ExampleUnitTest {
    AndroidDriver<MobileElement> appiumDriver = null;
    DesiredCapabilities desiredCapabilities = null;
    WebDriverWait webDriverWait = null;
    WebElement webElement = null;
    WebDriver driver = null;
    wrapperClass wrapperClass=new wrapperClass();

    @Before
    public void initialisation(){
        try{
            desiredCapabilities =new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel API 24");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ebay.mobile");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ebay.mobile.activities.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        }catch (Exception ex){
            System.out.println("Exception in initialisation method" +ex);
        }
    }

    @Test
    public void execution(){
        //sign in to EBay App
        boolean sign_in =wrapperClass.webElementOperations(appiumDriver, "wait","id",variablePaths.signIn, null);
        System.out.println("INFO : sign in button visibility status "+sign_in);
        boolean sign_in_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.signIn, null);
        System.out.println("INFO :sign in button click status "+sign_in_click_status);
        boolean username_status =wrapperClass.webElementOperations(appiumDriver, "text","id",variablePaths.userName,variablePaths.userName_details);
        System.out.println("INFO :Username status "+username_status);
        boolean password_status =wrapperClass.webElementOperations(appiumDriver, "text","id",variablePaths.password,variablePaths.password_details);
        System.out.println("INFO :password status "+password_status);
        sign_in_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.signIn, null);
        System.out.println("INFO :sign in button click status "+sign_in_click_status);
        //selecting TV
        boolean nothanks_status =wrapperClass.webElementOperations(appiumDriver, "wait","id",variablePaths.noThanks, null);
        System.out.println("INFO :no thanks button visibility status "+nothanks_status);
        boolean nothanks_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.noThanks, null);
        System.out.println("INFO :no thanks button click status "+nothanks_click_status);
        boolean searchBox_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.searchBox, null);
        System.out.println("INFO :Search box click status "+searchBox_click_status);
        boolean enter_status =wrapperClass.webElementOperations(appiumDriver, "text","id",variablePaths.searchBox_text, variablePaths.searchText);
        System.out.println("INFO :Search box entering text status "+enter_status);
        boolean tvlist_click_status =wrapperClass.webElementOperations(appiumDriver, "randomclick","id",variablePaths.tvList, null);
        System.out.println("INFO :tvlist check status "+tvlist_click_status);
        // Recording details of TV
        boolean buy_now_status =wrapperClass.webElementOperations(appiumDriver, "wait","id",variablePaths.buyNow, null);
        System.out.println("INFO : buy button visibility status "+buy_now_status);
        variablePaths.tvNameText =wrapperClass.getText(appiumDriver,"id",variablePaths.tvName);
        variablePaths.tvPriceText =wrapperClass.getText(appiumDriver,"id",variablePaths.tvPrice);
        //variablePaths.tvDescriptionText =wrapperClass.getText(appiumDriver,"id",variablePaths.tvDescripton);
        System.out.println("INFO : TV Name is "+variablePaths.tvNameText+ " TV Price is "+variablePaths.tvPriceText);
        boolean buynow_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.buyNow, null);
        System.out.println("INFO :buy now button click status "+buynow_click_status);
        boolean cancel_click_status =wrapperClass.webElementOperations(appiumDriver, "click","id",variablePaths.cancel, null);
        System.out.println("INFO :Cancel button click status "+cancel_click_status);
        Assert.assertTrue(!(variablePaths.tvNameText.isEmpty() || variablePaths.tvPriceText.isEmpty()));
    }

    @After
    public void cleanup(){
        try{
            appiumDriver.quit();
        }catch (Exception ex){
            System.out.println("Exception occured in cleanup" + ex);
        }
    }
}