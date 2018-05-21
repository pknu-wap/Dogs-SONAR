package screen;

import javax.swing.*;

public class TestUI extends JFrame {
    LoadingScreen ld;
    public TestUI() {
        this.setSize(1920,1080);
        ld=new LoadingScreen(this.getWidth(), this.getHeight());
        this.add(ld);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String args[]) {
        new TestUI();
    }
}