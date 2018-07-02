package Character;


import soundCapture.*;
import java.util.ArrayList;

public class Character {
	double hp=100;
	double damage;
	
	public void getDamge(double dmg) {
		hp-=dmg;
	}

	public static void main(String args[]) {
		Me me=new Me();
		Enemy enemy=new Enemy();
		Reactor re=new Reactor();
		Play p=new Play();
		re.c=me;
		re.t=enemy;
		LevelMeter lm=new LevelMeter(re, 0.08, 3);
		p.e_in(enemy);
		p.m_in(me);
		p.run();
	
        try {
            lm.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public double hit(double damage) {
		damage = damage * 0.5;
		return damage;
	}
	double get_sound(double damage) {
		return damage;
	}
}

class Me extends Character {
	double Me_length = 100;
	double get_me_length() {
		return Me_length;
	}
	void set_enemy_length(double length) {
		Me_length=length;
	}
	double get_hp() {
		return this.hp;
	}
	
	void set_hp(double hp) {
		this.hp=hp;
	}
	void attack_to_enemy(double dmg,Character target) {
		target.getDamge(dmg);
	}
}

class Enemy extends Character{
	
	double Enemy_length =0;
	double get_enemy_length() {
		return Enemy_length;
	}
	void set_enemy_length(double length) {
		Enemy_length=length;
	}
	void attack_to_me(double dmg, Character target) {
		target.getDamge(dmg);
	}
}

class Reactor implements SoundHandler{
	Me c;
	Enemy t;
    @Override
    public void action(double now,double peak) {
             if(now>50) {
            	 c.attack_to_enemy(now*0.5,t);
            	 System.out.println(t.hp);
            	 
             }
             else {
            	 
             }
    }
}
class Play implements Runnable{
	int index=0;
	Me m;
	Enemy[] emy=new Enemy[10];
	

	public Play(){
	for(int i=0; i<10; i++) {
		emy[i]=new Enemy();
	}
	m=new Me();
	}
	@Override
	public void run() {
		
		while(true) {
			for(int i=0; i<10; i++) {
				emy[i].set_enemy_length(emy[i].get_enemy_length()+1);
			}
			for(int i=0; i<10; i++) {
			if(m.get_me_length()==emy[i].get_enemy_length()) {
				m.set_hp(m.get_hp()-5);
				if(m.get_hp()<=0) {
					System.out.println("Game Over");
				}
			}
			}
			
			try {Thread.sleep(1000);}catch(Exception e) {}
		}
	}
	void e_in(Enemy e){
		emy[index]=e;
		index++;
	}
	void m_in(Me m) {
		this.m=m;
	}
}


