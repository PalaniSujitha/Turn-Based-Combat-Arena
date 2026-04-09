package combatant;

import effect.StatusEffect;
import item.Item;
import action.*;

import java.util.*;

public abstract class Combatant 
{
    protected String name;
    protected int maxHP , curHP , attack , defense , speed;

    protected boolean isInvulnerable = false , isStunned = false;

    protected List<StatusEffect> effects = new ArrayList<>();
    protected List<Item> inventory = new ArrayList<>();

    public Combatant( String name , int hp , int atk , int def , int spd )
    {
        this.name = name;
        this.curHP = hp;
        this.maxHP = hp;
        this.attack = atk;
        this.defense = def;
        this.speed = spd;
    }

    public void takeDamage(int dmg) 
    {
        if ( isInvulnerable ) 
        {
            
            return;
        }
        curHP -= dmg;
        if (curHP < 0) curHP = 0;
    }

    public boolean isAlive() { return curHP > 0; }

    public void applyEffect(StatusEffect e) 
    {
        effects.add(e);
        e.apply( this ); // immediate
    }

    public void processEffects() 
    {
        Iterator<StatusEffect> it = effects.iterator();
        while ( it.hasNext() ) 
        {
            StatusEffect e = it.next();
            
            if (e.isExpired())
            {
                e.remove( this );
                it.remove();
            }
            else e.decreaseDuration(); 
        }
    }

    // getters
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getHP() { return curHP; }
    public int getMaxHp() { return maxHP; }
    public String getName() { return name; }

    // increase attack for wizard
    public void increaseAttack( int val ) { attack += val; }
    // increase defend for defend action & effect
    public void increaseDefend( int val ) { defense += val; }
    // for invulnerable effect
    public void setInvulnerable( boolean judge ) { isInvulnerable = judge; }
    public boolean isVulnerable() { return isInvulnerable; }
    // for stun effect
    public void setStunned( boolean judge ) { isStunned = judge; }
    public boolean isStunned() { return isStunned; } 
    // effects for player
    public void heal( int amount )
    {
        curHP = Math.min( maxHP , curHP + amount );
    }
    // items
    public void setInventory(List<Item> items) { this.inventory = items; }
    public List<Item> getInventory() { return inventory; }
    //
    public abstract Action getSkill();
}