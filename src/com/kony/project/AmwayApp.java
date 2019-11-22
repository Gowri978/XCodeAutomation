package com.kony.project;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.kony.xcode.Constants;
import com.kony.xcode.XCodeUtils;

public class AmwayApp {
	public static void setCustomParams(Document document, Node nod,
			String nodeKeyName, String appNameLocales){

		// Adding the Files Details and Encoding based on Reference for KonyJS
		if (nodeKeyName.equals(Constants.KONYJS_FRAMEWORKS_BUILDPHASE_REF)) {
			XCodeUtils.addBuildFileRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
					Constants.SYSCONFIG_FW_BUILDACTIONREF2, nod);
			XCodeUtils.addBuildActionMaskRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
					nod);
		}
	
	
	}
}
