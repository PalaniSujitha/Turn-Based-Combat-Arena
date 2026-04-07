package effect;

import combatant.Combatant;

public class StunEffect extends StatusEffect {
    public StunEffect(int d) { super(d); }
    public void apply(Combatant c) {}
    public String getName() { return "Stun"; }
}