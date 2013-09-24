package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RusherL implements Listener {
	
	Main main;
	
	public RusherL(Main plugin) {
		plugin = main;
	}
	
	Plugin pl = main;
	
	private ArrayList<String> c = new ArrayList<String>();
	
	@EventHandler
	public void RushAbility(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		PData pd = PDUtils.getByName(p.getName());
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getType() == Material.FEATHER) {
				if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("rusher")) {
					if(c.contains(p.getName())) {
						p.sendMessage(ChatColor.RED + "Your Rush ability is still on cooldown!");
						return;
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 2));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 160, 1));
					c.add(p.getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(main.p, new Runnable() {
						@Override
						public void run() {
							c.remove(p.getName());
							p.sendMessage(ChatColor.DARK_AQUA + "Your Rush ability is recharged!");
						}
					}, 600);
				}
			}
		}
	}
	
	
}
