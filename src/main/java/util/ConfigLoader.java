package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
	private static Properties prop=new Properties();
	private static boolean isLoaded=false;
	public static void loadConfig() {
		if(isLoaded) {
			return;
		}
		String path=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resources"+File.separator+"Global.properties";
		try(FileInputStream fis=new FileInputStream(path)) {
//			FileInputStream fis=new FileInputStream(path);
			prop.load(fis);
			isLoaded=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getProperty(String propertyName) {
		if(!isLoaded) {
			throw new IllegalStateException("Config file is not loaded yet");
		}
		return prop.getProperty(propertyName);
	}
	
	

}
