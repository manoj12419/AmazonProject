package steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Temp {
//This class file used for temporary test practicing purpose
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Properties prop=new Properties();
		System.out.println(System.getProperty("user.dir"));
		String path="C:\\Users\\spata\\eclipse-workspace\\Manoj_Assignment\\src\\test\\resources\\project.properties";
		try {
			FileInputStream fs=new FileInputStream(path);
			prop.load(fs);
			String s=prop.getProperty("hamburgermenu_xpath");
			System.out.println("Iam here"+s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	

}
