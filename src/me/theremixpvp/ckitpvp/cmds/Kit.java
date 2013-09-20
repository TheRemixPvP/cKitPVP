package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor {
	
	Main main;
	
	public Kit(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.DARK_AQUA + "/kit <player>");
			return true;
		} else if(args.length == 1) {
			if(Bukkit.getPlayer(args[1]) == null) {
				sender.sendMessage(ChatColor.RED + "Invalid player!");
				return true;
			}
			
			Player p = Bukkit.getPlayer(args[1]);
			PData pd = PDUtils.getByName(p.getName());
			String kit = pd.getKit();
			sender.sendMessage(ChatColor.DARK_AQUA + p.getName() + "'s Kit: " + kit);
			return true;
		}
		
		return false;
	}

}
