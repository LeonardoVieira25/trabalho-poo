package src.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpression;

public class XmlLoader {

    public static void loadLevels() {
        try {
            File inputFile = new File("src/assets/niveis.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/root/level[@id='" + 1 + "']");

            Node levelNode = (Node) expr.evaluate(doc, XPathConstants.NODE);

            if (levelNode != null && levelNode.getNodeType() == Node.ELEMENT_NODE) {
                Element levelElement = (Element) levelNode;

                float spawnRate = Float.parseFloat(levelElement.getElementsByTagName("spawnRate").item(0).getTextContent());
                List<String> enemyTypes = new ArrayList<String>();
                for (int i = 0; i < levelElement.getElementsByTagName("enemyTypes").getLength(); i++) {
                    enemyTypes.add(levelElement.getElementsByTagName("enemyTypes").item(i).getTextContent());
                }
                // levelElement.getElementsByTagName("enemyTypes").item(0).getTextContent();

                
                System.out.println(spawnRate);
                System.out.println(enemyTypes.get(0));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}