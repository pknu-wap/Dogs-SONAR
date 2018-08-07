package Character;

import screen.Animator;

public class EnemyV2 {
    public int dist=1000;
    public double hp=100.0;
    public double armour=1;
    public double speed=0.2;
    public boolean isDied=false;
    double accSpeed=0;
    Animator anim;
    public Animator getAnimator() {
        return anim;
    }
    public void setAnimator(Animator anim) {
        this.anim=anim;
    }
    
    public void timeGo() {
        dist-=(int)speed;
        accSpeed+=(speed-(int)speed);
        if(accSpeed>=1) {
            dist-=(int)accSpeed;
            accSpeed-=(int)accSpeed;
        }
    }
    public void getDamage(double dmg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                deal(calcDamage(dmg));
            }
        }).start();
    }
    public void deal(double dmg) {
        hp-=calcDamage(dmg);
        if(hp<0) {
            die();
        }
    }
    public double calcDamage(double dmg){
        return dmg/armour;
    }
    public void die() {
        anim.dispose();
        isDied=true;
    }
}