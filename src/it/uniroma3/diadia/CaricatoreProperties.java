package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CaricatoreProperties {
	private static Properties prop;

	public static int get(String parametro) {
		if(prop == null)
			creaFile();
		try {
			String valore = prop.getProperty(parametro);
			if(valore != null)
				return Integer.parseInt(valore);
		}catch(NumberFormatException e) {
			e.getStackTrace();
		}
		return 0;
	}

	public static void creaFile() {
		prop = new Properties();
		try (InputStream input =  CaricatoreProperties.class.getClassLoader().getResourceAsStream("diadia.properties")){	
			prop.load(input);
		}catch(FileNotFoundException e) {
			e.getStackTrace();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
