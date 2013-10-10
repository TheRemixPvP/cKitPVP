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
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BomberL implements Listener {
	
	Main main;
	
	public BomberL(Main plugin) {
		plugin = main;
	}
	
	public ArrayList<Player> cooldown = new ArrayList<Player>();
	
	@EventHandler
	public void onBombThrown(final PlayerEggThrowEvent e) {
		final Player p = e.getPlayer();
		PData pd = PDUtils.getByName(p.getName());
		if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("Bomber")) {
			if(cooldown.contains(p)) {
				p.sendMessage(ChatColor.RED + "Your bombing ability is still on cooldown!");
				ItemStack bombs = new ItemStack(Material.EGG);
				ItemMeta bombmeta = bombs.getItemMeta();
				bombmeta.setDisplayName(ChatColor.GREEN + "Bomb");
				bombs.setItemMeta(bombmeta);
				p.getInventory().setItem(1, bombs);
				return;
			}
			cooldown.add(p);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, new Runnable() {
				@Override
				public void run() {
					p.getWorld().createExplosion(e.getEgg().getLocation(), 3F, false);
					p.getWorld().createExplosion(e.getEgg().getLocation(), 2F, false);
					p.getWorld().createExplosion(e.getEgg().getLocation(), 1F, false);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, new Runnable() {
						@Override
						public void run() {
							cooldown.remove(p);
							ItemStack bombs = new ItemStack(Material.EGG);
							ItemMeta bombmeta = bombs.getItemMeta();
							bombmeta.setDisplayName(ChatColor.GREEN + "Bomb");
							bombs.setItemMeta(bombmeta);
							p.getInventory().addItem(bombs);
						}
					}, 20 * 3);
				}
			}, 20 * 3);
		}
	}

}
