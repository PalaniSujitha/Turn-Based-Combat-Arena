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
        List<Player> players = Arrays.asList(
            new Warrior("Warrior"),
            new Wizard("Wizard")
        );

        List<Enemy> enemies = Arrays.asList(
            new Goblin("Goblin"),
            new Wolf("Wolf")
        );

        List<Level> levels = Arrays.asList(
            LevelFactory.createLevel(1),
            LevelFactory.createLevel(2),
            LevelFactory.createLevel(3)
        );

        List<Item> items1 = Arrays.asList(
            new Potion(),
            new PowerStone(),
            new SmokeBomb()
        );
        UI.showLoadingScreen( players , enemies , levels , items1 );

        // choose player
        int choice = UI.choosePlayer();
        Player player = (choice == 1) ? new Warrior("Warrior") : new Wizard("Wizard");

        // chooose items
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 2; i++) 
        {
            int it = UI.chooseItem(i);
            if (it == 1) items.add(new Potion());
            else if (it == 2) items.add(new PowerStone());
            else items.add(new SmokeBomb());
        }
        player.setInventory(items);

        // choose difficulty
        int diff = UI.chooseLevel();
        Level level = LevelFactory.createLevel(diff);
        
        UI.printGameAttributes( player, level, items , new SpeedBasedStrategy() );

        BattleManager manager = new BattleManager(player, level, new SpeedBasedStrategy());
        manager.startBattle();
    }
}