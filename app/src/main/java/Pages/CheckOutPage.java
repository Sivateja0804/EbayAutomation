package Pages;

import android.os.Build;
import android.support.annotation.RequiresApi;

import commonfiles.variablePaths;
import commonfiles.wrapperClass;
import ScreenshotCapture.captureScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CheckOutPage {
    wrapperClass wrapperClass=new wrapperClass();
    captureScreen screen=new captureScreen();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean validationCheckoutPage(AndroidDriver<MobileElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.reviewBtn, null);
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.tvCheckoutName, null);
            screen.takeScreenshot("checkoutpage");
            variablePaths.tvCheckoutNameText =wrapperClass.getText(androidDriver,"id",variablePaths.tvCheckoutName);
            if (wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.closeBtn, null)){
                return true;
            }else{
                screen.takeScreenshot("checkoutFailure");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("checkoutException");
            return false;
        }
    }

    public boolean validateItemDetails(){
        if (variablePaths.tvNameText.equalsIgnoreCase(variablePaths.tvCheckoutNameText)){
            return true;
        }else{
            return  false;
        }
    }
}
