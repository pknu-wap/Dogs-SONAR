package skill;

import java.util.ArrayList;

import Character.Dog;
import Character.EnemyV2;

public class AllKill implements Runnable{
	Dog dog;
	boolean isAllKill = false;
	
	public AllKill(Dog dog) {
        this.dog=dog;
    }
	
	public void allkilling() {
		
		allAttack();
	}
	public void run() {
		isAllKill=false;
		allAttack();
	}
	public void allAttack() {
		ArrayList<EnemyV2> arr=dog.pane.getEnemyController().getAll();
		for(int i=0;i<arr.size();i++) {
	        arr.get(i).deal(arr.get(i).getHp()*2,false);
	    }
	}
}
