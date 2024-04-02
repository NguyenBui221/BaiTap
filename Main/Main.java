import javax.swing.*;
import java.io.File;
import Model.FileTreeModel;
import View.FileTreeView;
import Controller.FileTreeController;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            File rootFile = new File("D:\\");
            FileTreeModel model = new FileTreeModel(rootFile);
            FileTreeView view = new FileTreeView(model.getRoot());
            FileTreeController controller = new FileTreeController(model, view);
            controller.saveToFile(new File("tree_data.ser"));
            controller.loadFromFile(new File("tree_data.ser"));
        });
    }
}