package strategy;

import combatant.Combatant;
import java.util.*;

// Sorts combatants so the highest speed goes first
public class SpeedBasedStrategy implements TurnOrderStrategy {

    @Override
    public List<Combatant> order(List<Combatant> list) {

        // Sorts the list in place based on speed (highest to lowest)
        list.sort((a,b)->b.getSpeed()-a.getSpeed());
        
        return list;
    }
}