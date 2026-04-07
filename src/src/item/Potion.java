package item;

import combatant.Combatant;

public class Potion implements Item {
    public void use(Combatant user) {
        user.heal(100);
        System.out.println("Heal +100");
    }
    public String getName() { return "Potion"; }
}