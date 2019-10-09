package com.kony.project;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.kony.xcode.Constants;
import com.kony.xcode.XCodeUtils;

public class BusinessCenter {

	public static void setLocalizedStrings(Document document, Node nod,
			String nodeKeyName, String appNameLocales) {
		appNameLocales = appNameLocales.trim();
		//System.out.println("Inside setLocalizedStrings with appNameLocales::"+appNameLocales+"::");
		if(!"false".equalsIgnoreCase(appNameLocales)){
			String[] locales = appNameLocales.split(":");
			//System.out.println("Locales are present so set the Strings::"+locales.length+"::");
			if(locales.length > 0){
				// Adding the Files Details and Encoding based on Reference above
				if (nodeKeyName.equals(Constants.INFO_PLIST_STRINGS_BUILD_ACTION_REF)) {
					XCodeUtils.addBuildFileRef(document,Constants.INFO_PLIST_STRINGS_FILE_BUILD_REF,
							Constants.INFO_PLIST_STRINGS_FILE_REF, nod);
					XCodeUtils.addBuildActionMaskRef(document,Constants.INFO_PLIST_STRINGS_FILE_BUILD_REF, nod);
					XCodeUtils.addLocalizedFolders(document,Constants.INFO_PLIST_STRINGS_FILE_REF, nod, locales);

					for(int index=0; index < locales.length; index++){
						String locale = locales[index];
						locale += "_lproj"; 
						locale = locale.toUpperCase();
						XCodeUtils.addLocalizedStringsFileRef(document,
								locale, locales[index], nod);
					}
				}
				if(nodeKeyName.equals(Constants.PLUGIN_CUSTOM_TEMPLATE_REF)){
					XCodeUtils.addFolderRefasChild(document, Constants.INFO_PLIST_STRINGS_FILE_REF, nod);
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
