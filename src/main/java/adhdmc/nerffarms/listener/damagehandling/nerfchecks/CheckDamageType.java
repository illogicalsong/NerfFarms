package adhdmc.nerffarms.listener.damagehandling.nerfchecks;

import adhdmc.nerffarms.util.NFConfig;
import adhdmc.nerffarms.listener.damagehandling.AddPDCDamage;
import adhdmc.nerffarms.util.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class CheckDamageType {
    /**
     * Checks the type of damage, compares to the configured blacklisted damage types.
     * Returns true if the damage matches one of the blacklisted types
     * @param nerfMob NamespacedKey
     * @param event EntityDamageEvent
     * @param entity Damaged Entity
     * @param mobPDC Mob's Persistent Data Container
     * @param hitDamage double Final Damage
     * @return boolean
     */
    public static boolean checkDamageType(NamespacedKey nerfMob, EntityDamageEvent event, Entity entity, PersistentDataContainer mobPDC, double hitDamage) {
        Util.debugLvl1("Performing checkDamageType on " + entity.getName());
        if (NFConfig.getBlacklistedDamageTypesSet().contains(event.getCause())) {
            Util.debugLvl2(event.getCause() + " is a blacklisted damage type. Returning true");
            AddPDCDamage.addPDCDamage(event, mobPDC, hitDamage);
            CheckDamageThreshold.checkDamageThreshold(nerfMob, mobPDC, entity);
            return true;
        }
        Util.debugLvl2("Cleared all checkDamageType checks. Returning false");
        return false;
    }
}
