package action;

import combatant.Combatant;
import effect.DefendEffect;
import main.UI;

import java.util.List;

public class DefendAction extends Action 
{
    public void execute(Combatant actor, List<Combatant> targets) 
    {
        int DF1 = actor.getDefense();
        actor.applyEffect(new DefendEffect(1));
        UI.printDefend(actor, DF1);
    }
}