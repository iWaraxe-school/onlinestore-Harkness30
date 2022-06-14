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
    private Map<String, String> sortOptions = new LinkedHashMap<>();
    private static final File file = new File("store\\src\\main\\resources\\sortConfigs\\ChangeableConfig.xml");
    private static final File defaultOrder = new File ("store\\src\\main\\resources\\sortConfigs\\DefaultConfig.xml");
    private static Document config = parse();
    private static Node root = config.getFirstChild();
    private static NodeList elements = root.getChildNodes();


    //Main method, which returns the Map, filled by all parameters
    public Map<String, String> getSortOptions() {

        assert config != null;
        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            sortOptions.put(elements.item(i).getNodeName(), elements.item(i).getTextContent());
        }
        return sortOptions;
    }

    //parsing the xml-file to the document, which can be used in other methods
    private static Document parse() {
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

    //if user wants to order list of products by the existing field,
    // this method will be used to set the order of sorting ( asc/desc )
    public static void updateType(String field, String type) {

        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.item(i).getNodeType() != Node.ELEMENT_NODE || !elements.item(i).getNodeName().equals(field)) {
                continue;
            }
            elements.item(i).setTextContent(type);
        }
    }

    //in case, if user wants to order list by the field, which is not in the DefaultConfig.xml
    //this method will create such field and add it into the DefaultConfig.xml
    private static void addFieldToConfig(String field, String type) {

        Element newField = config.createElement(field);
        newField.appendChild(config.createTextNode(type));
        root.appendChild(newField);
    }

    //method that allows user to choose a field and an order
    public void setOptions(String field, String type) {

        assert config != null;
        List<String> availableFields = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            availableFields.add(elements.item(i).getNodeName());
        }
        if (availableFields.contains(field)) {
            updateType(field, type);
        } else {
            addFieldToConfig(field, type);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(config);
            StreamResult result = new StreamResult(new File(file.toURI()));
            transformer.transform(source, result);
            config.normalizeDocument();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
