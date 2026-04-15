package combatant;

import action.*;

public class Wizard extends Player 
{
    private Action skill = new WizardSkill();
    public Wizard(String name) { super(name, 200, 50, 10, 20); }
    public Action getSkill() { return skill; }
}