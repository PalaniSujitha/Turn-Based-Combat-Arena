package main;

import combatant.*;
import item.*;

import java.util.*;

public class UI 
{
    // 回合开始
    public static void printRoundHeader(int round) { System.out.println("\n=== Round " + round + " ==="); }

    // 行动日志
    public static void printAction(String actor, String action, String target, String detail) 
    {
        System.out.println(actor + " → " + action + " → " + target + ": " + detail);
    }

    // 状态日志（stun / skip / eliminated）
    public static void printStatus(String msg) {
        System.out.println(msg);
    }

    // 回合结束总结
    public static void printRoundEnd(Player player, List<Enemy> enemies) 
    {
        System.out.print("End of Round: ");

        // Player HP
        System.out.print(player.getName() + " HP: " + player.getHP() + "/" + player.getMaxHp() + " | ");

        // Enemy HP
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                System.out.print(e.getName() + " HP: " + e.getHP());
            } else {
                System.out.print(e.getName() + " HP: ✗");
            }

            // 状态（比如 STUN）
            if (e.isStunned()) {
                System.out.print(" [STUNNED]");
            }

            System.out.print(" | ");
        }

        // Items
        System.out.print("Potion: " + countItem(player, "Potion") + " | ");
        System.out.print("Smoke Bomb: " + countItem(player, "Smoke Bomb") + " | ");

        // Cooldown
        System.out.print("Special Skills Cooldown: " + player.getCoolDown() + " rounds");

        System.out.println();
    }

    private static int countItem(Player player, String name) {
        int count = 0;
        for (Item i : player.getInventory()) {
            if (i.getName().equals(name)) count++;
        }
        return count;
    }
}