package winlyps.attackCreeperGolem

import org.bukkit.plugin.java.JavaPlugin

class AttackCreeperGolem : JavaPlugin() {

    override fun onEnable() {
        // Schedule the periodic task to make Iron Golems attack Creepers
        val task = GolemAttackTask(this)
        task.runTaskTimer(this, 0L, 20L) // Run every 20 ticks (1 second)

        logger.info("AttackCreeperGolem plugin has been enabled.")
    }

    override fun onDisable() {
        logger.info("AttackCreeperGolem plugin has been disabled.")
    }
}