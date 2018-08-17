package skill;

import java.awt.Color;
import java.util.ArrayList;

import Character.Dog;
import Character.EnemyV2;
import game.Window;
import screen.Animation;
import screen.Animator;
import screen.Effect;
import screen.TextIndicator;

public class ChargeShot {
	double chargedDamage=0.0;
	boolean isCharging=false;
	Dog dog;
	public ChargeShot(Dog dog) {
	    this.dog=dog;
    }
	public void charge() {
		chargedDamage=0.0;
		isCharging=true;
		dog.dogAnim.setNext("barking", "barking");
		dog.dogAnim.setNext("bark", "barking");
		dog.dogAnim.setNowAnim("bark");
		Animator chargingAnimator=new Animator(50,50);
		chargingAnimator.setX(105);
		chargingAnimator.setY(Window.CHARACTER_Y-20);
		chargingAnimator.addAnimation(new Animation("sprites\\effect\\charging",150,150), "charging");
		chargingAnimator.setNowAnim("charging");
		dog.pane.addComponent(chargingAnimator,"charging");
	}
	public void release() {
		isCharging=false;
		dog.dogAnim.setNext("barking", "idle");
		dog.pane.getComponentByName("charging").dispose();
		chargeAttack();
	}
	public void attack(double now) {
		if(isCharging) {
			chargedDamage+=(now/5);//TODO balance damage
			dog.pane.addComponent(new TextIndicator((int)chargedDamage+"",20,new Color(51, 153, 255),155,400,20),"charge");
		}else System.out.println("wrong access to chargeshot");
	}
	public void chargeAttack() {
	    dog.pane.addComponent(new Effect(new Animation("sprites\\effect\\beam",1200,50),155,Window.CHARACTER_Y+20), "beam");
	    ArrayList<EnemyV2> arr=dog.pane.getEnemyController().getAll();
	    for(int i=0;i<arr.size();i++) arr.get(i).getDamage(chargedDamage,false);
	}
}
