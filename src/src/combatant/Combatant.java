package combatant;

import effect.StatusEffect;
import item.Item;

import java.util.*;

public class Combatant 
{
    protected String name;
    public int maxHP;
    protected int currentHP, attack, defense, speed;

    protected List<StatusEffect> effects = new ArrayList<>();
    protected List<Item> inventory = new ArrayList<>();

    public int skillCooldown = 0;

    public Combatant(String name, int hp, int atk, int def, int spd) 
    {
        this.name = name;
        this.maxHP = hp;
        this.currentHP = hp;
        this.attack = atk;
        this.defense = def;
        this.speed = spd;
    }

    public void takeDamage(int dmg) {
        currentHP -= dmg;
        if (currentHP < 0) currentHP = 0;
    }

    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }

    public boolean isAlive() { return currentHP > 0; }

    public void applyEffect(StatusEffect e) { effects.add(e); }

    public void processEffects() 
    {
        Iterator<StatusEffect> it = effects.iterator();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            e.apply(this);
            e.decrease();
            if (e.isExpired()) it.remove();
        }
    }

    public boolean isStunned() 
    {
        return effects.stream().anyMatch(e -> e.getName().equals("Stun"));
    }

    public boolean isInvulnerable() 
    {
        return effects.stream().anyMatch(e -> e.getName().equals("Invulnerable"));
    }

    public void setInventory(List<Item> items) { this.inventory = items; }

    public List<Item> getInventory() { return inventory; }

    public void decreaseCooldown() 
    {
        if (skillCooldown > 0) skillCooldown--;
    }

    public boolean canUseSkill() { return skillCooldown == 0; }

    public void resetCooldown() { skillCooldown = 3; }

    // getters
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getHP() { return currentHP; }
    public String getName() { return name; }

    public void increaseAttack(int val) { attack += val; }
}