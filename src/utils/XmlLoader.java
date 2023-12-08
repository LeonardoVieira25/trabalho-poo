package src.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import src.components.Player;

import org.w3c.dom.Node;
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

                float spawnRate = Float
                        .parseFloat(levelElement.getElementsByTagName("spawnRate").item(0).getTextContent());
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

    public static void saveNewPlayer(Player player) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("players");
            doc.appendChild(rootElement);

            // Create player element
            Element playerElement = doc.createElement("player");
            rootElement.appendChild(playerElement);

            // Set attributes
            playerElement.setAttribute("id", String.valueOf(player.getId()));
            playerElement.setAttribute("username", player.getUsername());
            playerElement.setAttribute("password", player.getPassword());
            playerElement.setAttribute("vidaInicila", String.valueOf(player.getVidaInicila()));
            playerElement.setAttribute("maxPontuacao", String.valueOf(player.getMaxPontuacao()));
            playerElement.setAttribute("spawnRate", String.valueOf(player.getSpawnRate()));
            playerElement.setAttribute("spread", String.valueOf(player.getSpread()));

            // Write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/assets/players.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Player getPlayer(String username, String password) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("src/assets/players.xml"));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            // Query for the player with the specified username and password
            String expression = String.format("/root/player[@username='%s' and @password='%s']", username, password);
            Node playerNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);

            if (playerNode != null && playerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element playerElement = (Element) playerNode;

                System.out.println("Integer.parseInt(playerElement.getAttribute(id))");
                System.out.println(Integer.parseInt(playerElement.getAttribute("id")));

                // Create a new Player object with the attributes from the XML element
                Player player = new Player(null);
                player.setId(Integer.parseInt(playerElement.getAttribute("id")));
                player.setUsername(playerElement.getAttribute("username"));
                player.setPassword(playerElement.getAttribute("password"));
                player.setVidaInicila(Integer.parseInt(playerElement.getAttribute("vidaInicila"))); // Ajuste aqui
                player.setMaxPontuacao(Integer.parseInt(playerElement.getAttribute("maxPontuacao"))); // Ajuste aqui
                player.setSpawnRate(Float.parseFloat(playerElement.getAttribute("spawnRate")));
                player.setSpread(Float.parseFloat(playerElement.getAttribute("spread")));

                return player;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}