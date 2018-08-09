package skill;

import Character.*;
import game.Window;
import screen.Animation;
import screen.Effect;

public class BaseAttack {
	Dog dog;
	public BaseAttack(Dog dog) {
		this.dog=dog;
	}
	public void attack(double now,boolean isCrit) {
	    dog.dogAnim.setNowAnim("bark");
		dog.pane.addComponent(new Effect(new Animation("sprites\\effect\\baseBark",100,100),170,Window.CHARACTER_Y), "baseBark");
		EnemyV2 t=dog.pane.getEnemyController().getNearest();
		if(t!=null) {
		    t.getDamage(now,isCrit);
		}
		//TODO attacking
	}
}
