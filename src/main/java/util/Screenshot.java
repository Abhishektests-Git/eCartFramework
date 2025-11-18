package util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	public static String TakeScreenshot(WebDriver driver,String fileName) throws IOException {
		TakesScreenshot sc=(TakesScreenshot)driver;
		File srcFile=sc.getScreenshotAs(OutputType.FILE);
		File path=new File(System.getProperty("user.dir")+File.separator+"screenshot");
		if(!path.exists()) {
			path.mkdirs();
		}
		File destFile=new File(path+File.separator+fileName+".png");
		FileUtils.copyFile(srcFile, destFile);
		return destFile.getAbsolutePath();
	}
}
