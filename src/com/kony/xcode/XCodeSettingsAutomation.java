/**
 * 
 */
package com.kony.xcode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

/**
 * @author KIT134
 *
 */
public class XCodeSettingsAutomation {
	
	//private static Map<String, JSONObject> comparsionMap = new HashMap<String, JSONObject>();  
	
	/**
	 * @param args
	 * @return 
	 * @return 
	 */
    
	public static void xCodeAutomation(String XcodeXmlPath,String ConfigXmlPath,String XcodeVersion,String enablePushNotifications,String entitlementsFile){
		
		String configProperties = ConfigXmlPath;
		//HashMap hm = new HashMap();
		
		System.out.println("Inside build settings");
		DocumentBuilderFactory docBuilderFactory =  DocumentBuilderFactory.newInstance();
		  try
		    {
		        docBuilderFactory.setIgnoringElementContentWhitespace(true);
				FileInputStream XcodeXML = new FileInputStream(new File(XcodeXmlPath));
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document document = docBuilder.parse(XcodeXML);		
				XPath xPath =  XPathFactory.newInstance().newXPath();
		        Properties properties = new java.util.Properties();
		        FileInputStream configFile = new FileInputStream(new File(configProperties));
				properties.load(configFile);
				//HashMap nodeData =  new HashMap();
				
				String expression = "//key";
				//String expressionValue = "//string";
				NodeList nodeKeyList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
				String nodeKeyName ="";
				String mainGroupValue = "";
				//String nodeStringValue = "";
				for (int i = 0; i < nodeKeyList.getLength(); i++) {

					Node nod = nodeKeyList.item(i);
			        if(nod.getNodeType() == Node.ELEMENT_NODE)
			        	nodeKeyName = nod.getFirstChild().getNodeValue();
			        	//nodeStringValue = nod.getNextSibling().getNodeValue();
			        
		        		if(nodeKeyName.equals("OTHER_LDFLAGS")){
							//System.out.println("Creating CODE_SIGN_ENTITLEMENTS");
							Element archkey = document.createElement("key");
					        Text archKeyValue = document.createTextNode("ONLY_ACTIVE_ARCH");
					        archkey.appendChild(archKeyValue);
					        //nod.getNextSibling().appendChild(string);
					        Element archString = document.createElement("string");
					        //System.out.println("Getting prop of ONLY_ACTIVE_ARCH::"+properties.getProperty("ONLY_ACTIVE_ARCH"));
					        Text archStringValue = document.createTextNode(properties.getProperty("ONLY_ACTIVE_ARCH"));
					        archString.appendChild(archStringValue);
					        
					        Element cflagKey = document.createElement("key");
					        Text cflagKeyValue = document.createTextNode("OTHER_CFLAGS");
					        cflagKey.appendChild(cflagKeyValue);
					        //nod.getNextSibling().appendChild(string);
					        Element cflagString = document.createElement("string");
					        //System.out.println("Getting prop of OTHER_CFLAGS::"+properties.getProperty("OTHER_CFLAGS"));
					        Text cflagStringValue = document.createTextNode(properties.getProperty("OTHER_CFLAGS"));
					        cflagString.appendChild(cflagStringValue);
					        
					        Node parentNode = nod.getParentNode();
					        parentNode.appendChild(archkey);
					        parentNode.appendChild(archString);
					        parentNode.appendChild(cflagKey);
					        parentNode.appendChild(cflagString);
						}
		        		int version = Integer.parseInt(XcodeVersion);
		        		if(version >= 8){
		        			if(nodeKeyName.equals("TargetAttributes")){
			        			System.out.println("Creating node for ProvisioningStyle ");
			        			Node dictNode = nod.getNextSibling();
			        			Element cflagKey = document.createElement("key");
						        Text cflagKeyValue = document.createTextNode("1D6058900D05DD3D006BFB54");
						        cflagKey.appendChild(cflagKeyValue);
						        
						        Element dictKey = document.createElement("key");
						        Text dictKeyTxt = document.createTextNode("ProvisioningStyle");
						        dictKey.appendChild(dictKeyTxt);
						        
						        Element dictKeyValue = document.createElement("string");
						        Text dictKeyValueTxt = document.createTextNode("Manual");
						        dictKeyValue.appendChild(dictKeyValueTxt);
						        
						        Element dictElement = document.createElement("dict");
						        dictElement.appendChild(dictKey);
						        dictElement.appendChild(dictKeyValue);
						        
						        if ("true".equalsIgnoreCase(enablePushNotifications)) {
						        	 System.out.println("Creating node for Push Notifications ");
						        	 Element capabilitiesKey = document.createElement("key");
								     Text capabilitiesKeyTxt = document.createTextNode("SystemCapabilities");
								     capabilitiesKey.appendChild(capabilitiesKeyTxt);
								     
								     Element pushKey = document.createElement("key");
								     Text pushKeyTxt = document.createTextNode("com.apple.Push");
								     pushKey.appendChild(pushKeyTxt);
								     
								     Element enableKey = document.createElement("key");
								     Text enableKeyTxt = document.createTextNode("enabled");
								     enableKey.appendChild(enableKeyTxt);
								        
								     Element enableKeyValue = document.createElement("string");
								     Text enableKeyValueTxt = document.createTextNode("1");
								     enableKeyValue.appendChild(enableKeyValueTxt);
								     
								     Element dictPushElement = document.createElement("dict");
								     dictPushElement.appendChild(enableKey);
								     dictPushElement.appendChild(enableKeyValue);
								     
								     Element dictCapabilitiesElement = document.createElement("dict");
								     dictCapabilitiesElement.appendChild(pushKey);
								     dictCapabilitiesElement.appendChild(dictPushElement);
								     
								     dictElement.appendChild(capabilitiesKey);
								     dictElement.appendChild(dictCapabilitiesElement);
						        }
						        dictNode.appendChild(cflagKey);
						        dictNode.appendChild(dictElement);
			        		}
		        		if ("true".equalsIgnoreCase(enablePushNotifications)) {
			        		if(nodeKeyName.equals("objects")){
								System.out.println("Creating node for Entitlements reference ");
								Node dictNode = nod.getNextSibling();
								Element entitlementRefKey = document.createElement("key");
								Text entitlementRefKeyTxt = document.createTextNode("26E284CF1E647A51005A33DA");
								entitlementRefKey.appendChild(entitlementRefKeyTxt);
								
								dictNode.appendChild(entitlementRefKey);
								dictNode.appendChild(addEntitlementRef(document, entitlementsFile));
							}
			        		
			        		if(nodeKeyName.equals("mainGroup")){
			        			if(nod.getNextSibling().getNodeName().equals("string")){
			        				mainGroupValue = nod.getNextSibling().getTextContent();
			        				System.out.println("Getting mainGroup node value ::: "+mainGroupValue);
			        			}
			        		}
			        		if(mainGroupValue != "" && nodeKeyName.equals(mainGroupValue)){
			        			Node arrayNode = nod.getNextSibling().getFirstChild().getNextSibling();
			        			Element string = document.createElement("string");
						        Text stringValue = document.createTextNode("26E284CF1E647A51005A33DA");
						        string.appendChild(stringValue);
						        arrayNode.appendChild(string);
			        		}
		        		}
		        	}
		        		
			        if(nod.getNextSibling().getNodeName().equals("string")){
			        	for (Object xpathExpressionObj : properties.keySet()) {
			        		String configKey = (String) xpathExpressionObj;
							String configValue = properties.getProperty(configKey);
							if(nodeKeyName.equals(xpathExpressionObj)){
								nod.getNextSibling().setTextContent(configValue);
								
								if(nodeKeyName.equals("CODE_SIGN_IDENTITY")){
									//System.out.println("Creating CODE_SIGN_ENTITLEMENTS");
									Element key = document.createElement("key");
							        Text keyValue = document.createTextNode("CODE_SIGN_ENTITLEMENTS");
							        key.appendChild(keyValue);
							        //nod.getNextSibling().appendChild(string);
							        Element string = document.createElement("string");
							        //System.out.println("Getting prop of CODE_SIGN_ENTITLEMENTS::"+properties.getProperty("CODE_SIGN_ENTITLEMENTS"));
							        Text stringValue = document.createTextNode(entitlementsFile);
							        string.appendChild(stringValue);
							        Node parentNode = nod.getParentNode();
							        parentNode.appendChild(key);
							        parentNode.appendChild(string);
								}
							}
						}
			        }else{
				        //Creating New Node Element
			        	//System.out.println("Inside array");
		        	
			        	for (Object xpathExpressionObj : properties.keySet()) {
							String configKey = (String) xpathExpressionObj;
							String configValue = properties.getProperty(configKey);
							//System.out.println("xpathExpressionObj is :"+xpathExpressionObj);
							if(nodeKeyName.equals(xpathExpressionObj)){
								//System.out.println("nodeKeyname and xpathExpressionObj are equal : "+xpathExpressionObj);
								Node siblingNode = nod.getNextSibling();
								if(siblingNode.getNodeName().equals("array")){
									NodeList arrayNode = siblingNode.getChildNodes();
									List<String> arrayRefVar = new ArrayList<String>();
									List<Node> nodesToRemove = new ArrayList<Node>();

									for(int h=0; h<arrayNode.getLength();h++){
										Node arrayNext = arrayNode.item(h);
										//String stringNew = arrayNext.getTextContent();
										String stringValue = arrayNext.getTextContent();
										if(stringValue != null && stringValue.length() > 0 && configValue.contains(stringValue)){	
											arrayRefVar.add(stringValue);
											nodesToRemove.add(arrayNext);
										}else{
											nodesToRemove.add(arrayNext);
										}
									}
									for (Node node : nodesToRemove) {
										((Node) arrayNode).removeChild(node);
									}
								    String[] strValues = configValue.split(",");
								    ArrayList<String> aListNumbers = new ArrayList<String>(Arrays.asList(strValues));
						                
						            for(int y=0; y<aListNumbers.size();y++){
						            	//System.out.println("aListNumbers is :"+aListNumbers.get(y));
						            	//Element array =  document.createElement("array");
								        Element string = document.createElement("string");
								        Text stringValue = document.createTextNode(aListNumbers.get(y));
								        string.appendChild(stringValue);
								        nod.getNextSibling().appendChild(string);
						            }
								}
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
	            if(doctype != null) {
	                transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
	                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
	            }
				OutputStreamWriter fileOutput = new OutputStreamWriter(fileOutputStream, "UTF-8");
				Result fileUpdate  = (Result) new StreamResult(fileOutput);
				transformer.transform(new DOMSource(document),fileUpdate );
				fileOutputStream.close();
				fileOutput.close();
	    }
	    catch (IOException ioe)
	    {
	      System.out.println("Exception while reading/updating properties file: " + ioe);
	      ioe.printStackTrace();
	    } catch (Throwable e) {
			
			e.printStackTrace();
		} 
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
		Text refKey2ValueTxt = document.createTextNode("text.plist.entitlements");
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
}
