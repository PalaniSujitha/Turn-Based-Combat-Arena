package action;

import combatant.*;
import effect.BlastEffect;
import main.UI;

import java.util.List;

import battle.BattleManager;


public class WizardSkill extends SpecialSkill
{
    public void execute(Combatant actor, List<Combatant> targets)
    {
        UI.printBlast1();
        for (Combatant e : targets) 
        {
            if (!e.isAlive()) continue;

            int dmg = Math.max(0, actor.getAttack() - e.getDefense());
            int HP1 = e.getHP();
            e.takeDamage(dmg);
            int HP2 = e.getHP();
            UI.printBlast2(actor, e, HP1, HP2, dmg);

            if (!e.isAlive()) 
            {
                int atk1 = actor.getAttack();
                actor.applyEffect( new BlastEffect(Integer.MAX_VALUE) );
                int atk2 = actor.getAttack();
                UI.printBlast3(atk1, atk2);

            }
        }
    }

    public List<Enemy> selectTargets(BattleManager bm, Combatant actor) 
    {
        return bm.getAliveEnemies(); // call 
    }
}