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
                    UI.printRoundEnd( round , player, level.enemies );
                    if( player.isAlive() ) UI.printVictory( player , round );
                    else UI.printDefeat( getAliveEnemies() , round );
                    return;
                }
            }
            spawnBackup();
            UI.printRoundEnd( round , player, level.enemies );
            round++;
        }
    }

    private void playerTurn()
    {
        while( true )
        {
            try
            {
                int choice = UI.printPlayerOption();

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
                            UI.printSkillCoolDown();
                            continue;
                        }
                        action = player.getSkill(); // SpecialSkill / WarriorSkill / WizardSkill
                        player.resetCooldown();
                        break;
                    case 4: // item
                        if (player.getInventory().isEmpty()) 
                        {
                            UI.printNoItem();
                            continue;
                        }
                        Item item = selectItem();
                        if (item == null) continue;

                        action = new UseItem(item);
                        break;
                    default:
                        UI.printInvalidChoice();
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
                    UI.printNoTarget();
                    continue;
                }

                // execute 
                action.execute( player , targets );
                // UI.printAction( player , action , targets );
                break;
            }
            catch( Exception e ){ UI.printInvalidInput(); }
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

        UI.printSelectTarget();
        for (int i = 0; i < alive.size(); i++){ UI.printTargetInfo(i, alive); }

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

        UI.printSelectItem();
        for (int i = 0; i < inv.size(); i++) { UI.printItemInfo(i, inv); }

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
        if (!player.isAlive()) { return true; }

        boolean noEnemiesAlive = level.enemies.stream().noneMatch(Enemy::isAlive);

        // no backup（Easy）
        if (!level.hasBackup() && noEnemiesAlive) { return true; }

        // have backup and all killed
        if (level.hasBackup() && level.backupSpawned && noEnemiesAlive) { return true;}

        return false;
    }

}