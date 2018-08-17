package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Character.*;

public class EnemyController implements Runnable{
    GamePane pane;
    int day;
    boolean stopping=true;
    boolean isPaused=false;
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
    public EnemyController(GamePane pane) {
        this.pane=pane;
        day=pane.w.sv.getItem("day").getValueInt();
        enemies=new ArrayList<EnemyV2>();
        buffer=new ArrayList<EnemyV2>();
    }
    public void startGame() {
        new Thread(playing).start();
        new Thread(EnemyController.this).start();
    }
    public void pauseGame() {
        isPaused=true;
    }
    public void resumeGame() {
        isPaused=false;
    }
    public ArrayList<EnemyV2> getAll(){
        return new ArrayList<EnemyV2>(enemies);
    }
    Runnable playing=new Runnable() {
        @Override
        public void run() {
            Random rand=new Random();
            while(stopping) {
                if(!isPaused) {
                    synchronized(this) {
                        EnemyV2 tt;
                        switch(rand.nextInt(3)) {
                        case 0:
                            tt=new LightEnemy(day,pane);
                            break;
                        case 1:
                            tt=new HeavyEnemy(day,pane);
                            break;
                        default:
                            tt=new NormalEnemy(day,pane);
                            break;
                        }
                        tt.addComponent(count++);
                        buffer.add(tt);
                    }
                }
                try {
                    Thread.sleep(rand.nextInt(2000)+1000);
                } catch (InterruptedException e) {}
            }
        }
    };
    @Override
    public void run() {
        while(stopping) {
            if(!isPaused){
            enemies.addAll(buffer);
            buffer.clear();
            Iterator<EnemyV2> iter = enemies.iterator();
            minDist=9999;
            while (iter.hasNext()) {
                EnemyV2 t=iter.next();
                t.timeGo();
                if(t.dist<minDist) {
                    nearest=t;
                    minDist=t.dist;
                }
                if(t.dist<100) {
                    pane.result(false);
                    return;
                }
                if(t.isDied) {
                    iter.remove();
                    pane.addMoney+=t.getPrice();
                    pane.updateMoney();
                    }   
                }
            }
            try {Thread.sleep(50);} catch (InterruptedException e) {}
        }
    }
}