package game;

import javax.swing.JFrame;

import savingModule.SaveManager;

public class Window extends JFrame{
    SaveManager sv;
    public static void main(String[] args) {
        Window win=new Window();
        if(win.sv.getItem("money").getValueInt()==-1) {
            win.sv.getItem("money").setValueInt(0);
        }
        if(win.sv.getItem("dmgUp").getValueInt()==-1) {
            win.sv.getItem("dmgUp").setValueInt(0);
        }
        if(win.sv.getItem("critChanceUp").getValueInt()==-1) {
            win.sv.getItem("critChanceUp").setValueInt(0);
        }
        if(win.sv.getItem("critDmgUp").getValueInt()==-1) {
            win.sv.getItem("critDmgUp").setValueInt(0);
        }
        win.sv.save();
    }
    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);
        sv=new SaveManager("sav.dat");
        this.add(new TitlePane(1280,720,200,this));
    }
}
