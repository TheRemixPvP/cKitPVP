package me.theremixpvp.ckitpvp.cmds;

import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Soup implements CommandExecutor {
	
	Main main;
	
	public Soup(Main plugin) {
		plugin = main;
	}
	
	private ArrayList<String> cooldown = new ArrayList<String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		final Player p = (Player) sender;
		if(!(p.hasPermission("ckitpvp.soup"))) {
			p.sendMessage(ChatColor.RED + "You don't have access to this! Donate for access!");
			return true;
		}
		
		if(cooldown.contains(p.getName()) && !p.isOp()) {
			p.sendMessage(ChatColor.RED + "Still on cooldown!");
			return true;
		}
		
		Inventory inv = Bukkit.createInventory(p, 54);
		
		for(int i = 0; i < 54; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		p.openInventory(inv);
		cooldown.add(p.getName());
		Bukkit.getScheduler().scheduleSyncDelayedTask(main.p, new Runnable() {
			@Override
			public void run() {
				cooldown.remove(p.getName());
				p.sendMessage(ChatColor.GREEN + "You can use /soup again!");
			}
		}, 900);
		return true;
	}

}
