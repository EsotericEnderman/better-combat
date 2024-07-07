package net.slqmy.better_combat.listener;

import net.slqmy.better_combat.BetterCombatPlugin;
import net.slqmy.better_combat.manager.CombatManager;
import net.slqmy.better_combat.type.CombatInstance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDeathListener implements Listener {

    private final BetterCombatPlugin plugin;

    public EntityDeathListener(BetterCombatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(@NotNull EntityDeathEvent event) {
        CombatManager combatManager = plugin.getCombatManager();

        LivingEntity deadEntity = event.getEntity();

        CombatInstance combatInstance = combatManager.getCombatInstance(deadEntity);

        if (combatInstance == null) {
            return;
        }

        Entity highestDamageContributor = combatInstance.getHighestDamageContributor();

        Bukkit.getLogger().info(highestDamageContributor + " contributed most to the death of " + deadEntity);
        Bukkit.getLogger().info("combatInstance.damageContributorDamageMap = " + combatInstance.getDamageContributorDamageMap());
        Bukkit.getLogger().info("combatInstance.damageContributorDamageMap.entrySet = " + combatInstance.getDamageContributorDamageMap().entrySet());

        EntityDamageEvent entityDamageEvent = deadEntity.getLastDamageCause();

        if (entityDamageEvent instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
            Entity damager = entityDamageByEntityEvent.getDamager();

            if (damager instanceof Player player) {
                deadEntity.setKiller(player);
            }
        }

        combatManager.removeCombatInstance(combatInstance);
    }
}
