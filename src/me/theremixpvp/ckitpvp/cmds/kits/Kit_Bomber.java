package me.theremixpvp.ckitpvp.cmds.kits;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Kit_Bomber implements CommandExecutor {
	
	Main main;
	
	public Kit_Bomber(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this commands!");
			return true;
		}
		PData pd = PDUtils.getByName(sender.getName());
		if(!(pd.unlockedkits().contains("Bomber")) && !sender.isOp() && !sender.hasPermission("ckitpvp.kit.bomber")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this kit!");
			return true;
		}
		
		Player p = (Player)sender;
		
		if(main.usedkit.contains(p) && !p.isOp()) {
			p.sendMessage(ChatColor.RED + "Only one kit per life!");
			return true;
		}
		
		PlayerInventory inv = p.getInventory();
		
		inv.clear();
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
		
		inv.addItem(new ItemStack(Material.DIAMOND_SWORD));
		
		ItemStack bombs = new ItemStack(Material.EGG);
		bombs.setAmount(2);
		ItemMeta bombmeta = bombs.getItemMeta();
		bombmeta.setDisplayName(ChatColor.GREEN + "Bomb");
		bombs.setItemMeta(bombmeta);
		p.getInventory().addItem(bombs);
		
		ItemStack c = new ItemStack(Material.IRON_CHESTPLATE);
		c.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
		
		ItemStack l = new ItemStack(Material.IRON_LEGGINGS);
		l.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
		
		inv.setArmorContents(new ItemStack[] {
				new ItemStack(Material.LEATHER_BOOTS),
				l,
				c,
				new ItemStack(Material.IRON_HELMET),
		});
		
		for(int i = 0; i < 34; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "Bomber kit equipped!");
		p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 7.0F, 7.0F);
		pd.setKit("Bomber");
		main.usedkit.add(p);
		return true;
	}

}
