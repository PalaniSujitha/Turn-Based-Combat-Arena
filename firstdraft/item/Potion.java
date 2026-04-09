package item;

import java.util.List;

import combatant.Combatant;

public class Potion extends Item
{
    public Potion(){ this.name = "Potion"; }
    public void useItem( Combatant user , List<Combatant> targets )
    {
        user.heal(100);
    }
}