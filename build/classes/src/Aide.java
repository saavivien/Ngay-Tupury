package src;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

import javax.swing.*;

public class Aide extends JFrame {

    private JEditorPane editorPane;
    JScrollPane pan;
    ImageIcon imgb3;

    @SuppressWarnings("deprecation")
    public Aide() {
        this.setSize(600, 400);
        this.setSize(600, 400);
        this.setTitle("Aide Ngay");
        this.setLocationRelativeTo(null);
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        pan = new JScrollPane(editorPane);
        this.setContentPane(pan);
        try {
            File file = new File("aide.html");
            editorPane.setEditorKit(new HTMLEditorKit());
            editorPane.setPage(file.toURL());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ImageIcon imgb3 = new ImageIcon(getClass().getResource("image/imgban3.png"));
        this.setIconImage(imgb3.getImage());
        try {
//On force à utiliser le « look and feel » du système
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (InstantiationException e) {
        } catch (ClassNotFoundException e) {
        } catch (UnsupportedLookAndFeelException e) {
        } catch (IllegalAccessException e) {
        }
        this.setVisible(true);
    }

}
