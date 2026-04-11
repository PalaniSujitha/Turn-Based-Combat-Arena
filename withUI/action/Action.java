package action;

import combatant.*;

import java.util.ArrayList;
import java.util.List;

import battle.BattleManager;

public abstract class Action 
{
    public abstract void execute( Combatant actor , List<Combatant>targets );
    
    // choose multiple enemies
    public List<Enemy> selectTargets(BattleManager bm, Combatant actor) 
    {
        return new ArrayList<>(); // 默认空
    }

    // choose single enemies
    public Enemy selectSingleTarget(BattleManager bm, Combatant actor) 
    {
        return null; // 默认空
    }

}