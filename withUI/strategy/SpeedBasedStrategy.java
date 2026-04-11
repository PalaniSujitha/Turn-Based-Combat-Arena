package strategy;

import combatant.Combatant;
import java.util.*;

public class SpeedBasedStrategy implements TurnOrderStrategy
{
    public List<Combatant> order(List<Combatant> list)
    {
        list.sort((a,b)->b.getSpeed()-a.getSpeed());
        return list;
    }
}