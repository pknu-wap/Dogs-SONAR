package skill;

import Character.Dog;
import screen.Animation;
import screen.Animator;
import screen.Effect;

public class ChargeShot implements Runnable{
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
		chargingAnimator.setX(205);
		chargingAnimator.setY(335);
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
			chargedDamage+=now;//TODO balance damage
		}else {
			System.out.println("wrong access to chargeshot");
		}		
	}
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isCharging=false;
		chargeAttack();
	}
	public void chargeAttack() {
	    dog.pane.addComponent(new Effect(new Animation("sprites\\effect\\beam",1200,50),255,385), "baseBark");
		//TODO make attack
	}
}
