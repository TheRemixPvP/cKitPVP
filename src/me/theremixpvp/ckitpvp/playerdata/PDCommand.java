package me.theremixpvp.ckitpvp.playerdata;

import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PDCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender.hasPermission("ckitpvp.playerdata.admin"))) return false;
		
		if(args.length == 0) {
			sender.sendMessage(ChatColor.GOLD + "/pd <set:add:reset> <Player> <option> [value]");
			return true;
		} else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("reset")) {
				if(PDUtils.getByName(args[1]) == null) {
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				PData pd = PDUtils.getByName(args[1]);
				if(args[2].equalsIgnoreCase("all")) {
					pd.setCredits(0);
					pd.setDeaths(0);
					pd.setKills(0);
					pd.unlockedkits().clear();
					sender.sendMessage(ChatColor.GOLD + pd.name() + "'s playerdata has been reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("credits")) {
					pd.setCredits(0);
					sender.sendMessage(ChatColor.GOLD + "Credits reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("kills")) {
					pd.setKills(0);
					sender.sendMessage(ChatColor.GOLD + "Kills reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("deaths")) {
					pd.setDeaths(0);
					sender.sendMessage(ChatColor.GOLD + "Deaths reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("kits")) {
					pd.unlockedkits().clear();
					sender.sendMessage(ChatColor.GOLD + "Kits reset!");
					return true;
				}
				
				
			}
			
			
		} else if(args.length == 4) {
			if(args[0].equalsIgnoreCase("reset")) {
				if(PDUtils.getByName(args[1]) == null) {
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				PData pd = PDUtils.getByName(args[1]);
				if(args[2].equalsIgnoreCase("credits")) {
					pd.setCredits(0);
					sender.sendMessage(ChatColor.GOLD + "Credits reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("kills")) {
					pd.setKills(0);
					sender.sendMessage(ChatColor.GOLD + "Kills reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("deaths")) {
					pd.setDeaths(0);
					sender.sendMessage(ChatColor.GOLD + "Deaths reset!");
					return true;
				} else if(args[2].equalsIgnoreCase("kits")) {
					pd.unlockedkits().clear();
					sender.sendMessage(ChatColor.GOLD + "Kits reset!");
					return true;
				}
			}
		}
		
		
		
		
		return false;
	}

}
