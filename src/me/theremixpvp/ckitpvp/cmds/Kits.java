package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kits implements CommandExecutor {
	
	Main main;
	
	public Kits(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can use this command!");
				return true;
			}
			Player p = (Player) sender;
			PData pd = PDUtils.getByName(p.getName());
			String s = null;
			String n = null;
			for(String kn : Settings.kits) {
				if(p.isOp() || p.hasPermission("ckitpvp.kit." + kn) || pd.unlockedkits().contains(kn)) {
					s = s + ChatColor.GREEN + kn + ChatColor.GRAY + ", ";
				} else {
					n = n + ChatColor.RED + kn + ChatColor.GRAY + ", ";
				}
				
			}
			
			p.sendMessage(ChatColor.GREEN + "Your kits" + ChatColor.GRAY + ": ");
			if(s.length() != 0) p.sendMessage(s.substring(s.length() - 2));
			p.sendMessage(ChatColor.RED + "Other kits" + ChatColor.GRAY + ": ");
			if(n.length() != 0) p.sendMessage(n.substring(n.length() - 2));
			return true;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("add")) {
				if(sender.hasPermission("ckitpvp.kits.add")) {
					String k = args[0];
					Settings.kits.add(k);
					sender.sendMessage(ChatColor.AQUA + "Kit added!");
					return true;
				}
				return true;
			} else if(args[0].equalsIgnoreCase("remove")) {
				if(sender.hasPermission("ckitpvp.kits.remove")) {
					if(!Settings.kits.contains(args[0])) {
						sender.sendMessage(ChatColor.RED + "Kit isn't in the list!");
						return true;
					}
					Settings.kits.remove(args[0]);
					sender.sendMessage(ChatColor.AQUA + "Kit removed!");
					return true;
				}
				return true;
			}
				
		}
		
		return false;
	}

}
