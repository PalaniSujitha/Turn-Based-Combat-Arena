package item;

import java.util.List;

import combatant.Combatant;
import effect.*;

public class SmokeBomb extends Item 
{
    public void useItem( Combatant user , List<Combatant> targets ) 
    {
        user.applyEffect(new InvulnerableEffect(1));
        System.out.println("Invulnerable!");
    }
    public String getName() { return "SmokeBomb"; }
}