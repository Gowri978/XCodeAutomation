package com.kony.xcode;



public class MasterClass
{
  public static void main(String[] args)
    throws Throwable
  {
    String XcodeXmlPath = null;
    String ConfigXmlPath = null;
    String infoPlistConfigPath = null;
    String infoPlistXmlPath = null;
    String XcodeVersion = null;
    String enablePushNotifications = null;
    String entitlementsFile = null;
    String bundleKey = null;
    String region = null;
	String vizVersion = null;
	 
    infoPlistConfigPath = args[0];
    infoPlistXmlPath = args[1];
    XcodeXmlPath = args[2];
    ConfigXmlPath = args[3];
    XcodeVersion = args[4];
    enablePushNotifications = args[5];
    entitlementsFile = args[6];
    bundleKey = args[7];
    region = args[8];
	vizVersion			= args[9];
	
	 /* infoPlistConfigPath = "C:/Users/kit1012/Downloads/GIBBah/MeemBahrain/ci_config/Info.Plist_Config.json";
	//infoPlistXmlPath 	= "C:/Users/kit1012/Documents/banco-bolivariano-retail-banking/ci_config/InfoPlistXml.xml";
	XcodeXmlPath 		= "/Users/ncl/Desktop/mCoE/LAS/XCodeProps_AfterExtract.xml";
	ConfigXmlPath 		= "/Users/ncl/Desktop/mCoE/AutomationFiles/Config.properties";
	vizVersion 			= "V8";
	XcodeVersion 		= "10";
	enablePushNotifications="false";
	entitlementsFile 	= "test.entitlements";
	bundleKey			= "com.test.ipad";
	region				= "EIA_APAC";
	*/
   
    System.out.println("infoPlistConfigPath is :" + infoPlistConfigPath);
    System.out.println("infoPlistXmlPath is :" + infoPlistXmlPath);
    System.out.println("XcodeXmlPath is :" + XcodeXmlPath);
    System.out.println("ConfigXmlPath is :" + ConfigXmlPath);
    System.out.println("XcodeVersion is :" + XcodeVersion);
    System.out.println("enablePushNotifications is :" + enablePushNotifications);
    System.out.println("entitlementsFile is :" + entitlementsFile);
    System.out.println("bundlekey is :" + bundleKey);
    System.out.println("region is :" + region);
	System.out.println("vizVersion is :"+vizVersion);
    
    System.out.println("Calling xCodeAutomation function to modity XCode properties");
	if(vizVersion.equals("V8")) {
			XCodeSettingsAutomation.xCodeAutomationForViz8(XcodeXmlPath,ConfigXmlPath,XcodeVersion,enablePushNotifications,entitlementsFile, region);
	}
	else {
			XCodeSettingsAutomation.xCodeAutomation(XcodeXmlPath, ConfigXmlPath, XcodeVersion, enablePushNotifications, entitlementsFile, region);
	}
			
    System.out.println("Done with xcode settings and now calling InfoPlistConfigurator");
    InfoPlistConfigurator.getInfoPlistConfigs(infoPlistConfigPath, infoPlistXmlPath, bundleKey);
  }
}