package action;

import combatant.*;

import java.util.List;

public abstract class SpecialSkill extends Action 
{
    public void execute(Combatant actor, List<Combatant> targets) {}
}