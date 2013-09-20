package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.Random;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

public class SniperL implements Listener {
	
	Main main;
	
	public SniperL(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if(e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			PData pd = PDUtils.getByName(p.getName());
			
			if(pd.getKit().equalsIgnoreCase("sniper")) {
			}
		}
	}
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			PData pd = PDUtils.getByName(p.getName());
			if(pd.getKit().equalsIgnoreCase("sniper")) {
			}
		}
	}
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e) {
		if(e.getCause() == DamageCause.PROJECTILE && e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			PData pd = PDUtils.getByName(p.getName());
			if(pd.getKit().equalsIgnoreCase("sniper")) {
				e.setDamage(100000);
				p.getInventory().addItem(new ItemStack(Material.ARROW));
				Random rand = new Random();
				int ri = rand.nextInt(10) + 1;
				double rd = ri;
				pd.setCredits(pd.credits() + rd);
				if(e.getEntityType() == EntityType.PLAYER) {
					Player v = (Player) e.getEntity();
					p.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + v.getName() + "!");
				}
				p.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + e.getEntityType() + "!");
			}
		}
	}


}
