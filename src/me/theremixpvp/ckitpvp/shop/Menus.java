package me.theremixpvp.ckitpvp.shop;

import java.util.Arrays;
import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class Menus {
	
	static ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
	
		public static ShopMenu weapons = new ShopMenu(ChatColor.RED + "" + ChatColor.ITALIC + "Weapons", 36, new ShopMenu.OptionClickEventHandler() {
			@Override
			public void onOptionClick(OptionClickEvent event) {
				Player p = event.getPlayer();
				PData pd = PDUtils.getByName(event.getPlayer().getName());
				if(event.getItem().getType() == Material.DIAMOND_SWORD) MenuManager.getInstance().giveItem(new ShopItem("Diamond Sword", new ItemStack(Material.DIAMOND_SWORD), null, 75), p);
				if(event.getItem().getType() == Material.IRON_SWORD) MenuManager.getInstance().giveItem(new ShopItem("Iron Sword", new ItemStack(Material.IRON_SWORD), null, 40), p);
				if(event.getItem().getType() == Material.STONE_SWORD) MenuManager.getInstance().giveItem(new ShopItem("Stone Sword", new ItemStack(Material.STONE_SWORD), null, 15), p);
				if(event.getItem().getType() == Material.WOOD_SWORD) MenuManager.getInstance().giveItem(new ShopItem("Wood Sword", new ItemStack(Material.WOOD_SWORD), null, 5), p);
				if(event.getItem().getType() == Material.BOW) MenuManager.getInstance().giveItem(new ShopItem("Bow", new ItemStack(Material.BOW), null, 50), p);
			}
		}, Main.p)
		.setOption(0, new ItemStack(Material.DIAMOND_SWORD), "Diamond Sword", "Price: 75C")
		.setOption(9, new ItemStack(Material.IRON_SWORD), "Iron Sword", "Price: 40C")
		.setOption(18, new ItemStack(Material.STONE_SWORD), "Stone Sword", "Price: 15C")
		.setOption(27, new ItemStack(Material.WOOD_SWORD), "Wood Sword", "Price: 5C")
		.setOption(2, new ItemStack(Material.BOW), "Bow", "Price: 50C");

		
		public static ShopMenu enchants = new ShopMenu(ChatColor.GOLD + "" + ChatColor.ITALIC + "Enchantments", 54, new ShopMenu.OptionClickEventHandler() {
			
			@Override
			public void onOptionClick(OptionClickEvent event) {
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Sharpness II")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 60), Enchantment.DAMAGE_ALL, 2, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Sharpness III")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 100), Enchantment.DAMAGE_ALL, 3, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Sharpness IV")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 130), Enchantment.DAMAGE_ALL, 4, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Fire Aspect I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 60), Enchantment.FIRE_ASPECT, 1, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Fire Aspect II")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 90), Enchantment.FIRE_ASPECT, 2, event.getPlayer());
				
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Protection I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 40), Enchantment.PROTECTION_ENVIRONMENTAL, 1, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Protection II")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 80), Enchantment.PROTECTION_ENVIRONMENTAL, 2, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Protection III")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 130), Enchantment.PROTECTION_ENVIRONMENTAL, 3, event.getPlayer());
				
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Power II")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 40), Enchantment.ARROW_DAMAGE, 2, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Power III")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 90), Enchantment.ARROW_DAMAGE, 3, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Power IV")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 150), Enchantment.ARROW_DAMAGE, 4, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Power V")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 200), Enchantment.ARROW_DAMAGE, 5, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Flame I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 70), Enchantment.ARROW_FIRE, 1, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Punch II")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 80), Enchantment.ARROW_KNOCKBACK, 2, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Infinity I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 200), Enchantment.ARROW_INFINITE, 1, event.getPlayer());
				
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Unbreaking I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 100), Enchantment.DURABILITY, 1, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Unbreaking I")) MenuManager.getInstance().enchantItem(new ShopItem(event.getPlayer().getItemInHand().getType().name(), event.getPlayer().getItemInHand(), null, 180), Enchantment.DURABILITY, 2, event.getPlayer());
				
				
				
				event.setWillClose(false);
				event.setWillDestroy(false);
			}
		}, Main.p)
		.setOption(0, new ItemStack(Material.DIAMOND_SWORD), "Swords", "Price: N/A")
		.setOption(1, new ItemStack(Material.ENCHANTED_BOOK), "Sharpness II", "Price: 60C")
		.setOption(2, new ItemStack(Material.ENCHANTED_BOOK), "Sharpness III", "Price: 100C")
		.setOption(3, new ItemStack(Material.ENCHANTED_BOOK), "Sharpness IV", "Price: 130C")
		.setOption(4, new ItemStack(Material.ENCHANTED_BOOK), "Fire Aspect I", "Price 60C")
		.setOption(5, new ItemStack(Material.ENCHANTED_BOOK), "Fire Aspect II", "Price 90C")
		.setOption(9, new ItemStack(Material.DIAMOND_CHESTPLATE), "Armor", "Price: N/A")
		.setOption(10, new ItemStack(Material.ENCHANTED_BOOK), "Protection I", "Price: 40C")
		.setOption(11, new ItemStack(Material.ENCHANTED_BOOK), "Protection II", "Price: 90C")
		.setOption(12, new ItemStack(Material.ENCHANTED_BOOK), "Protection III", "Price: 140C")
		.setOption(18, new ItemStack(Material.BOW), "Bows", "Price: N/A")
		.setOption(19, new ItemStack(Material.ENCHANTED_BOOK), "Power II", "Price: 40C")
		.setOption(20, new ItemStack(Material.ENCHANTED_BOOK), "Power III", "Price: 90C")
		.setOption(21, new ItemStack(Material.ENCHANTED_BOOK), "Power IV", "Price: 150C")
		.setOption(22, new ItemStack(Material.ENCHANTED_BOOK), "Power V", "Price: 200C")
		.setOption(23, new ItemStack(Material.ENCHANTED_BOOK), "Flame I", "Price: 70C")
		.setOption(24, new ItemStack(Material.ENCHANTED_BOOK), "Punch II", "Price: 80C")
		.setOption(25, new ItemStack(Material.ENCHANTED_BOOK), "Infinity I", "Price: 200C")
		.setOption(45, new ItemStack(Material.SLIME_BALL), "Miscellaneous", "Price: N/A")
		.setOption(46, new ItemStack(Material.ENCHANTED_BOOK), "Unbreaking I", "Price: 100C")
		.setOption(47, new ItemStack(Material.ENCHANTED_BOOK), "Unbreaking II", "Price: 180C");
		
		public static ShopMenu kits = new ShopMenu(ChatColor.AQUA + "" + ChatColor.ITALIC + "Kits", 36, new ShopMenu.OptionClickEventHandler() {
			
			@Override
			public void onOptionClick(OptionClickEvent event) {
				
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Dodge")) MenuManager.getInstance().giveKit("Dodge", 640, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Fisherman")) MenuManager.getInstance().giveKit("Fisherman", 800, event.getPlayer());
				if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Hulk")) MenuManager.getInstance().giveKit("Hulk", 700, event.getPlayer());
				
			}
		}, Main.p)
		.setOption(0, new ItemStack(Material.ARROW), "Dodge", "Price: 640C", Arrays.asList(ChatColor.GREEN + "Return ALL projectiles", ChatColor.GREEN + "back to their sender!"))
		.setOption(1, new ItemStack(Material.FISHING_ROD), "Fisherman", "Price: 800C", Arrays.asList(ChatColor.GREEN + "Reel in with a player on your line", ChatColor.GREEN + "and the player will be teleported to you!"))
		.setOption(2, new ItemStack(Material.EMERALD), "Hulk", "Price: 700C", Arrays.asList(ChatColor.GREEN + "Use your special hulk strength ball", ChatColor.GREEN + "to activate the power of Hulk!"));
}
