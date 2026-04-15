package combatant;

import action.*;

public class Enemy extends Combatant
{
    public Enemy( String name , int hp , int atk , int def , int spd ){ super( name , hp , atk , def , spd ); }
    public Action getSkill(){ return null; }
}