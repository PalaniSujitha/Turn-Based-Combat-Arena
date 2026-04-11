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
            int HP1  = target.getHP();
            target.takeDamage(dmg);
            int HP2  = target.getHP();
            target.applyEffect(new StunEffect(2));

            System.out.println( "Warrior → Shield Bash → " + target.getName() +
                ": HP: " + HP1 + " → " + HP2 + "(dmg:" + actor.getAttack() + "-" + target.getDefense() + "=" + dmg + ")" +
                " | " + target.getName() + " STUNNED (2turns)" );
    }

    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return bm.selectEnemy(); // call original method
    }
}