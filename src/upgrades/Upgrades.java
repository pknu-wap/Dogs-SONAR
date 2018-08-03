package upgrades;
import savingModule.*;
public class Upgrades {
    SaveManager sv;
    static int dmgUpper=5;
    static int limit=20;
    int dmg=0,critChance=0,critDmg=0,hull=0;
    public int getDmg() {
        return dmg;
    }
    public int getCritChance() {
        return critChance;
    }
    public int getCritDmg() {
        return critDmg;
    }
    public int getHull() {
        return hull;
    }
    public enum UpType {
        DAMAGE,CRITCHANCE,CRITDAMAGE,HULL
    }
    public Upgrades(SaveManager save) {
        this.sv=save;
        dmg=sv.getItem("dmgUp").getValueInt();
        critChance=sv.getItem("critChanceUp").getValueInt();
        critDmg=sv.getItem("critDmgUp").getValueInt();
        hull=sv.getItem("hullUp").getValueInt();
    }
    public double calcDamage(double baseDmg) {
        return baseDmg+(5*dmg);
    }
    public double calcCritChance(double baseChance) {
        return baseChance+(0.005*critChance);
    }
    public double calcCritDamage(double baseDmg) {
        return baseDmg+(0.5*critDmg);
    }
    public double calcHull(double baseHull) {
        return baseHull+(10*hull);
    }
    public void upgrade(UpType type,int amount) {
        switch(type) {
        case DAMAGE:
            if(dmg+amount<=limit) {
                dmg+=amount;
                sv.modify("dmgUp",new SaveItem(dmg));
            }
            break;
        case CRITCHANCE:
            if(critChance+amount<=limit) {
                critChance+=amount;
                sv.modify("critChnaceUp",new SaveItem(critChance));
            }
            
            break;
        case CRITDAMAGE:
            if(critDmg+amount<=limit) {
                critDmg+=amount;
                sv.modify("critDmgUp",new SaveItem(critDmg));
            }
            break;
        case HULL:
            if(hull+amount<=limit) {
                hull+=amount;
                sv.modify("hullUp",new SaveItem(hull));
            }
            break;
        }
    }
}
