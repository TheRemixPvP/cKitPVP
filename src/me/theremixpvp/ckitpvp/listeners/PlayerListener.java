package me.theremixpvp.ckitpvp.listeners;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListener implements Listener {
	
	Main main;
	
	public PlayerListener(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		if(e.getItem().getItemStack().hasItemMeta()) {
			try {
				int amount = Integer.parseInt(e.getItem().getItemStack().getItemMeta().getDisplayName());
				Player p = e.getPlayer();
				PData pd = PDUtils.getByName(e.getPlayer().getName());
				pd.setCredits(pd.credits() + amount);
				p.sendMessage(ChatColor.GREEN + "You received " + amount + " credits for killing " + e.getItem().getItemStack().getItemMeta().getLore().get(0) + "!");
				e.setCancelled(true);
			} catch(Exception ex) {
				return;
			}
		}
	}

}
