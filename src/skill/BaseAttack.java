package skill;

import Character.Dog;
import screen.Animation;
import screen.Effect;

public class BaseAttack {
	Dog dog;
	public BaseAttack(Dog dog) {
		this.dog=dog;
	}
	public void attack(double now) {
		dog.pane.addComponent(new Effect(new Animation("sprites\\effect\\baseBark",100,100),230,360), "baseBark");
		//TODO attacking
	}
}
