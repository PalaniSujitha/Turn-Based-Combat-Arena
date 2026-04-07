package effect;

import combatant.Combatant;

public class DefendEffect extends StatusEffect {
    public DefendEffect(int d) { super(d); }
    public void apply(Combatant c) {}
    public String getName() { return "Defend"; }
}