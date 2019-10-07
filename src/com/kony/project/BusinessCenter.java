package com.kony.project;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.kony.xcode.XCodeUtils;

public class BusinessCenter {
	public static final String INFO_PLIST_STRINGS_BUILD_ACTION_REF 	= "1D60588D0D05DD3D006BFB54";
	public static final String INFO_PLIST_STRINGS_FILE_BUILD_REF 	= "FD16AF0D234373A700F8B4DD";
	public static final String INFO_PLIST_STRINGS_FILE_REF 			= "FD16AF0F234373A700F8B4DD";
	public static final String PLUGIN_CUSTOM_TEMPLATE_REF 			= "29B97314FDCFA39411CA2CEA";

	public static void setLocalizedStrings(Document document, Node nod,
			String nodeKeyName, String appNameLocales) {
		appNameLocales = appNameLocales.trim();
		//System.out.println("Inside setLocalizedStrings with appNameLocales::"+appNameLocales+"::");
		if(!"false".equalsIgnoreCase(appNameLocales)){
			String[] locales = appNameLocales.split(":");
			//System.out.println("Locales are present so set the Strings::"+locales.length+"::");
			if(locales.length > 0){
				// Adding the Files Details and Encoding based on Reference above
				if (nodeKeyName.equals(INFO_PLIST_STRINGS_BUILD_ACTION_REF)) {
					XCodeUtils.addBuildFileRef(document,INFO_PLIST_STRINGS_FILE_BUILD_REF,
							INFO_PLIST_STRINGS_FILE_REF, nod);
					XCodeUtils.addBuildActionMaskRef(document,INFO_PLIST_STRINGS_FILE_BUILD_REF, nod);
					XCodeUtils.addLocalizedFolders(document,INFO_PLIST_STRINGS_FILE_REF, nod, locales);

					for(int index=0; index < locales.length; index++){
						String locale = locales[index];
						locale += "_lproj"; 
						locale = locale.toUpperCase();
						XCodeUtils.addLocalizedStringsFileRef(document,
								locale, locales[index], nod);
					}
				}
				if(nodeKeyName.equals(PLUGIN_CUSTOM_TEMPLATE_REF)){
					XCodeUtils.addFolderRefasChild(document, INFO_PLIST_STRINGS_FILE_REF, nod);
				}
			}
		}
	}
	public static void setCustomParams(Document document, Node nod,
			String nodeKeyName, String appNameLocales){
		setLocalizedStrings(document, nod,
				 nodeKeyName, appNameLocales);
	}
}
