package Character;

import screen.*;
import skill.*;

public class Dog{
    public Animator dogAnim;
    public int attackMode=0;
    public GraphicViewer pane;
    BaseAttack baseAttack=new BaseAttack(this);
    ChargeShot chargeShot=new ChargeShot(this);
    public Dog(GraphicViewer pane) {
    	this.pane=pane;
        dogAnim=new Animator(100,100);
        dogAnim.setX(200);
        dogAnim.setY(360);
        dogAnim.addAnimation(new Animation("sprites\\Dog\\idle",100,100), "idle");
        dogAnim.addAnimation(new Animation("sprites\\Dog\\bark",100,100), "bark");
        dogAnim.addAnimation(new Animation("sprites\\Dog\\barking",100,100), "barking");
        dogAnim.setNowAnim("idle");
        dogAnim.setNext("bark","idle");
    }
    public void command(int code) {
    	switch(code) {
    	case 0:
    		chargeShot.charge();
    		attackMode=1;
    		break;
    	case 1:
    		chargeShot.release();
    		attackMode=0;
    		break;
    	}
    }
    public void attack(double now) {
    	switch(attackMode) {
    		case 0:
    			baseAttack.attack(now);
    			break;
    		case 1:
    			chargeShot.attack(now);
    			break;
    	}
    }
}