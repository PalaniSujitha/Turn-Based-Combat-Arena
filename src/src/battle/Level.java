package battle;

import combatant.*;
import java.util.*;

public class Level {
	public String diff;
    public List<Enemy> enemies;
    public List<Enemy> backup;
    public boolean backupSpawned = false;

    public Level( String d , List<Enemy> e, List<Enemy> b) 
    {
    	diff = d;
    	enemies = e;
        backup = b;
    }
    
    public boolean hasBackup() {
        return backup != null && !backup.isEmpty();
    }
}