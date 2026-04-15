package main;

import combatant.*;
import item.*;
import action.*;
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

    // print attributes
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
    

    // action UI
    public static void printInvulnerable( Combatant actor , Combatant target )
    {
        System.out.println( actor.getName() + " → BasicAttack → "+ target.getName() +
                ": 0 damage | "+target.getName()+" HP: "+ target.getHP() );
    }
    public static void printBasicAttack( Combatant actor , Combatant target , int HP1 , int HP2 , int dmg )
    {
        System.out.println( actor.getName() + " → BasicAttacks → " + target.getName() + 
                ": HP: " + HP1 + " → " + HP2 + "(dmg:" + actor.getAttack() + "-" + target.getDefense() + "=" + dmg + ")" );
    }
    public static void printDefend( Combatant actor , int DF1 )
    { System.out.println( actor.getName() + " →  Defend → Defence: " + DF1 + " → " + (DF1+10) + "(+10)"); }
    public static void printShieldBash( Combatant actor , Combatant target , int HP1 , int HP2 , int dmg )
    {
        System.out.println( "Warrior → Shield Bash → " + target.getName() +
                ": HP: " + HP1 + " → " + HP2 + "(dmg:" + actor.getAttack() + "-" + target.getDefense() + "=" + dmg + ")" +
                " | " + target.getName() + " STUNNED (2turns)" );
    }
    public static void printBlast1(){ System.out.print( "Wizard → Arcane Blast → All Enemies: " ); }
    public static void printBlast2( Combatant actor , Combatant e , int HP1 , int HP2 , int dmg )
    {
        System.out.print( e.getName() + " HP: " + HP1 + " → " + HP2 + 
                "(dmg: " + actor.getAttack() + "-" + e.getDefense() + "=" + dmg + ") " );
    }
    public static void printBlast3( int atk1 , int atk2 ) 
    { System.out.print("| ATK: " + atk1 + " → " + atk2 + " (+10 per Arcane Blast kill) "); }
    public static void printPotion( Combatant user , int HP1 , int HP2 )
    { System.out.println( user.getName() + " →  Item → Potion used: HP: " + HP1 + " → " + HP2 + "(+100)" ); }
    public static void printPowerStone( Combatant user , Action skill )
    { System.out.println( user.getName() + " → Item → Power Stone used → " + skill.getClass().getSimpleName() + " triggered"); }
    public static void printSmokeBomb( Combatant user )
    { System.out.println( user.getName() + " → Item → Smoke Bomb used: Enemy attacks deal 0 damage this turn + next" ); }


    // exception UI
    public static void printSkillCoolDown(){ System.out.println("Skill is on cooldown!"); }
    public static void printNoItem(){ System.out.println("No items available!"); }
    public static void printInvalidChoice(){ System.out.println("Invalid choice!"); }
    public static void printNoTarget(){ System.out.println("No valid target."); }
    public static void printInvalidInput(){ System.out.println("Invalid input, try again."); } 


    // selection UI
    public static void printSelectTarget(){ System.out.println("Select Target:"); }
    public static void printTargetInfo( int i , List<Enemy> alive )
    { System.out.println((i + 1) + ". " + alive.get(i).getName() +
                    " (HP: " + alive.get(i).getHP() + ")"); }
    public static void printSelectItem(){ System.out.println("Select Item:"); }
    public static void printItemInfo( int i , List<Item> inv ){ System.out.println((i + 1) + ". " + inv.get(i).getName()); }



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

        if( player instanceof Wizard ) System.out.print( " | Wizard ATK: " + player.getAttack() );

        System.out.println();
    }

    public static void printVictory( Player player , int round )
    {
        System.out.println();
        System.out.println( "VICTORY" );
        System.out.println( "Congratulations, you have defeated all your enemies." );
        System.out.println( "Result: Player Victory Remaining HP: " +player.getHP()+ " / " +player.getMaxHp()+ " | Total Rounds: " + round );
    }
    public static void printDefeat( List<Enemy> enemies , int round )
    {
        System.out.println();
        System.out.println( "DEFEATED" );
        System.out.println( "Don't give up, try again!" );
        System.out.print( "Result: Player Defeated Remaining enemies: " );
        for( Enemy e : enemies ) { System.out.print( e.getName() + " " ); }
        System.out.println( " | Total Rounds survived: " + round );
    }

    public static int printOption()
    {
        System.out.println();
        System.out.println("Choose Option:");
        System.out.println("1. Replay with same settings");
        System.out.println("2. Start new game");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        return choice;
    }

    public static void printThanks()
    {
        System.out.println();
        System.out.println("What do you think about the code? ");
        System.out.println("1. Fantastic! I will give it an A+");
        System.out.println("2. Excellent! I will give it an A+");
        System.out.println("3. Perfect! I will give it an A+");
        sc.nextInt();
        System.out.println("Thank you so much!!! Here are my gifts for you!!! 🎁🎁🎁🎁🎁");
    }

}