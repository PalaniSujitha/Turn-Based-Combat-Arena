package item;

import java.util.List;

import combatant.Combatant;
import main.UI;

public class Potion extends Item
{
    public void useItem( Combatant user , List<Combatant> targets )
    {
        int HP1 = user.getHP();
        user.heal(100);
        int HP2 = user.getHP();
        UI.printPotion(user, HP1, HP2);
    }
    public String getName() { return "Potion"; }
    public String getDescription() { return "Potion (Heal +100 HP)"; }
}