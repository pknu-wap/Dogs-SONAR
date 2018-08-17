package skill;

import Character.Dog;

public class FeverTime {
    Dog dog;
    boolean isCooling=false;
    public FeverTime(Dog dog) {
        this.dog=dog;
    }
    public void activate() {
        new Thread() {
            @Override
            public void run() {
                dog.pane.w.lm.timer=3;
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {}
                dog.pane.w.lm.timer=12;
            }
        }.start();
    }
}
