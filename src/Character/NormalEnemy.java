package Character;

import screen.*;

public class NormalEnemy extends EnemyV2 {
    public NormalEnemy(int hp) {
        this.hp=this.hp*Math.pow(1.02, hp);
    }
    public void setAnimator() {
        Animator tAnim=new Animator(50,100);
        tAnim.addAnimation(new Animation("sprites\\Caroline\\idle",50,100), "idle");
        tAnim.addAnimation(new Animation("sprites\\Caroline\\moving",50,100), "moving");
        tAnim.setNowAnim("moving");
        tAnim.setY(360);
        this.anim=tAnim;
    }
    public String toString() {
        String ret="type : normal Enemy\n hp : "+this.hp;
        return ret;
    }
    @Override
    public void deal (double dmg) {
        double tmp=speed;
        speed=tmp-0.8;
        anim.setNowAnim("idle");
        while(speed==tmp) {
            speed+=0.1;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        anim.setNowAnim("moving");
        super.deal(dmg);
    }
}
