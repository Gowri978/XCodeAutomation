package com.kony.xcode;

import java.util.HashMap;

public class MasterClass {

	public static void main(String args[]) throws Throwable {
		String XcodeXmlPath = null; 
		String ConfigXmlPath = null;
		String infoPlistConfigPath = null; 
		String infoPlistXmlPath = null;
		
		infoPlistConfigPath = args[0];//"D:\\Citi Global\\Automation\\Info.Plist_Config.json"; //
		infoPlistXmlPath 	= args[1];//"D:\\Citi Global\\Automation\\Info.xml"; //
		XcodeXmlPath 		= args[2];//"D:\\Citi Global\\Automation\\XCode6Properties.xml"; //
		ConfigXmlPath 		= args[3];//"D:\\Citi Global\\Automation\\Config.properties"; //

/*		infoPlistConfigPath = "D:\\Citi Global\\Automation\\Info.Plist_Config.json"; //
		infoPlistXmlPath 	= "D:\\Citi Global\\Automation\\JanProdFiles\\12Jan\\InfoPlistXml.xml"; //
		XcodeXmlPath 		= "D:\\Citi Global\\Automation\\JanProdFiles\\12Jan\\XCode6Properties.xml"; //
		ConfigXmlPath 		= "D:\\Citi Global\\Automation\\Config.properties"; //
*/		
		System.out.println("infoPlistConfigPath is :"+infoPlistConfigPath);
		System.out.println("infoPlistXmlPath is :"+infoPlistXmlPath);
		System.out.println("XcodeXmlPath is :"+XcodeXmlPath);
		System.out.println("ConfigXmlPath is :"+ConfigXmlPath);
		
		System.out.println("Calling xCodeAutomation function to modity XCode properties");
		XCodeSettingsAutomation.xCodeAutomation(XcodeXmlPath,ConfigXmlPath);
		System.out.println("Done with xcode settings and now calling InfoPlistConfigurator");
		InfoPlistConfigurator.getInfoPlistConfigs(infoPlistConfigPath,infoPlistXmlPath);
	}
}
