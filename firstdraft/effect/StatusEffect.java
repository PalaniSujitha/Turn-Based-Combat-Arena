package effect;


import combatant.Combatant;

public abstract class StatusEffect
{
    protected String name;
    protected int duration; 
    //protected boolean consumeAtStart;
    

    public String getName(){ return name; }

    public StatusEffect( int d ) 
    {
        duration = d; 
    }
    
    public abstract void apply(Combatant c);
    public abstract void remove(Combatant c);
    public void decreaseDuration(){ duration--; }
    public boolean isExpired(){ return duration <= 0; }

}