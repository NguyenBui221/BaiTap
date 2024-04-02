package View;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

public class FileTreeView {
    private JFrame frame;
    private JTree tree;
    private JTextArea textArea;

    public FileTreeView(DefaultMutableTreeNode root) {
        frame = new JFrame("Directory Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        textArea = new JTextArea();
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, textAreaScrollPane);
        splitPane.setDividerLocation(200);

        frame.add(splitPane);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public JTree getTree() {
        return tree;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}