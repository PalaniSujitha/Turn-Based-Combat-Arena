package effect;

import combatant.Combatant;

public class InvulnerableEffect extends StatusEffect 
{
    public InvulnerableEffect(int d) { super(d); }
    public void apply( Combatant c ) { c.setInvulnerable(true); }
    public void remove( Combatant c ){ c.setInvulnerable(false); }
    public String getName() { return "Invulnerable"; }
}