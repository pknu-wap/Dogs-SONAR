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
    FixedImage day=new FixedImage("sprites\\Buttons\\tooltip.png",100,50,200,50);
    FixedImage money=new FixedImage("sprites\\Buttons\\tooltip.png",950,50,250,30);
    FixedImage timer=new FixedImage("sprites\\Buttons\\tooltip.png",950,80,180,30);
    SkillButton feverBtn=new SkillButton("sprites\\Buttons\\main.png",400,600,150,50,"목풀기",20) {
        @Override
        public void act(MouseEvent e) {
            if(isCooling) return;
            dog.command(2);
            runCoolTime();
        }
    };
    SkillButton chargeBtn=new SkillButton("sprites\\Buttons\\main.png",200,600,150,50,"기모으기",3) {
    	@Override
    	public void act(MouseEvent e) {
    	    if(isCooling) return;
    		if(dog.attackMode==1) {
    		    this.runCoolTime();
    			dog.command(1);
    			this.setText("기모으기");
    		}else if(dog.attackMode==0) {
    			dog.command(0);
    			this.setText("발사");
    		}
    	}
    };
    SkillButton allkillBtn=new SkillButton("sprites\\Buttons\\main.png",600,600,150,50,"올킬",30) {
        @Override
        public void act(MouseEvent e) {
            if(isCooling) return;
            dog.command(3);
            runCoolTime();
        }
    };
    int addMoney=0;
    public GamePane(int width, int height, int rate,Window w) {
        super(width,height,rate);
        setFocusable(true);
        this.w=w;
        this.sv=w.sv;
        day.setText(sv.getItem("day").getValueString()+"일 차");
        money.setText("얻은 돈 : 0");
        enemyController=new EnemyController(this);
        addComponent(new FixedImage("sprites\\title\\title.jpg",0,0,1280,720),"background");
        dog=new Dog(this);
        w.setDog(dog);
        w.isGaming=true;
        addComponent(dog.dogAnim,"dog");
        addComponent(timer,"timer");
        addComponent(chargeBtn,"chargeBtn");
        addComponent(chargeBtn.getFader(),"chargeBtnFader");
        addComponent(feverBtn,"feverBtn");
        addComponent(feverBtn.getFader(),"feverBtnFader");
        addComponent(allkillBtn,"allkillBtn");
        addComponent(allkillBtn.getFader(),"allkillBtnFader");
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
                    while(i<=60000&&w.isGaming) {
                        Thread.sleep(100);
                        if(!isPaused) {
                            i+=100;
                            GamePane.this.timer.setText("남은 시간 : "+(60-(i/1000))+"");
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
        remove(w);
        w.add(new ResultPane(1280,720,200,w,cleared,addMoney));
    }
    public void updateMoney() {
        money.setText("얻은 돈 : "+addMoney);
    }
    class SkillButton extends Button{
        int coolTime=0;
        boolean isCooling=false;
        FixedImage fader;
        public SkillButton(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content,int coolTime) {
            super(imgSrc,locX,locY,sizeWidth,sizeHeight,content);
            this.coolTime=coolTime;
            fader=new FixedImage("sprites\\bars\\back.png",locX,locY,sizeWidth,sizeHeight,""+coolTime);
            fader.setAlpha(0.8f);
            fader.setVisible(false);
        }
        public FixedImage getFader() {
            return fader;
        }
        public void runCoolTime() {
            this.isCooling=true;
            new Thread() {
                @Override
                public void run() {
                    fader.setVisible(true);
                    for(int i=coolTime;i>=0;i--) {
                        fader.setText("쿨타임 : "+i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    fader.setVisible(false);
                    isCooling=false;
                }
            }.start();
        }
    }
}

