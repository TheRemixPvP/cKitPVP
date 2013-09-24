package me.theremixpvp.ckitpvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;

public class PDUtils {
	
	Main main;
	
	public PDUtils(Main plugin) {
		plugin = main;
	}
	
	public static PData getByName(String name) {
		/*if(Main.pdata.get(name) == null) {
			PlayerData pd = new PlayerData(name);
			Main.pdata.put(name, pd);
			return pd;
		}*/
		if(Main.pdata.get(name) == null) return null;
		return Main.pdata.get(name);
	}
	
	public static void save(Plugin p) {
		for(PData pd : Main.pdata.values()) {
			File f = new File(p.getDataFolder() + "/playerdata/" + pd.name() + ".yml");
			if(f.exists()) f.delete();
			try {
	            f.createNewFile();
            } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
            }
			
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			double credits = pd.credits();
			int lvl = pd.lvl();
			int kills = pd.kills();
			int deaths = pd.deaths();
			String kit = pd.getKit();
			ArrayList<String> kits = pd.unlockedkits();
			Inventory inv = pd.getBank();
			cfg.set("credits", credits);
			cfg.set("level", lvl);
			cfg.set("kills", kills);
			cfg.set("deaths", deaths);
			cfg.set("kit", kit);
			cfg.set("unlocked-kits", kits);
			
			
			cfg.set("bank.holder", pd.name());
			cfg.set("bank.size", inv.getSize());
			
			for(int i = 0; i < inv.getSize(); i++) {
				ItemStack is = inv.getItem(i);
				if(is == null) {
					break;
				}
					cfg.set("bank.items." + i, is.serialize());
					String mn = is.getType().name();
					int amount = is.getAmount();
					short durability = is.getDurability();
					cfg.set("bank.items." + i + ".materialname", mn);
					cfg.set("bank.items." + i + ".durability", durability);
					if(is.hasItemMeta()) {
						ItemMeta im = is.getItemMeta();
						String name = im.getDisplayName();
						Map<Enchantment, Integer> ench = im.getEnchants();
						List<String> lore = im.getLore();
					
						cfg.set("bank.items." + i + ".name", name);
						cfg.set("bank.items." + i + ".lore", lore);
						for(Enchantment e : ench.keySet()) {
							cfg.set("bank.items." + i + ".enchantments." + i + ".enchant", e.getName());
							cfg.set("bank.items." + i + ".enchantments." + i + ".lvl", is.getEnchantmentLevel(e));
						}
						cfg.set("bank.items." + i + ".enchantments", ench);
					}
				
					MaterialData md = is.getData();
					cfg.set("bank.items." + i + ".data", md.toString());			
				
					cfg.set("bank.items." + i + ".amount", amount);
			}
			
			
				try {
	                cfg.save(f);
                } catch (IOException e) {
	                e.printStackTrace();
                }
		}
	}
	
	public static PData loadPlayerConf(Plugin p, String name) {
		File f = new File(p.getDataFolder() + "/playerdata/" + name + ".yml");
		if(f.exists()) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		double credits = cfg.getDouble("credits");
		int lvl = cfg.getInt("level");
		int kills = cfg.getInt("kills");
		int deaths = cfg.getInt("deaths");
		String kit = cfg.getString("kit");
		ArrayList<String> kits = (ArrayList<String>) cfg.get("unlocked-kits");
		
		PData pd = new PData(name);
		pd.setCredits(credits);
		pd.setLvl(lvl);
		pd.setKills(kills);
		pd.setDeaths(deaths);
		pd.setKit(kit);
		for(String k : kits) {
			pd.addkit(k);
		}
		
		String holdername = cfg.getString("bank.holder");
		OfflinePlayer ih = Bukkit.getOfflinePlayer(holdername);
		int size = cfg.getInt("bank.size");
		Inventory inv = Bukkit.createInventory(ih.getPlayer(), size);
		
		for(int i = 0; i < size; i++) {
			if(cfg.get("bank.items." + i) != null) {
			Material mat = Material.getMaterial(cfg.getString("bank.items." + i + ".materialname"));
			int amount = cfg.getInt("bank.items." + i + ".amount");
			short durability = (short) cfg.getInt("bank.items." + i + ".durability");
			
			ItemStack is = new ItemStack(mat, amount);
			is.setDurability(durability);
			
			ItemMeta im = is.getItemMeta();
			String iname = cfg.getString("bank.items." + i + ".name");
			List<String> lore = cfg.getStringList("bank.items." + i + ".lore");
			
			if(iname != null) im.setDisplayName(iname);
			if(lore != null) im.setLore(lore);
			
			inv.addItem(is);
		}
		}
		pd.setBank(inv);
		
		Main.pdata.put(pd.name(), pd);
		return pd;
		} else {
			return null;
		}
	}

}
