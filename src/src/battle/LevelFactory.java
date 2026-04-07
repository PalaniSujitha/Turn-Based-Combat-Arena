package battle;

import combatant.*;
import java.util.*;

public class LevelFactory {

    public static Level createLevel(int d) {
        if (d == 1) {
            return new Level(
            	"Easy",
                List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C")),
                new ArrayList<>()
            );
        }
        if (d == 2) {
            return new Level(
            	"Medium",
                List.of(new Goblin("Goblin G1"), new Wolf("Wolf W1")),
                List.of(new Wolf("Wolf W2"), new Wolf("Wolf W3"))
            );
        }
        return new Level(
        	"Difficult",
            List.of(new Goblin("Goblin G1"), new Goblin("Goblin G2")),
            List.of(new Goblin("Goblin G3"), new Wolf("Wolf W1"), new Wolf("Wolf W2"))
        );
    }
}