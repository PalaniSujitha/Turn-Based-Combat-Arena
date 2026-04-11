package item;

import java.util.List;

import combatant.Combatant;
import effect.*;

public class SmokeBomb extends Item 
{
    public void useItem( Combatant user , List<Combatant> targets ) 
    {
        user.applyEffect(new InvulnerableEffect(1));
        System.out.println( user.getName() + " → Item → Smoke Bomb used: Enemy attacks deal 0 damage this turn + next" );
    }
    public String getName() { return "SmokeBomb"; }
    public String getDescription() { return " Enemy attacks deal 0 damage this turn + next"; }
}