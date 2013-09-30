package me.theremixpvp.ckitpvp.shop;

import java.util.Arrays;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_PVP;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopCmd implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use the shop!");
			return true;
		}
		
		
		final Player p = (Player) sender;
		
		ShopMenu menu = new ShopMenu(ChatColor.GOLD + "" + ChatColor.ITALIC + "Shop", 9, new ShopMenu.OptionClickEventHandler() {
			
			@Override
			public void onOptionClick(OptionClickEvent event) {
				if(event.getItem().getType() == Material.DIAMOND_SWORD) {
					event.setWillClose(false);
					event.setWillDestroy(false);
					p.closeInventory();
					ShopMenu m = Menus.weapons;
					m.open(p);
					return;
				} else if(event.getItem().getType() == Material.ENCHANTED_BOOK) {
					event.setWillClose(false);
					event.setWillDestroy(false);
					p.closeInventory();
					ShopMenu m = Menus.enchants;
					m.open(p);
					return;
				} else if(event.getItem().getType() == Material.CHEST) {
					event.setWillClose(false);
					event.setWillDestroy(false);
					p.closeInventory();
					ShopMenu m = Menus.kits;
					m.open(p);
					return;
				}
				
				
				
				
			}
		}, Main.p)
		.setOption(0, new ItemStack(Material.DIAMOND_SWORD), "Weapons", "Buy weapons.")
		.setOption(1, new ItemStack(Material.ENCHANTED_BOOK), "Enchantments", "Buy enchantments.")
		.setOption(2, new ItemStack(Material.CHEST), "Kits", "Buy kits");
		menu.open(p);
		
		
		return false;
	}
	

}
