package item;

import combatant.*;
import java.util.*;

public abstract class Item 
{
    protected String name;
    public abstract void useItem( Combatant user , List<Combatant> targets );
    public String getName(){ return name; }
}