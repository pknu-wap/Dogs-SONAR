package Character;

import game.GamePane;
import savingModule.SaveManager;
import screen.*;
import skill.*;

public class Dog{
    public Animator dogAnim;
    public int attackMode=0;
    public GamePane pane;
    SaveManager sv;
    BaseAttack baseAttack=new BaseAttack(this);
    ChargeShot chargeShot=new ChargeShot(this);
    public Dog(GamePane pane) {
    	this.pane=pane;
        dogAnim=new Animator(100,100);
        dogAnim.setX(100);
        dogAnim.setY(360);
        dogAnim.addAnimation(new Animation("sprites\\Dog\\idle",100,100), "idle");
        dogAnim.addAnimation(new Animation("sprites\\Dog\\bark",100,100), "bark");
        dogAnim.addAnimation(new Animation("sprites\\Dog\\barking",100,100), "barking");
        dogAnim.setNowAnim("idle");
        dogAnim.setNext("bark","idle");
        this.sv=pane.w.sv;
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
    			baseAttack.attack(now*Math.pow(1.01, sv.getItem("dmgUp").getValueInt()));
    			break;
    		case 1:
    			chargeShot.attack(now*Math.pow(1.01, sv.getItem("dmgUp").getValueInt()));
    			break;
    	}
    }
}