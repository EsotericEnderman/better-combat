package net.slqmy.better_combat.type;

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class CombatInstance {

    private final Entity attackedEntity;

    private final Map<Entity, Double> damageContributorDamageMap = new HashMap<>();

    public Entity getAttackedEntity() {
        return attackedEntity;
    }

    public Map<Entity, Double> getDamageContributorDamageMap() {
        return damageContributorDamageMap;
    }

    public CombatInstance(Entity attackedEntity) {
        this.attackedEntity = attackedEntity;
    }

    public Double getEntityDamageContribution(Entity damager) {
        return damageContributorDamageMap.get(damager);
    }

    public void addEntityDamageContribution(Entity damagerEntity, Double damageAmount) {
        Double currentDamage = damageContributorDamageMap.get(damagerEntity);

        damageContributorDamageMap.put(damagerEntity, currentDamage == null ? damageAmount : currentDamage + damageAmount);
    }

    public Entity getHighestDamageContributor() {
        Entity highestDamageContributor = null;
        double highestContributedDamage = 0;

        for (Map.Entry<Entity, Double> damageContributionEntry : damageContributorDamageMap.entrySet()) {
            double contributedDamage = damageContributionEntry.getValue();

            if (contributedDamage > highestContributedDamage) {
                highestDamageContributor = damageContributionEntry.getKey();
                highestContributedDamage = contributedDamage;
            }
        }

        return highestDamageContributor;
    }

    @Override
    public String toString() {
        return "CombatInstance{attackedEntity=" + attackedEntity + "}";
    }
}
