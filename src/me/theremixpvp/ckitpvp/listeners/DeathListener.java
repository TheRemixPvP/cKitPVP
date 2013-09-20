package me.theremixpvp.ckitpvp.listeners;

import java.util.Random;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
	
	Main main;
	
	public DeathListener(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		main.usedkit.remove(e.getEntity().getPlayer());
		Player vic = e.getEntity().getPlayer();
		if(!(vic.getKiller() instanceof Player)) return;
		Player k = vic.getKiller();
		Random rand = new Random();
		int ri = rand.nextInt(10) + 1;
		double rd = ri;
		PData pd = PDUtils.getByName(k.getName());
		pd.setCredits(pd.credits() + rd);
		pd.setKills(pd.kills() + 1);
		PDUtils.getByName(vic.getName()).setDeaths(PDUtils.getByName(vic.getName()).deaths() + 1);
		k.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + vic.getName() + "!");
		
		if(main.getConfig().getBoolean("death-messages") == true) {
			e.setDeathMessage(ChatColor.DARK_AQUA + k.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + vic.getName());
		} else {
			e.setDeathMessage(null);
		}
		
		
	}
	
	public void onEntityDeath(EntityDeathEvent e) {
		
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		Player k = e.getEntity().getKiller();
		Random rand = new Random();
		int ri = rand.nextInt(10) + 1;
		double rd = ri;
		PData pd = PDUtils.getByName(k.getName());
		pd.setCredits(pd.credits() + rd);
		pd.setKills(pd.kills() + 1);
		
		if(Settings.deathmessages == true) Bukkit.broadcastMessage(ChatColor.DARK_AQUA + k.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + e.getEntityType());
	}

}