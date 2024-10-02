package winlyps.attackCreeperGolem

import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.IronGolem
import org.bukkit.scheduler.BukkitRunnable

class GolemAttackTask(private val plugin: AttackCreeperGolem) : BukkitRunnable() {

    override fun run() {
        // Iterate through all worlds
        for (world in Bukkit.getWorlds()) {
            // Iterate through all entities in the world
            for (entity in world.entities) {
                // Check if the entity is an Iron Golem
                if (entity is IronGolem) {
                    // Find nearby Creepers
                    val nearbyCreepers = entity.getNearbyEntities(10.0, 10.0, 10.0)
                            .filter { it.type == EntityType.CREEPER }

                    // Find the nearest Creeper
                    var nearestCreeper: org.bukkit.entity.LivingEntity? = null
                    var nearestDistance = Double.MAX_VALUE

                    for (creeper in nearbyCreepers) {
                        val distance = entity.location.distance(creeper.location)
                        if (distance < nearestDistance) {
                            nearestDistance = distance
                            nearestCreeper = creeper as? org.bukkit.entity.LivingEntity
                        }
                    }

                    // Make the Iron Golem attack the nearest Creeper
                    if (nearestCreeper != null) {
                        entity.target = nearestCreeper
                    }
                }
            }
        }
    }
}