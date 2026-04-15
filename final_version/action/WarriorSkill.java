package action;

import combatant.*;
import effect.StunEffect;
import main.UI;

import java.util.List;

import battle.BattleManager;

public class WarriorSkill extends SpecialSkill
{
    public void execute(Combatant actor, List<Combatant> targets)
    {
        Combatant target = targets.get(0);

            int dmg = Math.max(0, actor.getAttack() - target.getDefense());
            int HP1  = target.getHP();
            target.takeDamage(dmg);
            int HP2  = target.getHP();
            target.applyEffect(new StunEffect(2));

            UI.printShieldBash(actor, target, HP1, HP2, dmg);
    }

    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return bm.selectEnemy(); // call original method
    }
}