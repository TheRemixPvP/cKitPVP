package me.theremixpvp.ckitpvp.cmds.kits;

import java.util.Arrays;
import java.util.List;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.Color;
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
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;

public class Kit_Acrobat implements CommandExecutor {
	
	Main main;
	
	public Kit_Acrobat(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this commands!");
			return true;
		}
		PData pd = PDUtils.getByName(sender.getName());
		if(!(pd.unlockedkits().contains("Acrobat")) && !sender.isOp() && !sender.hasPermission("ckitpvp.kit.acrobat")) {
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
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.addItem(sword);
		
		ItemStack si = new ItemStack(Material.STRING);
		ItemMeta sim = si.getItemMeta();
		sim.setDisplayName(ChatColor.GREEN + "Spring");
		List<String> l = Arrays.asList(ChatColor.AQUA + "--Ability--", ChatColor.GOLD + "Punch the air with this and you", ChatColor.GOLD + "will be launched into the air!");
		sim.setLore(l);
		si.setItemMeta(sim);
		inv.addItem(si);
		
		ItemStack h = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hm = (LeatherArmorMeta) h.getItemMeta();
		hm.setColor(Color.FUCHSIA);
		h.setItemMeta(hm);
		
		ItemStack b = new ItemStack(Material.GOLD_BOOTS);
		b.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
		b.addUnsafeEnchantment(Enchantment.DURABILITY, 8);
		
		inv.setArmorContents(new ItemStack[] {
				b,
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				h,
		});
		
		for(int i = 0; i < 34; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "Acrobat kit equipped!");
		p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 7.0F, 7.0F);
		pd.setKit("Acrobat");
		main.usedkit.add(p);
		return true;
	}

}
