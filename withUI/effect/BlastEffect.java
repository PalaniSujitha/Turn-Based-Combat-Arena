package effect;

import combatant.Combatant;

public class BlastEffect extends StatusEffect
{
    public BlastEffect( int d ) { super(d); }
    public void apply( Combatant c ) { c.increaseAttack(10); }
    public void remove( Combatant c ) {  }
    public String getName() { return "BlastEffect"; }

    // override to be permanent
    public void decreaseDuration() {} // duration is always Interger.MAX_VALUE
    public boolean isExpired() { return false; } // never expire
}
