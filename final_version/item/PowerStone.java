package item;

import java.util.List;

import combatant.Combatant;
import combatant.Player;
import main.UI;
import action.*;

public class PowerStone extends Item 
{
    public void useItem( Combatant user , List<Combatant> targets ) 
    {
        Action skill = user.getSkill(); 

        UI.printPowerStone(user, skill);
        // execute skill but not reset cooldown
        skill.execute(user, targets);
        
        if (user instanceof Player) { ((Player) user).powerstoneCoolDownCompensate(); }
    }
    public String getName() { return "PowerStone"; }
    public String getDescription() { return "Power Stone (Free skill use, no cooldown)"; }
}