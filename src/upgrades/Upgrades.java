package upgrades;
import savingModule.*;
public class Upgrades {
    SaveManager sv;
    int dmg=0,critChance=0,critDmg=0,hull=0;
    enum UpType {
        DAMAGE,CRITCHANCE,CRITDAMAGE,HULL
    };
    public Upgrades(SaveManager save) {
        this.sv=save;
        sv.getItem("dmgUp");
    }
    
}
