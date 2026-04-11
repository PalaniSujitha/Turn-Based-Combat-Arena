package main;

import combatant.*;
import item.*;
//import action.*;
import battle.*;
//import effect.*;
import strategy.*;

import java.util.*;

public class UI 
{
    private static Scanner sc = new Scanner(System.in);
    
    // show loading screen
    public static void showLoadingScreen(List<Player> players , List<Enemy> enemies , List<Level> levels , List<Item> items )
    {
        System.out.println("=== Turn-Based Combat Arena ===");
        System.out.println("=== Loading Screen - Initiation ===\n");

        // Players
        System.out.println("Players:");
        for ( int i = 0 ; i < players.size() ; i ++ ) 
        {
            Player p = players.get(i);
            System.out.println( (i+1) + ". " +  p.getClass().getSimpleName() +
                    " - HP: " + p.getMaxHp() +
                    ", ATK: " + p.getAttack() +
                    ", DEF: " + p.getDefense() +
                    ", SPD: " + p.getSpeed());
        }
        System.out.println();

        // Enemies
        System.out.println("Enemies:");
        for ( int i = 0 ; i < enemies.size() ; i ++ ) 
        {
            Enemy e = enemies.get(i);
            System.out.println( (i+1) + ". " +  e.getClass().getSimpleName() +
                    " - HP: " + e.getMaxHp() +
                    ", ATK: " + e.getAttack() +
                    ", DEF: " + e.getDefense() +
                    ", SPD: " + e.getSpeed());
        }
        System.out.println();

        // Levels
        System.out.println("Difficulty Levels:");
        for ( int i = 0 ; i < levels.size() ; i ++ ) 
        {
            Level l = levels.get(i);
            System.out.print ( (i+1) + ". " +  l.getDiff() + " : enemies - ");
            for( Enemy e : l.getEnemies() ) { System.out.print ( " " + e.getName() + " " ); }
            System.out.print ( "   backups - " );
            for( Enemy e : l.getBacks() ) { System.out.print ( " " + e.getName() + " " ); }
            System.out.println();
        }
        System.out.println();

        // Items
        System.out.println("Items: ");
        for ( int i = 0 ; i < items.size() ; i ++ ) 
        {
            Item I = items.get(i);
            System.out.println ( I.getDescription() );
        }
        System.out.println();

        System.out.println("\n====================================\n");
    }
    
    
    // choose player
    public static int choosePlayer()
    {
        System.out.println("Choose Character: 1. Warroir  2. Wizard");
        int choice = sc.nextInt();
        return choice;
    }

    // choose item
    public static int chooseItem( int i )
    {
        System.out.println("Choose Item " + (i+1) + ": 1. Potion  2. PowerStone  3. SmokeBomb ");
        int it = sc.nextInt();;
        return it;
    }

    // choose level
    public static int chooseLevel()
    {
        System.out.println("Choose difficulty Level: 1. Easy  2. Medium  3. Difficult ");
        int diff = sc.nextInt();
        return diff;
    }

    public static void printGameAttributes( Player player , Level level , List<Item> items , TurnOrderStrategy s )
    {
        System.out.println();
        System.out.println( "Difficulty Level: " + level.diff );
        System.out.println( "Player: " + player.getName() + ", HP: " + player.getHP() + ", ATK: " + player.getAttack() + ", DEF: " + player.getDefense() + ", SPD: " + player.getSpeed());
        System.out.println( "Items: " + items.get(0).getName() + " + " + items.get(1).getName() );

        System.out.print( "Level: " + level.diff + " : enemies - ");
        for( Enemy e : level.getEnemies() ) { System.out.print ( " " + e.getName() + " " ); }
        System.out.print ( "   backups - " );
        for( Enemy e : level.getBacks() ) { System.out.print ( " " + e.getName() + " " ); }
        System.out.println();

        List<Combatant> all = new ArrayList<>();
        all.add(player);
        all.addAll(level.enemies);
        all = s.order(all);
        System.out.print( "Turn order: " );
        for( Combatant c : all ) System.out.print( c.getName() + "(SPD" + c.getSpeed() + ")  " );
        System.out.println();
    }
    
    // round starts
    public static void printRoundHeader(int round) { System.out.println("\n=== Round " + round + " ==="); }

    // player turn starts
    public static void printPlayerOption()
    {
        System.out.println("Choose Action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Special Skill");
        System.out.println("4. Use Item");
    }
    

    // round ends
    public static void printRoundEnd( int round , Player player, List<Enemy> enemies ) 
    {
        System.out.print("End of Round " + round + ": ");

        // Player HP
        System.out.print(player.getName() + " HP: " + player.getHP() + "/" + player.getMaxHp() + " | ");

        // Enemy HP
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                System.out.print(e.getName() + " HP: " + e.getHP());
            } else {
                System.out.print(e.getName() + " HP: die");
            }

            // 状态（比如 STUN）
            if (e.isStunned()&&e.isAlive()) { System.out.print(" [STUNNED]"); }

            System.out.print(" | ");
        }

        // Items
        for( Item i : player.getInventory() )
        {
            System.out.print( i.getName() + ": 1" + " | " );
        }

        // Cooldown
        System.out.print("Special Skills Cooldown: " + player.getCoolDown() + " rounds");

        System.out.println();
    }

    public static void printVictory( Player player , int round )
    {
        System.out.println( "VICTORY" );
        System.out.println( "Congratulations, you have defeated all your enemies." );
        System.out.println( "Result: Player Victory Remaining HP: " +player.getHP()+ " / " +player.getMaxHp()+ " | Total Rounds: " + round );
    }
    public static void printDefeat( List<Enemy> enemies , int round )
    {
        System.out.println( "DEFEATED" );
        System.out.println( "Don't give up, try again!" );
        System.out.print( "Result: Player Defeated Remaining enemies: " );
        for( Enemy e : enemies ) { System.out.print( e.getName() + " " ); }
        System.out.println( " | Total Rounds survived: " + round );
    }

}