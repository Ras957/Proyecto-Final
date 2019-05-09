/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author carlosserrano
 */
public class CargarXML {

    public static ArrayList<String[]> getFields(File file) {
        ArrayList<String[]> result = new ArrayList();
        try {
           
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            if (doc.hasChildNodes()) {
                NodeList nodeList = doc.getDocumentElement().getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node tempNode = nodeList.item(i);
                    String[] nodeValue = new String[3]; //0 nombre col, prop maxlengh, prop maxvalue
                    if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (tempNode.getNodeName().equals("field")) {
                            nodeValue[0] = tempNode.getTextContent();
                            if (tempNode.hasAttributes()) {
                                NamedNodeMap nodeMap = tempNode.getAttributes();
                                for (int j = 0; j < nodeMap.getLength(); j++) {
                                    Node node = nodeMap.item(j);
                                    if (node.getNodeName().equals("maxLenght")) {
                                        nodeValue[1] = node.getNodeValue();
                                    }
                                    if (node.getNodeName().equals("maxValue")) {
                                        nodeValue[2] = node.getNodeValue();
                                    }

                                }
                            } 
                            result.add(nodeValue);
                        }
                    }
                    
                }

            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
            
        }
        return result;
    }

}
