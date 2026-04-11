package combatant;

import action.Action;

public abstract class Player extends Combatant
{
    
    protected int CoolDown = 0;

    public Player( String name , int hp , int atk , int def , int spd )
    {
        super( name , hp , atk , def , spd );
    }
    
    // special skill cooldown
    public void decreaseCooldown() 
    {
        if (CoolDown > 0) CoolDown--;
    }
    public boolean canUseSkill() { return CoolDown == 0; }
    public void resetCooldown() { CoolDown = 3; }
    public void powerstoneCoolDownCompensate() { CoolDown++; }
    public int getCoolDown() { return CoolDown; }

    // 
    public abstract Action getSkill();
}