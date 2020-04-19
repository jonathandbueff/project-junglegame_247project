package utils;

import java.util.Locale;

public class SystemTypeChecker {

	public enum SystemType{
		Mac,
		Windows,
		Linux
	}
	
	public static SystemType getType() {
		String system = System.getProperty("os.name");
		system = system.toLowerCase(Locale.ENGLISH);
		if(system.contains("mac")) {
			return SystemType.Mac;
		}
		if(system.contains("windows")) {
			return SystemType.Windows;
		}
		else {
			return SystemType.Linux;
		}
		
	}
	
}
