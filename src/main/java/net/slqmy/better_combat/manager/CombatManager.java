package net.slqmy.better_combat.manager;

import net.slqmy.better_combat.type.CombatInstance;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class CombatManager {

    private final List<CombatInstance> combatInstances = new ArrayList<>();

    public CombatInstance getCombatInstance(Entity attackedEntity) {
        for (CombatInstance combatInstance : combatInstances) {
            if (combatInstance.getAttackedEntity().equals(attackedEntity)) {
                return combatInstance;
            }
        }

        return null;
    }

    public void addCombatInstance(CombatInstance combatInstance) {
        combatInstances.add(combatInstance);
    }

    public void removeCombatInstance(CombatInstance combatInstance) {
        combatInstances.remove(combatInstance);
    }
}
