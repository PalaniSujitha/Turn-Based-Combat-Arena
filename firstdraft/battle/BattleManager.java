package battle;

import combatant.*;
import action.*;
import item.*;
import strategy.*;

import java.util.*;

import main.UI;

public class BattleManager 
{

    private Player player;
    private Level level;
    private TurnOrderStrategy strategy;

    private Scanner sc = new Scanner( System.in );

    public BattleManager(Player p, Level l, TurnOrderStrategy s) 
    {
        player = p;
        level = l;
        strategy = s;
    }

    public void startBattle()
    {
        int round = 1;

        while( true )
        {
            UI.printRoundHeader(round);

            List<Combatant> all = new ArrayList<>();
            all.add(player);
            all.addAll(level.enemies);
            all = strategy.order(all);

            for( Combatant c : all )
            {
                if ( !c.isAlive() ) continue;
                c.processEffects(); // since every turn process , apply function in StatusEffect should only perform 1 turns
                
                if (c instanceof Player) 
                {
                    player.decreaseCooldown();
                    playerTurn();
                }
                else if( c instanceof Enemy) enemyTurn((Enemy)c);
                
                if (checkEnd()) 
                {
                    return;
                }
            }
            spawnBackup();

            System.out.println( "End of round " + round );
            System.out.print( player.getName() + " HP: " + player.getHP() + "/" + player.getMaxHp() + "  ");
            for( Enemy e : level.enemies )
            {
            	System.out.print( e.getName() + " HP: " + e.getHP() + "  ");
            }
            System.out.println();
            for( Item i : player.getInventory() )
            {
            	System.out.print( i.getName() + ": 1  ");
            }
            System.out.println( "Special Skills Cooldown: " + player.getCoolDown() + " Rounds" );
            
            round++;
        }
    }

    private void playerTurn()
    {
        while( true )
        {
            try
            {
                System.out.println("Choose Action:");
                System.out.println("1. Basic Attack");
                System.out.println("2. Defend");
                System.out.println("3. Special Skill");
                System.out.println("4. Use Item");
                int choice = sc.nextInt();

                Action action = null;
                List<Combatant> targets = new ArrayList<>(); // enemy list

                // choose action first
                switch (choice) 
                {
                    case 1: // basic attack
                        action = new BasicAttack();
                        break;
                    case 2: // defend
                        action = new DefendAction();
                        break;
                    case 3: // skill
                        if (!player.canUseSkill()) 
                        {
                            System.out.println("Skill is on cooldown!");
                            continue;
                        }
                        action = player.getSkill(); // SpecialSkill / WarriorSkill / WizardSkill
                        player.resetCooldown();
                        break;
                    case 4: // item
                        if (player.getInventory().isEmpty()) 
                        {
                            System.out.println("No items available!");
                            continue;
                        }
                        Item item = selectItem();
                        if (item == null) continue;

                        action = new UseItem(item);
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        continue;
                }

                // then choose target according to action
                Combatant singleTarget = action.selectSingleTarget(this, player);

                // basic attack  or  worrior skill  or  powerstone
                if (singleTarget != null) { targets.add(singleTarget); } 
                // wizard skill  or  defend  or  other items
                else { targets.addAll(action.selectTargets(this, player)); }

                // exclude defend and other items 
                if (targets.isEmpty() && !(action instanceof DefendAction) && !(action instanceof UseItem) ) 
                { // Defend and pther items do not have enemy targets
                    System.out.println("No valid target.");
                    continue;
                }

                // execute 
                action.execute( player , targets );
                break;
            }
            catch( Exception e )
            {
                System.out.println("Invalid input, try again.");
                sc.nextLine(); 
            }
        }
    }

    private void enemyTurn(Enemy enemy)
    {
        if (!enemy.isAlive()) return;
        if (!player.isAlive()) return;

        Action action = new BasicAttack();
        List<Combatant> targets = new ArrayList<>();
        targets.add(player);

        action.execute(enemy, targets);
    }

    public List<Enemy> getAliveEnemies() 
    {
        List<Enemy> alive = new ArrayList<>();
        for (Enemy e : level.enemies) { if (e.isAlive()) alive.add(e); }
        return alive;
    }

    public Enemy selectEnemy() 
    {
        List<Enemy> alive = getAliveEnemies();

        if (alive.isEmpty()) return null;

        System.out.println("Select Target:");
        for (int i = 0; i < alive.size(); i++) 
        {
            System.out.println((i + 1) + ". " + alive.get(i).getName() +
                    " (HP: " + alive.get(i).getHP() + ")");
        }

        try 
        {
            int choice = sc.nextInt();
            if ( choice < 1 || choice > alive.size() ) return null;
            return alive.get( choice - 1 );
        } 
        catch (Exception e) 
        {
            sc.nextLine();
            return null;
        }
    }

    public Item selectItem() {
        List<Item> inv = player.getInventory();

        System.out.println("Select Item:");
        for (int i = 0; i < inv.size(); i++) {
            System.out.println((i + 1) + ". " + inv.get(i).getName());
        }

        try {
            int choice = sc.nextInt();
            if (choice < 1 || choice > inv.size()) return null;
            return inv.get(choice - 1);
        } catch (Exception e) {
            sc.nextLine();
            return null;
        }
    }

    private void spawnBackup() 
    {
        if (!level.backupSpawned && level.hasBackup() &&
            level.enemies.stream().noneMatch(Enemy::isAlive)) 
        {

            level.enemies = new ArrayList<>(level.backup);
            level.backupSpawned = true;

            System.out.println("Backup Spawn!");
        }
    }

    private boolean checkEnd() 
    {

        // user die
        if (!player.isAlive()) 
        {
            System.out.println("LOSE");
            return true;
        }

        boolean noEnemiesAlive = level.enemies.stream().noneMatch(Enemy::isAlive);

        // no backup（Easy）
        if (!level.hasBackup() && noEnemiesAlive) 
        {
            System.out.println("WIN");
            return true;
        }

        // have backup and all killed
        if (level.hasBackup() && level.backupSpawned && noEnemiesAlive) 
        {
            System.out.println("WIN");
            return true;
        }

        return false;
    }

}