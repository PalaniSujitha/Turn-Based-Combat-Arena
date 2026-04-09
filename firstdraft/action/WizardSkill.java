package action;

import combatant.*;
import effect.BlastEffect;

import java.util.List;

import battle.BattleManager;


public class WizardSkill extends SpecialSkill
{
    public void execute(Combatant actor, List<Combatant> targets)
    {
        for (Combatant e : targets) 
        {
            if (!e.isAlive()) continue;

            int dmg = Math.max(0, actor.getAttack() - e.getDefense());
            e.takeDamage(dmg);

            if (!e.isAlive()) 
            {
                actor.applyEffect( new BlastEffect(Integer.MAX_VALUE) );
                System.out.println("Arcane Boost! +10 Attack");
            }
        }
    }

    public List<Enemy> selectTargets(BattleManager bm, Combatant actor) 
    {
        return bm.getAliveEnemies(); // call 
    }
}
