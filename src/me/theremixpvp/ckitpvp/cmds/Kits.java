package me.theremixpvp.ckitpvp.cmds;

import java.util.Arrays;
import java.util.List;

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
	
	private List<String> defkits = Arrays.asList("PvP", "Archer");
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can use this command!");
				return true;
			}
			Player p = (Player) sender;
			PData pd = PDUtils.getByName(p.getName());
			String kits = "";
			for(String defk : Settings.defaultkits) {
				defk = ChatColor.GREEN + defk + ChatColor.GRAY + ", " + ChatColor.RESET;
				kits = kits + defk;
			}
			String otherkits = "";
			for(String kit : Settings.kits) {
				if(p.isOp() || p.hasPermission("ckitpvp.kit." + kit) || pd.unlockedkits().contains(kit)) {
					kit = ChatColor.GREEN + kit + ChatColor.GRAY + ", " + ChatColor.RESET;
					kits = kits + kit;
				} else {
					kit = ChatColor.RED + kit + ChatColor.GRAY + ", " + ChatColor.RESET;
					otherkits = otherkits + kit;
				}
				
			}
			if(kits.length() != 0) kits = kits.substring(0, kits.length() - 2);
			if(otherkits.length() != 0) otherkits = otherkits.substring(0, otherkits.length() - 2);
			p.sendMessage(ChatColor.GREEN + "Your Kits" + ChatColor.GRAY + ": " + kits);
			p.sendMessage(ChatColor.RED + "Other Kits" + ChatColor.GRAY + ": " + otherkits);
			
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
