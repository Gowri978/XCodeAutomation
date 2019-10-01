package com.kony.xcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XCodeSettingsAutomation {
	public static final String DTBAPPDELEGATE_M_REF = "A651E1C320A2E1CD00DC5DBC";
	public static final String DTBAPPDELEGATE_H_REF = "A651E1C220A2E15F00DC5DBC";
	public static final String DTBAPPDELEGATE_M_FILE_NAME = "DTBAppDelegate.m";
	public static final String DTBAPPDELEGATE_H_FILE_NAME = "DTBAppDelegate.h";
	public static final String APPDELEGATE_FOLDER = "38FEE1831BDFA666000739B1";
	public static final String DTBAPPDELEGATE_M_BUILDREF = "A651E1C420A2E1CE00DC5DBC";
	public static final String DTBAPPDELEGATE_M_BUILDREF1 = "6145E2C9222F25C7004E9172";
	public static final String DTBAPPDELEGATE_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String DTBAPPDELEGATE_M_BUILDACTIONREF1 = "0C1CB9F520DB764200226998";
	public static final String KONYJS_M_BUILDACTIONREF = "0C1CB9F820DB764200226998";
	public static final String KONYAPPDELEGATE_H_FILE = "38FEE1861BDFA666000739B1";
	public static final String TEALIUM_IOS_FW_REF = "A685ECE020A5F82800A22EB0";
	public static final String TEALIUM_IOS_FW_FILE_NAME = "TealiumIOS.framework";
	public static final String FRAMEWORKS_FOLDER = "7C2065F41C63396D00B7BEE7";
	public static final String TEALIUM_IOS_FW_FILE_REF = "A685ECE020A5F82800A22EB0";
	public static final String TEALIUM_IOS_FW_BUILDREF1 = "A685ECE220A5F82800A22EB0";
	public static final String TEALIUM_IOS_FW_BUILDREF2 = "A685ECE420A5F86900A22EB0";
	public static final String TEALIUM_IOS_FW_BUILDREF3 = "A685ECE520A5F86900A22EB0";
	public static final String TEALIUM_IOS_FW_BUILDACTIONREF1 = "1D60588F0D05DD3D006BFB54";
	public static final String TEALIUM_IOS_FW_BUILDACTIONREF2 = "3EE2236F1E5EF31F001AAB89";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_REF = "A685ECE120A5F82800A22EB0";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_FILE_NAME = "TealiumIOSLifecycle.framework";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_FILE_REF = "A685ECE120A5F82800A22EB0";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_BUILDREF1 = "A685ECE320A5F82800A22EB0";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_BUILDREF2 = "A685ECE620A5F86900A22EB0";
	public static final String TEALIUM_IOS_LIFECYCLE_FW_BUILDREF3 = "A685ECE720A5F86900A22EB0";

	public static final String KRELEASE_RELEASE_NODE = "1D6058950D05DD3E006BFB54";
	public static final String KRELEASE_DEBUG_NODE = "1D6058940D05DD3E006BFB54";
	public static final String KRELEASE_PDEBUG_NODE = "5EB24D111B6B7BA300369597";
	public static final String KRELEASE_PROTECTED_NODE = "5EB24D0B1B6B7B9800369597";
	public static final String KRELEASE_DISTRIBUTION_NODE = "CEAE36E712F01BB100DCFC0E";
	public static final String KONYJS_DEBUG_NODE = "0C1CBA0320DB764200226998";
	public static final String KONYJS_PDEBUG_NODE = "0C1CBA0420DB764200226998";
	public static final String KONYJS_RELEASE_NODE = "0C1CBA0520DB764200226998";
	public static final String KONYJS_PROTECTED_NODE = "0C1CBA0620DB764200226998";
	public static final String KONYJS_DISTRIBUTION_NODE = "0C1CBA0720DB764200226998";

	public static final String KPROTECTED_PROTECTED_NODE = "5EB24D841B6B7C0200369597";
	public static final String KPROTECTED_DISTRIBUTION_NODE = "5EB24D851B6B7C0200369597";

	public static final String SVPROGRESSHUD_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String SVPROGRESSHUD_M_BUILDACTIONREF1 = "61D292EF233CFE4400518346";
	public static String SVPROGRESSHUD_M_BUILDACTIONREF2 = "58725ABC53B1BBE93B84FCCD";
	public static String SVPROGRESSHUD_M_FILE_REF = "6151AFC922C4ADE400265F9B";
	public static String SVPROGRESSHUD_M_BUILDFILE_REF = "58725ABC99DB6D5AD8884A14";
	public static String SVPROGRESSHUD_M_BUILDFILE_REF1 = "58725ABC99DB6D5AD8884A14";
	public static final String SVPROGRESSHUD_M_FILE_NAME = "SVProgressHUD.m";

	public static String ZHUGE_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String ZHUGE_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String ZHUGE_M_FILE_NAME = "Zhuge.m";

	public static String ZHUGEBASE64_M_FILE_REF = "6151AFC722C4ADDE00265F9B";
	public static String ZHUGEBASE64_M_BUILDFILE_REF = "58725ABC457CCDEF69AF7D5B";
	public static final String ZHUGEBASE64_M_FILE_NAME = "ZhugeBase64.m";

	public static String ZHUGECONFIG_M_FILE_REF = "58725ABC6C09D59B2514EB2B";
	public static String ZHUGECONFIG_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String ZHUGECONFIG_M_FILE_NAME = "ZhugeConfig.m";

	public static final String SVRADIAL_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String SVRADIAL_M_BUILDACTIONREF1 = "61D292F1233CFE4A00518346";
	public static String SVRADIAL_M_BUILDACTIONREF2 = "58725ABC53B1BBE93B84FCCD";
	public static String SVRADIAL_M_FILE_REF = "6151AFC722C4ADDE00265F9B";
	public static String SVRADIAL_M_BUILDFILE_REF = "58725ABC457CCDEF69AF7D5B";
	public static final String SVRADIAL_M_FILE_NAME = "SVRadialGradientLayer.m";

	public static final String SVPROGRESSANIM_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String SVPROGRESSANIM_M_BUILDACTIONREF1 = "61D292F2233CFE4D00518346";
	public static String SVPROGRESSANIM_M_BUILDACTIONREF2 = "58725ABC53B1BBE93B84FCCD";
	public static String SVPROGRESSANIM_M_FILE_REF = "6151AFC822C4ADE100265F9B";
	public static String SVPROGRESSANIM_M_BUILDFILE_REF = "58725ABC2017F96A10E4028B";
	public static final String SVPROGRESSANIM_M_FILE_NAME = "SVProgressAnimatedView.m";

	public static final String SVINDEFINITE_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String SVINDEFINITE_M_BUILDACTIONREF1 = "61D292F3233CFE4F00518346";
	public static String SVINDEFINITE_M_BUILDACTIONREF2 = "58725ABC53B1BBE93B84FCCD";
	public static String SVINDEFINITE_M_FILE_REF = "6151AFCA22C4ADE600265F9B";
	public static String SVINDEFINITE_M_BUILDFILE_REF = "58725ABCB5E48944818BF6A4";
	public static final String SVINDEFINITE_M_FILE_NAME = "SVIndefiniteAnimatedView.m";

	public static final String LOGINBUNDLE_M_BUILDACTIONREF = "1D60588D0D05DD3D006BFB54";
	public static final String LOGINBUNDLE_M_BUILDACTIONREF1 = "A642B6B6228043DF007B0BFF";
	public static String LOGINBUNDLE_M_BUILDACTIONREF2 = "58725ABCB7566CAE56E5A15F";
	public static final String LOGIN_BUNDLE_FILE_NAME = "LoginCenterSource.bundle";

	public static final String SVPBUNDLE_M_BUILDACTIONREF = "1D60588D0D05DD3D006BFB54";
	public static final String SVPBUNDLE_M_BUILDACTIONREF1 = "61D292ED233CFD7A00518346";
	public static String SVPBUNDLE_M_BUILDACTIONREF2 = "58725ABC3777B74B11EBFA43";
	public static final String SVP_BUNDLE_FILE_NAME = "SVProgressHUD.bundle";
	
	public static final String ZHGM_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ZHGM_M_BUILDACTIONREF1 = "A66DE6E923435CA700A4AB58";
	public static String ZHGM_M_BUILDACTIONREF2 = "58725ABC7BF1CAFA48090477";
	public static final String ZHGM_M_FILE_NAME = "ZhuGeManager.m";
	
	public static final String LNM_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String LNM_M_BUILDACTIONREF1 = "A693E6E523436BCD00E41C29";
	public static String LNM_M_BUILDACTIONREF2 = "58725ABCC2F8D98A08EF61C6";
	public static final String LNM_M_FILE_NAME = "LocalNotificationManager.m";

	public static final String WEIBOBUNDLE_M_BUILDACTIONREF = "1D60588D0D05DD3D006BFB54";
	public static final String WEIBOBUNDLE_M_BUILDACTIONREF1 = "610DBBDA22DCA0F10071E424";
	public static String WEIBOBUNDLE_M_BUILDACTIONREF2 = "58725ABC46B7A913000C970E";
	public static final String WEIBO_BUNDLE_FILE_NAME = "WeiboSDK.bundle";

	public static final String SYSCONFIG_FW_BUILDACTIONREF = "1D60588F0D05DD3D006BFB54";
	public static final String SYSCONFIG_FW_BUILDACTIONREF1 = "610DBBE022DDDA1C0071E424";
	public static String SYSCONFIG_FW_BUILDACTIONREF2 = "58725ABCC8DB7F05D0841B7C";
	public static final String SYSCONFIG_FW_FILE_NAME = "SystemConfiguration.framework";

	public static final String CORETELEPHONY_FW_BUILDACTIONREF = "1D60588F0D05DD3D006BFB54";
	public static final String CORETELEPHONY_FW_BUILDACTIONREF1 = "610DBBE122DDDA220071E424";
	public static String CORETELEPHONY_FW_BUILDACTIONREF2 = "58725ABC3BFDC64DA7B8B930";
	public static final String CORETELEPHONY_FW_FILE_NAME = "CoreTelephony.framework";

	public static final String AMWAYLOGIN_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String AMWAYLOGIN_M_BUILDACTIONREF1 = "61C8A0B022F4567D00E1D3BF";
	public static String AMWAYLOGIN_M_BUILDACTIONREF2 = "58725ABC53B1BBE93B84FCCD";
	public static final String AMWAYLOGIN_BUNDLE_FILE_NAME = "FFIAmwayLogin.m";

	public static final String LOGINMANAGER_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String LOGINMANAGER_M_BUILDACTIONREF1 = "61C8A0B122F456BD00E1D3BF";
	public static String LOGINMANAGER_M_BUILDACTIONREF2 = "58725ABC57EF94E6802E0089";
	public static final String LOGINMANAGER_BUNDLE_FILE_NAME = "FFILoginManager.m";

	public static final String WXAPIMANAGER_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String WXAPIMANAGER_M_BUILDACTIONREF1 = "61C8A0B222F4571600E1D3BF";
	public static String WXAPIMANAGER_M_BUILDACTIONREF2 = "58725ABCA43038F987792A33";
	public static final String WXAPIMANAGER_BUNDLE_FILE_NAME = "WXAPIManager.m";

	public static final String ISSMSGOPERATOR_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ISSMSGOPERATOR_M_BUILDACTIONREF1 = "61C8A0B322F4575600E1D3BF";
	public static String ISSMSGOPERATOR_M_BUILDACTIONREF2 = "58725ABC4F7528935FD9EF42";
	public static final String ISSMSGOPERATOR_BUNDLE_FILE_NAME = "ISSMessageOperator.m";

	public static final String ISSMSGCENTER_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static String ISSMSGCENTER_M_BUILDACTIONREF1 = "61C8A0B422F457A100E1D3BF";
	public static String ISSMSGCENTER_M_BUILDACTIONREF2 = "58725ABC1819BBC6709B1E2B";
	public static final String ISSMSGCENTER_BUNDLE_FILE_NAME = "ISSmessageCenter.m";

	public static final String ISSMSG_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ISSMSG_M_BUILDACTIONREF1 = "61C8A0B522F457DD00E1D3BF";
	public static String ISSMSG_M_BUILDACTIONREF2 = "58725ABC56F924CC8DF38141";

	public static String ISSMSG_M_FILE_REF = "58725ABC56F924CC8DF38141";
	public static String ISSMSG_M_BUILDFILE_REF = "58725ABCD9066C3FBDFEA577";
	public static final String ISSMSG_BUNDLE_FILE_NAME = "ISSMessage.mm";

	public static final String MSGALERTVW_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String MSGALERTVW_M_BUILDACTIONREF1 = "61C8A0AE22F452CA00E1D3BF";
	public static String MSGALERTVW_M_BUILDACTIONREF2 = "58725ABCD6E035B53A341A2B";
	public static final String MSGALERTVW_BUNDLE_FILE_NAME = "MessageAlertView.m";

	public static final String ISSDESC_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ISSDESC_M_BUILDACTIONREF1 = "61C8A0B622F4581F00E1D3BF";
	public static String ISSDESC_M_BUILDACTIONREF2 = "58725ABC1EDBE3B496BE1FD2";
	public static final String ISSDESC_BUNDLE_FILE_NAME = "ISSDescription.m";

	public static final String ISSNWTOOL_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ISSNWTOOL_M_BUILDACTIONREF1 = "61C8A0B722F4586100E1D3BF";
	public static String ISSNWTOOL_M_BUILDACTIONREF2 = "58725ABCB1094625BD2730C5";
	public static final String ISSNWTOOL_BUNDLE_FILE_NAME = "ISSNetworkTool.m";

	public static final String ISSURL_M_BUILDACTIONREF = "1D60588E0D05DD3D006BFB54";
	public static final String ISSURL_M_BUILDACTIONREF1 = "61C8A0AF22F4560D00E1D3BF";
	public static String ISSURL_M_BUILDACTIONREF2 = "58725ABC79657FC16299630C";
	public static final String ISSURL_BUNDLE_FILE_NAME = "ISSURL.m";

	public static String UIMASADD_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String UIMASADD_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String UIMASADD_M_FILE_NAME = "View+MASAdditions.m";

	public static String MASVIEWCONS_M_FILE_REF = "6151AFC722C4ADDE00265F9B";
	public static String MASVIEWCONS_M_BUILDFILE_REF = "58725ABC457CCDEF69AF7D5B";
	public static final String MASVIEWCONS_M_FILE_NAME = "MASViewConstraint.m";

	public static String MASCONS_M_FILE_REF = "58725ABC6C09D59B2514EB2B";
	public static String MASCONS_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String MASCONS_M_FILE_NAME = "MASConstraint.m";

	public static String MASCONSMAKER_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String MASCONSMAKER_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String MASCONSMAKER_M_FILE_NAME = "MASConstraintMaker.m";

	public static String MASCOMPCONS_M_FILE_REF = "6151AFC722C4ADDE00265F9B";
	public static String MASCOMPCONS_M_BUILDFILE_REF = "58725ABC457CCDEF69AF7D5B";
	public static final String MASCOMPCONS_M_FILE_NAME = "MASCompositeConstraint.m";

	public static String JSONMODEL_M_FILE_REF = "58725ABC6C09D59B2514EB2B";
	public static String JSONMODEL_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String JSONMODEL_M_FILE_NAME = "JSONModel.m";

	public static String CDVAUTH_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String CDVAUTH_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String CDVAUTH_M_FILE_NAME = "CDVAuthorization.m";

	public static String CBROW_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String CBROW_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String CBROW_M_FILE_NAME = "CBrowserFFI.m";

	public static String PGNATIVE_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String PGNATIVE_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String PGNATIVE_M_FILE_NAME = "PGNative.m";

	public static String CDVBARSCAN_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String CDVBARSCAN_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String CDVBARSCAN_M_FILE_NAME = "CDVBarcodeScanner.m";

	public static String NSAC_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String NSAC_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String NSAC_M_FILE_NAME = "NSArray+Comparisons.m";

	public static String SCANVW_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String SCANVW_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String SCANVW_M_FILE_NAME = "ScanView.m";

	public static String QRCVC_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String QRCVC_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String QRCVC_M_FILE_NAME = "QRCodeViewController.m";

	public static String CDVC_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String CDVC_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String CDVC_M_FILE_NAME = "CDVCamera.m";

	public static String AHAUTHHELP_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String AHAUTHHELP_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String AHAUTHHELP_M_FILE_NAME = "AHAuthorizationHelper.m";

	public static String CDVJHW_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String CDVJHW_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String CDVJHW_M_FILE_NAME = "CDVJpegHeaderWriter.m";

	public static String NSDICTEX_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String NSDICTEX_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String NSDICTEX_M_FILE_NAME = "NSDictionary+Extensions.m";

	public static String NSDB_M_FILE_REF = "58725ABC58AF84786B7CFF95";
	public static String NSDB_M_BUILDFILE_REF = "58725ABC1C7DD5DA71A4374B";
	public static final String NSDB_M_FILE_NAME = "NSData+Base64.m";

	public static final String MJREFRESH_FILEREF_KONYJS = "0C1CB9F820DB764200226998";
	public static final String MJREFRESH_FILEREF_KRELEASE = "1D60588D0D05DD3D006BFB54";
	public static String MJREFRESH_BUILDFILE_REF = "58725ABC7867852FE76DF1FF";
	public static String MJREFRESH_FILE_REF = "58725ABC03000E7E50283E61";
	public static final String MJREFRESH_BUNDLE_FILE_NAME = "MJRefresh.bundle";

	// public static final String ASSETS_FILEREF_KONYJS =
	// "0C1CB9F820DB764200226998";
	// public static final String ASSETS_FILEREF_KRLEASE =
	// "1D60588D0D05DD3D006BFB54";
	public static String ASSETS_BUILDFILE_REF = "61C8A0BC22F4614B00E1D3BF";
	public static String ASSETS_FILE_REF = "58725ABC1917288CA11376E9";
	public static final String ASSETS_BUNDLE_FILE_NAME = "Assets.xcassets";

	public static final String JSMESSAGE_CENTER_FFI_FILE_NAME = "JSMessageCenterIOSMsgCenterFFIFFIClass.m";
	public static final String JSMESSAGE_CENTER_FFI_C_FILE_TYPE = "sourcecode.c.objc";
	public static final String JSMESSAGE_CENTER_FFI_CPP_FILE_TYPE = "sourcecode.cpp.objcpp";

	public static String APP_ID = "";
	public static final String BRIDGING_HEADER = "-Bridging-Header.h";

	public static void xCodeAutomation(String XcodeXmlPath,
			String ConfigXmlPath, String XcodeVersion, String capabilitiesList,
			String entitlementsFile, String region) {
		String configProperties = ConfigXmlPath;

		String enablePushNotifications = "false";
		String enableKeychainSharing = "false";

		String[] capList = capabilitiesList.split(":");
		for (int i = 0; i < capList.length; i++) {
			if ("push".equalsIgnoreCase(capList[i])) {
				enablePushNotifications = "true";
			}
			if ("keychainsharing".equalsIgnoreCase(capList[i])) {
				enableKeychainSharing = "true";
			}
		}
		System.out.println("Inside build settings");
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			FileInputStream Xcode = new FileInputStream(new File(XcodeXmlPath));
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(Xcode);
			XPath xPath = XPathFactory.newInstance().newXPath();
			Properties properties = new Properties();
			FileInputStream configFile = new FileInputStream(new File(
					configProperties));
			properties.load(configFile);

			String expression = "//key";

			NodeList nodeKeyList = (NodeList) xPath.compile(expression)
					.evaluate(document, XPathConstants.NODESET);
			String nodeKeyName = "";
			String mainGroupValue = "";
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE) {
					Node keyNode = nod.getFirstChild();
					nodeKeyName = keyNode.getNodeValue();
					System.out.println("nodeKeyName---> " + nodeKeyName);
					if (nodeKeyName.equals("OTHER_LDFLAGS")) {
						Element archkey = document.createElement("key");
						Text archKeyValue = document
								.createTextNode("ONLY_ACTIVE_ARCH");
						archkey.appendChild(archKeyValue);

						Element archString = document.createElement("string");

						Text archStringValue = document
								.createTextNode(properties
										.getProperty("ONLY_ACTIVE_ARCH"));
						archString.appendChild(archStringValue);

						Element cflagKey = document.createElement("key");
						Text cflagKeyValue = document
								.createTextNode("OTHER_CFLAGS");
						cflagKey.appendChild(cflagKeyValue);

						Element cflagString = document.createElement("string");

						Text cflagStringValue = document
								.createTextNode(properties
										.getProperty("OTHER_CFLAGS"));
						cflagString.appendChild(cflagStringValue);

						Node parentNode = nod.getParentNode();
						parentNode.appendChild(archkey);
						parentNode.appendChild(archString);
					}
					int version = Integer.parseInt(XcodeVersion);
					if (version >= 8) {
						if (nodeKeyName.equals("TargetAttributes")) {
							System.out
									.println("Creating node for ProvisioningStyle ");
							Node dictNode = nod.getNextSibling();
							Element cflagKey = document.createElement("key");
							Text cflagKeyValue = document
									.createTextNode("1D6058900D05DD3D006BFB54");
							cflagKey.appendChild(cflagKeyValue);

							Element dictKey = document.createElement("key");
							Text dictKeyTxt = document
									.createTextNode("ProvisioningStyle");
							dictKey.appendChild(dictKeyTxt);

							Element dictKeyValue = document
									.createElement("string");
							Text dictKeyValueTxt = document
									.createTextNode("Manual");
							dictKeyValue.appendChild(dictKeyValueTxt);

							Element dictElement = document
									.createElement("dict");
							dictElement.appendChild(dictKey);
							dictElement.appendChild(dictKeyValue);
							if ("true"
									.equalsIgnoreCase(enablePushNotifications)) {
								System.out
										.println("Creating node for Push Notifications ");
								Element capabilitiesKey = document
										.createElement("key");
								Text capabilitiesKeyTxt = document
										.createTextNode("SystemCapabilities");
								capabilitiesKey.appendChild(capabilitiesKeyTxt);

								Element pushKey = document.createElement("key");
								Text pushKeyTxt = document
										.createTextNode("com.apple.Push");
								pushKey.appendChild(pushKeyTxt);

								Element enableKey = document
										.createElement("key");
								Text enableKeyTxt = document
										.createTextNode("enabled");
								enableKey.appendChild(enableKeyTxt);

								Element enableKeyValue = document
										.createElement("string");
								Text enableKeyValueTxt = document
										.createTextNode("1");
								enableKeyValue.appendChild(enableKeyValueTxt);

								Element dictPushElement = document
										.createElement("dict");
								dictPushElement.appendChild(enableKey);
								dictPushElement.appendChild(enableKeyValue);

								Element dictCapabilitiesElement = document
										.createElement("dict");
								dictCapabilitiesElement.appendChild(pushKey);
								dictCapabilitiesElement
										.appendChild(dictPushElement);

								dictElement.appendChild(capabilitiesKey);
								dictElement
										.appendChild(dictCapabilitiesElement);
							}
							if ("true".equalsIgnoreCase(enableKeychainSharing)) {
								System.out
										.println("Creating node for Keychain Sharing ");
								Element capabilitiesKey = document
										.createElement("key");
								Text capabilitiesKeyTxt = document
										.createTextNode("SystemCapabilities");
								capabilitiesKey.appendChild(capabilitiesKeyTxt);

								Element pushKey = document.createElement("key");
								Text pushKeyTxt = document
										.createTextNode("com.apple.Keychain");
								pushKey.appendChild(pushKeyTxt);

								Element enableKey = document
										.createElement("key");
								Text enableKeyTxt = document
										.createTextNode("enabled");
								enableKey.appendChild(enableKeyTxt);

								Element enableKeyValue = document
										.createElement("string");
								Text enableKeyValueTxt = document
										.createTextNode("1");
								enableKeyValue.appendChild(enableKeyValueTxt);

								Element dictPushElement = document
										.createElement("dict");
								dictPushElement.appendChild(enableKey);
								dictPushElement.appendChild(enableKeyValue);

								Element dictCapabilitiesElement = document
										.createElement("dict");
								dictCapabilitiesElement.appendChild(pushKey);
								dictCapabilitiesElement
										.appendChild(dictPushElement);

								dictElement.appendChild(capabilitiesKey);
								dictElement
										.appendChild(dictCapabilitiesElement);
							}
							dictNode.appendChild(cflagKey);
							dictNode.appendChild(dictElement);
						}
						if ("true".equalsIgnoreCase(enablePushNotifications)) {
							if (nodeKeyName.equals("objects")) {
								System.out
										.println("Creating node for Entitlements reference ");
								Node dictNode = nod.getNextSibling();
								Element entitlementRefKey = document
										.createElement("key");
								Text entitlementRefKeyTxt = document
										.createTextNode("26E284CF1E647A51005A33DA");
								entitlementRefKey
										.appendChild(entitlementRefKeyTxt);

								dictNode.appendChild(entitlementRefKey);
								dictNode.appendChild(addEntitlementRef(
										document, entitlementsFile));
							}
							if (nodeKeyName.equals("mainGroup")) {
								if (nod.getNextSibling().getNodeName()
										.equals("string")) {
									mainGroupValue = nod.getNextSibling()
											.getTextContent();
									System.out
											.println("Getting mainGroup node value ::: "
													+ mainGroupValue);
								}
							}
							if ((mainGroupValue != "")
									&& (nodeKeyName.equals(mainGroupValue))) {
								Node arrayNode = nod.getNextSibling()
										.getFirstChild().getNextSibling();
								Element string = document
										.createElement("string");
								Text stringValue = document
										.createTextNode("26E284CF1E647A51005A33DA");
								string.appendChild(stringValue);
								arrayNode.appendChild(string);
							}
						}
					}
					setRegionSpecificSettings(document, nod, nodeKeyName,
							region);

					setBuildSettingsParameters(document, nod, nodeKeyName,
							properties, false);
				}
			}
			System.out.println("Completed modification. Writing back file");
			OutputStream fileOutputStream = new FileOutputStream(XcodeXmlPath);
			Transformer transformer;
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DocumentType doctype = document.getDoctype();
			if (doctype != null) {
				transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
						doctype.getPublicId());
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
						doctype.getSystemId());
			}
			OutputStreamWriter fileOutput = new OutputStreamWriter(
					fileOutputStream, "UTF-8");
			Result fileUpdate = (Result) new StreamResult(fileOutput);
			transformer.transform(new DOMSource(document), fileUpdate);
			fileOutputStream.close();
			fileOutput.close();
		} catch (IOException ioe) {
			System.out
					.println("Exception while reading/updating properties file: "
							+ ioe);
			ioe.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @return
	 * @return
	 */

	public static void xCodeAutomationForViz8(String XcodeXmlPath,
			String ConfigXmlPath, String XcodeVersion, String capabilitiesList,
			String entitlementsFile, String region) {

		String configProperties = ConfigXmlPath;
		String enablePushNotifications = "false";
		String enableKeychainSharing = "false";

		String[] capList = capabilitiesList.split(":");
		for (int i = 0; i < capList.length; i++) {
			if ("push".equalsIgnoreCase(capList[i])) {
				enablePushNotifications = "true";
			}
			if ("keychainsharing".equalsIgnoreCase(capList[i])) {
				enableKeychainSharing = "true";
			}
		}
		System.out.println("Inside build settings xCodeAutomationForViz8:");
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			FileInputStream XcodeXML = new FileInputStream(new File(
					XcodeXmlPath));
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(XcodeXML);
			XPath xPath = XPathFactory.newInstance().newXPath();
			Properties properties = new java.util.Properties();
			FileInputStream configFile = new FileInputStream(new File(
					configProperties));
			properties.load(configFile);
			// HashMap nodeData = new HashMap();

			String expression = "//key";
			// String expressionValue = "//string";
			NodeList nodeKeyList = (NodeList) xPath.compile(expression)
					.evaluate(document, XPathConstants.NODESET);
			String nodeKeyName = "";
			String mainGroupValue = "";
			String pNode = "";
			String cNode = "";
			boolean kReleaseNodeFound = false;
			boolean konyJSNodeFound = false;
			boolean isTargetBuildSettingsFound = false;
			boolean isProvisioningStyleAdded = false;
			boolean kProtectedNodeFound = false;

			getFileReferencesforChinaApp(nodeKeyList, region);
			// String nodeStringValue = "";
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE)
					nodeKeyName = nod.getFirstChild().getNodeValue();
				// nodeStringValue = nod.getNextSibling().getNodeValue();

				// Check for all KRelease Nodes
				if (nodeKeyName.equals(KRELEASE_RELEASE_NODE)) {
					/*
					 * || nodeKeyName.equals(KRELEASE_PDEBUG_NODE) ||
					 * nodeKeyName.equals(KRELEASE_DEBUG_NODE) ||
					 * nodeKeyName.equals(KRELEASE_PROTECTED_NODE) ||
					 * nodeKeyName.equals(KRELEASE_DISTRIBUTION_NODE)) {
					 */
					isProvisioningStyleAdded = false;
					kReleaseNodeFound = true;
					konyJSNodeFound = false;
					kProtectedNodeFound = false;
				}
				// Check for KProtected Node
				if (nodeKeyName.equals(KPROTECTED_PROTECTED_NODE)) {
					isProvisioningStyleAdded = false;
					kReleaseNodeFound = false;
					konyJSNodeFound = false;
					kProtectedNodeFound = true;
				}
				// Check for all KonyJS Nodes
				if (nodeKeyName.equals(KONYJS_DEBUG_NODE)
						|| nodeKeyName.equals(KONYJS_PDEBUG_NODE)
						|| nodeKeyName.equals(KONYJS_RELEASE_NODE)
						|| nodeKeyName.equals(KONYJS_PROTECTED_NODE)
						|| nodeKeyName.equals(KONYJS_DISTRIBUTION_NODE)) {
					konyJSNodeFound = true;
					kReleaseNodeFound = false;
					isProvisioningStyleAdded = false;
					kProtectedNodeFound = false;
				}

				// Set Flag if node is buildSettings
				if (nodeKeyName.equals("buildSettings")) {
					isTargetBuildSettingsFound = true;
				}

				// Node after KRelease to reset the flags
				if (nodeKeyName.equals("1D6058960D05DD3E006BFB54")) {
					isTargetBuildSettingsFound = false;
					kReleaseNodeFound = false;
					konyJSNodeFound = false;
					isProvisioningStyleAdded = false;
					kProtectedNodeFound = false;
				}
				if (nodeKeyName.equals(KPROTECTED_DISTRIBUTION_NODE)) {
					isTargetBuildSettingsFound = false;
					kReleaseNodeFound = false;
					konyJSNodeFound = false;
					isProvisioningStyleAdded = false;
					kProtectedNodeFound = false;
				}
				// Check if KRelease Build Settings Node is found and set the
				// Parameters
				if ((isTargetBuildSettingsFound)
						&& ((kReleaseNodeFound) || (kProtectedNodeFound))) {
					// Setting Code Signing Style to Manual
					if (nodeKeyName.equals("CODE_SIGN_RESOURCE_RULES_PATH")) {
						pNode = nod.getNextSibling().getNextSibling()
								.getFirstChild().getNodeValue();
						if (!pNode.equals("CODE_SIGN_STYLE")) {

							Element key = document.createElement("key");
							Text keyValue = document
									.createTextNode("CODE_SIGN_STYLE");
							key.appendChild(keyValue);
							Element string = document.createElement("string");
							Text stringValue = document
									.createTextNode("Manual");
							string.appendChild(stringValue);
							Node parentNode = nod.getParentNode();
							parentNode.appendChild(key);
							parentNode.appendChild(string);
						} else {
							if (pNode.equals("CODE_SIGN_STYLE")) {
								if (nod.getNextSibling().getNextSibling()
										.getNextSibling().getFirstChild() == null) {
									Text stringValue = document
											.createTextNode("Manual");
									nod.getNextSibling().getNextSibling()
											.getNextSibling()
											.appendChild(stringValue);
								} else {
									cNode = nod.getNextSibling()
											.getNextSibling().getNextSibling()
											.getFirstChild().getNodeValue();
									if (cNode != null)
										nod.getNextSibling().getNextSibling()
												.getNextSibling()
												.getFirstChild()
												.setNodeValue("Manual");
								}
							}
						}
					}
					// Adding Code Signing Style to Manual
					if (nodeKeyName.equals("CODE_SIGN_STYLE")) {
						if (nod.getNextSibling().getFirstChild() == null) {
							Text stringValue = document
									.createTextNode("Manual");
							nod.getNextSibling().appendChild(stringValue);
						} else {
							cNode = nod.getNextSibling().getFirstChild()
									.getNodeValue();
							if (cNode != null)
								nod.getNextSibling().getFirstChild()
										.setNodeValue("Manual");
						}
					}
					// Setting Provisioning Profile from Properties
					if (nodeKeyName
							.equals("PROVISIONING_PROFILE[sdk=iphoneos*]")) {
						pNode = nod.getNextSibling().getNextSibling()
								.getFirstChild().getNodeValue();
						if (!pNode.equals("PROVISIONING_PROFILE_SPECIFIER")) {

							Element key = document.createElement("key");
							Text keyValue = document
									.createTextNode("PROVISIONING_PROFILE_SPECIFIER");
							key.appendChild(keyValue);
							Element string = document.createElement("string");
							// Text stringValue =
							// document.createTextNode("NewComKoneContainerappProfile");
							Text stringValue = document
									.createTextNode(properties
											.getProperty("PROVISIONING_PROFILE_NAME"));
							string.appendChild(stringValue);
							Node parentNode = nod.getParentNode();
							parentNode.appendChild(key);
							parentNode.appendChild(string);
						} else {
							if (pNode.equals("PROVISIONING_PROFILE_SPECIFIER")) {
								if (nod.getNextSibling().getNextSibling()
										.getNextSibling().getFirstChild() == null) {
									Text stringValue = document
											.createTextNode(properties
													.getProperty("PROVISIONING_PROFILE_NAME"));
									nod.getNextSibling().getNextSibling()
											.getNextSibling()
											.appendChild(stringValue);
								} else {
									cNode = nod.getNextSibling()
											.getNextSibling().getNextSibling()
											.getFirstChild().getNodeValue();
									if (cNode != null && cNode == "")
										nod.getNextSibling()
												.getNextSibling()
												.getNextSibling()
												.getFirstChild()
												.setNodeValue(
														properties
																.getProperty("PROVISIONING_PROFILE_NAME"));
								}
							}
						}
					}
					// Set Build Settings parameters for KRelease
					setBuildSettingsParameters(document, nod, nodeKeyName,
							properties, false);
				}
				// Set Build Settings parameters for KonyJS
				if (isTargetBuildSettingsFound && konyJSNodeFound) {
					/*
					 * if("PRODUCT_NAME".equals(nodeKeyName)){ Node name =
					 * nod.getNextSibling(); String nameString =
					 * name.getTextContent();
					 * 
					 * if(nameString.indexOf("$") < 0){ APP_ID = nameString;
					 * System.out.println("APP_ID::"+APP_ID); } }
					 */
					setBuildSettingsParameters(document, nod, nodeKeyName,
							properties, true);
				}
				// Setting Region Specific Settings like adding extra files &
				// settings
				setRegionSpecificSettings(document, nod, nodeKeyName, region);

				int version = Integer.parseInt(XcodeVersion);
				if (version >= 8) {
					if (nodeKeyName.equals("TargetAttributes")
							&& !isProvisioningStyleAdded) {
						System.out
								.println("Creating node for ProvisioningStyle ");
						Node dictNode = nod.getNextSibling();

						Element cflagKey = document.createElement("key");
						Text cflagKeyValue = document
								.createTextNode("1D6058900D05DD3D006BFB54");
						cflagKey.appendChild(cflagKeyValue);

						Element dictKey = document.createElement("key");
						Text dictKeyTxt = document
								.createTextNode("ProvisioningStyle");
						dictKey.appendChild(dictKeyTxt);

						Element dictKeyValue = document.createElement("string");
						Text dictKeyValueTxt = document
								.createTextNode("Manual");
						dictKeyValue.appendChild(dictKeyValueTxt);

						Element dictElement = document.createElement("dict");
						dictElement.appendChild(dictKey);
						dictElement.appendChild(dictKeyValue);
						if ("true".equalsIgnoreCase(enablePushNotifications)) {
							System.out
									.println("Creating node for Push Notifications ");
							Element capabilitiesKey = document
									.createElement("key");
							Text capabilitiesKeyTxt = document
									.createTextNode("SystemCapabilities");
							capabilitiesKey.appendChild(capabilitiesKeyTxt);

							Element pushKey = document.createElement("key");
							Text pushKeyTxt = document
									.createTextNode("com.apple.Push");
							pushKey.appendChild(pushKeyTxt);

							Element enableKey = document.createElement("key");
							Text enableKeyTxt = document
									.createTextNode("enabled");
							enableKey.appendChild(enableKeyTxt);

							Element enableKeyValue = document
									.createElement("string");
							Text enableKeyValueTxt = document
									.createTextNode("1");
							enableKeyValue.appendChild(enableKeyValueTxt);

							Element dictPushElement = document
									.createElement("dict");
							dictPushElement.appendChild(enableKey);
							dictPushElement.appendChild(enableKeyValue);

							Element dictCapabilitiesElement = document
									.createElement("dict");
							dictCapabilitiesElement.appendChild(pushKey);
							dictCapabilitiesElement
									.appendChild(dictPushElement);

							dictElement.appendChild(capabilitiesKey);
							dictElement.appendChild(dictCapabilitiesElement);
						}
						if ("true".equalsIgnoreCase(enableKeychainSharing)) {
							System.out
									.println("Creating node for Keychain Sharing ");
							Element capabilitiesKey = document
									.createElement("key");
							Text capabilitiesKeyTxt = document
									.createTextNode("SystemCapabilities");
							capabilitiesKey.appendChild(capabilitiesKeyTxt);

							Element pushKey = document.createElement("key");
							Text pushKeyTxt = document
									.createTextNode("com.apple.Keychain");
							pushKey.appendChild(pushKeyTxt);

							Element enableKey = document.createElement("key");
							Text enableKeyTxt = document
									.createTextNode("enabled");
							enableKey.appendChild(enableKeyTxt);

							Element enableKeyValue = document
									.createElement("string");
							Text enableKeyValueTxt = document
									.createTextNode("1");
							enableKeyValue.appendChild(enableKeyValueTxt);

							Element dictPushElement = document
									.createElement("dict");
							dictPushElement.appendChild(enableKey);
							dictPushElement.appendChild(enableKeyValue);

							Element dictCapabilitiesElement = document
									.createElement("dict");
							dictCapabilitiesElement.appendChild(pushKey);
							dictCapabilitiesElement
									.appendChild(dictPushElement);

							dictElement.appendChild(capabilitiesKey);
							dictElement.appendChild(dictCapabilitiesElement);
						}
						dictNode.appendChild(cflagKey);
						dictNode.appendChild(dictElement);
						isProvisioningStyleAdded = true;
					}
					if ("true".equalsIgnoreCase(enablePushNotifications)
							|| "true".equalsIgnoreCase(enableKeychainSharing)) {
						if (nodeKeyName.equals("objects")) {
							System.out
									.println("Creating node for Entitlements reference ");
							Node dictNode = nod.getNextSibling();
							Element entitlementRefKey = document
									.createElement("key");
							Text entitlementRefKeyTxt = document
									.createTextNode("26E284CF1E647A51005A33DA");
							entitlementRefKey.appendChild(entitlementRefKeyTxt);

							dictNode.appendChild(entitlementRefKey);
							dictNode.appendChild(addEntitlementRef(document,
									entitlementsFile));
						}
						if (nodeKeyName.equals("mainGroup")) {
							if (nod.getNextSibling().getNodeName()
									.equals("string")) {
								mainGroupValue = nod.getNextSibling()
										.getTextContent();
								System.out
										.println("Getting mainGroup node value ::: "
												+ mainGroupValue);
							}
						}
						if ((mainGroupValue != "")
								&& (nodeKeyName.equals(mainGroupValue))) {
							Node arrayNode = nod.getNextSibling()
									.getFirstChild().getNextSibling();
							Element string = document.createElement("string");
							Text stringValue = document
									.createTextNode("26E284CF1E647A51005A33DA");
							string.appendChild(stringValue);
							arrayNode.appendChild(string);
						}
					}
				}
			}

			System.out.println("Completed modification. Writing back file");
			OutputStream fileOutputStream = new FileOutputStream(XcodeXmlPath);
			Transformer transformer;
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DocumentType doctype = document.getDoctype();
			if (doctype != null) {
				transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
						doctype.getPublicId());
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
						doctype.getSystemId());
			}
			OutputStreamWriter fileOutput = new OutputStreamWriter(
					fileOutputStream, "UTF-8");
			Result fileUpdate = (Result) new StreamResult(fileOutput);
			transformer.transform(new DOMSource(document), fileUpdate);
			fileOutputStream.close();
			fileOutput.close();
		} catch (IOException ioe) {
			System.out
					.println("Exception while reading/updating properties file: "
							+ ioe);
			ioe.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static Element addBuildFileRef(Document document,
			String buildFileRef, String fileRef, Node node) {
		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(buildFileRef);
		refKey.appendChild(refKeyTxt);

		Element refKey0 = document.createElement("key");
		Text refKey0Txt = document.createTextNode("fileRef");
		refKey0.appendChild(refKey0Txt);

		Element refKey0Value = document.createElement("string");
		Text refKey0ValueTxt = document.createTextNode(fileRef);
		refKey0Value.appendChild(refKey0ValueTxt);

		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXBuildFile");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element dictEntitlementRefElement = document.createElement("dict");
		dictEntitlementRefElement.appendChild(refKey0);
		dictEntitlementRefElement.appendChild(refKey0Value);

		dictEntitlementRefElement.appendChild(refKey1);
		dictEntitlementRefElement.appendChild(refKey1Value);

		node.getParentNode().insertBefore(refKey, node);
		node.getParentNode().insertBefore(dictEntitlementRefElement, node);
		return refKey;
	}

	public static Element addBuildFileRefWithComplierFlags(Document document,
			String buildFileRef, String fileRef, Node node) {
		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(buildFileRef);
		refKey.appendChild(refKeyTxt);

		Element refKey0 = document.createElement("key");
		Text refKey0Txt = document.createTextNode("fileRef");
		refKey0.appendChild(refKey0Txt);

		Element refKey0Value = document.createElement("string");
		Text refKey0ValueTxt = document.createTextNode(fileRef);
		refKey0Value.appendChild(refKey0ValueTxt);

		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXBuildFile");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element refKey2 = document.createElement("key");
		Text refKey2Txt = document.createTextNode("settings");
		refKey2.appendChild(refKey2Txt);

		Element dictEntitlementRefElement1 = document.createElement("dict");

		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("COMPILER_FLAGS");
		refKey3.appendChild(refKey3Txt);

		Element refKey3Value = document.createElement("string");
		Text refKey3ValueTxt = document.createTextNode("-fobjc-arc");
		refKey3Value.appendChild(refKey3ValueTxt);

		dictEntitlementRefElement1.appendChild(refKey3);
		dictEntitlementRefElement1.appendChild(refKey3Value);

		Element dictEntitlementRefElement = document.createElement("dict");
		dictEntitlementRefElement.appendChild(refKey0);
		dictEntitlementRefElement.appendChild(refKey0Value);

		dictEntitlementRefElement.appendChild(refKey1);
		dictEntitlementRefElement.appendChild(refKey1Value);

		dictEntitlementRefElement.appendChild(refKey2);
		dictEntitlementRefElement.appendChild(dictEntitlementRefElement1);

		node.getParentNode().insertBefore(refKey, node);
		node.getParentNode().insertBefore(dictEntitlementRefElement, node);
		return refKey;
	}

	public static Element addBuildAttibutesFileRef(Document document,
			String buildFileRef, String fileRef, Node node) {
		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(buildFileRef);
		refKey.appendChild(refKeyTxt);

		Element refKey0 = document.createElement("key");
		Text refKey0Txt = document.createTextNode("fileRef");
		refKey0.appendChild(refKey0Txt);

		Element refKey0Value = document.createElement("string");
		Text refKey0ValueTxt = document.createTextNode(fileRef);
		refKey0Value.appendChild(refKey0ValueTxt);

		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXBuildFile");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element refKey2 = document.createElement("key");
		Text refKey2Txt = document.createTextNode("settings");
		refKey2.appendChild(refKey2Txt);

		Element dictAttributes = document.createElement("dict");
		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("ATTRIBUTES");
		refKey3.appendChild(refKey3Txt);

		Element arrayAttrib = document.createElement("array");
		Element refKey4Value = document.createElement("string");
		Text refKey4ValueTxt = document.createTextNode("CodeSignOnCopy");
		refKey4Value.appendChild(refKey4ValueTxt);

		Element refKey5Value = document.createElement("string");
		Text refKey5ValueTxt = document.createTextNode("RemoveHeadersOnCopy");
		refKey5Value.appendChild(refKey5ValueTxt);

		arrayAttrib.appendChild(refKey4Value);
		arrayAttrib.appendChild(refKey5Value);

		dictAttributes.appendChild(refKey3);
		dictAttributes.appendChild(arrayAttrib);

		Element dictRefElement = document.createElement("dict");
		dictRefElement.appendChild(refKey0);
		dictRefElement.appendChild(refKey0Value);

		dictRefElement.appendChild(refKey1);
		dictRefElement.appendChild(refKey1Value);

		dictRefElement.appendChild(refKey2);
		dictRefElement.appendChild(dictAttributes);

		node.getParentNode().insertBefore(refKey, node);
		node.getParentNode().insertBefore(dictRefElement, node);
		return refKey;
	}

	public static void addNewFileRef(Document document, String fileName,
			String fileRef, Node parentNode, boolean isFileEncoding) {
		Element dictEntitlementRefElement = document.createElement("dict");

		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(fileRef);
		refKey.appendChild(refKeyTxt);
		if (isFileEncoding) {
			Element refKey0 = document.createElement("key");
			Text refKey0Txt = document.createTextNode("fileEncoding");
			refKey0.appendChild(refKey0Txt);

			Element refKey0Value = document.createElement("string");
			Text refKey0ValueTxt = document.createTextNode("4");
			refKey0Value.appendChild(refKey0ValueTxt);

			dictEntitlementRefElement.appendChild(refKey0);
			dictEntitlementRefElement.appendChild(refKey0Value);
		}
		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXFileReference");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element refKey2 = document.createElement("key");
		Text refKey2Txt = document.createTextNode("lastKnownFileType");
		refKey2.appendChild(refKey2Txt);

		Element refKey2Value = document.createElement("string");
		String textNode = "";
		if (fileName.endsWith(".m")) {
			textNode = "sourcecode.c.objc";
		} else if (fileName.endsWith(".h")) {
			textNode = "sourcecode.c.h";
		} else if (fileName.endsWith(".framework")) {
			textNode = "wrapper.framework";
		} else if (fileName.endsWith(".a")) {
			textNode = "archive.ar";
		} else if (fileName.endsWith(".a")) {
			textNode = "Resource";
		}
		Text refKey2ValueTxt = document.createTextNode(textNode);
		refKey2Value.appendChild(refKey2ValueTxt);

		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("path");
		refKey3.appendChild(refKey3Txt);

		Element refKey3Value = document.createElement("string");
		Text refKey3ValueTxt = document.createTextNode(fileName);
		refKey3Value.appendChild(refKey3ValueTxt);

		Element refKey4 = document.createElement("key");
		Text refKey4Txt = document.createTextNode("sourceTree");
		refKey4.appendChild(refKey4Txt);

		Element refKey4Value = document.createElement("string");
		Text refKey4ValueTxt = document.createTextNode("<group>");
		refKey4Value.appendChild(refKey4ValueTxt);

		dictEntitlementRefElement.appendChild(refKey1);
		dictEntitlementRefElement.appendChild(refKey1Value);
		dictEntitlementRefElement.appendChild(refKey2);
		dictEntitlementRefElement.appendChild(refKey2Value);
		dictEntitlementRefElement.appendChild(refKey3);
		dictEntitlementRefElement.appendChild(refKey3Value);
		dictEntitlementRefElement.appendChild(refKey4);
		dictEntitlementRefElement.appendChild(refKey4Value);
		parentNode.getParentNode().insertBefore(refKey, parentNode);
		parentNode.getParentNode().insertBefore(dictEntitlementRefElement,
				parentNode);
	}

	public static Element addEntitlementRef(Document document, String fileName) {
		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXFileReference");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element refKey2 = document.createElement("key");
		Text refKey2Txt = document.createTextNode("lastKnownFileType");
		refKey2.appendChild(refKey2Txt);

		Element refKey2Value = document.createElement("string");
		Text refKey2ValueTxt = document
				.createTextNode("text.plist.entitlements");
		refKey2Value.appendChild(refKey2ValueTxt);

		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("path");
		refKey3.appendChild(refKey3Txt);

		Element refKey3Value = document.createElement("string");
		Text refKey3ValueTxt = document.createTextNode(fileName);
		refKey3Value.appendChild(refKey3ValueTxt);

		Element refKey4 = document.createElement("key");
		Text refKey4Txt = document.createTextNode("sourceTree");
		refKey4.appendChild(refKey4Txt);

		Element refKey4Value = document.createElement("string");
		Text refKey4ValueTxt = document.createTextNode("<group>");
		refKey4Value.appendChild(refKey4ValueTxt);

		Element dictEntitlementRefElement = document.createElement("dict");
		dictEntitlementRefElement.appendChild(refKey1);
		dictEntitlementRefElement.appendChild(refKey1Value);
		dictEntitlementRefElement.appendChild(refKey2);
		dictEntitlementRefElement.appendChild(refKey2Value);
		dictEntitlementRefElement.appendChild(refKey3);
		dictEntitlementRefElement.appendChild(refKey3Value);
		dictEntitlementRefElement.appendChild(refKey4);
		dictEntitlementRefElement.appendChild(refKey4Value);

		return dictEntitlementRefElement;
	}

	public static void addBuildActionMaskRef(Document document,
			String buildFileRef, Node nod) {
		Element dtbBuildRef = document.createElement("string");
		Text dtbBuildRefValue = document.createTextNode(buildFileRef);
		dtbBuildRef.appendChild(dtbBuildRefValue);

		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node buildActionMaskKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(buildActionMaskKey.getNodeName())) {
				if ("buildActionMask".equalsIgnoreCase(buildActionMaskKey
						.getFirstChild().getNodeValue())) {
					Node string = buildActionMaskKey.getNextSibling();
					Node key = string.getNextSibling();
					Node array = key.getNextSibling();
					if ("array".equalsIgnoreCase(array.getNodeName())) {
						array.appendChild(dtbBuildRef);
					}
				}
			}
		}
	}

	public static void removeBuildActionMaskRef(Document document,
			String buildFileRef, Node nod) {
		Element dtbBuildRef = document.createElement("string");
		Text dtbBuildRefValue = document.createTextNode(buildFileRef);
		dtbBuildRef.appendChild(dtbBuildRefValue);

		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node buildActionMaskKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(buildActionMaskKey.getNodeName())) {
				if ("buildActionMask".equalsIgnoreCase(buildActionMaskKey
						.getFirstChild().getNodeValue())) {
					Node string = buildActionMaskKey.getNextSibling();
					Node key = string.getNextSibling();
					Node array = key.getNextSibling();
					Node node = null;
					if ("array".equalsIgnoreCase(array.getNodeName())) {
						NodeList nList = array.getChildNodes();
						for (int index = 0; index < nList.getLength(); index++) {
							node = nList.item(index);
							if (buildFileRef.equals(node.getTextContent())) {
								break;
							}
						}
						array.removeChild(node);
					}
				}
			}
		}
	}

	/**
	 * Function to add Build Mask Reference in KRelease or KonyJS
	 * 
	 * @param document
	 * @param buildFileRef
	 * @param nod
	 */
	public static void addBuildActionMaskEmbedRef(Document document,
			String buildFileRef, Node nod) {
		Element dtbBuildRef = document.createElement("string");
		Text dtbBuildRefValue = document.createTextNode(buildFileRef);
		dtbBuildRef.appendChild(dtbBuildRefValue);

		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node buildActionMaskKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(buildActionMaskKey.getNodeName())) {
				if ("buildActionMask".equalsIgnoreCase(buildActionMaskKey
						.getFirstChild().getNodeValue())) {
					Node string = buildActionMaskKey.getNextSibling();
					Node key = string.getNextSibling();
					Node keyString = key.getNextSibling();
					Node key1 = keyString.getNextSibling();
					Node keyString1 = key1.getNextSibling();
					Node key2 = keyString1.getNextSibling();
					Node keyString2 = key2.getNextSibling();
					if ("array".equalsIgnoreCase(keyString2.getNodeName())) {
						keyString2.appendChild(dtbBuildRef);
					}
				}
			}
		}
	}

	/**
	 * Method to set Region Specific Settings in XCode Build Settings or Build
	 * Phases
	 * 
	 * @param document
	 * @param nod
	 * @param nodeKeyName
	 * @param region
	 */
	public static void setRegionSpecificSettings(Document document, Node nod,
			String nodeKeyName, String region) {
		if ("EIA_APAC".equalsIgnoreCase(region)
				|| "LAS".equalsIgnoreCase(region)) {
			// Adding the File Reference of DTBAppDelegate.h & .m files to the
			// App Delegate Extension Folder
			if (nodeKeyName.equals(APPDELEGATE_FOLDER)) {
				// System.out.println("inside APPDELEGATE_FOLDER....");
				Element dtbHString = document.createElement("string");
				Text dtbHStringValue = document
						.createTextNode(DTBAPPDELEGATE_H_REF);
				dtbHString.appendChild(dtbHStringValue);

				Element dtbMString = document.createElement("string");
				Text dtbMStringValue = document
						.createTextNode(DTBAPPDELEGATE_M_REF);
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
			if (nodeKeyName.equals(KONYAPPDELEGATE_H_FILE)) {
				addNewFileRef(document, "DTBAppDelegate.m",
						DTBAPPDELEGATE_M_REF, nod, true);
				addNewFileRef(document, "DTBAppDelegate.h",
						DTBAPPDELEGATE_H_REF, nod, true);

				addBuildFileRef(document, DTBAPPDELEGATE_M_BUILDREF,
						DTBAPPDELEGATE_M_REF, nod);
				addBuildFileRef(document, DTBAPPDELEGATE_M_BUILDREF1,
						DTBAPPDELEGATE_M_REF, nod);
			}
			// Adding the Files in Build Phases for KRelease
			if (nodeKeyName.equals(DTBAPPDELEGATE_M_BUILDACTIONREF)) {
				addBuildActionMaskRef(document, DTBAPPDELEGATE_M_BUILDREF, nod);
			}
			// Adding the Files for Build Phases for KonyJS
			if (nodeKeyName.equals(DTBAPPDELEGATE_M_BUILDACTIONREF1)) {
				addBuildActionMaskRef(document, DTBAPPDELEGATE_M_BUILDREF1, nod);
			}
			/*
			 * if("path".equals(nodeKeyName)){ Node name = nod.getNextSibling();
			 * String nameString = name.getTextContent();
			 * if(nameString.endsWith(BRIDGING_HEADER)){
			 * System.out.println("APP_ID::"+APP_ID); String hdrName =
			 * APP_ID+BRIDGING_HEADER; name.setTextContent(hdrName); } }
			 * if("name".equals(nodeKeyName)){ Node name = nod.getNextSibling();
			 * String nameString = name.getTextContent();
			 * if(nameString.endsWith(BRIDGING_HEADER)){
			 * System.out.println("APP_ID::"+APP_ID); String hdrName =
			 * APP_ID+BRIDGING_HEADER; name.setTextContent(hdrName); } }
			 */
		}
		if ("NA".equalsIgnoreCase(region)) {
			// Adding Tealium Frameworks is not required as Frameworks are added
			// in the
			// Visualizer FFI itself
		}
		if ("CN_CH".equalsIgnoreCase(region)) {
			// Adding the Files in Build Phases for KRelease
			if (nodeKeyName.equals(DTBAPPDELEGATE_M_BUILDACTIONREF)) {
				addBuildActionMaskRef(document, SVPROGRESSHUD_M_BUILDFILE_REF,
						nod);
				addBuildFileRefWithComplierFlags(document,
						SVPROGRESSHUD_M_BUILDFILE_REF,
						SVPROGRESSHUD_M_FILE_REF, nod);

				addBuildActionMaskRef(document, SVRADIAL_M_BUILDFILE_REF, nod);
				addBuildFileRefWithComplierFlags(document,
						SVRADIAL_M_BUILDFILE_REF, SVRADIAL_M_FILE_REF, nod);

				addBuildActionMaskRef(document, SVPROGRESSANIM_M_BUILDFILE_REF,
						nod);
				addBuildFileRefWithComplierFlags(document,
						SVPROGRESSANIM_M_BUILDFILE_REF,
						SVPROGRESSANIM_M_FILE_REF, nod);

				addBuildActionMaskRef(document, SVINDEFINITE_M_BUILDFILE_REF,
						nod);
				addBuildFileRefWithComplierFlags(document,
						SVINDEFINITE_M_BUILDFILE_REF, SVINDEFINITE_M_FILE_REF,
						nod);
			}
			// Changing the compiler flag value in the Build Phases for China
			// Content Hub App
			if (nodeKeyName.equals(SVPROGRESSHUD_M_BUILDFILE_REF1)) {
				updateCompilerFlags(nod, SVPROGRESSHUD_M_FILE_REF);
			}
			if (nodeKeyName.equals(ZHUGE_M_BUILDFILE_REF)) {
				updateCompilerFlags(nod, ZHUGE_M_FILE_REF);
			}
			if (nodeKeyName.equals(ZHUGECONFIG_M_BUILDFILE_REF)) {
				updateCompilerFlags(nod, ZHUGECONFIG_M_FILE_REF);
			}
			if (nodeKeyName.equals(ZHUGEBASE64_M_BUILDFILE_REF)) {
				updateCompilerFlags(nod, ZHUGEBASE64_M_FILE_REF);
			}

			// Adding the Files Details and Encoding based on Reference above
			if (nodeKeyName.equals(LOGINBUNDLE_M_BUILDACTIONREF)) {
				addBuildFileRef(document, LOGINBUNDLE_M_BUILDACTIONREF1,
						LOGINBUNDLE_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, LOGINBUNDLE_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference above
			if (nodeKeyName.equals(WEIBOBUNDLE_M_BUILDACTIONREF)) {
				addBuildFileRef(document, WEIBOBUNDLE_M_BUILDACTIONREF1,
						WEIBOBUNDLE_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, WEIBOBUNDLE_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference above
			if (nodeKeyName.equals(SYSCONFIG_FW_BUILDACTIONREF)) {
				addBuildFileRef(document, SYSCONFIG_FW_BUILDACTIONREF1,
						SYSCONFIG_FW_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, SYSCONFIG_FW_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference above
			if (nodeKeyName.equals(CORETELEPHONY_FW_BUILDACTIONREF)) {
				addBuildFileRef(document, CORETELEPHONY_FW_BUILDACTIONREF1,
						CORETELEPHONY_FW_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document,
						CORETELEPHONY_FW_BUILDACTIONREF1, nod);
			}
		}
		if ("CN_DH".equalsIgnoreCase(region)) {
			// Changing the compiler flag value in the Build Phases for China
			// Digital Hub App
			if (nodeKeyName.equals(SVPROGRESSHUD_M_BUILDFILE_REF1)) {
				removeCompilerFlags(nod, SVPROGRESSHUD_M_FILE_REF);
			}
			if (nodeKeyName.equals(UIMASADD_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, UIMASADD_M_FILE_REF);
			}
			if (nodeKeyName.equals(MASVIEWCONS_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, MASVIEWCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(MASCONS_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, MASCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(MASCONSMAKER_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, MASCONSMAKER_M_FILE_REF);
			}
			if (nodeKeyName.equals(MASCOMPCONS_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, MASCOMPCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(JSONMODEL_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, JSONMODEL_M_FILE_REF);
			}
			if (nodeKeyName.equals(ISSMSG_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, ISSMSG_M_FILE_REF);
			}
			if (nodeKeyName.equals(CDVAUTH_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, CDVAUTH_M_FILE_REF);
			}
			if (nodeKeyName.equals(CBROW_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, CBROW_M_FILE_REF);
			}
			if (nodeKeyName.equals(PGNATIVE_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, PGNATIVE_M_FILE_REF);
			}
			if (nodeKeyName.equals(CDVBARSCAN_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, CDVBARSCAN_M_FILE_REF);
			}
			if (nodeKeyName.equals(NSAC_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, NSAC_M_FILE_REF);
			}
			if (nodeKeyName.equals(SCANVW_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, SCANVW_M_FILE_REF);
			}
			if (nodeKeyName.equals(QRCVC_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, QRCVC_M_FILE_REF);
			}
			if (nodeKeyName.equals(CDVC_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, CDVC_M_FILE_REF);
			}
			if (nodeKeyName.equals(AHAUTHHELP_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, AHAUTHHELP_M_FILE_REF);
			}
			if (nodeKeyName.equals(CDVJHW_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, CDVJHW_M_FILE_REF);
			}
			if (nodeKeyName.equals(NSDICTEX_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, NSDICTEX_M_FILE_REF);
			}
			if (nodeKeyName.equals(NSDB_M_BUILDFILE_REF)) {
				removeCompilerFlags(nod, NSDB_M_FILE_REF);
			}

			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(LOGINBUNDLE_M_BUILDACTIONREF)) {
				addBuildFileRef(document, LOGINBUNDLE_M_BUILDACTIONREF1,
						LOGINBUNDLE_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, LOGINBUNDLE_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(AMWAYLOGIN_M_BUILDACTIONREF)) {
				addBuildFileRef(document, AMWAYLOGIN_M_BUILDACTIONREF1,
						AMWAYLOGIN_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, AMWAYLOGIN_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(LOGINMANAGER_M_BUILDACTIONREF)) {
				addBuildFileRef(document, LOGINMANAGER_M_BUILDACTIONREF1,
						LOGINMANAGER_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, LOGINMANAGER_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(WXAPIMANAGER_M_BUILDACTIONREF)) {
				addBuildFileRef(document, WXAPIMANAGER_M_BUILDACTIONREF1,
						WXAPIMANAGER_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, WXAPIMANAGER_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSMSGOPERATOR_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSMSGOPERATOR_M_BUILDACTIONREF1,
						ISSMSGOPERATOR_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document,
						ISSMSGOPERATOR_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSMSGCENTER_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSMSGCENTER_M_BUILDACTIONREF1,
						ISSMSGCENTER_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ISSMSGCENTER_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSMSG_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSMSG_M_BUILDACTIONREF1,
						ISSMSG_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ISSMSG_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSDESC_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSDESC_M_BUILDACTIONREF1,
						ISSDESC_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ISSDESC_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSNWTOOL_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSNWTOOL_M_BUILDACTIONREF1,
						ISSNWTOOL_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ISSNWTOOL_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ISSURL_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ISSURL_M_BUILDACTIONREF1,
						ISSURL_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ISSURL_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(MSGALERTVW_M_BUILDACTIONREF)) {
				addBuildFileRef(document, MSGALERTVW_M_BUILDACTIONREF1,
						MSGALERTVW_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, MSGALERTVW_M_BUILDACTIONREF1,
						nod);
			}
			// Removing the Action Build Mask for KonyJS
			if (nodeKeyName.equals(MJREFRESH_FILEREF_KONYJS)) {
				removeBuildActionMaskRef(document, MJREFRESH_BUILDFILE_REF, nod);
				removeBuildActionMaskRef(document, ASSETS_BUILDFILE_REF, nod);
			}
			// Adding the Action Build Mask for KRelease
			if (nodeKeyName.equals(MJREFRESH_FILEREF_KRELEASE)) {
				addBuildActionMaskRef(document, MJREFRESH_BUILDFILE_REF, nod);
				addBuildActionMaskRef(document, ASSETS_BUILDFILE_REF, nod);
			}

			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(SVRADIAL_M_BUILDACTIONREF)) {
				addBuildFileRef(document, SVRADIAL_M_BUILDACTIONREF1,
						SVRADIAL_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, SVRADIAL_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(SVPROGRESSANIM_M_BUILDACTIONREF)) {
				addBuildFileRef(document, SVPROGRESSANIM_M_BUILDACTIONREF1,
						SVPROGRESSANIM_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document,
						SVPROGRESSANIM_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(SVINDEFINITE_M_BUILDACTIONREF)) {
				addBuildFileRef(document, SVINDEFINITE_M_BUILDACTIONREF1,
						SVINDEFINITE_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, SVINDEFINITE_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(SVPROGRESSHUD_M_BUILDACTIONREF)) {
				addBuildFileRef(document, SVPROGRESSHUD_M_BUILDACTIONREF1,
						SVPROGRESSHUD_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document,
						SVPROGRESSHUD_M_BUILDACTIONREF1, nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(SVPBUNDLE_M_BUILDACTIONREF)) {
				addBuildFileRef(document, SVPBUNDLE_M_BUILDACTIONREF1,
						SVPBUNDLE_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, SVPBUNDLE_M_BUILDACTIONREF1,
						nod);
			}
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(ZHGM_M_BUILDACTIONREF)) {
				addBuildFileRef(document, ZHGM_M_BUILDACTIONREF1,
						ZHGM_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, ZHGM_M_BUILDACTIONREF1,
						nod);
			}	
			// Adding the Files Details and Encoding based on Reference for
			// KRelease
			if (nodeKeyName.equals(LNM_M_BUILDACTIONREF)) {
				addBuildFileRef(document, LNM_M_BUILDACTIONREF1,
						LNM_M_BUILDACTIONREF2, nod);
				addBuildActionMaskRef(document, LNM_M_BUILDACTIONREF1,
						nod);
			}
		}
	}

	public static void setBuildSettingsParameters(Document document, Node nod,
			String nodeKeyName, Properties properties, boolean isKonyJS) {
		if (nodeKeyName.equalsIgnoreCase("buildSettings")) {
			Node dict = nod.getNextSibling();
			if ("dict".equalsIgnoreCase(dict.getNodeName())) {
				NodeList dictChilds = dict.getChildNodes();
				Properties temp = new Properties();
				// Filter the Profile related properties as its not required for
				// KonyJS
				if (isKonyJS) {
					properties = filterProfileProps(properties);
				}
				temp.putAll(properties);

				Node keyNod;
				for (int j = 0; j < dictChilds.getLength(); j++) {
					keyNod = dictChilds.item(j);
					Node stringNod = dictChilds.item(j + 1);
					Node keysValue = keyNod.getFirstChild();
					Node stringValue = stringNod.getFirstChild();
					if ("key".equalsIgnoreCase(keyNod.getNodeName())) {
						j++;
						if (keysValue != null) {
							String settingsKey = keysValue.getTextContent();
							String configKey = (String) properties
									.get(settingsKey);
							if (configKey != null) {
								String configValue = properties
										.getProperty(settingsKey);
								if (stringValue != null) {
									stringValue.setTextContent(configValue);
								} else {
									Text stringChild = document
											.createTextNode(configValue);
									stringNod.appendChild(stringChild);
								}
								temp.remove(settingsKey);
							}
						}
					}
				}
				if (!temp.isEmpty()) {
					for (Object xpathExpressionObj : temp.keySet()) {
						String configKey = (String) xpathExpressionObj;
						String configValue = properties.getProperty(configKey);
						Element key = document.createElement("key");
						Text keyValue = document.createTextNode(configKey);
						key.appendChild(keyValue);
						Element string = document.createElement("string");
						Text stringValue = document.createTextNode(configValue);
						string.appendChild(stringValue);

						dict.appendChild(key);
						dict.appendChild(string);
					}
				}
			}
		}
	}

	public static Properties filterProfileProps(Properties properties) {
		// System.out.println("Actual Properties:" + properties.toString());
		ArrayList<String> ProfileProps = new ArrayList<String>(Arrays.asList(
				"DEVELOPMENT_TEAM", "CODE_SIGN_IDENTITY[sdk=iphoneos*]",
				"CODE_SIGN_IDENTITY", "PROVISIONING_PROFILE",
				"PROVISIONING_PROFILE[sdk=iphoneos*]",
				"PROVISIONING_PROFILE_NAME",
				"ALWAYS_EMBED_SWIFT_STANDARD_LIBRARIES"));
		Properties temp = new Properties();
		Enumeration<Object> propKeys = properties.keys();
		while (propKeys.hasMoreElements()) {
			String key = (String) propKeys.nextElement();
			if (!ProfileProps.contains(key)) {
				temp.put(key, properties.get(key));
			}
		}
		// System.out.println("Filtered Properties:" + temp.toString());
		return temp;
	}

	public static void updateCompilerFlags(Node nod, String fileRef) {
		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node fileRefKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(fileRefKey.getNodeName())) {
				if ("fileRef".equalsIgnoreCase(fileRefKey.getFirstChild()
						.getNodeValue())) {
					Node fileRefValue = fileRefKey.getNextSibling();
					String SVDFile = fileRefValue.getTextContent();
					if (fileRef.equalsIgnoreCase(SVDFile)) {
						Node isaKey = fileRefValue.getNextSibling();
						Node isaValue = isaKey.getNextSibling();
						Node settingsKey = isaValue.getNextSibling();
						Node dictNode = settingsKey.getNextSibling();
						if ("dict".equalsIgnoreCase(dictNode.getNodeName())) {
							Node compilerFlagKey = dictNode.getFirstChild();
							if ("COMPILER_FLAGS"
									.equalsIgnoreCase(compilerFlagKey
											.getTextContent())) {
								Node key2 = compilerFlagKey.getNextSibling();
								key2.setTextContent("-fobjc-arc");
							}
						}
					}
				}
			}
		}
	}

	public static void removeCompilerFlags(Node nod, String fileRef) {
		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node fileRefKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(fileRefKey.getNodeName())) {
				if ("fileRef".equalsIgnoreCase(fileRefKey.getFirstChild()
						.getNodeValue())) {
					Node fileRefValue = fileRefKey.getNextSibling();
					String SVDFile = fileRefValue.getTextContent();
					if (fileRef.equalsIgnoreCase(SVDFile)) {
						Node isaKey = fileRefValue.getNextSibling();
						Node isaValue = isaKey.getNextSibling();
						if (null != isaValue) {
							Node settingsKey = isaValue.getNextSibling();
							if (null != settingsKey) {
								Node dictNode = settingsKey.getNextSibling();
								dict.removeChild(settingsKey);
								dict.removeChild(dictNode);
							}
						}

						/*
						 * if ("dict".equalsIgnoreCase(dictNode.getNodeName()))
						 * { Node compilerFlagKey = dictNode.getFirstChild(); if
						 * ("COMPILER_FLAGS" .equalsIgnoreCase(compilerFlagKey
						 * .getTextContent())) { Node key2 =
						 * compilerFlagKey.getNextSibling();
						 * key2.setTextContent("-fobjc-arc"); } }
						 */
					}
				}
			}
		}
	}

	public static void getFileReferencesforChinaApp(NodeList nodeKeyList,
			String region) {
		boolean isSVDFileFound = false;
		boolean isSVPFileFound = false;
		boolean isSVIFileFound = false;
		boolean isSVRFileFound = false;
		boolean isZHFileFound = false;
		boolean isZHCFileFound = false;
		boolean isZHBFileFound = false;
		boolean isLoginBundleFileFound = false;
		boolean isWeiboBundleFileFound = false;
		boolean isSysConfigFWFileFound = false;
		boolean isCoreTeleFWFileFound = false;
		boolean isAmwayLoginFileFound = false;
		boolean isLoginManagerFileFound = false;
		boolean isWXAPIManagerFileFound = false;
		boolean isISSMsgOperatorFileFound = false;
		boolean isISSMsgCenterFileFound = false;
		boolean isISSMSGFileFound = false;
		boolean isISSDescFileFound = false;
		boolean isISSNEToolFileFound = false;
		boolean isISSURLFileFound = false;
		boolean isUIMASFileFound = false;
		boolean isMVCFileFound = false;
		boolean isMCFileFound = false;
		boolean isMCMFileFound = false;
		boolean isMCCFileFound = false;
		boolean isJMFileFound = false;
		boolean isISSMsgFileFound = false;
		boolean isMJRFileFound = false;
		boolean isAXFileFound = false;
		boolean isCDVFileFound = false;
		boolean isCBRWFileFound = false;
		boolean isPGNFileFound = false;
		boolean isCDVBSFileFound = false;
		boolean isNSACFileFound = false;
		boolean isSCANVWFileFound = false;
		boolean isQRCVCFileFound = false;
		boolean isCDVCFileFound = false;
		boolean isAHAHFileFound = false;
		boolean isCDVJHMFileFound = false;
		boolean isNSDEXFileFound = false;
		boolean isNSDBFileFound = false;

		boolean isSVPBundleFileFound = false;
		boolean isSVRRefFileFound = false;
		boolean isSVPARefFileFound = false;
		boolean isSVIRefFileFound = false;
		boolean isSVPHRefFileFound = false;
		boolean isZHGMRefFileFound = false;
		boolean isLNMRefFileFound = false;

		String nodeKeyName = "";

		Map<String, String> fileRefMap = new HashMap<String, String>();
		Map<String, String> reverseFileRefMap = new HashMap<String, String>();
		if ("CN_DH".equalsIgnoreCase(region)
				|| "CN_CH".equalsIgnoreCase(region)) {
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE) {
					nodeKeyName = nod.getFirstChild().getNodeValue();
				}
				// Adding the Files for Build Phases for KonyJS
				if (nodeKeyName.equals(DTBAPPDELEGATE_M_BUILDACTIONREF1)
						|| nodeKeyName.equals(KONYJS_M_BUILDACTIONREF)) {
					Node dict = nod.getNextSibling();
					if ("dict".equalsIgnoreCase(dict.getNodeName())) {
						Node fileRefKey = dict.getFirstChild();
						if ("key".equalsIgnoreCase(fileRefKey.getNodeName())) {
							if ("buildActionMask".equalsIgnoreCase(fileRefKey
									.getTextContent())) {
								Node filesKey = fileRefKey.getNextSibling()
										.getNextSibling();
								if ("files".equalsIgnoreCase(filesKey
										.getTextContent())) {
									Node arrayNode = filesKey.getNextSibling();
									NodeList arrayList = arrayNode
											.getChildNodes();
									for (int j = 0; j < arrayList.getLength(); j++) {
										Node node = arrayList.item(j);
										String fileRefs = node.getTextContent();
										fileRefMap.put(fileRefs, "");
									}
								}
							}
						}
					}
				}
				if (fileRefMap.containsKey(nodeKeyName)) {
					Node dict = nod.getNextSibling();
					if ("dict".equalsIgnoreCase(dict.getNodeName())) {
						Node fileRefKey = dict.getFirstChild();
						if ("key".equalsIgnoreCase(fileRefKey.getNodeName())) {
							if ("fileRef".equalsIgnoreCase(fileRefKey
									.getTextContent())) {
								Node stringKey = fileRefKey.getNextSibling();
								String fileRef = stringKey.getTextContent();
								fileRefMap.put(nodeKeyName, fileRef);
								reverseFileRefMap.put(fileRef, nodeKeyName);
							}
						}
					}
				}
			}
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE) {
					nodeKeyName = nod.getFirstChild().getNodeValue();
				}
				if (fileRefMap.containsValue(nodeKeyName)) {
					Node dict = nod.getNextSibling();
					if ("dict".equalsIgnoreCase(dict.getNodeName())) {
						NodeList dictArray = dict.getChildNodes();
						for (int k = 0; k < dictArray.getLength(); k++) {
							Node node = dictArray.item(k);
							String fileRefs = node.getTextContent();
							if ("path".equalsIgnoreCase(fileRefs)) {
								Node stringNode = dictArray.item(k + 1);
								String fileName = stringNode.getTextContent();
								if (SVPROGRESSHUD_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									SVPROGRESSHUD_M_FILE_REF = nodeKeyName;
									SVPROGRESSHUD_M_BUILDFILE_REF1 = reverseFileRefMap
											.get(nodeKeyName);
									isSVDFileFound = true;
								}
								if (ZHUGE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									ZHUGE_M_FILE_REF = nodeKeyName;
									ZHUGE_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHFileFound = true;
								}
								if (ZHUGECONFIG_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									ZHUGECONFIG_M_FILE_REF = nodeKeyName;
									ZHUGECONFIG_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHCFileFound = true;
								}
								if (ZHUGEBASE64_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									ZHUGEBASE64_M_FILE_REF = nodeKeyName;
									ZHUGEBASE64_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHBFileFound = true;
								}
								if (SVRADIAL_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									SVRADIAL_M_FILE_REF = nodeKeyName;
									// SVRADIAL_M_BUILDFILE_REF =
									// reverseFileRefMap.get(nodeKeyName);
									isSVRFileFound = true;
								}
								if (SVPROGRESSANIM_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									SVPROGRESSANIM_M_FILE_REF = nodeKeyName;
									// SVPROGRESSANIM_M_BUILDFILE_REF
									// =reverseFileRefMap.get(nodeKeyName);
									isSVPFileFound = true;
								}
								if (SVINDEFINITE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									SVINDEFINITE_M_FILE_REF = nodeKeyName;
									// SVINDEFINITE_M_BUILDFILE_REF
									// =reverseFileRefMap.get(nodeKeyName);
									isSVIFileFound = true;
								}
								if (UIMASADD_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									UIMASADD_M_FILE_REF = nodeKeyName;
									UIMASADD_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isUIMASFileFound = true;
								}
								if (MASVIEWCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									MASVIEWCONS_M_FILE_REF = nodeKeyName;
									MASVIEWCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMVCFileFound = true;
								}
								if (MASCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									MASCONS_M_FILE_REF = nodeKeyName;
									MASCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCFileFound = true;
								}
								if (MASCONSMAKER_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									MASCONSMAKER_M_FILE_REF = nodeKeyName;
									MASCONSMAKER_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCMFileFound = true;
								}
								if (MASCOMPCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									MASCOMPCONS_M_FILE_REF = nodeKeyName;
									MASCOMPCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCCFileFound = true;
								}
								if (JSONMODEL_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									JSONMODEL_M_FILE_REF = nodeKeyName;
									JSONMODEL_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isJMFileFound = true;
								}
								if (ISSMSG_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									ISSMSG_M_FILE_REF = nodeKeyName;
									ISSMSG_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isISSMsgFileFound = true;
								}
								if (MJREFRESH_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									MJREFRESH_FILE_REF = nodeKeyName;
									MJREFRESH_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMJRFileFound = true;
								}
								if (ASSETS_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									ASSETS_FILE_REF = nodeKeyName;
									ASSETS_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isAXFileFound = true;
								}

								if (CDVAUTH_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									CDVAUTH_M_FILE_REF = nodeKeyName;
									CDVAUTH_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVFileFound = true;
								}
								if (CBROW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									CBROW_M_FILE_REF = nodeKeyName;
									CBROW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCBRWFileFound = true;
								}
								if (PGNATIVE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									PGNATIVE_M_FILE_REF = nodeKeyName;
									PGNATIVE_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isPGNFileFound = true;
								}
								if (CDVBARSCAN_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									CDVBARSCAN_M_FILE_REF = nodeKeyName;
									CDVBARSCAN_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVBSFileFound = true;
								}
								if (NSAC_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									NSAC_M_FILE_REF = nodeKeyName;
									NSAC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isNSACFileFound = true;
								}
								if (SCANVW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									SCANVW_M_FILE_REF = nodeKeyName;
									SCANVW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isSCANVWFileFound = true;
								}
								if (QRCVC_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									QRCVC_M_FILE_REF = nodeKeyName;
									QRCVC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isQRCVCFileFound = true;
								}
								if (CDVC_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									CDVC_M_FILE_REF = nodeKeyName;
									CDVC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVCFileFound = true;
								}
								if (AHAUTHHELP_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									AHAUTHHELP_M_FILE_REF = nodeKeyName;
									AHAUTHHELP_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isAHAHFileFound = true;
								}
								if (CDVJHW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									CDVJHW_M_FILE_REF = nodeKeyName;
									CDVJHW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVJHMFileFound = true;
								}
								if (NSDICTEX_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									NSDICTEX_M_FILE_REF = nodeKeyName;
									NSDICTEX_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isNSDEXFileFound = true;
								}
								if (NSDB_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									NSDB_M_FILE_REF = nodeKeyName;
									NSDB_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isNSDBFileFound = true;
								}

							}
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (LOGIN_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							LOGINBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLoginBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (WEIBO_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							WEIBOBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isWeiboBundleFileFound = true;
						}
					}
				}
				if ("name".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SYSCONFIG_FW_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SYSCONFIG_FW_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSysConfigFWFileFound = true;
						}
					}
				}
				if ("name".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (CORETELEPHONY_FW_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							CORETELEPHONY_FW_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isCoreTeleFWFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (AMWAYLOGIN_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							AMWAYLOGIN_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isAmwayLoginFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (LOGINMANAGER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							LOGINMANAGER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLoginManagerFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (WXAPIMANAGER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							WXAPIMANAGER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isWXAPIManagerFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSMSGOPERATOR_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSMSGOPERATOR_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSMsgOperatorFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSMSGCENTER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSMSGCENTER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSMsgCenterFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSMSG_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSMSG_M_BUILDACTIONREF2 = keyNode.getTextContent();
							isISSMSGFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSDESC_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSDESC_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSDescFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSNWTOOL_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSNWTOOL_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSNEToolFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ISSURL_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ISSURL_M_BUILDACTIONREF2 = keyNode.getTextContent();
							isISSURLFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (MSGALERTVW_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							MSGALERTVW_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSURLFileFound = true;
						}
					}
				}

				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SVP_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SVPBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SVRADIAL_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SVRADIAL_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVRRefFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SVPROGRESSANIM_M_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SVPROGRESSANIM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPARefFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SVINDEFINITE_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SVINDEFINITE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVIRefFileFound = true;
						}
					}
				}

				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (SVPROGRESSHUD_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							SVPROGRESSHUD_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPHRefFileFound = true;
						}
					}
				}
				
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (ZHGM_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							ZHGM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isZHGMRefFileFound = true;
						}
					}
				}
				
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (LNM_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							LNM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLNMRefFileFound = true;
						}
					}
				}

				if (("CN_DH".equalsIgnoreCase(region))
						&& ("path".equalsIgnoreCase(nodeKeyName))) {
					Node pathNode = nod.getNextSibling();
					Node fileTypeNode = nod.getPreviousSibling();
					String path = pathNode.getTextContent();
					String fileType = fileTypeNode.getTextContent();
					if (JSMESSAGE_CENTER_FFI_FILE_NAME.equals(path)) {
						pathNode.setTextContent(JSMESSAGE_CENTER_FFI_FILE_NAME
								+ "m");
						if (JSMESSAGE_CENTER_FFI_C_FILE_TYPE.equals(fileType)) {
							fileTypeNode
									.setTextContent(JSMESSAGE_CENTER_FFI_CPP_FILE_TYPE);
						} else if (JSMESSAGE_CENTER_FFI_FILE_NAME
								.equals(fileType)) {
							Node nameNode = fileTypeNode.getPreviousSibling();
							if ("name".equals(nameNode.getTextContent())) {
								Node typeValeN = nameNode.getPreviousSibling();
								Node fileTypeN = typeValeN.getPreviousSibling();
								if ("lastKnownFileType".equals(fileTypeN
										.getTextContent())) {
									typeValeN
											.setTextContent(JSMESSAGE_CENTER_FFI_CPP_FILE_TYPE);
									fileTypeNode
											.setTextContent(JSMESSAGE_CENTER_FFI_FILE_NAME
													+ "m");
								}
							}
						}
					}
				}
				if (isSVDFileFound && isSVRFileFound && isSVPFileFound
						&& isSVIFileFound && isLoginBundleFileFound
						&& isWeiboBundleFileFound && isSysConfigFWFileFound
						&& isCoreTeleFWFileFound && isZHFileFound
						&& isZHCFileFound && isZHBFileFound
						&& isAmwayLoginFileFound && isLoginManagerFileFound
						&& isWXAPIManagerFileFound && isISSMsgOperatorFileFound
						&& isISSMsgCenterFileFound && isISSMSGFileFound
						&& isISSDescFileFound && isISSNEToolFileFound
						&& isISSURLFileFound && isUIMASFileFound
						&& isMVCFileFound && isMCFileFound && isMCMFileFound
						&& isMCCFileFound && isJMFileFound && isISSMsgFileFound
						&& isMJRFileFound && isAXFileFound && isCDVFileFound
						&& isCBRWFileFound && isPGNFileFound
						&& isCDVBSFileFound && isNSACFileFound
						&& isSCANVWFileFound && isQRCVCFileFound
						&& isCDVCFileFound && isAHAHFileFound
						&& isCDVJHMFileFound && isNSDEXFileFound
						&& isNSDBFileFound && isSVPBundleFileFound
						&& isSVRRefFileFound && isSVPARefFileFound
						&& isSVIRefFileFound && isSVPHRefFileFound 
						&& isZHGMRefFileFound && isLNMRefFileFound) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		xCodeAutomationForViz8(
				"/Users/ncl/Desktop/mCoE/AutomationFiles/XCodeCurBuildProperties.xml",
				"/Users/ncl/Desktop/mCoE/AutomationFiles/Config.properties",
				"10", "false", "", "CN_DH");
		/*
		 * Properties properties = new Properties(); try { FileInputStream
		 * configFile = new FileInputStream(new
		 * File("/Users/ncl/Desktop/mCoE/Config.properties"));
		 * properties.load(configFile); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * System.out.println("Properties"
		 * +filterProfileProps(properties).toString());
		 */
	}
}
