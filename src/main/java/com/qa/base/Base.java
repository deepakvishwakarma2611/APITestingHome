package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

	public static Properties prop;

	public final int Status_Code_200 = 200;
	public final int Status_Code_201 = 201;
	public final int Status_Code_404 = 404;
	public final int Status_Code_500 = 500;

	public Base() {

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\configurations\\config.properties");

			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
