package strategy;

import combatant.Combatant;
import java.util.*;

public interface TurnOrderStrategy {
    List<Combatant> order(List<Combatant> list);
}