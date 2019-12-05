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

import com.kony.project.AmwayApp;
import com.kony.project.BusinessCenter;
import com.kony.project.ResourceCenter;

public class XCodeSettingsAutomation {


	/**
	 * Method for Xcode settings for Older versions of Viz (Below 8.x)
	 * @param XcodeXmlPath
	 * @param ConfigXmlPath
	 * @param XcodeVersion
	 * @param capabilitiesList
	 * @param entitlementsFile
	 * @param region
	 */
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
								dictNode.appendChild(XCodeUtils.addEntitlementRef(
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
							region,"");

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
	 * Method for Xcode Settings for Newer versions of Viz (Post 8.x)
	 * @param XcodeXmlPath
	 * @param ConfigXmlPath
	 * @param XcodeVersion
	 * @param capabilitiesList
	 * @param entitlementsFile
	 * @param region
	 * @param appNameLocales
	 */
	public static void xCodeAutomationForViz8(String XcodeXmlPath,
			String ConfigXmlPath, String XcodeVersion, String capabilitiesList,
			String entitlementsFile, String region,String appNameLocales) {

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
            getFileReferences(nodeKeyList, region);
			// String nodeStringValue = "";
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE)
					nodeKeyName = nod.getFirstChild().getNodeValue();
				// nodeStringValue = nod.getNextSibling().getNodeValue();

				// Check for all KRelease Nodes
				if (nodeKeyName.equals(Constants.KRELEASE_RELEASE_NODE)) {
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
				if (nodeKeyName.equals(Constants.KPROTECTED_PROTECTED_NODE)) {
					isProvisioningStyleAdded = false;
					kReleaseNodeFound = false;
					konyJSNodeFound = false;
					kProtectedNodeFound = true;
				}
				// Check for all KonyJS Nodes
				if (nodeKeyName.equals(Constants.KONYJS_DEBUG_NODE)
						|| nodeKeyName.equals(Constants.KONYJS_PDEBUG_NODE)
						|| nodeKeyName.equals(Constants.KONYJS_RELEASE_NODE)
						|| nodeKeyName.equals(Constants.KONYJS_PROTECTED_NODE)
						|| nodeKeyName.equals(Constants.KONYJS_DISTRIBUTION_NODE)) {
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
				if (nodeKeyName.equals(Constants.KPROTECTED_DISTRIBUTION_NODE)) {
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
				setRegionSpecificSettings(document, nod, nodeKeyName, region,appNameLocales);

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
							dictNode.appendChild(XCodeUtils.addEntitlementRef(document,
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
			String nodeKeyName, String region, String appNameLocales) {
		if ("EIA_APAC".equalsIgnoreCase(region) || "APAC_IND".equalsIgnoreCase(region)) {
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
			// Adding File in Build Phases of KRelease
			if (nodeKeyName.equals(Constants.KRELEASE_SOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KRELEASE, nod);
			}
			// Adding File in Build Phases of KonyJS
			if (nodeKeyName.equals(Constants.KONYJS_SOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KONYJS, nod);
			}
			// Adding File in Build Phases of KProtected
			if (nodeKeyName.equals(Constants.KPROTECTED_SOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildActionMaskRef(document, Constants.DTBAPPDLG_M_BUILDREF_KPROTECTED, nod);
			}
            if("APAC_IND".equalsIgnoreCase(region)){
                if (nodeKeyName.equals(Constants.KRELEASE_RESOURCES_BUILDPHASE_REF)) {
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_1X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_1X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT1_1X_BUILDACTIONREF,
                                                     nod);
                    
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_2X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_2X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT1_2X_BUILDACTIONREF,
                                                     nod);
                    
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_3X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT1_3X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT1_3X_BUILDACTIONREF,
                                                     nod);
                    
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT_1X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT_1X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT_1X_BUILDACTIONREF,
                                                     nod);
                    
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT_2X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT_2X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT_2X_BUILDACTIONREF,
                                                     nod);
                    
                    XCodeUtils.addBuildFileRef(document,
                                               Constants.ICON_CHEVRON_LEFT_ALT_3X_BUILDACTIONREF,
                                               Constants.ICON_CHEVRON_LEFT_ALT_3X_FILE_REF, nod);
                    XCodeUtils.addBuildActionMaskRef(document,
                                                     Constants.ICON_CHEVRON_LEFT_ALT_3X_BUILDACTIONREF,
                                                     nod);
                    
                } 
            }
		}
		if ("LAS".equalsIgnoreCase(region)) {
			ResourceCenter.setCustomParams(document, nod, nodeKeyName, appNameLocales);
		}
		if ("NA_AA".equalsIgnoreCase(region)) {
			AmwayApp.setCustomParams(document, nod, nodeKeyName, appNameLocales);
		}
		if ("NA".equalsIgnoreCase(region)) {
			BusinessCenter.setCustomParams(document, nod, nodeKeyName, appNameLocales);
		}
		if ("CN_CH".equalsIgnoreCase(region)) {
			// Adding below Sources for KRelease in Target Membership
			if (nodeKeyName.equals(Constants.KRELEASE_SOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildActionMaskRef(document, Constants.SVPROGRESSHUD_M_BUILDFILE_REF,
						nod);
				XCodeUtils.addBuildFileRefWithComplierFlags(document,
						Constants.SVPROGRESSHUD_M_BUILDFILE_REF,
						Constants.SVPROGRESSHUD_M_FILE_REF, nod);

				XCodeUtils.addBuildActionMaskRef(document, Constants.SVRADIAL_M_BUILDFILE_REF, nod);
				XCodeUtils.addBuildFileRefWithComplierFlags(document,
						Constants.SVRADIAL_M_BUILDFILE_REF, Constants.SVRADIAL_M_FILE_REF, nod);

				XCodeUtils.addBuildActionMaskRef(document, Constants.SVPROGRESSANIM_M_BUILDFILE_REF,
						nod);
				XCodeUtils.addBuildFileRefWithComplierFlags(document,
						Constants.SVPROGRESSANIM_M_BUILDFILE_REF,
						Constants.SVPROGRESSANIM_M_FILE_REF, nod);

				XCodeUtils.addBuildActionMaskRef(document, Constants.SVINDEFINITE_M_BUILDFILE_REF,
						nod);
				XCodeUtils.addBuildFileRefWithComplierFlags(document,
						Constants.SVINDEFINITE_M_BUILDFILE_REF, Constants.SVINDEFINITE_M_FILE_REF,
						nod);
			}
			// Updating compiler flag value in the Build Phases in KonyJS
			if (nodeKeyName.equals(Constants.SVPROGRESSHUD_M_BUILDFILE_REF1)) {
				XCodeUtils.updateCompilerFlags(nod, Constants.SVPROGRESSHUD_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.ZHUGE_M_BUILDFILE_REF)) {
				XCodeUtils.updateCompilerFlags(nod, Constants.ZHUGE_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.ZHUGECONFIG_M_BUILDFILE_REF)) {
				XCodeUtils.updateCompilerFlags(nod, Constants.ZHUGECONFIG_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.ZHUGEBASE64_M_BUILDFILE_REF)) {
				XCodeUtils.updateCompilerFlags(nod, Constants.ZHUGEBASE64_M_FILE_REF);
			}

			// Adding below Resources for KRelease in Target Membership
			if (nodeKeyName.equals(Constants.KRELEASE_RESOURCES_BUILDPHASE_REF)) {
				//Adding File reference for LoginCenter.bundle File
				XCodeUtils.addBuildFileRef(document, Constants.LOGINBUNDLE_M_BUILDACTIONREF1,
						Constants.LOGINBUNDLE_M_BUILDACTIONREF2, nod);
				//Adding Build Phase reference for LoginCenter.bundle File
				XCodeUtils.addBuildActionMaskRef(document, Constants.LOGINBUNDLE_M_BUILDACTIONREF1,
						nod);
				
				//Adding File reference for Weibo.bundle File
				XCodeUtils.addBuildFileRef(document, Constants.WEIBOBUNDLE_M_BUILDACTIONREF1,
						Constants.WEIBOBUNDLE_M_BUILDACTIONREF2, nod);
				//Adding Build Phase reference for Weibo.bundle File
				XCodeUtils.addBuildActionMaskRef(document, Constants.WEIBOBUNDLE_M_BUILDACTIONREF1,
						nod);
				
				//Adding File reference for LoginCenter.bundle File
				XCodeUtils.addBuildFileRef(document, Constants.UIMODEL_BUNDLE_BUILDFILE_REF,
						Constants.UIMODEL_BUNDLE_FILE_REF, nod);
				//Adding Build Phase reference for LoginCenter.bundle File
				XCodeUtils.addBuildActionMaskRef(document, Constants.UIMODEL_BUNDLE_BUILDFILE_REF,
						nod);
			}
			// Adding below Frameworks for KRelease in Target Membership
			if (nodeKeyName.equals(Constants.KRELEASE_FRAMEWORKS_BUILDPHASE_REF)) {
				//Adding File reference for SystemConfiguration.Framework File
				XCodeUtils.addBuildFileRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
						Constants.SYSCONFIG_FW_BUILDACTIONREF2, nod);
				//Adding Build Phase reference for SystemConfiguration.Framework File
				XCodeUtils.addBuildActionMaskRef(document, Constants.SYSCONFIG_FW_BUILDACTIONREF1,
						nod);
				
				//Adding File reference for CoreTelephony.framework File
				XCodeUtils.addBuildFileRef(document, Constants.CORETELEPHONY_FW_BUILDACTIONREF1,
						Constants.CORETELEPHONY_FW_BUILDACTIONREF2, nod);
				//Adding Build Phase reference for CoreTelephony.framework File
				XCodeUtils.addBuildActionMaskRef(document,
						Constants.CORETELEPHONY_FW_BUILDACTIONREF1, nod);
			}
		}
		if ("CN_DH".equalsIgnoreCase(region)) {
			// Updating compiler flag value in the Build Phases for KonyJS
			if (nodeKeyName.equals(Constants.SVPROGRESSHUD_M_BUILDFILE_REF1)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.SVPROGRESSHUD_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.UIMASADD_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.UIMASADD_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.MASVIEWCONS_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.MASVIEWCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.MASCONS_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.MASCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.MASCONSMAKER_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.MASCONSMAKER_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.MASCOMPCONS_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.MASCOMPCONS_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.JSONMODEL_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.JSONMODEL_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.ISSMSG_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.ISSMSG_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.CDVAUTH_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.CDVAUTH_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.CBROW_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.CBROW_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.PGNATIVE_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.PGNATIVE_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.CDVBARSCAN_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.CDVBARSCAN_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.NSAC_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.NSAC_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.SCANVW_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.SCANVW_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.QRCVC_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.QRCVC_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.CDVC_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.CDVC_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.AHAUTHHELP_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.AHAUTHHELP_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.CDVJHW_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.CDVJHW_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.NSDICTEX_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.NSDICTEX_M_FILE_REF);
			}
			if (nodeKeyName.equals(Constants.NSDB_M_BUILDFILE_REF)) {
				XCodeUtils.removeCompilerFlags(nod, Constants.NSDB_M_FILE_REF);
			}
			
			// Adding below Resources for KRelease in Target Membership 
			if (nodeKeyName.equals(Constants.KRELEASE_RESOURCES_BUILDPHASE_REF)) {
				
				XCodeUtils.addBuildFileRef(document, Constants.LOGINBUNDLE_M_BUILDACTIONREF1,
						Constants.LOGINBUNDLE_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.LOGINBUNDLE_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.ALIPAYBUNDLE_BUILDACTIONREF,
						Constants.ALIPAY_BUNDLE_FILE_REF, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ALIPAYBUNDLE_BUILDACTIONREF,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.PAYMENTCENTER_BUNDLE_BUILDACTIONREF,
						Constants.PAYMENTCENTER_BUNDLE_FILE_REF, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.PAYMENTCENTER_BUNDLE_BUILDACTIONREF,
						nod);
			}

			if (nodeKeyName.equals(Constants.KRELEASE_SOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildFileRef(document, Constants.AMWAYLOGIN_M_BUILDACTIONREF1,
						Constants.AMWAYLOGIN_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.AMWAYLOGIN_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.LOGINMANAGER_M_BUILDACTIONREF1,
						Constants.LOGINMANAGER_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.LOGINMANAGER_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.WXAPIMANAGER_M_BUILDACTIONREF1,
						Constants.WXAPIMANAGER_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.WXAPIMANAGER_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.ISSMSGOPERATOR_M_BUILDACTIONREF1,
						Constants.ISSMSGOPERATOR_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document,
						Constants.ISSMSGOPERATOR_M_BUILDACTIONREF1, nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.ISSMSGCENTER_M_BUILDACTIONREF1,
						Constants.ISSMSGCENTER_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ISSMSGCENTER_M_BUILDACTIONREF1,
						nod);

				XCodeUtils.addBuildFileRef(document, Constants.ISSMSG_M_BUILDACTIONREF1,
						Constants.ISSMSG_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ISSMSG_M_BUILDACTIONREF1, nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.ISSDESC_M_BUILDACTIONREF1,
						Constants.ISSDESC_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ISSDESC_M_BUILDACTIONREF1, nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.ISSNWTOOL_M_BUILDACTIONREF1,
						Constants.ISSNWTOOL_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ISSNWTOOL_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.ISSURL_M_BUILDACTIONREF1,
						Constants.ISSURL_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ISSURL_M_BUILDACTIONREF1, nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.MSGALERTVW_M_BUILDACTIONREF1,
						Constants.MSGALERTVW_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.MSGALERTVW_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.SVRADIAL_M_BUILDACTIONREF1,
						Constants.SVRADIAL_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.SVRADIAL_M_BUILDACTIONREF1, nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.SVPROGRESSANIM_M_BUILDACTIONREF1,
						Constants.SVPROGRESSANIM_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document,
						Constants.SVPROGRESSANIM_M_BUILDACTIONREF1, nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.SVINDEFINITE_M_BUILDACTIONREF1,
						Constants.SVINDEFINITE_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.SVINDEFINITE_M_BUILDACTIONREF1,
						nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.SVPROGRESSHUD_M_BUILDACTIONREF1,
						Constants.SVPROGRESSHUD_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document,
						Constants.SVPROGRESSHUD_M_BUILDACTIONREF1, nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.ZHGM_M_BUILDACTIONREF1,
						Constants.ZHGM_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ZHGM_M_BUILDACTIONREF1,
						nod);
				

				XCodeUtils.addBuildFileRef(document, Constants.LNM_M_BUILDACTIONREF1,
						Constants.LNM_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.LNM_M_BUILDACTIONREF1,
						nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.WECHATSHAREFFI_M_BUILDFILE_REF,
						Constants.WECHATSHAREFFI_M_FILE_REF, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.WECHATSHAREFFI_M_BUILDFILE_REF,
						nod);
			}
			
			// Adding the Action Build Mask for KRelease
			if (nodeKeyName.equals(Constants.KRELEASE_RESOURCES_BUILDPHASE_REF)) {
				XCodeUtils.addBuildActionMaskRef(document, Constants.MJREFRESH_BUILDFILE_REF, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.ASSETS_BUILDFILE_REF, nod);
				
				XCodeUtils.addBuildFileRef(document, Constants.SVPBUNDLE_M_BUILDACTIONREF1,
						Constants.SVPBUNDLE_M_BUILDACTIONREF2, nod);
				XCodeUtils.addBuildActionMaskRef(document, Constants.SVPBUNDLE_M_BUILDACTIONREF1,
						nod);
			}
			// Removing below File Reference in Build Phases for KonyJS
			if (nodeKeyName.equals(Constants.KONYJS_RESOURCES_BUILDPHASE_REF)) {
				XCodeUtils.removeBuildActionMaskRef(document, Constants.MJREFRESH_BUILDFILE_REF, nod);
				XCodeUtils.removeBuildActionMaskRef(document, Constants.ASSETS_BUILDFILE_REF, nod);
			}
		}
	}

	/**
	 * Set the Build Settings for KRelease or KonyJS with the specified properties
	 * @param document
	 * @param nod
	 * @param nodeKeyName
	 * @param properties
	 * @param isKonyJS
	 */
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

	/**
	 * Method to filter the Properties not used in KonyJS
	 * @param properties
	 * @return
	 */
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
    public static void getFileReferences(NodeList nodeKeyList, String region) {
        String nodeKeyName = "";
        boolean isAlt1_1xFound = false;
        boolean isAlt1_2xFound = false;
        boolean isAlt1_3xFound = false;
        boolean isAlt_1xFound = false;
        boolean isAlt_2xFound = false;
        boolean isAlt_3xFound = false;
        
        if ("APAC_IND".equalsIgnoreCase(region)) {
            for (int i = 0; i < nodeKeyList.getLength(); i++) {
                Node nod = nodeKeyList.item(i);
                if (nod.getNodeType() == 1) {
                    nodeKeyName = nod.getFirstChild().getNodeValue();
                }
                if ("path".equalsIgnoreCase(nodeKeyName)) {
                    Node stringNode = nod.getNextSibling();
                    String fileName = stringNode.getTextContent();
                    if (Constants.ICON_CHEVRON_LEFT_ALT1_1X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT1_1X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt1_1xFound = true;
                        }
                    }
                    if (Constants.ICON_CHEVRON_LEFT_ALT1_2X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT1_2X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt1_2xFound = true;
                        }
                    }
                    if (Constants.ICON_CHEVRON_LEFT_ALT1_3X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT1_3X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt1_3xFound = true;
                        }
                    }
                    if (Constants.ICON_CHEVRON_LEFT_ALT_1X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT_1X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt_1xFound = true;
                        }
                    }
                    if (Constants.ICON_CHEVRON_LEFT_ALT_2X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT_2X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt_2xFound = true;
                        }
                    }
                    if (Constants.ICON_CHEVRON_LEFT_ALT_3X_FILE_NAME.equalsIgnoreCase(fileName)) {
                        Node keyNode = nod.getParentNode().getPreviousSibling();
                        if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
                            Constants.ICON_CHEVRON_LEFT_ALT_3X_FILE_REF = keyNode
                            .getTextContent();
                            isAlt_3xFound = true;
                        } 
                    } 
                } 
                if (isAlt1_1xFound && isAlt1_2xFound && isAlt1_3xFound && 
                    isAlt_1xFound && isAlt_2xFound && isAlt_3xFound) {
                    break;
                }
            }
        }
	}

	/**
	 * Method for fetching the File References for China specific files used in 
	 * other subsequent methods for updation/deletion
	 * @param nodeKeyList
	 * @param region
	 */
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
		boolean isWCHSFileFound = false;
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
		boolean isAliBundleFileFound  = false;
		boolean isPCBundleFileFound = false;

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
				if (nodeKeyName.equals(Constants.DTBAPPDLG_M_BUILDACTREF_KONYJS)
						|| nodeKeyName.equals(Constants.KONYJS_M_BUILDACTIONREF)) {
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
								if (Constants.SVPROGRESSHUD_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.SVPROGRESSHUD_M_FILE_REF = nodeKeyName;
									Constants.SVPROGRESSHUD_M_BUILDFILE_REF1 = reverseFileRefMap
											.get(nodeKeyName);
									isSVDFileFound = true;
								}
								if (Constants.ZHUGE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.ZHUGE_M_FILE_REF = nodeKeyName;
									Constants.ZHUGE_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHFileFound = true;
								}
								if (Constants.ZHUGECONFIG_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.ZHUGECONFIG_M_FILE_REF = nodeKeyName;
									Constants.ZHUGECONFIG_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHCFileFound = true;
								}
								if (Constants.ZHUGEBASE64_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.ZHUGEBASE64_M_FILE_REF = nodeKeyName;
									Constants.ZHUGEBASE64_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isZHBFileFound = true;
								}
								if (Constants.SVRADIAL_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.SVRADIAL_M_FILE_REF = nodeKeyName;
									// SVRADIAL_M_BUILDFILE_REF =
									// reverseFileRefMap.get(nodeKeyName);
									isSVRFileFound = true;
								}
								if (Constants.SVPROGRESSANIM_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.SVPROGRESSANIM_M_FILE_REF = nodeKeyName;
									// SVPROGRESSANIM_M_BUILDFILE_REF
									// =reverseFileRefMap.get(nodeKeyName);
									isSVPFileFound = true;
								}
								if (Constants.SVINDEFINITE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.SVINDEFINITE_M_FILE_REF = nodeKeyName;
									// SVINDEFINITE_M_BUILDFILE_REF
									// =reverseFileRefMap.get(nodeKeyName);
									isSVIFileFound = true;
								}
								if (Constants.UIMASADD_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.UIMASADD_M_FILE_REF = nodeKeyName;
									Constants.UIMASADD_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isUIMASFileFound = true;
								}
								if (Constants.MASVIEWCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.MASVIEWCONS_M_FILE_REF = nodeKeyName;
									Constants.MASVIEWCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMVCFileFound = true;
								}
								if (Constants.MASCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.MASCONS_M_FILE_REF = nodeKeyName;
									Constants.MASCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCFileFound = true;
								}
								if (Constants.MASCONSMAKER_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.MASCONSMAKER_M_FILE_REF = nodeKeyName;
									Constants.MASCONSMAKER_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCMFileFound = true;
								}
								if (Constants.MASCOMPCONS_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.MASCOMPCONS_M_FILE_REF = nodeKeyName;
									Constants.MASCOMPCONS_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMCCFileFound = true;
								}
								if (Constants.JSONMODEL_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.JSONMODEL_M_FILE_REF = nodeKeyName;
									Constants.JSONMODEL_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isJMFileFound = true;
								}
								if (Constants.ISSMSG_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.ISSMSG_M_FILE_REF = nodeKeyName;
									Constants.ISSMSG_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isISSMsgFileFound = true;
								}
								if (Constants.MJREFRESH_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.MJREFRESH_FILE_REF = nodeKeyName;
									Constants.MJREFRESH_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isMJRFileFound = true;
								}
								if (Constants.ASSETS_BUNDLE_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.ASSETS_FILE_REF = nodeKeyName;
									Constants.ASSETS_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isAXFileFound = true;
								}

								if (Constants.CDVAUTH_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.CDVAUTH_M_FILE_REF = nodeKeyName;
									Constants.CDVAUTH_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVFileFound = true;
								}
								if (Constants.CBROW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.CBROW_M_FILE_REF = nodeKeyName;
									Constants.CBROW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCBRWFileFound = true;
								}
								if (Constants.WECHATSHAREFFI_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.WECHATSHAREFFI_M_FILE_REF = nodeKeyName;
									Constants.WECHATSHAREFFI_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isWCHSFileFound = true;
								}
								if (Constants.PGNATIVE_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.PGNATIVE_M_FILE_REF = nodeKeyName;
									Constants.PGNATIVE_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isPGNFileFound = true;
								}
								if (Constants.CDVBARSCAN_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.CDVBARSCAN_M_FILE_REF = nodeKeyName;
									Constants.CDVBARSCAN_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVBSFileFound = true;
								}
								if (Constants.NSAC_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									Constants.NSAC_M_FILE_REF = nodeKeyName;
									Constants.NSAC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isNSACFileFound = true;
								}
								if (Constants.SCANVW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.SCANVW_M_FILE_REF = nodeKeyName;
									Constants.SCANVW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isSCANVWFileFound = true;
								}
								if (Constants.QRCVC_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.QRCVC_M_FILE_REF = nodeKeyName;
									Constants.QRCVC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isQRCVCFileFound = true;
								}
								if (Constants.CDVC_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									Constants.CDVC_M_FILE_REF = nodeKeyName;
									Constants.CDVC_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVCFileFound = true;
								}
								if (Constants.AHAUTHHELP_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.AHAUTHHELP_M_FILE_REF = nodeKeyName;
									Constants.AHAUTHHELP_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isAHAHFileFound = true;
								}
								if (Constants.CDVJHW_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.CDVJHW_M_FILE_REF = nodeKeyName;
									Constants.CDVJHW_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isCDVJHMFileFound = true;
								}
								if (Constants.NSDICTEX_M_FILE_NAME
										.equalsIgnoreCase(fileName)) {
									Constants.NSDICTEX_M_FILE_REF = nodeKeyName;
									Constants.NSDICTEX_M_BUILDFILE_REF = reverseFileRefMap
											.get(nodeKeyName);
									isNSDEXFileFound = true;
								}
								if (Constants.NSDB_M_FILE_NAME.equalsIgnoreCase(fileName)) {
									Constants.NSDB_M_FILE_REF = nodeKeyName;
									Constants.NSDB_M_BUILDFILE_REF = reverseFileRefMap
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
					if (Constants.LOGIN_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.LOGINBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLoginBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.WEIBO_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.WEIBOBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isWeiboBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.UIMODEL_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.UIMODEL_BUNDLE_FILE_REF = keyNode
									.getTextContent();
							isWeiboBundleFileFound = true;
						}
					}
				}
				if ("name".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SYSCONFIG_FW_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SYSCONFIG_FW_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSysConfigFWFileFound = true;
						}
					}
				}
				if ("name".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.CORETELEPHONY_FW_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.CORETELEPHONY_FW_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isCoreTeleFWFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.AMWAYLOGIN_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.AMWAYLOGIN_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isAmwayLoginFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.LOGINMANAGER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.LOGINMANAGER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLoginManagerFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.WXAPIMANAGER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.WXAPIMANAGER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isWXAPIManagerFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSMSGOPERATOR_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSMSGOPERATOR_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSMsgOperatorFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSMSGCENTER_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSMSGCENTER_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSMsgCenterFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSMSG_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSMSG_M_BUILDACTIONREF2 = keyNode.getTextContent();
							isISSMSGFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSDESC_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSDESC_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSDescFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSNWTOOL_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSNWTOOL_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSNEToolFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ISSURL_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ISSURL_M_BUILDACTIONREF2 = keyNode.getTextContent();
							isISSURLFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.MSGALERTVW_BUNDLE_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.MSGALERTVW_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isISSURLFileFound = true;
						}
					}
				}

				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SVP_BUNDLE_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SVPBUNDLE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SVRADIAL_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SVRADIAL_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVRRefFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SVPROGRESSANIM_M_FILE_NAME
							.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SVPROGRESSANIM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPARefFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SVINDEFINITE_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SVINDEFINITE_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVIRefFileFound = true;
						}
					}
				}

				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.SVPROGRESSHUD_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.SVPROGRESSHUD_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isSVPHRefFileFound = true;
						}
					}
				}
				
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.ZHGM_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ZHGM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isZHGMRefFileFound = true;
						}
					}
				}
				
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String loginBundle = stringNode.getTextContent();
					if (Constants.LNM_M_FILE_NAME.equalsIgnoreCase(loginBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.LNM_M_BUILDACTIONREF2 = keyNode
									.getTextContent();
							isLNMRefFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String aliBundle = stringNode.getTextContent();
					if (Constants.ALIPAYBUNDLE_BUNDLE_FILE_NAME.equalsIgnoreCase(aliBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.ALIPAY_BUNDLE_FILE_REF = keyNode
									.getTextContent();
							isAliBundleFileFound = true;
						}
					}
				}
				if ("path".equalsIgnoreCase(nodeKeyName)) {
					Node stringNode = nod.getNextSibling();
					String paycntBundle = stringNode.getTextContent();
					if (Constants.PAYMENTCENTER_BUNDLE_FILE_NAME.equalsIgnoreCase(paycntBundle)) {
						Node keyNode = nod.getParentNode().getPreviousSibling();
						if ("key".equalsIgnoreCase(keyNode.getNodeName())) {
							Constants.PAYMENTCENTER_BUNDLE_FILE_REF = keyNode
									.getTextContent();
							isPCBundleFileFound = true;
						}
					}
				}

				if (("CN_DH".equalsIgnoreCase(region))
						&& ("path".equalsIgnoreCase(nodeKeyName))) {
					Node pathNode = nod.getNextSibling();
					Node fileTypeNode = nod.getPreviousSibling();
					String path = pathNode.getTextContent();
					String fileType = fileTypeNode.getTextContent();
					if (Constants.JSMESSAGE_CENTER_FFI_FILE_NAME.equals(path)) {
						pathNode.setTextContent(Constants.JSMESSAGE_CENTER_FFI_FILE_NAME
								+ "m");
						if (Constants.JSMESSAGE_CENTER_FFI_C_FILE_TYPE.equals(fileType)) {
							fileTypeNode
									.setTextContent(Constants.JSMESSAGE_CENTER_FFI_CPP_FILE_TYPE);
						} else if (Constants.JSMESSAGE_CENTER_FFI_FILE_NAME
								.equals(fileType)) {
							Node nameNode = fileTypeNode.getPreviousSibling();
							if ("name".equals(nameNode.getTextContent())) {
								Node typeValeN = nameNode.getPreviousSibling();
								Node fileTypeN = typeValeN.getPreviousSibling();
								if ("lastKnownFileType".equals(fileTypeN
										.getTextContent())) {
									typeValeN
											.setTextContent(Constants.JSMESSAGE_CENTER_FFI_CPP_FILE_TYPE);
									fileTypeNode
											.setTextContent(Constants.JSMESSAGE_CENTER_FFI_FILE_NAME
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
						&& isCBRWFileFound && isPGNFileFound && isWCHSFileFound
						&& isCDVBSFileFound && isNSACFileFound
						&& isSCANVWFileFound && isQRCVCFileFound
						&& isCDVCFileFound && isAHAHFileFound
						&& isCDVJHMFileFound && isNSDEXFileFound
						&& isNSDBFileFound && isSVPBundleFileFound
						&& isSVRRefFileFound && isSVPARefFileFound
						&& isSVIRefFileFound && isSVPHRefFileFound 
						&& isZHGMRefFileFound && isLNMRefFileFound
						&& isAliBundleFileFound && isPCBundleFileFound) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		xCodeAutomationForViz8(
				"/Users/ncl/Desktop/mCoE/AutomationFiles/XCodeCurBuildProperties.xml",
				"/Users/ncl/Desktop/mCoE/AutomationFiles/Config.properties",
				"10", "false", "", "NA","");
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
