/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basdat.telu.graphing;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

    public static void GraphingData(String file) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(file);
        Graph graph = new SingleGraph("Graphing");
        graph.setAutoCreate(true);
        graph.setStrict(false);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("RECORD");

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                graph.addNode(node.getChildText("articleid"));

                for (Node n : graph) {
                    n.addAttribute("ui.label", n.getId());
                }

                Node nn = graph.getNode(node.getChildText("articleid"));
//                nn.addAttribute("ui.label", node.getChildText("title"));
                nn.addAttribute("title", node.getChildText("title"));
                nn.addAttribute("datee", node.getChildText("datee"));
                nn.addAttribute("medianame", node.getChildText("medianame"));
                nn.addAttribute("content", node.getChildText("content"));
                nn.addAttribute("page", node.getChildText("page"));
                nn.addAttribute("journalist", node.getChildText("journalist"));
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        
        
        
        graph.display();
    }

    public static void main(String[] args) {
//        GraphingData("F:\\parserdata\\data_10k_articles.xml");
        GraphingData("C:\\Users\\Aas Suhendar\\Documents\\NetBeansProjects\\Dev-GraphingNews\\src\\basdat\\telu\\file\\data_10k_articles.xml");
    }
}
