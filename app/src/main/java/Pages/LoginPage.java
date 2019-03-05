package Pages;

import android.os.Build;
import android.support.annotation.RequiresApi;
import commonfiles.variablePaths;
import commonfiles.wrapperClass;
import ScreenshotCapture.captureScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
    wrapperClass wrapperClass=new wrapperClass();
    captureScreen screen=new captureScreen();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean ValidateSigninPage(AndroidDriver<MobileElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.signIn, null);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.signIn, null);
            if (wrapperClass.webElementOperations(androidDriver, "isdisplayed","id",variablePaths.userName,variablePaths.userName_details)){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("SignInError");
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean validateSignInFunctionality(AndroidDriver<MobileElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.userName,variablePaths.userName_details);
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.password,variablePaths.password_details);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.signIn, null);
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.noThanks, null);
            if(wrapperClass.webElementOperations(androidDriver, "isdisplayed","id",variablePaths.noThanks, null)){
                screen.takeScreenshot("Nothanks");
                wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.noThanks, null);
                return true;
            }else{
                screen.takeScreenshot("SignInFuncFail");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("SignInFuncError");
            return false;
        }
    }
}
