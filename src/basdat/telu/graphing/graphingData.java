/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basdat.telu.graphing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Aas Suhendar
 */
public class graphingData {

    public void GraphingData(String file) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(file);
        Graph graph = new SingleGraph("Graphing");
        graph.setAutoCreate(true);
        graph.setStrict(false);

        graph.addNode("b1");
        graph.addNode("b2");
        graph.addNode("b3");
        graph.addNode("b4");
        graph.addEdge("m1", "b1", "b2");
        graph.addEdge("m2", "b4", "b3");

        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("RECORD");
            HashSet<String> authors = new HashSet<>();
            HashSet<String> media = new HashSet<>();

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                graph.addNode(node.getChildText("articleid"));

                Node nn = graph.getNode(node.getChildText("articleid"));
//                nn.addAttribute("ui.label", node.getChildText("title"));
                nn.addAttribute("title", node.getChildText("title"));
                nn.addAttribute("datee", node.getChildText("datee"));
                nn.addAttribute("medianame", node.getChildText("medianame"));
                nn.addAttribute("content", node.getChildText("content"));
                nn.addAttribute("page", node.getChildText("page"));
                nn.addAttribute("journalist", node.getChildText("journalist"));
                authors.add(nn.getAttribute("journalist"));
//                media.add(nn.getAttribute("medianame"));
            }

            for (String a : authors) {
                graph.addNode(a);
            }
            
            for (Node node : graph) {
                if (node.hasAttribute("journalist")) {
                    String id = node.getId();
                    String jurnalis = node.getAttribute("journalist");
                    for (String a : authors) {
                        if (jurnalis.equalsIgnoreCase(a)) {
                            graph.addEdge(id + jurnalis, a, id);
                        }
                    }
                }
            }

            for (Node n : graph) {
                n.addAttribute("ui.label", n.getId());
            }

            for (Edge edge : graph.getEachEdge()) {
                Node n = edge.getNode1();
                edge.addAttribute("ui.label", edge.getId());
            }

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        graph.display();
    }

    public static void main(String[] args) {
//        GraphingData("F:\\parserdata\\data_10k_articles.xml");
        graphingData g = new graphingData();
        g.GraphingData("F:\\parserdata\\new_data.xml");
    }
}
