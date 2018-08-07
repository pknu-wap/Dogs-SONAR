package game;

import screen.*;
import soundCapture.*;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Character.*;
import savingModule.SaveManager;
public class GamePane extends GraphicViewer{
    Window w;
    SaveManager sv;
    EnemyController enemyController;
    Dog dog;
    FixedImage timer=new FixedImage(null,1000,50,200,50);
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
        this.chargeBtn.setText("start charge");
        this.w=w;
        this.sv=w.sv;
        addComponent(new FixedImage("sprites\\title\\title.jpg",0,0,1280,720),"background");
        dog=new Dog(this);
        w.setDog(dog);
        w.isGaming=true;
        addComponent(dog.dogAnim,"dog");
        addComponent(timer,"timer");
        addComponent(chargeBtn,"chargeBtn");
        addComponent(new Effect(new Animation("sprites\\effect\\startEffect",1280,720),-10,0),"starter");
        
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
                    enemyController=new EnemyController();
                    while(i<=60000) {
                        Thread.sleep(100);
                        i+=100;
                        GamePane.this.timer.setText((60-(i/1000))+"");
                    }
                    GamePane.this.result(true);
                }catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        k.start();
    }
    public void result(boolean cleared) {
        //TODO make result screen pane
        w.isGaming=false;
        sv.getItem("day").setValueInt(sv.getItem("day").getValueInt()+1);
        w.remove(GamePane.this);
        w.add(new ResultPane(1280,720,200,w,cleared,addMoney));
    }
    class EnemyController implements Runnable{
        int day=sv.getItem("day").getValueInt();
        boolean stopping=true;
        int count=0;
        ArrayList<EnemyV2> enemies;
        ArrayList<EnemyV2> buffer;
        EnemyV2 nearest;
        int minDist;
        public void dispose() {
            stopping=false;
        }
        public EnemyV2 getNearest() {
            return nearest;
        }
        public EnemyController() {
            
            enemies=new ArrayList<EnemyV2>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Random rand=new Random();
                    while(stopping) {
                        NormalEnemy tt=new NormalEnemy(day);
                        tt.setAnimator();
                        System.out.println("made");
                        try {
                            Thread.sleep(rand.nextInt(2500)+500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        addComponent(tt.getAnimator(),"Enemy"+count);
                        count+=1;
                        buffer.add(tt);
                    }
                }
            }).start();
        }
        
        @Override
        public void run() {
            while(stopping) {
                enemies.addAll(buffer);
                buffer.clear();
                Iterator<EnemyV2> iter = enemies.iterator();
                minDist=9999;
                while (iter.hasNext()) {
                    EnemyV2 t=iter.next();
                    if(t.dist<minDist) {
                        nearest=t;
                        minDist=t.dist;
                    }
                    t.timeGo();
                    if(t.isDied) {
                        iter.remove();
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
        
    }

}
