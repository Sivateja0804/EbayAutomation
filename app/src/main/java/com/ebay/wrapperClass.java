package com.ebay;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class wrapperClass {
    int waitTime=20;
    int min=1;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean webElementOperations(AndroidDriver driver, String operation, String type, String path, String text){
        WebElement element=null;
        try{
            if (operation.equalsIgnoreCase("click")){
                element=findElement(driver,type,path);
                element.click();
            }
            else if(operation.equalsIgnoreCase("wait")){
                element=waitForElement(driver,type,path);
            }
            else if(operation.equalsIgnoreCase("randomclick")){
                List<WebElement> elementlist=waitForElementList(driver,type,path);
                Dimension size =driver.manage().window().getSize();
                int param_len[]= new int[]{(int)(size.width*0.5),(int)(size.height*0.5),(int)(size.height*0.05)};
                TouchAction ta=new TouchAction(driver).press(point(param_len[0], param_len[1]))
                        .waitAction(waitOptions(ofMillis(1000)))
                        .moveTo(point(param_len[0], param_len[2]));
                ta.release().perform();
                Thread.sleep(2000);
                elementlist=waitForElementList(driver,type,path);
                element=elementlist.get((int)(Math.random() * (elementlist.size()) + min));
                element.click();
            }
            else if(operation.equalsIgnoreCase("text")){
                element=findElement(driver,type,path);
                element.click();
                element.clear();
                element.sendKeys(text);
            }
            if (element!=null){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            System.out.println("Exception occured in webElementOperations"+ ex);
            return false;
        }
    }

    public WebElement findElement(AppiumDriver driver, String type, String path){
        WebElement element=null;
        try {
            if (type.equalsIgnoreCase("id")){
                element=driver.findElementById(path);
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in findElement"+ ex);
            return null;
        }

    }

    public WebElement waitForElement(AppiumDriver driver, String type, String path){
        WebElement element=null;
        try {
            WebDriverWait webDriverWait =new WebDriverWait(driver,waitTime);
            if (type.equalsIgnoreCase("id")){
                 element=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in waitForElement"+ ex);
            return null;
        }

    }

    public String getText(AndroidDriver driver, String type, String path){
        try{
            WebElement element=findElement(driver,type,path);
            return element.getText();
        }catch (Exception ex){
           System.out.println("ERROR : Exception in get Text method "+ex);
           return  null;
        }
    }

    public List<WebElement> waitForElementList(AppiumDriver driver, String type, String path){
        List<WebElement> element=null;
        try {
            WebDriverWait webDriverWait =new WebDriverWait(driver,waitTime);
            if (type.equalsIgnoreCase("id")){
                element=webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(path)));
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in waitForElement"+ ex);
            return null;
        }

    }
}
