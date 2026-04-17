package strategy;

import combatant.Combatant;
import java.util.*;

// Strategy to figure out who attacks when
public interface TurnOrderStrategy {

    // Sorts the combatants into their turn order
    public abstract List<Combatant> order(List<Combatant> list);
}
