package Character;

import game.GamePane;
import screen.Animation;

public class HeavyEnemy extends EnemyV2{

    public HeavyEnemy(int hp, GamePane pane) {
        super(hp, pane);
        anim.addAnimation(new Animation("sprites\\linus\\hit",50,100), "hit");
        anim.addAnimation(new Animation("sprites\\linus\\moving",50,100), "move");
        anim.setNowAnim("move");
        this.totHp*=2;
        this.maxSpeed*=0.5;
        init();
    }
    @Override
    public void deal (double dmg,boolean isCrit) {
        super.deal(dmg,isCrit);
        knock("hit","move",dmg);
    }
}
