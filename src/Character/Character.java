package Character;

import soundCapture.*;
import java.util.ArrayList;




public class Character {
	boolean timeout = false;
	double hp=100.0;
	double damage;
	int length;
	static ArrayList<Enemy> emy = new ArrayList<Enemy>();
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
		Move m = new Move();
		Reactor re = new Reactor();
		for(int i=0; i<10; i++) {
			enemy_make();}
		re.c=me;
		re.t=emy;
		
		LevelMeter lm=new LevelMeter(re, 0.08, 3);

        try {
            lm.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	static void enemy_make() {
		Enemy e = new Enemy();
		emy.add(e);
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
	void attack_to_enemy(double dmg, ArrayList<Enemy> t,int i) {
		t.get(i).getDamge(dmg);
	}
}

class Enemy extends Character {	
	public Enemy() {
		this.hp = 100;
	}
	void attack_to_me(double dmg, Character target) {
		target.getDamge(dmg);
	}
	void enemy_die(ArrayList<Enemy> t) {
		emy.remove(t);
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
	int i=0;
    @Override
    public void action(double now,double peak) {
             if(now>50) {
           	 c.attack_to_enemy(now*0.5,t,i);
           	 c.get_sound(now*0.5);
            	 System.out.println(t.get(i).hp);
            	 if(t.get(i).hp<=0) {
            		 enemy_die(t);
            		 System.out.println(i+"사망");
            		 i++;
            	 }
             }
             else{
            	 
             }
    }

	private void enemy_die(ArrayList<Enemy> t2) {
		// TODO Auto-generated method stub
		t.remove(t2);
	}
}

