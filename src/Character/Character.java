package Character;


import soundCapture.*;



public class Character {
	double hp=100;
	double damage;
	Character(){
		
	}
	public void getDamge(double dmg) {
		hp-=dmg;
	}
	public static void main(String args[]) {
		Me me=new Me();
		Enemy enemy=new Enemy();
		Reactor re=new Reactor();
		re.c=me;
		re.t=enemy;
		LevelMeter lm=new LevelMeter(re, 0.08, 3);
        try {
            lm.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public double hit(double damage) {
		damage = damage * 0.1;
		return damage;
	}
	double get_sound(double damage) {
		return damage;
	}
}

class Me extends Character {

	
	void attack(double dmg,Character target) {
		target.getDamge(dmg);
	}
}

class Enemy extends Character{
	
}

class Reactor implements SoundHandler{
	Me c;
	Enemy t;
    @Override
    public void action(double now,double peak) {
             if(now>50) {
            	 c.attack(now*0.1,t);
            	 System.out.println(t.hp);
             }
             else {
            	 
             }
    }
}


