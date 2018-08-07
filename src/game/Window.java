package game;

import javax.swing.JFrame;

import Character.*;
import savingModule.SaveManager;
import soundCapture.LevelMeter;
import soundCapture.SoundHandler;

public class Window extends JFrame{
    SaveManager sv;
    LevelMeter lm;
    VoiceHandler vh;
    Dog dog;
    boolean isGaming=false;
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
        if(win.sv.getItem("day").getValueInt()==-1) {
            win.sv.getItem("day").setValueInt(0);
        }
        win.sv.save();
        
    }
    public void setDog(Dog dog) {
        Window.this.dog=dog;
    }
    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);
        sv=new SaveManager("sav.dat");
        this.add(new TitlePane(1280,720,200,this));
        vh=new VoiceHandler();
        lm=new LevelMeter(vh,0.05,3);
    }
    class VoiceHandler implements SoundHandler{
        public VoiceHandler() {
        }
        
        @Override
        public synchronized void action(double now, double peak) {
            if(isGaming) {
                dog.attack(now);               
            }
            
            
        }
    }
}
