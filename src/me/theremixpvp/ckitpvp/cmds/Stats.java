package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {
	
	Main main;
	
	public Stats(Main plugin) {
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
			double credits = pd.credits();
			int kills = pd.kills();
			int deaths = pd.deaths();
			p.sendMessage(ChatColor.DARK_AQUA + "----[ " + p.getName() + " ]----");
			p.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.DARK_AQUA + kills);
			p.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.DARK_AQUA + deaths);
			p.sendMessage(ChatColor.GRAY + "Credits: " + ChatColor.DARK_AQUA + credits);
			return true;
			
		} else if(args.length == 1) {
			if(PDUtils.getByName(args[1]) == null) {
				sender.sendMessage(ChatColor.RED + "Invalid player!");
				return true;
			}
			PData pd = PDUtils.getByName(args[1]);
			double credits = pd.credits();
			int kills = pd.kills();
			int deaths = pd.deaths();
			sender.sendMessage(ChatColor.DARK_AQUA + "----[ " + pd.name() + " ]----");
			sender.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.DARK_AQUA + kills);
			sender.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.DARK_AQUA + deaths);
			sender.sendMessage(ChatColor.GRAY + "Credits: " + ChatColor.DARK_AQUA + credits);
			return true;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("clear") && PDUtils.getByName(args[1]) != null) {
				PData pd = PDUtils.getByName(args[1]);
				pd.setCredits(0);
				pd.setDeaths(0);
				pd.setKills(0);
				sender.sendMessage(ChatColor.DARK_AQUA + pd.name() + "'s stats cleared!");
				return true;
			}
		}
		
		
		return false;
	}

}
