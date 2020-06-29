package com.ec.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlUtil {

    public static String getNodeTextContent(String xml, String nodeTag){
        System.out.println("xml " +xml);
        System.out.println("nodeTag " +nodeTag);
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList node = doc.getElementsByTagName(nodeTag);
            System.out.println("nodeLeng " +node.getLength());
            Element element = (Element) node.item(0);
            System.out.println("element " +element.getTextContent());
           // System.out.println("element " +element.getTextContent());
            return element.getTextContent();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T unmarshall(String xml, Class<T> clazz) {
        try
            {
                JAXBContext jc = JAXBContext.newInstance(clazz);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                T obj = clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
                return obj;
            }catch (JAXBException e) 
            {
                e.printStackTrace();
                return null;
            }
            
}
}