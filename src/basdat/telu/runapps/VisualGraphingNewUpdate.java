/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basdat.telu.runapps;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
public class VisualGraphingNewUpdate extends javax.swing.JFrame {

    private Graph graph;
    
    /**
     * Creates new form VisualGraphingNew
     */
    public VisualGraphingNewUpdate() {
        initComponents();
        graph = new SingleGraph("GraphNews");
        graph.setAutoCreate(true);
        graph.setStrict(false);
        setLocationRelativeTo(null);
    }

    public void GraphingData(String file) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(file);
        Graph graph = new SingleGraph("Graphing News Update");
        graph.setAutoCreate(true);
        graph.setStrict(false);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("RECORD");
            HashSet<String> authors = new HashSet<>();

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
            
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        graph.display();
    }
    
    public void initNews(String file) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(file);

        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("RECORD");

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
//                authors.add(nn.getAttribute("journalist"));
//                media.add(nn.getAttribute("medianame"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void displayGraph() {
        for (Node n : graph) {
            n.addAttribute("ui.label", n.getId());
        }

        for (Edge edge : graph.getEachEdge()) {
//            edge.addAttribute("ui.label", edge.getId());
        }

        graph.display();
    }

    public void createEdgeAuthor() {
        for (Node node0 : graph) {
            for (Node node1 : graph) {
                if (node0.getAttribute("journalist").equals(node1.getAttribute("journalist"))) {
                    String idnode0 = node0.getId();
                    String idnode1 = node1.getId();
                    String edge = node0.getAttribute("journalist") + idnode0 + idnode1;
                    if (!idnode0.equalsIgnoreCase(idnode1)) {
                        graph.addEdge(edge, node0, node1, false);
                    }
                }
            }
        }
    }

    public void createEdgeMedia() {
        for (Node node0 : graph) {
            for (Node node1 : graph) {
                if (node0.getAttribute("medianame").equals(node1.getAttribute("medianame"))) {
                    String idnode0 = node0.getId();
                    String idnode1 = node1.getId();
                    String edge = node0.getAttribute("medianame") + idnode0 + idnode1;
                    if (!idnode0.equalsIgnoreCase(idnode1)) {
                        graph.addEdge(edge, node0, node1, false);
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSelectFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        txtPath = new javax.swing.JTextField();
        btnGraphing = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Visual Graphing News");

        btnSelectFile.setText("Select File");
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane1.setViewportView(txtOutput);

        btnGraphing.setText("Graphing");
        btnGraphing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraphingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelectFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGraphing)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectFile)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGraphing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChoose = new JFileChooser();
        int open = fileChoose.showOpenDialog(this);
        boolean run = false;
        File file = null;
        if (open == JFileChooser.APPROVE_OPTION) {
            txtOutput.setText("Please Wait...");
            String fileName = fileChoose.getSelectedFile().getPath();
            file = new File(fileName);
            if (file.exists()) {
                txtPath.setText(file.getAbsolutePath());
                run = true;
                initNews(file.getAbsolutePath());
                txtOutput.append("\nNode has be create! \nClick Graphing Button");
            } else {
                run = false;
                JOptionPane.showMessageDialog(null, "Maaf, file " + fileName + " terdapat tidak ditemukan", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void btnGraphingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraphingActionPerformed
        // TODO add your handling code here:
        createEdgeAuthor();
        createEdgeMedia();
        displayGraph();
    }//GEN-LAST:event_btnGraphingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualGraphingNewUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualGraphingNewUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualGraphingNewUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualGraphingNewUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualGraphingNewUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGraphing;
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOutput;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}