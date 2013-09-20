package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

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
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
			return true;
		}
		
		Player p = (Player)sender;
		
		String s = ChatColor.DARK_AQUA + "PVP" + ChatColor.GRAY;
		PData pd = PDUtils.getByName(p.getName());
		for(String k : pd.unlockedkits()) {
			s = s + ChatColor.GRAY + ", " + ChatColor.DARK_AQUA + k;
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "Your Kits: " + s);
		return true;
		
	}

}
