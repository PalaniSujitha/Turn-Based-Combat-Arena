package item;

import combatant.Combatant;
import effect.*;

public class SmokeBomb implements Item {
    public void use(Combatant user) {
        user.applyEffect(new InvulnerableEffect(2));
        System.out.println("Invulnerable!");
    }
    public String getName() { return "SmokeBomb"; }
}