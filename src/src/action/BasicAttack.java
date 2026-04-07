package action;

import combatant.Combatant;
import java.util.List;

public class BasicAttack implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        Combatant target = targets.get(0);

        if (target.isInvulnerable()) {
            System.out.println(target.getName() + " is invulnerable! No damage.");
            return;
        }

        int dmg = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(dmg);

        System.out.println(actor.getName() + " attacks " +
                target.getName() + " for " + dmg);
    }
}