package game;

import javax.swing.JFrame;

import savingModule.SaveManager;

public class Window extends JFrame{
    SaveManager sv;
    TitlePane title;
    GamePane game;
    UpgradePane upgrade;
    public static void main(String[] args) {
        Window win=new Window();
    }
    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);
        sv=new SaveManager("sav.dat");
        title=new TitlePane(this);
        this.add(title);
    }
}
