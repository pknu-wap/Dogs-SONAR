package skill;

import Character.Dog;

public class FeverTime implements Runnable{
    Dog dog;
    boolean isCooling=false;
    public FeverTime(Dog dog) {
        this.dog=dog;
    }
    public void activate() {
        if(isCooling)return;
        
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        isCooling=true;
        new Thread() {
            @Override
            public void run() {
                int coolTime=20;
                for(int i=coolTime;i>=0;i--) {
                    try {
                        Thread.sleep(1000);
                        //dog.pane.
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                isCooling=false;
            }
        }.start();
    }

}
