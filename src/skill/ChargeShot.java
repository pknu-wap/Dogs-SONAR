package skill;

import Character.Dog;

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
	}
	public void release() {
		isCharging=false;
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
		//TODO make attack
	}
}
