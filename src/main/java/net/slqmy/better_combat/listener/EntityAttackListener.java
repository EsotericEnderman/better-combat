package net.slqmy.better_combat.listener;

import net.slqmy.better_combat.BetterCombatPlugin;
import net.slqmy.better_combat.manager.CombatManager;
import net.slqmy.better_combat.type.CombatInstance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityAttackListener implements Listener {
    private final BetterCombatPlugin plugin;

    public EntityAttackListener(BetterCombatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityAttack(@NotNull EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity attacked = event.getEntity();

        double damage = event.getFinalDamage();

        Bukkit.getLogger().info("Original damage: " + damage);

        if (attacked instanceof LivingEntity livingEntity) {
            double remainingHealth = livingEntity.getHealth();

            damage = Math.min(damage, remainingHealth); // Can't deal more damage than there is health remaining.

            Bukkit.getLogger().info("New damage: " + damage);
        }

        Bukkit.getLogger().info(attacker + " attacked " + attacked + " & dealt " + damage + " damage");

        CombatManager combatManager = plugin.getCombatManager();

        CombatInstance combatInstance = combatManager.getCombatInstance(attacked);

        if (combatInstance == null) {
            combatInstance = new CombatInstance(attacked);

            combatManager.addCombatInstance(combatInstance);
        }

        combatInstance.addEntityDamageContribution(attacker, damage);
    }
}
