package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Test implements CommandExecutor, Listener {
	
	Main main;
	
	public Test(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}

}
