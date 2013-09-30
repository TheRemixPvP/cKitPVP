package me.theremixpvp.ckitpvp.listeners.kits;

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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NinjaL implements Listener {
	
	Main main;
	
	public NinjaL(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
	public void NinjaAbility(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		PData pd = PDUtils.getByName(p.getName());
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && pd.getKit() != null && pd.getKit().equalsIgnoreCase("Ninja")) {
			if(p.getItemInHand().getType() == Material.REDSTONE) {
				p.getInventory().setArmorContents(new ItemStack[] {
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.DIAMOND_HELMET),
				});
				p.updateInventory();
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 4));
					p.setItemInHand(new ItemStack(Material.SUGAR));
					Bukkit.getScheduler().scheduleSyncDelayedTask(main.p, new Runnable() {
						@Override
						public void run() {
							if(p.getInventory().contains(new ItemStack(Material.SUGAR))) {
								p.getInventory().remove(Material.SUGAR);
								ItemStack si = new ItemStack(Material.REDSTONE);
								ItemMeta sim = si.getItemMeta();
								sim.setDisplayName(ChatColor.AQUA + "Vanish Powder");
								si.setItemMeta(sim);
								p.getInventory().addItem(si);
								p.getInventory().setArmorContents(new ItemStack[] {
										new ItemStack(Material.CHAINMAIL_BOOTS),
										new ItemStack(Material.CHAINMAIL_LEGGINGS),
										new ItemStack(Material.CHAINMAIL_CHESTPLATE),
										new ItemStack(Material.DIAMOND_HELMET),
								});
								p.updateInventory();
							}
						}
					}, 500);
			}
		}
	}
	
	@EventHandler
	public void AbilityDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		PData pd = PDUtils.getByName(p.getName());
		if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("Ninja") && p.getItemInHand().getType() == Material.REDSTONE || p.getItemInHand().getType() == Material.SUGAR) {
			e.setCancelled(true);
		}
			
	}

}
