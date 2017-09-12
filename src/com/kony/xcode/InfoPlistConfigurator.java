package com.kony.xcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * 
 * This will reads the Info.Plist entries that needs to updated from json file, 
 * reads the Info.Plist XML file and updates the specified entries in xml and 
 * finally writes to the hard disk  
 *@author Khadeer
 *
 */
public class InfoPlistConfigurator {

	private static List<String> keysListToUpdate = new ArrayList<String>();
	private static Set<String> existingKeysSet = new HashSet<String>();
	private static Map<String, JSONObject> infoPlistKeyDataMap = new HashMap<String, JSONObject>();
	
	/**
	 * Reads the Info.Plist json file which specifies the entries to be modified
	 * @param infoPlistConfigPath
	 */
	public static void getInfoPlistConfigs(String infoPlistConfigPath, String infoPlistXmlPath, String bundleKey) {
		try {
			System.out.println("Reading the Info.Plist configurations from: "+infoPlistConfigPath +"...\n");
			FileReader fr = new FileReader(new File(infoPlistConfigPath));
			JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(fr);
			JSONObject jsonObject = (JSONObject) object;			
			JSONArray infoPlistData = (JSONArray) jsonObject.get("infoplist");			
			System.out.println("The following properties and their actions as specified in Info.Plist configuration which needs to be updated,");
			System.out.println("---------------------------------------------------------------------------------------------");
			for(int i=0; i<infoPlistData.size(); i++) {
				JSONObject entryObject = (JSONObject) infoPlistData.get(i);				
				keysListToUpdate.add((String)entryObject.get("key"));
				infoPlistKeyDataMap.put((String)entryObject.get("key"), entryObject);
				System.out.println((String)entryObject.get("key") + " - Action: " +(String)entryObject.get("action"));
			}
			
			if (bundleKey != "" && bundleKey != null) {
				JSONObject entryObject = new JSONObject();
				entryObject.put("key", "CFBundleIdentifier");
				entryObject.put("value", bundleKey);
				entryObject.put("type", "string");
				entryObject.put("action", "update");
				keysListToUpdate.add((String) entryObject.get("key"));
				infoPlistKeyDataMap.put((String) entryObject.get("key"), entryObject);
				System.out.println((String) entryObject.get("key")	+ " - Action: " + (String) entryObject.get("action"));
				
				entryObject = new JSONObject();
				entryObject.put("key", "KonyBundleIdentifier");
				entryObject.put("value", bundleKey);
				entryObject.put("type", "string");
				entryObject.put("action", "update");
				keysListToUpdate.add((String) entryObject.get("key"));
				infoPlistKeyDataMap.put((String) entryObject.get("key"), entryObject);
				System.out.println((String) entryObject.get("key")	+ " - Action: " + (String) entryObject.get("action"));
			}
			System.out.println("---------------------------------------------------------------------------------------------\n");		
			readAndUpdateInfoPlistXml(infoPlistXmlPath);
		} catch (FileNotFoundException e) {
			System.err.println("An error occurred while reading the config file: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("An error occurred while reading the config file: "+e.getMessage());
		} catch (ParseException e) {
			System.err.println("An error occurred while reading the config file: "+e.getMessage());
		}
	}

	private static void readAndUpdateInfoPlistXml(String infoPlistXmlPath) {
		try {
			System.out.println("Reading the Info.Plist XML file from: "+infoPlistXmlPath);
			DocumentBuilderFactory docBuilderFactory =  DocumentBuilderFactory.newInstance();
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(infoPlistXmlPath);			
			Element docElement = document.getDocumentElement();
			Node parentDictNode = docElement.getFirstChild();			
			NodeList entriesList = parentDictNode.getChildNodes();
			
			int j = 0;
			boolean isKey = false;
			String currentEntryKey = "";
			List<Node> listOfNodeToBeRemoved = new ArrayList<Node>();
			for (int i=0;i<entriesList.getLength();i++) {
				String textContent = entriesList.item(i).getTextContent();
				if(j == 0) {
					isKey = true;
					currentEntryKey = textContent;
				} else {
					isKey = false;
				}				
				
				if(keysListToUpdate.contains(currentEntryKey)) {
					//Entry exists					
					JSONObject infoPlistData = infoPlistKeyDataMap.get(currentEntryKey);
					String action = (String) infoPlistData.get("action");
					Object object = infoPlistData.get("value");
					System.out.println("Object is ::"+object.toString());
					String type = (String) infoPlistData.get("type");
					
					if(!isKey && (action.equals("add") || action.equals("update"))) {
						System.out.println("\nEntry is found, checking the values - "+ currentEntryKey);
						if(type.equals("string")) {
							String value = (String) object;
							if(textContent.equals(value)) {
								System.out.println("Values are same");
							} else {
								System.out.println("Updated the entry - "+ currentEntryKey +" : "+ value +"\n");						
								entriesList.item(i).setTextContent(value);
							}
						} else if(type.equals("boolean")) {
							String nodeName = entriesList.item(i).getNodeName();
							String value = (String) object;
							if(nodeName.equals(value)) {
								System.out.println("Values are same");
							} else {
								document.renameNode(entriesList.item(i), null, value);
								System.out.println("Updated the entry - "+ currentEntryKey +" : "+ value +"\n");
							}
						} else if(type.equals("array")) {
								JSONArray arrayData = (JSONArray) object;								
								NodeList orientNodeList = entriesList.item(i).getChildNodes();
								if(arrayData.size() == orientNodeList.getLength() && arrayData.size() == 1) {
									JSONObject obj1 = (JSONObject) arrayData.get(0);
									if(!obj1.get("string").equals(orientNodeList.item(0).getTextContent())) {										
										System.out.println("Updated the entry - "+ currentEntryKey +" : "+ (String)obj1.get("string") +"\n");						
										orientNodeList.item(0).setTextContent((String)obj1.get("string"));
									} else {
										System.out.println("Values are same");
									}
								} else {
									List<Node> listOfArrayOptionsToRemove = new ArrayList<Node>();
									for(int n=0;n<arrayData.size();n++){
										JSONObject obj1 = (JSONObject) arrayData.get(n);
										String value = (String)obj1.get("string");
										System.out.println("value ::"+value);
										boolean isExpectedOptionExists = false;
										for(int k=0;k<orientNodeList.getLength();k++) {
											if(value.equals(orientNodeList.item(k).getTextContent())) {											
												isExpectedOptionExists = true;
											} else if(!value.equals(orientNodeList.item(k).getTextContent())) {
												if(!isExpectedOptionExists && k == orientNodeList.getLength() - 1) {
													orientNodeList.item(k).setTextContent(value);
													isExpectedOptionExists = true;
												} else {
													listOfArrayOptionsToRemove.add(orientNodeList.item(k));
												}
											}						
										}
									}
									//JSONObject obj1 = (JSONObject) arrayData.get(0);
									//String value = (String)obj1.get("string");									
									//Delete the String Nodes in the array here
									for (Node arrayOptionToRemove : listOfArrayOptionsToRemove) {
										entriesList.item(i).removeChild(arrayOptionToRemove);
									}
									System.out.println("Updated the entry - "+ currentEntryKey);
								}
						}
						existingKeysSet.add(currentEntryKey);
					} else if(action.equals("delete")) {
						if(isKey)System.out.println("\nEntry to delete : "+ currentEntryKey);
						listOfNodeToBeRemoved.add(entriesList.item(i));
					}
				} else if(isKey) {
					//Here the Key is not there in the configs to update, so skip iterating it's value as well
					i++;
					j = 1;
				}												
				if(j == 1) {
					//Entry is completed, reset the count
					j = 0;
				} else {
					j++;
				}
			}
			
			//Delete the Entries here
			for (Node nodeToBeRemoved : listOfNodeToBeRemoved) {
				/*if (parentDictNode.isSameNode(nodeToBeRemoved.getParentNode())){
					
				}*/
				parentDictNode.removeChild(nodeToBeRemoved);				
			}
			if(!listOfNodeToBeRemoved.isEmpty()) {
				System.out.println("Deleted [" + (listOfNodeToBeRemoved.size()/2)+"] Entries...\n");
			}			

			//Add the Entries if any here
			for (String keyToUpdate : keysListToUpdate) {		
				if(!existingKeysSet.contains(keyToUpdate)) {
					JSONObject infoPlistData = infoPlistKeyDataMap.get(keyToUpdate);
					String action = (String) infoPlistData.get("action");
					if(action.equals("add") || action.equals("update")) {
						Object object = infoPlistData.get("value");
						String type = (String) infoPlistData.get("type");
						if(type.equals("boolean")) {
							// Add the boolean entry to the document
							createNodeEntry(document, parentDictNode, keyToUpdate, object, type);
							System.out.println("Adding a new boolean entry - "+ keyToUpdate+" : "+ (String) object +"\n");
						} else if(type.equals("string")) {
							// Add the string entry to the document
							createNodeEntry(document, parentDictNode, keyToUpdate, object , type);
							System.out.println("Adding a new string entry : "+ keyToUpdate+" : "+ (String) object +"\n");
						} else if(type.equals("array")) {
							// Add the array (of strings) entry to the document
							createNodeEntry(document, parentDictNode, keyToUpdate, object, "array");
							System.out.println("Adding a new array entry : "+ keyToUpdate+" : "+ (JSONArray) object +"\n");
						}
					}
				}
			}
			
			// Write the XML document to hard disk
			System.out.println("\nDocument updation is done, XML file writing started...");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DocumentType doctype = document.getDoctype();
            if(doctype != null) {
                transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            }
            
            DOMSource source = new DOMSource(document);            
            File newXmlFile = new File(infoPlistXmlPath);   
            StreamResult streamRes = new StreamResult(newXmlFile);
            transformer.transform(source, streamRes);
            System.out.println("Completed writing the XML file to hard disk!!!\n");
		} catch (ParserConfigurationException e) {
			System.err.println("An error occurred while parsing the xml file: "+ e.getMessage());
		} catch (SAXException e) {
			System.err.println("An error occurred while parsing the xml file: "+ e.getMessage());
		} catch (IOException e) {
			System.err.println("An error occurred while reading the xml file: "+ e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.err.println("An error occurred while writing the xml file: "+ e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println("An error occurred while writing the xml file: "+ e.getMessage());
		} catch (TransformerException e) {
			System.err.println("An error occurred while writing the xml file: "+ e.getMessage());
		}
	}

	private static void createNodeEntry(Document document, Node parentDictNode, String key, Object object, String type) {		
		Node keyNode = document.createElement("key");
		keyNode.setTextContent(key);			
		parentDictNode.appendChild(keyNode);
		if(type.equals("boolean")) {
			Node valueNode = document.createElement((String) object);				
			parentDictNode.appendChild(valueNode);
		} else if(type.equals("string")) {
			Node valueNode = document.createElement("string");
			valueNode.setTextContent((String) object);
			parentDictNode.appendChild(valueNode);
		} if(type.equals("array")) {
			//TODO As of now it is implemented only for array of strings
			Node arrayNode = document.createElement("array");

			JSONArray arrayData = (JSONArray) object;
			for(int i=0;i<arrayData.size();i++) {
				JSONObject obj = (JSONObject) arrayData.get(i);
				Node valueNode = document.createElement("string");
				valueNode.setTextContent((String) obj.get("string"));
				arrayNode.appendChild(valueNode);
			}			
			parentDictNode.appendChild(arrayNode);
		}		
	}
}
