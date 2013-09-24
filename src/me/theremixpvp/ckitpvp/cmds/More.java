package me.theremixpvp.ckitpvp.cmds;

import me.theremixpvp.ckitpvp.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class More implements CommandExecutor {
	
	Main main;
	
	public More(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		if(p.isOp()) {
			ItemStack i = p.getItemInHand();
			i.setAmount(64);
			p.setItemInHand(i);
			return true;
		}
		return false;
	}

}
