package item;

import combatant.Combatant;

public class PowerStone implements Item {
    public void use(Combatant user) {
        //user.resetCooldown();
        System.out.println("Free skill use!");
    }
    public String getName() { return "PowerStone"; }
}