package item;

import combatant.Combatant;

public interface Item {
    public void use(Combatant user);
    public String getName();
}