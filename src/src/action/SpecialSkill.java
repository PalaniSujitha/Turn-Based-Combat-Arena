package action;

import combatant.*;
import effect.StunEffect;

import java.util.List;

public class SpecialSkill implements Action {

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {

        // Warrior：Shield Bash（单体 + stun）
        if (actor instanceof Warrior) {
            Combatant target = targets.get(0);

            int dmg = Math.max(0, actor.getAttack() - target.getDefense());
            target.takeDamage(dmg);
            target.applyEffect(new StunEffect(2));

            System.out.println("Shield Bash! " + target.getName() + " stunned!");
        }

        // Wizard：Arcane Blast（AOE + 击杀加攻击）
        else if (actor instanceof Wizard) {
            for (Combatant e : targets) {
                if (!e.isAlive()) continue;

                int dmg = Math.max(0, actor.getAttack() - e.getDefense());
                e.takeDamage(dmg);

                if (!e.isAlive()) {
                    actor.increaseAttack(10);
                    System.out.println("Arcane Boost! +10 Attack");
                }
            }
        }
    }
}