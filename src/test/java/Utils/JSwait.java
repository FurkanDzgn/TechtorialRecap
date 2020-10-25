package Utils;

import org.openqa.selenium.JavascriptExecutor;

public class JSwait {

    public static void waitPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        while (!js.executeScript("return document.readyState").toString().equals("complete")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
