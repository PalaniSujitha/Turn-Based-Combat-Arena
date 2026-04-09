package effect;

import combatant.Combatant;

public class DefendEffect extends StatusEffect 
{
    public DefendEffect(int d) { super(d); }
    public void apply(Combatant c) { c.increaseDefend(10); }
    public void remove( Combatant c ) { c.increaseDefend(-10); }
    public String getName() { return "Defend"; }
}