package action;

import combatant.*;
import effect.StunEffect;
import java.util.List;

import battle.BattleManager;

public class WarriorSkill extends SpecialSkill
{
    public void execute(Combatant actor, List<Combatant> targets)
    {
        Combatant target = targets.get(0);

            int dmg = Math.max(0, actor.getAttack() - target.getDefense());
            target.takeDamage(dmg);
            target.applyEffect(new StunEffect(2));

            System.out.println("Shield Bash! " + target.getName() + " stunned!");
    }

    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return bm.selectEnemy(); // call original method
    }
}
