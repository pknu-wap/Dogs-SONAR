package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TESTUI {
    
    public static void main(String args[]){//TODO main method
        GraphicViewer gV1=new GraphicViewer(100,500,500);
        gV1.setLocation(new Point(0,0));
        ExampleJFrame frame=new ExampleJFrame();
        Button danos=new Button("danos.jpg",0, 0, 500, 500);
        ExampleButton garageDoor=new ExampleButton("inside.png", 42, 178, 420, 225,"Yess!",new Font("Consolas",Font.BOLD,100),Color.red);
        Button garageBack=new Button("outside.png", 0, 0, 500, 500);
        frame.add(gV1);
        gV1.addButton(danos);
        gV1.addButton(garageBack);
        gV1.addButton(garageDoor);
        frame.setVisible(true);
    }
}
class ExampleButton extends Button{
    LoadedEffect ef;
    public ExampleButton(String string, int i, int j, int k, int l, String string2, Font font, Color col){
        super(string,i,j,k,l,string2,font,col);
        ef=new LoadedEffect("sprites\\explosion",200,200);
    }
    Random rand=new Random();
    boolean isOpened=false;
    @Override

    public void act(MouseEvent e) {
        GraphicViewer t=(GraphicViewer) e.getSource();
        t.addEffect(new Effect(ef,rand.nextInt(400),rand.nextInt(400)));
    }
}

class ExampleJFrame extends JFrame{
    public ExampleJFrame() {
        this.setTitle("custom canvas test");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(510,540);
    }
}
