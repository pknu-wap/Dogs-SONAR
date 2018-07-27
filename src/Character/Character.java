package Character;

import soundCapture.*;
import java.util.ArrayList;

public class Character {
	boolean timeout = false;
	double hp=100.0;
	double damage;
	int length;
	
	int get_length() {
		return length;
	}
	public void getDamge(double dmg) {
		hp-=dmg;
	}
	public void get_sound(double dmg) {
		damage= dmg;
	}
	public static void main(String args[]) {
		Me me=new Me();
		ArrayList<Enemy> emy = new ArrayList<Enemy>();
		Move m = new Move();
		Reactor re = new Reactor();
		
		re.c=me;
		re.t=emy;
		
		LevelMeter lm=new LevelMeter(re, 0.08, 3);

        try {
            lm.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	void run() {
		int clock = 0;
		while(!timeout) {
			clock++;
			
			try{Thread.sleep(200);} catch(Exception e){}
			if(clock>45) stop();
		}
	}
	void stop(){
		timeout=true;
	}
}

class Me extends Character {
	
	double get_hp() {
		return this.hp;
	}
	
	void set_hp(double hp) {
		this.hp=hp;
	}
	void attack_to_enemy(double dmg,Character target<enemy>) {
		target<enemy>.getDamge(dmg);
	}
}

class Enemy extends Character{	
	void attack_to_me(double dmg, Character target) {
		target.getDamge(dmg);
	}
	void enemy_die() {
		
	}
}

class Move{
	int x;
	double dx,speed;
	int move(Character c) {
		dash(c);
		x=c.length+(int)dx;
		return x;
	}
	void dash(Character c) {
		speed = speed * 0.8 + randM(4);
		dx=c.get_length()*speed;
	}
	double randM(int M){
		return Math.random()*M;
	}
}

class Chance extends Enemy{
	public double heal(double hp,double heal_amount) {
		hp=hp+heal_amount;
		return hp;
	}
	public double slow(double speed) {
		speed = speed/2;
		return speed;
	}
	public double fast(double speed) {
		speed = speed*2;
		return speed;
	}
	
}

class Reactor implements SoundHandler{
	Me c;
	ArrayList<Enemy> t;
	
    @Override
    public void action(double now,double peak) {
             if(now>50) {
           	 c.attack_to_enemy(now*0.5,t);
           	 c.get_sound(now*0.5);
            	 System.out.println(t.hp);
            	 if(t.hp<=0) {
            		 t.enemy_die();
            	 }
             }
             else {
            	 
             }
    }
}

//class Play implements Runnable{
//	int index=0;
//	Me m;
//	//Enemy[] emy=new Enemy[10];
//	ArrayList<Integer> emy = new ArrayList<>();
//
//	public Play(){
//	for(int i=0; i<10; i++) {
//		emy.add(i);
//	}
//
//	}
//	@Override
//	public void run() {
//		
//		while(true) {
//			for(int i=0; i<10; i++) {
//				emy[i].set_enemy_length(emy[i].get_enemy_length()+1);
//			}
//			for(int i=0; i<10; i++) {
//			if(m.get_me_length()==emy[i].get_enemy_length()) {
//				m.set_hp(m.get_hp()-5);
//				if(m.get_hp()<=0) {
//					System.out.println("Game Over");
//				}
//			}
//			}
//			
//			try {Thread.sleep(1000);}catch(Exception e) {}
//		}
//	}
//	void e_in(Enemy e){
//		emy[index]=e;
//		index++;
//	}
//	void m_in(Me m) {
//		this.m=m;
//	}
//}


