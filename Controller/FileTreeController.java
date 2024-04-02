package Controller;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import Model.FileTreeModel;
import View.FileTreeView;

public class FileTreeController {
    private FileTreeModel model;
    private FileTreeView view;

    public FileTreeController(FileTreeModel model, FileTreeView view) {
        this.model = Objects.requireNonNull(model);
        this.view = Objects.requireNonNull(view);

        view.getTree().setModel(new javax.swing.tree.DefaultTreeModel(model.getRoot()));

        Thread fileTreeThread = new Thread(() -> {
            while (true) {
                updateFileTree();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fileTreeThread.setDaemon(true);
        fileTreeThread.start();
    }

    private void updateFileTree() {
        File rootFile = model.getRoot().getFile();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new FileNode(rootFile));
        model.createNodes(rootNode, rootFile);
        view.getTree().setModel(new javax.swing.tree.DefaultTreeModel(rootNode));
    }

    public void saveToFile(File file) {
        try {
            model.saveToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(File file) {
        try {
            model.loadFromFile(file);
            DefaultMutableTreeNode root = model.getRoot();
            view.getTree().setModel(new javax.swing.tree.DefaultTreeModel(root));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
