package Character;

import java.util.Random;

import game.GamePane;
import savingModule.SaveManager;
import screen.*;
import skill.*;

public class Dog{
    public Animator dogAnim;
    public int attackMode=0;
    public GamePane pane;
    int critChanceUp,critDmgUp;
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
        critChanceUp=sv.getItem("critChanceUp").getValueInt();
        critDmgUp=sv.getItem("critDmgUp").getValueInt();
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
        Random rand=new Random();
        boolean isCrit=false;
        if(rand.nextInt(1000)<100+10*critChanceUp) {
            isCrit=true;
            now*=(20.0+critDmgUp)/10.0;
        }
    	switch(attackMode) {
    		case 0:
    			baseAttack.attack(now*Math.pow(1.01, sv.getItem("dmgUp").getValueInt()),isCrit);
    			break;
    		case 1:
    			chargeShot.attack(now*Math.pow(1.01, sv.getItem("dmgUp").getValueInt()));
    			break;
    	}
    }
}