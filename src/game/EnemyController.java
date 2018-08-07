package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Character.EnemyV2;
import Character.NormalEnemy;

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
                
                try {
                    Thread.sleep(rand.nextInt(2500)+500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!isPaused) {
                    synchronized(this) {
                    NormalEnemy tt=new NormalEnemy(day,pane);
                    tt.addComponent(count++);
                    buffer.add(tt);
                    }
                }
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
                }
                if(t.isDied) {
                    iter.remove();
                    pane.addMoney+=t.getPrice();
                    pane.updateMoney();
                }   
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