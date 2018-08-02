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
        GraphicViewer gV1=new GraphicViewer(500,500);
        gV1.setLocation(new Point(0,0));
        ExampleJFrame frame=new ExampleJFrame();
        Animation enemyRun=new Animation("sprites\\cat\\down",200,200);
        Animation enemyMove=new Animation("sprites\\cat\\move",200,200);
        Animation enemyHit=new Animation("sprites\\cat\\hit",200,200);
        Animator enemy=new Animator(200,200);
        ExampleButton garageDoor=new ExampleButton("sprites\\button\\greenButton.png", 100, 100,100, 100,"switch",new Font("Consolas",Font.BOLD,30),Color.red);
        enemy.setX(200);
        enemy.setY(200);
        enemy.addAnimation(enemyMove, "move");
        enemy.addAnimation(enemyRun, "run");
        enemy.addAnimation(enemyHit, "hit");
        enemy.setNext("hit", "run");
        enemy.setNowAnim("move");
        frame.add(gV1);
        gV1.addComponent(garageDoor,"doorButton");
        gV1.addComponent(enemy,"enemy");
        frame.setVisible(true);
    }
}
class ExampleButton extends Button{
    Animation ef;
    boolean isRunning;
    public ExampleButton(String string, int i, int j, int k, int l, String string2, Font font, Color col){
        super(string,i,j,k,l,string2,font,col);
        ef=new Animation("sprites\\explosion",200,200);
        isRunning=false;
    }
    Random rand=new Random();
    boolean isOpened=false;
    
    @Override

    public synchronized void act(MouseEvent e) {
        GraphicViewer t=(GraphicViewer) e.getSource();
        Animator k=(Animator)t.getComponentByName("enemy");
        if(isRunning) {
            this.isRunning=false;
            k.setNowAnim("move");
        }else {
            this.isRunning=true;
            k.setNowAnim("hit");
            t.addComponent(new Effect(ef,200,200),"explosion");
            
            
        }
        
    }
}

class ExampleJFrame extends JFrame{
    public ExampleJFrame() {
        this.setTitle("custom canvas test");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(510,540);
    }
}
