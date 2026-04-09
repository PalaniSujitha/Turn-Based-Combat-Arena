package strategy;

import combatant.Combatant;
import java.util.*;

public interface TurnOrderStrategy 
{
    public List<Combatant> order(List<Combatant> list);
}