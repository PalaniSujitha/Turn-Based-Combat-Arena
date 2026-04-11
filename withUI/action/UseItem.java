package action;

import combatant.Combatant;
import combatant.Enemy;
import combatant.Warrior;
import combatant.Wizard;
import item.Item;
import item.PowerStone;

import java.util.ArrayList;
import java.util.List;

import battle.BattleManager;

public class UseItem extends Action 
{
    private Item item;

    public UseItem(Item item) { this.item = item; }

    public void execute(Combatant actor, List<Combatant> targets) 
    {
        item.useItem( actor , targets ) ;
        actor.getInventory().remove(item);
    }

    // if not powerstone , no targets
    public Enemy selectSingleTarget(BattleManager bm, Combatant actor)
    {
        if( ! (item instanceof PowerStone) ) return null;
        if( actor instanceof Warrior ) return bm.selectEnemy();
        return null;
    }

    public List<Enemy> selectTargets(BattleManager bm, Combatant actor) 
    {
        if( ! (item instanceof PowerStone) ) return new ArrayList<>();
        if( actor instanceof Wizard ) return bm.getAliveEnemies();
        return new ArrayList<>();
    }
    

}