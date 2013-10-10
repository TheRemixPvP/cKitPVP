package me.theremixpvp.ckitpvp.listeners;

import java.util.Arrays;
import java.util.Random;

import me.theremixpvp.ckitpvp.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathListener implements Listener {
	
	Main main;
	
	public DeathListener(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player v = e.getEntity();
		if(main.usedkit.contains(v)) main.usedkit.remove(v);
		Player k = v.getKiller();
		Random r = new Random();
		int reward = r.nextInt(100) + 1;
		ItemStack i = new ItemStack(Material.EMERALD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("" + ChatColor.RESET + reward);
		im.setLore(Arrays.asList(ChatColor.RESET + v.getName()));
		i.setItemMeta(im);
		Location l = v.getLocation();
		l.setY(l.getBlockY() + 3);
		k.getWorld().dropItem(l, i);
		
		if(main.getConfig().getBoolean("death-messages")) e.setDeathMessage(ChatColor.DARK_AQUA + k.getName() + ChatColor.GRAY + " killed " + ChatColor.DARK_AQUA + v.getName());
		else e.setDeathMessage(null);
	}
	
	/*@EventHandler
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
	}*/

}
