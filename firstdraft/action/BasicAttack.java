package action;

import combatant.*;
import java.util.List;

import battle.BattleManager;

public class BasicAttack extends Action
{

    public void execute( Combatant actor , List<Combatant> targets )
    {
        if ( actor.isStunned() )  // this is for enemy stun
        {
            System.out.println(actor.getName()+" stunned skip");
            return;
        }

        Combatant target = targets.get(0);
        int dmg;

        if( target.isVulnerable() ){ dmg = 0; }
        else dmg = Math.max(0, actor.getAttack() - target.getDefense());
        target.takeDamage(dmg);

        System.out.println(actor.getName() + " attacks " + target.getName() + " for " + dmg);
    }

    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return bm.selectEnemy(); // call original method
    }
}