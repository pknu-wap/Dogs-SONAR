package Character;

import game.GamePane;
import screen.Animation;

public class LightEnemy extends EnemyV2{
    public LightEnemy(int hp,GamePane pane) {
        super(hp,pane);
        anim.addAnimation(new Animation("sprites\\cat\\hit",50,50), "hit");
        anim.addAnimation(new Animation("sprites\\cat\\move",50,50), "move");
        anim.setNowAnim("move");
        this.totHp/=2;
        this.maxSpeed*=2;
        init();
    }
    @Override
    public void deal (double dmg,boolean isCrit) {
        super.deal(dmg,isCrit);
        knock("hit","move",dmg);
    }
}
