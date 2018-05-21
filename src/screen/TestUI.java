package screen;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TestUI extends JFrame {
    JPanel contentPane;
    JLabel imageLabel1;
    JLabel imageLabel2;

    public TestUI() {
        try {
            imageLabel1=new JLabel();
            imageLabel2=new JLabel();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(null);
            setSize(new Dimension(1000, 300));
            setTitle("Your Job Crashed!");
            ImageIcon ii1 = new ImageIcon(this.getClass().getResource("test1.gif"));
            ImageIcon ii2 = new ImageIcon(this.getClass().getResource("test2.gif"));
            imageLabel1.setLocation(10,10);
            imageLabel2.setLocation(150,150);
            imageLabel1.setSize(200,200);
            imageLabel2.setSize(200,200);
            imageLabel1.setIcon(ii1);
            imageLabel2.setIcon(ii2);
            contentPane.add(imageLabel1);
            contentPane.add(imageLabel2);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestUI();
    }

}