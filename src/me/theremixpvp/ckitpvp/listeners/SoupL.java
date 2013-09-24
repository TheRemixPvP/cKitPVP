package me.theremixpvp.ckitpvp.listeners;

import me.theremixpvp.ckitpvp.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoupL implements Listener {
	
	Main main;
	
	public SoupL(Main plugin) {
		plugin = main;
	}
	
	int soupheal = 7;
	
	@EventHandler
	public void DrinkSoup(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
				if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "SUPERSOUP")) {
					p.setHealth(20.0);
					p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 1000));
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1000000000));
					p.setHealth(20);
					p.setHealthScaled(false);
					p.setItemInHand(new ItemStack(Material.BOWL));
					e.setCancelled(true);
					return;
				}
				double ch = p.getHealth();
				if(p.getHealth() != 20) {
					p.setHealth(ch + soupheal > 20 ? 20 : ch + soupheal);
					ItemStack bowl = new ItemStack(Material.BOWL);
					ItemMeta im = bowl.getItemMeta();
					im.setDisplayName(ChatColor.RED + "Bowl");
					bowl.setItemMeta(im);
					p.setItemInHand(bowl);
					e.setCancelled(true);
				}
				int cf = p.getFoodLevel();
				if(p.getFoodLevel() != 20) {
					p.setFoodLevel(cf + soupheal > 20 ? 20 : cf + soupheal);
					ItemStack bowl = new ItemStack(Material.BOWL);
					ItemMeta im = bowl.getItemMeta();
					im.setDisplayName(ChatColor.RED + "Bowl");
					bowl.setItemMeta(im);
					p.setItemInHand(bowl);
					e.setCancelled(true);
				}
				
			}
		}
	}

}
