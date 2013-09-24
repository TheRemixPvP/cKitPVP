package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.List;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DodgeL implements Listener {
	
	Main main;
	
	public DodgeL(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void ProjectileHit(ProjectileHitEvent e) {
		List<Entity> nbe = e.getEntity().getNearbyEntities(e.getEntity().getLocation().getX(), e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ());
		for(Entity ee : nbe) {
			if(ee instanceof Player) {
				Player ph = (Player) ee;
				PData pd = PDUtils.getByName(ph.getName());
				if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("Dodge")) {
					Arrow a = ph.launchProjectile(Arrow.class);
					a.setShooter(ph);
					a.setVelocity(e.getEntity().getShooter().getLocation().toVector());
				}
			}
		}
	}
	
	@EventHandler
	public void DodgeHit(EntityDamageByEntityEvent e) {
		if(e.getCause() == DamageCause.PROJECTILE) {
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(PDUtils.getByName(p.getName()).getKit() != null && PDUtils.getByName(p.getName()).getKit().equalsIgnoreCase("Dodge")) {
					PData pd = PDUtils.getByName(p.getName());
					Arrow a = p.launchProjectile(Arrow.class);
					a.setShooter(p);
					a.setVelocity(e.getDamager().getVelocity().multiply(-1));
					e.setCancelled(true);
				}
			}
		}
	}

}
