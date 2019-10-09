package com.kony.project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.kony.xcode.Constants;
import com.kony.xcode.XCodeUtils;

public class ResourceCenter {
	public static void setCustomParams(Document document, Node nod,
			String nodeKeyName, String appNameLocales){
		// Adding the File Reference of DTBAppDelegate.h & .m files to the
		// App Delegate Extension Folder

		// Adding the File Reference of DTBAppDelegate.h & .m files to the
		// App Delegate Extension Folder
		if (nodeKeyName.equals(Constants.APPDELEGATE_FOLDER)) {
			// System.out.println("inside APPDELEGATE_FOLDER....");
			Element dtbHString = document.createElement("string");
			Text dtbHStringValue = document
					.createTextNode(Constants.DTBAPPDELEGATE_H_REF);
			dtbHString.appendChild(dtbHStringValue);

			Element dtbMString = document.createElement("string");
			Text dtbMStringValue = document
					.createTextNode(Constants.DTBAPPDELEGATE_M_REF);
			dtbMString.appendChild(dtbMStringValue);
			Node dict = nod.getNextSibling();
			if ("dict".equalsIgnoreCase(dict.getNodeName())) {
				Node childrenKey = dict.getFirstChild();
				if ("key".equalsIgnoreCase(childrenKey.getNodeName())) {
					if ("children".equalsIgnoreCase(childrenKey
							.getFirstChild().getNodeValue())) {
						Node array = childrenKey.getNextSibling();
						if ("array".equalsIgnoreCase(array.getNodeName())) {
							array.appendChild(dtbMString);
							array.appendChild(dtbHString);
						}
					}
				}
			}
		}
		// Adding the Files Details and Encoding based on Reference above
		if (nodeKeyName.equals(Constants.KONYAPPDELEGATE_H_FILE)) {
			//Adding DTBAppDelegate.m file reference
			XCodeUtils.addNewFileRef(document, Constants.DTBAPPDELEGATE_M_FILE_NAME,
					Constants.DTBAPPDELEGATE_M_REF, nod, true);
			//Adding DTBAppDelegate.h file reference
			XCodeUtils.addNewFileRef(document, Constants.DTBAPPDELEGATE_H_FILE_NAME,
					Constants.DTBAPPDELEGATE_H_REF, nod, true);

			//Adding DTBAppDelegate.m file Target Membership for KRelease
			XCodeUtils.addBuildFileRef(document, Constants.DTBAPPDLG_M_BUILDREF_KRELEASE,
					Constants.DTBAPPDELEGATE_M_REF, nod);
			//Adding DTBAppDelegate.m file Target Membership for KonyJS
			XCodeUtils.addBuildFileRef(document, Constants.DTBAPPDLG_M_BUILDREF_KONYJS,
					Constants.DTBAPPDELEGATE_M_REF, nod);
			//Adding DTBAppDelegate.m file Target Membership for KProtected
			XCodeUtils.addBuildFileRef(document, Constants.DTBAPPDLG_M_BUILDREF_KPROTECTED,
					Constants.DTBAPPDELEGATE_M_REF, nod);
		}
		// Adding the Files in Build Phases for KRelease
		if (nodeKeyName.equals(Constants.DTBAPPDLG_M_BUILDACTREF_KRELEASE)) {
			XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KRELEASE, nod);
		}
		// Adding the Files for Build Phases for KonyJS
		if (nodeKeyName.equals(Constants.DTBAPPDLG_M_BUILDACTREF_KONYJS)) {
			XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KONYJS, nod);
		}
		// Adding the Files for Build Phases for KProtected
		if (nodeKeyName.equals(Constants.DTBAPPDLG_M_BUILDACTREF_KPROTECTED)) {
			XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KPROTECTED, nod);
		}

		// Adding the Files Details and Encoding based on Reference for KonyJS
		if (nodeKeyName.equals(Constants.SYSCONFIG_FW_BUILDACTIONREF_KONYJS)) {
			XCodeUtils.addBuildFileRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
					Constants.SYSCONFIG_FW_BUILDACTIONREF2, nod);
			XCodeUtils.addBuildActionMaskRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
					nod);
		}
	
	
	}
}
