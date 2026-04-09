package item;

import java.util.List;

import combatant.Combatant;
import combatant.Player;
import action.*;

public class PowerStone extends Item 
{
    public void useItem( Combatant user , List<Combatant> targets ) 
    {
        Action skill = user.getSkill(); 
        // execute skill but not reset cooldown
        skill.execute(user, targets);
        
        if (user instanceof Player) { ((Player) user).powerstoneCoolDownCompensate(); }

        System.out.println("Power Stone triggered " + skill.getClass().getSimpleName() + " for free!");
        System.out.println("Free skill use!");
    }
    public String getName() { return "PowerStone"; }
}