package me.theremixpvp.ckitpvp.listeners;

import java.util.HashMap;
import java.util.Random;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {
	
	Main main;
	
	public DeathListener(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		main.usedkit.remove(e.getEntity().getPlayer());
		Player vic = e.getEntity();
		int lvls = vic.getLevel();
		Player k = vic.getKiller();
		Random rand = new Random();
		int ri = rand.nextInt(10) + 1;
		double rd = ri;
		PData pd = PDUtils.getByName(k.getName());
		pd.setCredits(pd.credits() + rd);
		pd.setKills(pd.kills() + 1);
		PDUtils.getByName(vic.getName()).setDeaths(PDUtils.getByName(vic.getName()).deaths() + 1);
		k.sendMessage(ChatColor.GREEN + "You earned " + rd + " credits for killing " + vic.getName() + "!");
		PDUtils.getByName(vic.getName()).setKit(null);
		
		if(main.getConfig().getBoolean("death-messages") == true) {
			e.setDeathMessage(ChatColor.DARK_AQUA + k.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + vic.getName());
		} else {
			e.setDeathMessage(null);
		}
		
		
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		Player k = e.getEntity().getKiller();
		Random rand = new Random();
		int ri = rand.nextInt(10) + 1;
		double rd = ri;
		PData pd = PDUtils.getByName(k.getName());
		pd.setCredits(pd.credits() + rd);
		pd.setKills(pd.kills() + 1);
		e.setDroppedExp(0);
		
		if(Settings.deathmessages == true) Bukkit.broadcastMessage(ChatColor.DARK_AQUA + k.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + e.getEntityType());
	}

}
