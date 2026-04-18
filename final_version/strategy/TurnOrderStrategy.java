package strategy;

import combatant.Combatant;
import java.util.*;

public interface TurnOrderStrategy 
{
    public abstract List<Combatant> order(List<Combatant> list);
}