package Character;

import game.GamePane;
import screen.*;

public class NormalEnemy extends EnemyV2 {
    public NormalEnemy(int hp,GamePane pane) {
        super(hp,pane);
        anim.addAnimation(new Animation("sprites\\Caroline\\idle",50,100), "idle");
        anim.addAnimation(new Animation("sprites\\Caroline\\moving",50,100), "moving");
        anim.setNowAnim("moving");
        setSize();
    }
    
    @Override
    public void deal (double dmg,boolean isCrit) {
        super.deal(dmg,isCrit);
        knock("idle","moving",dmg);
    }
}
