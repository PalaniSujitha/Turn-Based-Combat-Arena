package effect;

import combatant.Combatant;

public abstract class StatusEffect {
    protected int duration;
    public StatusEffect(int d) { duration = d; }
    public abstract void apply(Combatant c);
    public void decrease() { duration--; }
    public boolean isExpired() { return duration <= 0; }
    public abstract String getName();
}