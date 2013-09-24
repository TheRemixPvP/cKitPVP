package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Credits implements CommandExecutor {
	
	Main main;
	
	public Credits(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(PDUtils.getByName(p.getName()) == null) {
			p.sendMessage(ChatColor.RED + "A critical error has occurred! Tell the owner!");
			return true;
		}
		
		PData pd = PDUtils.getByName(p.getName());
		
		p.sendMessage(ChatColor.AQUA + "Credits: " + ChatColor.DARK_AQUA + pd.credits());		
		return true;
	}

}
