package me.theremixpvp.ckitpvp.cmds.kits;

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

public class Kit_Hulk implements CommandExecutor {
	
	Main main;
	
	public Kit_Hulk(Main plugin) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this commands!");
			return true;
		}
		PData pd = PDUtils.getByName(sender.getName());
		if(!(pd.unlockedkits().contains("Hulk")) && !sender.isOp() && !sender.hasPermission("ckitpvp.kit.hulk")) {
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
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.addItem(sword);
		
		ItemStack fwc = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta fwcm = fwc.getItemMeta();
		fwcm.setDisplayName(ChatColor.GREEN + "Hulk Strength");
		fwc.setItemMeta(fwcm);
		inv.addItem(fwc);
		
		ItemStack h = new ItemStack(Material.LEATHER_HELMET);
		ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack l = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack b = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta hm = (LeatherArmorMeta) h.getItemMeta();
		LeatherArmorMeta cm = (LeatherArmorMeta) c.getItemMeta();
		LeatherArmorMeta lm = (LeatherArmorMeta) l.getItemMeta();
		LeatherArmorMeta bm = (LeatherArmorMeta) b.getItemMeta();
		hm.setColor(Color.GREEN);
		cm.setColor(Color.GREEN);
		lm.setColor(Color.GREEN);
		bm.setColor(Color.GREEN);
		h.setItemMeta(hm);
		c.setItemMeta(cm);
		l.setItemMeta(lm);
		b.setItemMeta(bm);
		
		inv.setArmorContents(new ItemStack[] {
				b,
				l,
				c,
				h,
		});
		
		for(int i = 0; i < 34; i++) {
			ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta im = soup.getItemMeta();
			im.setDisplayName(ChatColor.DARK_AQUA + "Stew");
			soup.setItemMeta(im);
			inv.addItem(soup);
		}
		
		p.sendMessage(ChatColor.DARK_AQUA + "Hulk kit equipped!");
		p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 7.0F, 7.0F);
		pd.setKit("Hulk");
		main.usedkit.add(p);
		return true;
	}

}
