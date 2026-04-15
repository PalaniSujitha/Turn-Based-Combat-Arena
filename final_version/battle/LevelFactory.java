package battle;

import combatant.*;
import java.util.*;

public class LevelFactory 
{

    public static Level createLevel( int d ) 
    {
        if ( d == 1 ) 
        {
            return new Level(
            	"Easy",
                List.of(new Goblin("Goblin A"), new Goblin("Goblin B"), new Goblin("Goblin C")),
                new ArrayList<>()
            );
        }
        else if ( d == 2 ) 
        {
            return new Level(
            	"Medium",
                List.of(new Goblin("Goblin A"), new Wolf("Wolf A")),
                List.of(new Wolf("Wolf B"), new Wolf("Wolf C"))
            );
        }

        return new Level(
        "Difficult",
        List.of(new Goblin("Goblin A"), new Goblin("Goblin B")),
        List.of(new Goblin("Goblin C"), new Wolf("Wolf A"), new Wolf("Wolf B"))
        );
        
    }
}