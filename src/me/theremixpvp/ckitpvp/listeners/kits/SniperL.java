package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.Random;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class SniperL implements Listener {
	
	Main main;
	
	public SniperL(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void BowShoot(EntityShootBowEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(PDUtils.getByName(p.getName()).getKit() != null && PDUtils.getByName(p.getName()).getKit().equalsIgnoreCase("Sniper")) {
				e.getProjectile().setMetadata("isSniperBullet", new FixedMetadataValue(main.p, true));
			}
			
		}
	}
	
	@EventHandler
	public void SniperBulletHit(EntityDamageByEntityEvent e) {
		if(e.getDamager().hasMetadata("isSniperBullet")) {
			Arrow a = (Arrow) e.getDamager();
			Player p = (Player) a.getShooter();
			PData pd = PDUtils.getByName(p.getName());
			if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("Sniper")) {
				e.setDamage(100);
				p.getInventory().addItem(new ItemStack(Material.ARROW));
				if(e.getEntity() instanceof Player) {
					Player vic = (Player) e.getEntity();
					if(Settings.deathmessages == true) {
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + vic.getName());
					}
					Random rand = new Random();
					int ri = rand.nextInt(10) + 1;
					double rd = ri;
					pd.setCredits(pd.credits() + rd);
					pd.setKills(pd.kills() + 1);
					PDUtils.getByName(vic.getName()).setDeaths(PDUtils.getByName(vic.getName()).deaths() + 1);
					p.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + vic.getName() + "!");
				} else {
					Random rand = new Random();
					int ri = rand.nextInt(10) + 1;
					double rd = ri;
					pd.setCredits(pd.credits() + rd);
					pd.setKills(pd.kills() + 1);
					p.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + e.getEntityType() + "!");
					if(Settings.deathmessages == true) Bukkit.broadcastMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + e.getEntityType());
				}
			}
		}
	}
	
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		if(PDUtils.getByName(e.getEntity().getKiller().getName()).getKit() != null && PDUtils.getByName(e.getEntity().getKiller().getName()).getKit().equalsIgnoreCase("Sniper") && e.getEntity().getKiller().getItemInHand().getType() != Material.BOW) {
			e.getEntity().getKiller().getInventory().addItem(new ItemStack(Material.ARROW));
		}
		
		
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		if(PDUtils.getByName(e.getEntity().getKiller().getName()).getKit() != null && PDUtils.getByName(e.getEntity().getKiller().getName()).getKit().equalsIgnoreCase("Sniper") && e.getEntity().getKiller().getItemInHand().getType() != Material.BOW) {
			e.getEntity().getKiller().getInventory().addItem(new ItemStack(Material.ARROW));
		}
	}

}
