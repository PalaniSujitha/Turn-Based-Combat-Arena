package combatant;

import action.*;

public class Warrior extends Player 
{
    private Action skill = new WarriorSkill();
    public Warrior(String name) 
    {
        super(name, 260, 40, 20, 30);
    }
    public Action getSkill() { return skill; }
}