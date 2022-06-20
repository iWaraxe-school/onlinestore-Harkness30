package utils.parserAndComparator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLParser {

    private static final File FILE = new File("store\\src\\main\\resources\\sortConfigs\\config.xml");
    private static final File DEFAULT_CONFIG = new File("store\\src\\main\\resources\\sortConfigs\\DefaultConfig.xml");
    private static final Document DEFAULT_STATE = parse(FILE);
    private static Document config = parse(FILE);
    private static Node root = config.getFirstChild();
    private static NodeList elements = root.getChildNodes();


    //Final method, which returns the Map, filled by sorting parameters
    public Map<String, String> getSortOptions(String field, String type) {

        Map<String, String> sortOptions = new LinkedHashMap<>();
        setOptions(field, type);
        assert config != null;
        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            sortOptions.remove(elements.item(i).getNodeName());
            sortOptions.put(elements.item(i).getNodeName(), elements.item(i).getTextContent());
        }
        return sortOptions;
    }
    public Map<String, String> sortDefault (){
        Map <String, String> defaultOptions = new LinkedHashMap<>();
        Document document = parse(DEFAULT_CONFIG);
        assert document != null;
        Node root = document.getFirstChild();
        NodeList tags = root.getChildNodes();
        for (int i=0; i< tags.getLength(); i++){
            if (tags.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
        defaultOptions.put(tags.item(i).getNodeName(), tags.item(i).getTextContent());
        }
        return defaultOptions;
    }

    //parsing the xml-FILE to the Document-class object, to be processed by other methods
    private static Document parse(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            document = dbf.newDocumentBuilder().parse(file);
            return document;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    //method that allows user to choose a field and an order
    private static void setOptions(String field, String type) {

        assert config != null;
        Element newField = config.createElement(field);
        newField.appendChild(config.createTextNode(type));
        root.appendChild(newField);
        setConfigs(config, FILE);
    }

    private static void setConfigs(Document src, File dest) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(src);
            StreamResult result = new StreamResult(new File(dest.toURI()));
            transformer.transform(source, result);           // adds new tags from created Document to the existing config.xml
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    public void cleanUpConfigs(){
        setConfigs(DEFAULT_STATE, FILE);
    }
}
