/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
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
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.xpath.XPathExpression;

public class XmlLoader {

    public static void saveNewPlayer(Player player) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            File file = new File("src/assets/players.xml");
            if (file.exists()) {
                doc = dBuilder.parse(file);
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("root");
                doc.appendChild(rootElement);
            }

            Element playerElement = doc.createElement("player");
            playerElement.setAttribute("id", String.valueOf(player.getId()));
            playerElement.setAttribute("username", player.getUsername());
            playerElement.setAttribute("password", player.getPassword());
            playerElement.setAttribute("vidaInicial", String.valueOf(player.getVidaInicial()));
            playerElement.setAttribute("maxPontuacao", String.valueOf(player.getMaxPontuacao()));
            playerElement.setAttribute("spawnRate", String.valueOf(player.getSpawnRate()));
            playerElement.setAttribute("spread", String.valueOf(player.getSpread()));

            doc.getDocumentElement().appendChild(playerElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerPlayer(String username, String password) {
        Player player = new Player(null);
        player.setUsername(username);
        player.setPassword(password);
        player.setVidaInicial(10);
        player.setMaxPontuacao(0);
        player.setSpawnRate(5f);
        player.setSpread(0.5f);

        saveNewPlayer(player);
    }

    public static Player getPlayer(String username, String password) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("src/assets/players.xml"));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            String expression = String.format("/root/player[@username='%s' and @password='%s']", username, password);
            Node playerNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);

            if (playerNode != null && playerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element playerElement = (Element) playerNode;

                

                Player player = new Player(null);
                player.setId(Integer.parseInt(playerElement.getAttribute("id")));
                player.setUsername(playerElement.getAttribute("username"));
                player.setPassword(playerElement.getAttribute("password"));
                player.setVidaInicial(Integer.parseInt(playerElement.getAttribute("vidaInicial")));
                player.setMaxPontuacao(Integer.parseInt(playerElement.getAttribute("maxPontuacao")));
                player.setSpawnRate(Float.parseFloat(playerElement.getAttribute("spawnRate")));
                player.setSpread(Float.parseFloat(playerElement.getAttribute("spread")));

                return player;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> loadNiveis() {
        List<String> niveis = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("src/assets/niveis.xml"));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            XPathExpression expression = xpath.compile("/root/level");
            NodeList levelNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < levelNodes.getLength(); i++) {
                Node levelNode = levelNodes.item(i);
                if (levelNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element levelElement = (Element) levelNode;
                    String enemyTypes = levelElement.getAttribute("enemyTypes");
                    niveis.add(enemyTypes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return niveis;
    }

    public static void saveNiveis(List<String> niveis) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            for (int i = 0; i < niveis.size(); i++) {
                String enemyTypes = niveis.get(i);
                Element levelElement = doc.createElement("level");
                levelElement.setAttribute("enemyTypes", enemyTypes);
                levelElement.setAttribute("id", i + 1 + "");
                rootElement.appendChild(levelElement);
            }

       
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/assets/niveis.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePlayer(Player player) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("src/assets/players.xml"));

            File file = new File("src/assets/players.xml");
            if (file.exists()) {
                doc = dBuilder.parse(file);
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("root");
                doc.appendChild(rootElement);
            }

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            String expression = String.format("/root/player[@username='%s' and @password='%s']", player.getUsername(),
                    player.getPassword());
            Node playerNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
            playerNode.getAttributes().getNamedItem("vidaInicial")
                    .setNodeValue(String.valueOf(player.getVidaInicial()));
            playerNode.getAttributes().getNamedItem("maxPontuacao")
                    .setNodeValue(String.valueOf(player.getMaxPontuacao()));
            playerNode.getAttributes().getNamedItem("spawnRate").setNodeValue(String.valueOf(player.getSpawnRate()));
            playerNode.getAttributes().getNamedItem("spread").setNodeValue(String.valueOf(player.getSpread()));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEnemyTypes(int currentLevel)
    {
        List<String> niveis = loadNiveis();
        if(currentLevel > niveis.size())
        {
            return niveis.get(niveis.size() - 1);
        }
        return niveis.get(currentLevel - 1);
    }

    public static List<String> getPlayersRankings() {
        List<String[]> playersRankings = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("src/assets/players.xml"));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            XPathExpression expression = xpath.compile("/root/player");
            NodeList playerNodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < playerNodes.getLength(); i++) {
                Node playerNode = playerNodes.item(i);
                if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElement = (Element) playerNode;
                    String username = playerElement.getAttribute("username");
                    String maxPontuacao = playerElement.getAttribute("maxPontuacao");
                    playersRankings.add(new String[] {username, maxPontuacao});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sort the players based on their score
        Collections.sort(playersRankings, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
            }
        });

        // Convert the list of arrays to a list of strings
        List<String> sortedPlayersRankings = new ArrayList<>();
        for (String[] playerRanking : playersRankings) {
            sortedPlayersRankings.add(playerRanking[0] + " - " + playerRanking[1]);
        }

        return sortedPlayersRankings;
    }

}