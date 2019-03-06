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
	public static final String KRELEASE_NODE = "1D6058950D05DD3E006BFB54";
	public static final String KONYJS_DEBUG_NODE = "0C1CBA0320DB764200226998";
	public static final String KONYJS_PDEBUG_NODE = "0C1CBA0420DB764200226998";
	public static final String KONYJS_RELEASE_NODE = "0C1CBA0520DB764200226998";
	public static final String KONYJS_PROTECTED_NODE = "0C1CBA0620DB764200226998";
	public static final String KONYJS_DISTRIBUTION_NODE = "0C1CBA0720DB764200226998";
	private static final ArrayList<String> Properties = null;

	public static void xCodeAutomation(String XcodeXmlPath,
			String ConfigXmlPath, String XcodeVersion,
			String enablePushNotifications, String entitlementsFile,
			String region) {
		String configProperties = ConfigXmlPath;

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
			String ConfigXmlPath, String XcodeVersion,
			String enablePushNotifications, String entitlementsFile,
			String region) {

		String configProperties = ConfigXmlPath;

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

			// String nodeStringValue = "";
			for (int i = 0; i < nodeKeyList.getLength(); i++) {
				Node nod = nodeKeyList.item(i);
				if (nod.getNodeType() == Node.ELEMENT_NODE)
					nodeKeyName = nod.getFirstChild().getNodeValue();
				// nodeStringValue = nod.getNextSibling().getNodeValue();

				if (nodeKeyName.equals(KRELEASE_NODE)) {
					isProvisioningStyleAdded = false;
					kReleaseNodeFound = true;
					konyJSNodeFound = false;

				}

				if (nodeKeyName.equals(KONYJS_DEBUG_NODE)
						|| nodeKeyName.equals(KONYJS_PDEBUG_NODE)
						|| nodeKeyName.equals(KONYJS_RELEASE_NODE)
						|| nodeKeyName.equals(KONYJS_PROTECTED_NODE)
						|| nodeKeyName.equals(KONYJS_DISTRIBUTION_NODE)) {
					konyJSNodeFound = true;
					kReleaseNodeFound = false;
					isProvisioningStyleAdded = false;
				}

				if (nodeKeyName.equals("buildSettings"))
					isTargetBuildSettingsFound = true;

				if (nodeKeyName.equals("1D6058960D05DD3E006BFB54")) {
					isTargetBuildSettingsFound = false;
					kReleaseNodeFound = false;
					konyJSNodeFound = false;
					isProvisioningStyleAdded = false;
				}

				if (isTargetBuildSettingsFound && kReleaseNodeFound) {
					//Setting Code Signing Style to Manual 
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
					//Adding Code Signing Style to Manual
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
					//Setting Provisioning Profile from Properties
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
					setBuildSettingsParameters(document, nod, nodeKeyName,
							properties, true);
				}
				// Setting Region Specific Settings like adding extra files & settings
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
						dictNode.appendChild(cflagKey);
						dictNode.appendChild(dictElement);
						isProvisioningStyleAdded = true;
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
		}
		if ("NA".equalsIgnoreCase(region)) {
			// Adding Tealium Frameworks is not required as Frameworks are added
			// in the
			// Visualizer FFI itself
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
		//System.out.println("Actual Properties:" + properties.toString());
		ArrayList<String> ProfileProps = new ArrayList<String>(Arrays.asList(
				"DEVELOPMENT_TEAM", "CODE_SIGN_IDENTITY[sdk=iphoneos*]",
				"CODE_SIGN_IDENTITY", "PROVISIONING_PROFILE",
				"PROVISIONING_PROFILE[sdk=iphoneos*]",
				"PROVISIONING_PROFILE_NAME"));
		Properties temp = new Properties();
		Enumeration<Object> propKeys = properties.keys();
		while (propKeys.hasMoreElements()) {
			String key = (String) propKeys.nextElement();
			if (!ProfileProps.contains(key)) {
				temp.put(key, properties.get(key));
			}
		}

		//System.out.println("Filtered Properties:" + temp.toString());
		return temp;
	}

	public static void main(String[] args) {
		xCodeAutomationForViz8(
				"/Users/ncl/Desktop/mCoE/LAS/XCodeCurBuildProperties.xml",
				"/Users/ncl/Desktop/mCoE/LAS/Config.properties", "9", "false",
				"", "LAS");
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
