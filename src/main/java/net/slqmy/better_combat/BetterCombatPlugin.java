package net.slqmy.better_combat;

import net.slqmy.better_combat.listener.EntityAttackListener;
import net.slqmy.better_combat.listener.EntityDeathListener;
import net.slqmy.better_combat.manager.CombatManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterCombatPlugin extends JavaPlugin {

    private CombatManager combatManager;

    public CombatManager getCombatManager() {
        return combatManager;
    }

    @Override
    public void onEnable() {
        combatManager = new CombatManager();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new EntityAttackListener(this), this);
        pluginManager.registerEvents(new EntityDeathListener(this), this);
    }
}
