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
			int kills = pd.kills();
			int deaths = pd.deaths();
			String kit = pd.getKit();
			ArrayList<String> kits = pd.unlockedkits();
			Inventory inv = pd.getBank();
			cfg.set("credits", credits);
			cfg.set("kills", kills);
			cfg.set("deaths", deaths);
			cfg.set("kit", kit);
			cfg.set("unlocked-kits", kits);
			
			
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
		int kills = cfg.getInt("kills");
		int deaths = cfg.getInt("deaths");
		String kit = cfg.getString("kit");
		ArrayList<String> kits = (ArrayList<String>) cfg.get("unlocked-kits");
		
		PData pd = new PData(name);
		pd.setCredits(credits);
		pd.setKills(kills);
		pd.setDeaths(deaths);
		pd.setKit(kit);
		if(kits != null) {
			for(String k : kits) {
				pd.addkit(k);
			}
		}
		
		Main.pdata.put(pd.name(), pd);
		return pd;
		} else {
			return null;
		}
	}

}
