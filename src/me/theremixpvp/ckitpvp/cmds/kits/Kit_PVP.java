package me.theremixpvp.ckitpvp.cmds.kits;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;

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

public class Kit_PVP implements CommandExecutor {
	
	Main main;
	
	public Kit_PVP(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this commands!");
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
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.addItem(sword);
		
		inv.setArmorContents(new ItemStack[] {
				new ItemStack(Material.IRON_BOOTS),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_HELMET),
		});
		
		for(int i = 0; i < 34; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "PVP kit equipped!");
		p.playSound(p.getLocation(), Sound.ANVIL_LAND, 7.0F, 7.0F);
		PDUtils.getByName(p.getName()).setKit("PVP");
		main.usedkit.add(p);
		return true;
	}
	
	public static void giveKit(Player p) {
		PlayerInventory inv = p.getInventory();
		
		inv.clear();
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.addItem(sword);
		
		inv.setArmorContents(new ItemStack[] {
				new ItemStack(Material.IRON_BOOTS),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_HELMET),
		});
		
		for(int i = 0; i < 34; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "PVP kit equipped!");
		p.playSound(p.getLocation(), Sound.ANVIL_LAND, 7.0F, 7.0F);
		PDUtils.getByName(p.getName()).setKit("PVP");
		Main.usedkit.add(p);
	}

}
