package action;

import combatant.*;
import main.UI;

import java.util.List;

import battle.BattleManager;

public class BasicAttack extends Action
{

    public void execute( Combatant actor , List<Combatant> targets )
    {
        if ( actor.isStunned() )  // this is for enemy stun
        {
            System.out.println( actor.getName()+ " →  STUNNED: Turn skipped");
            return;
        }

        Combatant target = targets.get(0);
        int dmg;

        if( target.isVulnerable() )
        {
            dmg = 0; 
            UI.printInvulnerable(actor, target);
        }
        else 
        {
            dmg = Math.max(0, actor.getAttack() - target.getDefense());
            int HP1 = target.getHP();
            target.takeDamage(dmg);
            int HP2 = target.getHP();
            UI.printBasicAttack(actor, target, HP1, HP2, dmg);
        }
    }

    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return bm.selectEnemy(); // call original method
    }
}