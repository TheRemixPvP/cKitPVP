package me.theremixpvp.ckitpvp.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.ShopKit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class KitShopUtils {
	
	Main main;
	
	public KitShopUtils(Main plugin) {
		plugin = main;
	}
	
	public static void save(Plugin p) {
		File f = new File(p.getDataFolder() + "/shopconfig.yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		Main.log.log(Level.INFO, "Saving config");
		
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		cfg.createSection("shop-kits");
		for(ShopKit sk : Main.shopkits) {
			String name = sk.name();
			double price = sk.price();
			cfg.createSection("shop-kits." + name);
			cfg.set("shop-kits." + name + ".name", name);
			cfg.set("shop-kits." + name + ".price", price);
		}
		try {
			cfg.save(f);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load(Plugin p) {
		File f = new File(p.getDataFolder() + "/shopconfig.yml");
		if(!f.exists()) {
			try {
	            f.createNewFile();
	            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	            cfg.createSection("shop-kits");
	            ShopKit skn = new ShopKit("Archer", 100);
	            Main.shopkits.add(skn);
	            try {
	            	cfg.save(f);
	            } catch(IOException e) {
	            	e.printStackTrace();
	            }
	            
	            for(String s : cfg.getStringList("shop-kits")) {
	            	String name = cfg.getString("shop-kits." + s + ".name");
	            	double price = cfg.getDouble("shop-kits." + s + ".price");
	            	ShopKit sk = new ShopKit(name, price);
	            	Main.shopkits.add(sk);
	            }
            } catch (IOException e) {
	            e.printStackTrace();
            }
		} else {
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			for(String s : cfg.getStringList("shop-kits")) {
            	String name = cfg.getString("shop-kits." + s + ".name");
            	double price = cfg.getDouble("shop-kits." + s + ".price");
            	ShopKit sk = new ShopKit(name, price);
            	Main.shopkits.add(sk);
            }
		}
	}

}
