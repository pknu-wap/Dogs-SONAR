package game;

import screen.*;
import soundCapture.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Character.*;
import savingModule.SaveManager;
public class GamePane extends GraphicViewer{
    public Window w;
    Pauser p;
    boolean isPaused=false;
    SaveManager sv;
    EnemyController enemyController;
    public EnemyController getEnemyController() {
        return enemyController;
    }
    Dog dog;
    FixedImage day=new FixedImage(null,100,50,200,50);
    FixedImage money=new FixedImage(null,1100,80,200,50);
    FixedImage timer=new FixedImage(null,1100,50,200,50);
    Button chargeBtn=new Button("sprites\\Buttons\\main.png",200,600,100,50) {
    	@Override
    	public void act(MouseEvent e) {
    		if(dog.attackMode==1) {
    			dog.command(1);
    			this.setText("start charge");
    		}else if(dog.attackMode==0) {
    			dog.command(0);
    			this.setText("release charge");
    		}
    	}
    };
    int addMoney=0;
    public GamePane(int width, int height, int rate,Window w) {
        super(width,height,rate);
        setFocusable(true);
        this.chargeBtn.setText("start charge");
        this.w=w;
        this.sv=w.sv;
        day.setText(sv.getItem("day").getValueString());
        money.setText("0");
        enemyController=new EnemyController(this);
        addComponent(new FixedImage("sprites\\title\\title.jpg",0,0,1280,720),"background");
        dog=new Dog(this);
        w.setDog(dog);
        w.isGaming=true;
        addComponent(dog.dogAnim,"dog");
        addComponent(timer,"timer");
        addComponent(chargeBtn,"chargeBtn");
        addComponent(new Effect(new Animation("sprites\\effect\\startEffect",1280,720),-10,0),"starter");
        addComponent(money,"money");
        addComponent(day,"day");
        Thread k=new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                try {
                    try {
                        Thread.sleep(2900);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    enemyController.startGame();
                    while(i<=60000) {
                        Thread.sleep(100);
                        if(!isPaused) {
                            i+=100;
                            GamePane.this.timer.setText((60-(i/1000))+"");
                        }
                    }
                    GamePane.this.result(true);
                }catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        k.start();
        this.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                if(isPaused) {
                    p.resume();
                }else {
                    p=new Pauser(GamePane.this);
                }
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    public void result(boolean cleared) {
        w.isGaming=false;
        if(cleared) {
            sv.getItem("day").setValueInt(sv.getItem("day").getValueInt()+1);            
        }
        enemyController.dispose();
        w.remove(GamePane.this);
        w.add(new ResultPane(1280,720,200,w,cleared,addMoney));
    }
    public void updateMoney() {
        money.setText(addMoney+"");
    }
}

