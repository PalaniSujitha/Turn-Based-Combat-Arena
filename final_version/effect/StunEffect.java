package effect;

import combatant.Combatant;

public class StunEffect extends StatusEffect {
    public StunEffect(int d) { super(d); }
    public void apply( Combatant c ) { c.setStunned(true); }
    public void remove( Combatant c ) { c.setStunned(false); }
    public String getName() { return "Stun"; }
}