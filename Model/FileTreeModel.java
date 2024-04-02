package Model;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.Objects;

public class FileTreeModel {
    private DefaultMutableTreeNode root;

    public FileTreeModel(File rootFile) {
        root = new DefaultMutableTreeNode(new FileNode(rootFile));
        createNodes(root, rootFile);
    }

    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    private void createNodes(DefaultMutableTreeNode node, File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File fileEntry : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(fileEntry));
                node.add(childNode);
                if (fileEntry.isDirectory()) {
                    createNodes(childNode, fileEntry);
                }
            }
        }
    }

    public void saveToFile(File file) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(root);
        }
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            root = (DefaultMutableTreeNode) inputStream.readObject();
        }
    }

    static class FileNode implements Serializable {
        private final File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            return file.getName().length() > 0 ? file.getName() : file.getPath();
        }
    }
}