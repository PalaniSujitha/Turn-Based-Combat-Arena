package action;

import combatant.Combatant;
import effect.DefendEffect;
import java.util.List;

public class DefendAction extends Action 
{
    public void execute(Combatant actor, List<Combatant> targets) 
    {
        actor.applyEffect(new DefendEffect(1));
        System.out.println(actor.getName() + " defends! (+Defense)");
    }
}