package Character;

import java.awt.Color;

import game.GamePane;
import screen.*;

public class EnemyV2 {
    public int id=-1;
    public int dist=1300;
    public double totHp=100.0;
    public double hp=100.0;
    public double armour=1;
    public double maxSpeed=2.0;
    public double speed=2.0;
    public double knockback=1;
    public boolean isDied=false;
    int price=10;
    double accSpeed=0;
    GamePane pane;
    FixedImage hpBarBack=new FixedImage("sprites\\bars\\back.png",dist,350,50,5);
    FixedImage hpBarFore=new FixedImage("sprites\\bars\\hp.png",dist+1,351,48,3);
    Animator anim=new Animator(50,100);
    public EnemyV2(int hp,GamePane pane){
        this.pane=pane;
        this.hp=this.hp*Math.pow(1.02, hp);
        anim.setY(360);
        anim.setX(dist);
    }
    public void addComponent(int index) {
        this.id=index;
        pane.addComponent(anim,"Enemy"+index);
        pane.addComponent(hpBarBack,"EnemyHpBack"+index);
        pane.addComponent(hpBarFore,"EnemyHpFore"+index);
    }
    public int getPrice() {
        return price;
    }
    public Animator getAnimator() {
        return anim;
    }
    public void setAnimator(Animator anim) {
        this.anim=anim;
    }
    public String toString() {
        String ret="type : normal Enemy\n hp : "+this.hp;
        return ret;
    }
    public void timeGo() {
        dist-=(int)speed;
        accSpeed+=(speed-(int)speed);
        if(accSpeed>=1) {
            dist-=(int)accSpeed;
            accSpeed-=(int)accSpeed;
        }
        anim.setX(dist);
        hpBarBack.setX(dist);
        hpBarFore.setX(dist+1);
    }
    public void getDamage(double dmg,boolean isCrit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                deal(calcDamage(dmg),isCrit);
            }
        }).start();
    }
    public void deal(double dmg,boolean isCrit) {
        hp-=calcDamage(dmg);
        Color textColor=Color.WHITE;
        if(isCrit) {
            textColor=Color.RED;
        }
        pane.addComponent(new TextIndicator((int)dmg+"",20,textColor,dist,400,20), TextIndicator.counter+"text"+id);
        pane.addComponent(new Effect(new Animation("sprites\\effect\\charging",50,50),dist,400),"hitEffect"+id);
        hpBarFore.setWidth((int)(48*hp/totHp));
        if(hp<0) {
            die();
        }
    }
    public void knock(String startAnimSet,String endAnimSet,double dmg) {
        double tmp=speed;
        speed=tmp-calcDamage(dmg)/2*knockback;
        anim.setNowAnim(startAnimSet);
        while(maxSpeed>speed) {
            speed+=calcDamage(dmg)/20;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        speed=maxSpeed;
        anim.setNowAnim(endAnimSet);
    }
    public double calcDamage(double dmg){
        return dmg/armour;
    }
    public void die() {
        anim.dispose();
        hpBarBack.dispose();
        hpBarFore.dispose();
        isDied=true;
    }
}