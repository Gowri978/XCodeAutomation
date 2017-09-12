package com.kony.xcode;


public class MasterClass {

	public static void main(String args[]) throws Throwable {
		String XcodeXmlPath = null; 
		String ConfigXmlPath = null;
		String infoPlistConfigPath = null; 
		String infoPlistXmlPath = null;
		String XcodeVersion = null;
		String enablePushNotifications = null;
		String entitlementsFile = null;
		String bundleKey=null;
		
		infoPlistConfigPath = args[0];//Info.Plist_Config.json
		infoPlistXmlPath 	= args[1];//Info.xml
		XcodeXmlPath 		= args[2];//XCode6Properties.xml
		ConfigXmlPath 		= args[3];//Config.properties
		XcodeVersion 		= args[4];// 7 or 8
		enablePushNotifications=args[5];// true or false
		entitlementsFile	= args[6];//test.entitlements
		bundleKey			= args[7];//bundle identifier for iPad

		/*infoPlistConfigPath = "G:/work/Projects/TOOLS/Info.Plist_Config.json";
		infoPlistXmlPath 	= "G:/work/Projects/TOOLS/InfoPlistXml.xml";
		XcodeXmlPath 		= "G:/work/Projects/TOOLS/XCode6Properties.xml";
		ConfigXmlPath 		= "G:/work/Projects/TOOLS/Config.properties";
		XcodeVersion 		= "8";
		enablePushNotifications="true";
		entitlementsFile 	= "test.entitlements";
		bundleKey			= "com.test.ipad";*/
		
		System.out.println("infoPlistConfigPath is :"+infoPlistConfigPath);
		System.out.println("infoPlistXmlPath is :"+infoPlistXmlPath);
		System.out.println("XcodeXmlPath is :"+XcodeXmlPath);
		System.out.println("ConfigXmlPath is :"+ConfigXmlPath);
		System.out.println("XcodeVersion is :"+XcodeVersion);
		System.out.println("enablePushNotifications is :"+enablePushNotifications);
		System.out.println("entitlementsFile is :"+entitlementsFile);
		System.out.println("bundlekey is :"+bundleKey);
		
		System.out.println("Calling xCodeAutomation function to modity XCode properties");
		XCodeSettingsAutomation.xCodeAutomation(XcodeXmlPath,ConfigXmlPath,XcodeVersion,enablePushNotifications,entitlementsFile);
		System.out.println("Done with xcode settings and now calling InfoPlistConfigurator");
		InfoPlistConfigurator.getInfoPlistConfigs(infoPlistConfigPath,infoPlistXmlPath,bundleKey);
	}
}
