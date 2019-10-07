package com.kony.xcode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XCodeUtils {
	
	/**
	 * Adds file reference for Localized App name with respective strings file
	 * @param document
	 * @param folderRef
	 * @param node
	 * @param fileRefs
	 * @return
	 */
	public static Element addLocalizedStringsFileRef(Document document,
			String buildFileRef, String locale, Node node) {
		String lcl = locale + ".lproj";
		
		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(buildFileRef);
		refKey.appendChild(refKeyTxt);

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
		Text refKey2ValueTxt = document.createTextNode("text.plist.strings");
		refKey2Value.appendChild(refKey2ValueTxt);


		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("name");
		refKey3.appendChild(refKey3Txt);

		Element refKey3Value = document.createElement("string");
		Text refKey3ValueTxt = document.createTextNode(locale);
		refKey3Value.appendChild(refKey3ValueTxt);

		Element refKey4 = document.createElement("key");
		Text refKey4Txt = document.createTextNode("path");
		refKey4.appendChild(refKey4Txt);
		
		Element refKey4Value = document.createElement("string");
		Text refKey4ValueTxt = document.createTextNode(lcl+"/InfoPlist.strings");
		refKey4Value.appendChild(refKey4ValueTxt);

		Element refKey5 = document.createElement("key");
		Text refKey5Txt = document.createTextNode("sourceTree");
		refKey5.appendChild(refKey5Txt);
		
		Element refKey5Value = document.createElement("string");
		Text refKey5ValueTxt = document.createTextNode("<group>");
		refKey5Value.appendChild(refKey5ValueTxt);


		Element dictRefElement = document.createElement("dict");
		dictRefElement.appendChild(refKey1);
		dictRefElement.appendChild(refKey1Value);

		dictRefElement.appendChild(refKey2);
		dictRefElement.appendChild(refKey2Value);

		dictRefElement.appendChild(refKey3);
		dictRefElement.appendChild(refKey3Value);
		
		dictRefElement.appendChild(refKey4);
		dictRefElement.appendChild(refKey4Value);

		dictRefElement.appendChild(refKey5);
		dictRefElement.appendChild(refKey5Value);

		node.getParentNode().insertBefore(refKey, node);
		node.getParentNode().insertBefore(dictRefElement, node);
		return refKey;
	}
	/**
	 * Adds Folder Reference for Localized App name with respective strings file
	 * @param document
	 * @param folderRef
	 * @param node
	 * @param fileRefs
	 * @return
	 */
	public static Element addLocalizedFolders(Document document,
			String folderRef, Node node, String[] fileRefs) {
		Element refKey = document.createElement("key");
		Text refKeyTxt = document.createTextNode(folderRef);
		refKey.appendChild(refKeyTxt);

		Element dictRefElement = document.createElement("dict");
		
		Element refKey0 = document.createElement("key");
		Text refKey0Txt = document.createTextNode("children");
		refKey0.appendChild(refKey0Txt);
		
		Element arrayAttrib = document.createElement("array");
		for(int index=0; index < fileRefs.length; index++){
			String locale = fileRefs[index];
			locale += "_lproj"; 
			locale = locale.toUpperCase();
			Element refKey01Value = document.createElement("string");
			Text refKey01ValueTxt = document.createTextNode(locale);
			refKey01Value.appendChild(refKey01ValueTxt);
			arrayAttrib.appendChild(refKey01Value);
		}

		Element refKey1 = document.createElement("key");
		Text refKey1Txt = document.createTextNode("isa");
		refKey1.appendChild(refKey1Txt);

		Element refKey1Value = document.createElement("string");
		Text refKey1ValueTxt = document.createTextNode("PBXVariantGroup");
		refKey1Value.appendChild(refKey1ValueTxt);

		Element refKey2 = document.createElement("key");
		Text refKey2Txt = document.createTextNode("name");
		refKey2.appendChild(refKey2Txt);
		
		Element refKey2Value = document.createElement("string");
		Text refKey2ValueTxt = document.createTextNode("InfoPlist.strings");
		refKey2Value.appendChild(refKey2ValueTxt);

		Element refKey3 = document.createElement("key");
		Text refKey3Txt = document.createTextNode("sourceTree");
		refKey3.appendChild(refKey3Txt);
		
		Element refKey3Value = document.createElement("string");
		Text refKey3ValueTxt = document.createTextNode("<group>");
		refKey3Value.appendChild(refKey3ValueTxt);
		
		dictRefElement.appendChild(refKey0);
		dictRefElement.appendChild(arrayAttrib);

		dictRefElement.appendChild(refKey1);
		dictRefElement.appendChild(refKey1Value);

		dictRefElement.appendChild(refKey2);
		dictRefElement.appendChild(refKey2Value);

		dictRefElement.appendChild(refKey3);
		dictRefElement.appendChild(refKey3Value);

		node.getParentNode().insertBefore(refKey, node);
		node.getParentNode().insertBefore(dictRefElement, node);
		return refKey;
	}

	/**
	 * @param document
	 * @param buildFileRef
	 * @param fileRef
	 * @param node
	 * @return
	 */
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
	
	

	/**
	 * Adds a File Reference with specified Compiler Flags
	 * Compiler Flags - (-fobjc-arc)
	 * @param document
	 * @param buildFileRef
	 * @param fileRef
	 * @param node
	 * @return
	 */
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

	/**
	 * Add a Attributes File reference
	 * @param document
	 * @param buildFileRef
	 * @param fileRef
	 * @param node
	 * @return
	 */
	public static Element addBuildAttributesFileRef(Document document,
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

	
	/**
	 * Adds a New File Reference to a parent Node
	 * @param document
	 * @param fileName
	 * @param fileRef
	 * @param parentNode
	 * @param isFileEncoding
	 */
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

	
	/**
	 * Add an Entitlement Reference with the Path of the Entitlement file
	 * @param document
	 * @param fileName
	 * @return
	 */
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

	/**
	 * Add a Folder/File Reference as a Child to a Custom Template (VMAppWithKonylib) or to another folder
	 * @param document
	 * @param buildFileRef
	 * @param nod
	 */
	public static void addFolderRefasChild(Document document,
			String folderRef, Node nod) {
		Element fileRef = document.createElement("string");
		Text dtbBuildRefValue = document.createTextNode(folderRef);
		fileRef.appendChild(dtbBuildRefValue);

		Node dict = nod.getNextSibling();
		if ("dict".equalsIgnoreCase(dict.getNodeName())) {
			Node childKey = dict.getFirstChild();
			if ("key".equalsIgnoreCase(childKey.getNodeName())) {
				if ("children".equalsIgnoreCase(childKey
						.getFirstChild().getNodeValue())) {
					Node array = childKey.getNextSibling();
					if ("array".equalsIgnoreCase(array.getNodeName())) {
						array.appendChild(fileRef);
					}
				}
			}
		}
	}
	

	/**
	 * Add a File Reference in Build Action Mask shown in Build Phases 
	 * @param document
	 * @param buildFileRef
	 * @param nod
	 */
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

	/**
	 * Removes a File Reference in Build Action Mask shown in Build Phases 
	 * @param document
	 * @param buildFileRef
	 * @param nod
	 */
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
	 * Updates the Compiler Flags for a File with (-fobjc-arc)
	 * @param nod
	 * @param fileRef
	 */
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

	/**
	 * Remove compiler flags for a File (Sets as empty)
	 * @param nod
	 * @param fileRef
	 */
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
					}
				}
			}
		}
	}
}
