package Pages;

import android.os.Build;
import android.support.annotation.RequiresApi;

import commonfiles.variablePaths;
import commonfiles.wrapperClass;

import ScreenshotCapture.captureScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ProductPage {
    wrapperClass wrapperClass=new wrapperClass();
    captureScreen screen=new captureScreen();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean searchItem(AndroidDriver<MobileElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.searchBox, null);
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.searchBox_text, variablePaths.searchText);
            wrapperClass.webElementOperations(androidDriver, "randomclick","id",variablePaths.tvList, null);
            if (wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.buyNow, null)){
                screen.takeScreenshot("searchItemPass");
                return true;
            }else{
                screen.takeScreenshot("searchItemFail");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("searchItemError");
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean buyItem(AndroidDriver<MobileElement> androidDriver) throws Exception{
        try{
            variablePaths.tvNameText =wrapperClass.getText(androidDriver,"id",variablePaths.tvName);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.buyNow, null);
            if(wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.reviewBtn, null)){
                screen.takeScreenshot("BuyItemPass");
                return true;
            }else{
                screen.takeScreenshot("buyItemFail");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("buyItemError");
            return false;
        }
    }
}
