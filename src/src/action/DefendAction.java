package action;

import combatant.Combatant;
import effect.DefendEffect;
import java.util.List;

public class DefendAction implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        actor.applyEffect(new DefendEffect(2));
        System.out.println(actor.getName() + " defends! (+Defense)");
    }
}