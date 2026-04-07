package main;

import battle.*;
import combatant.*;
import item.*;
import strategy.*;

import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Turn-Based Combat Arena ===");

        // 选择角色
        System.out.println("Choose Character: 1. Warrior  2. Wizard");
        int choice = sc.nextInt();
        Player player = (choice == 1) ? new Warrior("Warrior") : new Wizard("Wizard");

        // 选择Items（2个）
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 2; i++) 
        {
            System.out.println("Choose Item " + (i+1) + ": 1.Potion 2.PowerStone 3.SmokeBomb");
            int it = sc.nextInt();
            if (it == 1) items.add(new Potion());
            else if (it == 2) items.add(new PowerStone());
            else items.add(new SmokeBomb());
        }
        player.setInventory(items);

        // 选择难度
        System.out.println("Choose Difficulty: 1.Easy 2.Medium 3.Hard");
        int diff = sc.nextInt();

        Level level = LevelFactory.createLevel(diff);
        
        System.out.println( "Difficulty Level: " + level.diff );
        System.out.println( "Player: " + player.getName() + ", HP: " + player.getHP() + ", ATK: " + player.getAttack() + ", DEF: " + player.getDefense() + ", SPD: " + player.getSpeed());
        System.out.println( "Items: " + items.get(0).getName() + " + " + items.get(1).getName() );
        System.out.println( "Level: " + level.diff );

        BattleManager manager = new BattleManager(player, level, new SpeedBasedStrategy());
        manager.startBattle();
        
        sc.close();
    }
}