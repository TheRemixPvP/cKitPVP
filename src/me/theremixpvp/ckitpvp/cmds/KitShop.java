package me.theremixpvp.ckitpvp.cmds;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KitShop implements CommandExecutor {
	
	Main main;
	
	public KitShop(Main plugin) {
		plugin = main;
	}
	
	
	public static HashMap<String, Double> shopkits = new HashMap<String, Double>();
	
	public static void load(Plugin p) {
		File f = new File(p.getDataFolder() + "/kitshop.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		for(String s : cfg.getStringList("kits")) {
			double price = cfg.getDouble(s);
			shopkits.put(s, price);
		}
		Main.log.log(Level.INFO, "Kitshop loaded.");
		Main.log.log(Level.INFO, shopkits.toString());
	}
	
	public static void save(Plugin p) {
		File f = new File(p.getDataFolder() + "/kitshop.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		ConfigurationSection sec = cfg.createSection("kits");
		for(String name : shopkits.keySet()) {
			cfg.set(sec + "." + name, shopkits.get(name));
		}
		try {
			cfg.save(f);
			Main.log.log(Level.INFO, "Kitshop saved.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.DARK_AQUA + "/kitshop <list:buy> [name]");
			return true;
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("list")) {
				for(String name : shopkits.keySet()) {
					double price = shopkits.get(name);
					sender.sendMessage(ChatColor.DARK_AQUA + name + ChatColor.GRAY + " - $" + price);
				}
				return true;
			} else if(args[0].equalsIgnoreCase("listr")) {
				sender.sendMessage(shopkits.toString());
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_AQUA + "/kitshop <list:buy> [name]");
				return true;
			}
		} else if(args.length == 2) {
			
			if(args[0].equalsIgnoreCase("buy")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Only players can buy kits!");
					return true;
				}
				Player p = (Player) sender;
				for(String name : shopkits.keySet()) {
					if(name.equalsIgnoreCase(args[1])) {
						PData pd = PDUtils.getByName(p.getName());
						if(pd.credits() >= shopkits.get(name)) {
							pd.setCredits(pd.credits() - shopkits.get(name));
							pd.addkit(name);
							p.sendMessage(ChatColor.DARK_AQUA + "You bought the " + name + " kit for $" + shopkits.get(name) + "!");
							return true;
						}
						p.sendMessage(ChatColor.RED + "You do not have enough credits to buy this kit!");
						return true;
					}
				}
				p.sendMessage(ChatColor.RED + "Invalid kit!");
				return true;
				
			}
			
		} else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("add")) {
				String name = args[1];
				double price = Double.parseDouble(args[2]);
				shopkits.put(name, price);
				sender.sendMessage(ChatColor.DARK_AQUA + "Kit added!");
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_AQUA + "/kitshop <list:buy> [name]");
				return true;
			}
		}
		
		
		return false;
	}

}
