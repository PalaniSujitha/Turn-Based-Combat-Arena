package action;

import combatant.Combatant;
import item.Item;

import java.util.List;

public class UseItem implements Action {

    private Item item;

    public UseItem(Item item) {
        this.item = item;
    }

    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        item.use(actor);
        actor.getInventory().remove(item);
    }
}