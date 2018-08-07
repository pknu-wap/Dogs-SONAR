package skill;

import Character.*;
import screen.Animation;
import screen.Effect;

public class BaseAttack {
	Dog dog;
	public BaseAttack(Dog dog) {
		this.dog=dog;
	}
	public void attack(double now) {
	    dog.dogAnim.setNowAnim("bark");
		dog.pane.addComponent(new Effect(new Animation("sprites\\effect\\baseBark",100,100),130,360), "baseBark");
		EnemyV2 t=dog.pane.getEnemyController().getNearest();
		if(t!=null) {
		    t.getDamage(now);
		}
		//TODO attacking
	}
}
