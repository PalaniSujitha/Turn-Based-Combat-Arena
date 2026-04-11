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

        System.out.println( user.getName() + " → Item → Power Stone used → " + skill.getClass().getSimpleName() + " triggered");
        // execute skill but not reset cooldown
        skill.execute(user, targets);
        
        if (user instanceof Player) { ((Player) user).powerstoneCoolDownCompensate(); }
    }
    public String getName() { return "PowerStone"; }
    public String getDescription() { return "Power Stone (Free skill use, no cooldown)"; }
}