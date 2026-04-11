package action;

import combatant.Combatant;
import effect.DefendEffect;
import java.util.List;

public class DefendAction extends Action 
{
    public void execute(Combatant actor, List<Combatant> targets) 
    {
        int DF1 = actor.getDefense();
        actor.applyEffect(new DefendEffect(1));
        System.out.println( actor.getName() + " →  Defend → Defence: " + DF1 + " → " + (DF1+10) + "(+10)");
    }
}