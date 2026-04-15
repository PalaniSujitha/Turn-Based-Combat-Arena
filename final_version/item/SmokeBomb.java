package item;

import java.util.List;

import combatant.Combatant;
import effect.*;
import main.UI;

public class SmokeBomb extends Item 
{
    public void useItem( Combatant user , List<Combatant> targets ) 
    {
        user.applyEffect(new InvulnerableEffect(1));
        UI.printSmokeBomb(user);
    }
    public String getName() { return "SmokeBomb"; }
    public String getDescription() { return " Enemy attacks deal 0 damage this turn + next"; }
}