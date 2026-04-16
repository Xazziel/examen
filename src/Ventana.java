import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Ventana extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ventana().createUI());
    }

    public void createUI() {
        JFrame frame = new JFrame("Multitask");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        // ===== PANEL IZQUIERDO (JTree) =====
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("My Work");
        DefaultMutableTreeNode inbox = new DefaultMutableTreeNode("Inbox");
        root.add(inbox);
        inbox.add(new DefaultMutableTreeNode("Scheduled Actions"));
        inbox.add(new DefaultMutableTreeNode("Closed Actions"));

        JTree tree = new JTree(root);
        JScrollPane treeScroll = new JScrollPane(tree);

        
        String[] columnas = {"Name", "Month", "Days", "Status"};
        Object[][] data = {
                {"Item 1", "Jan", "25", "Closed"},
                {"Item 2", "Feb", "12", "Received"},
                {"Item 3", "Mar", "7", "Canceled"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columnas));
        JScrollPane tableScroll = new JScrollPane(table);

      
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Contact ID:", "Date of Signature:", "Status:", "Type:", "Expiration Date:"};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(new JTextField(15), gbc);
        }

        
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Description:"), gbc);

        gbc.gridy++;
        JTextArea description = new JTextArea(5, 20);
        formPanel.add(new JScrollPane(description), gbc);

        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Details", formPanel);
        tabs.addTab("Other", new JPanel());

        
        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll, tabs);
        rightSplit.setDividerLocation(250);

        
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, rightSplit);
        mainSplit.setDividerLocation(200);

        frame.add(mainSplit);
        frame.setVisible(true);
    }
}