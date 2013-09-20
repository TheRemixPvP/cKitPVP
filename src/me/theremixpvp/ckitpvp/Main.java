package me.theremixpvp.ckitpvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.theremixpvp.ckitpvp.cmds.Kit;
import me.theremixpvp.ckitpvp.cmds.KitShop;
import me.theremixpvp.ckitpvp.cmds.Kits;
import me.theremixpvp.ckitpvp.cmds.Stats;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Fisherman;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_PVP;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Sniper;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Tank;
import me.theremixpvp.ckitpvp.listeners.DeathListener;
import me.theremixpvp.ckitpvp.listeners.JoinListener;
import me.theremixpvp.ckitpvp.listeners.kits.FishermanL;
import me.theremixpvp.ckitpvp.listeners.kits.SniperL;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	
	public static HashMap<String, PData> pdata = new HashMap<String, PData>();
	public static ArrayList<ShopKit> shopkits = new ArrayList<ShopKit>();
	public static ArrayList<Player> usedkit = new ArrayList<Player>();
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		executors();
		listeners();
		setupConfig();
		KitShop.load(this);
		
		File fdir = new File(getDataFolder() + "/playerdata/");
		fdir.mkdir();
		for(File f : fdir.listFiles()) {
			File[] files = fdir.listFiles();
			String name = f.getName().replace(".yml", "");
			PDUtils.loadPlayerConf(this, name);
		}
		
	}
	
	public void onDisable() {
		PDUtils.save(this);
		KitShop.save(this);
	}
	
	public void executors() {
		getCommand("pvp").setExecutor(new Kit_PVP(this));
		getCommand("tank").setExecutor(new Kit_Tank(this));
		getCommand("sniper").setExecutor(new Kit_Sniper(this));
		getCommand("fisherman").setExecutor(new Kit_Fisherman(this));
		getCommand("stats").setExecutor(new Stats(this));
		getCommand("kitshop").setExecutor(new KitShop(this));
		getCommand("kits").setExecutor(new Kits(this));
		getCommand("kit").setExecutor(new Kit(this));
	}
	
	public void listeners() {
		pm.registerEvents(new DeathListener(this), this);
		pm.registerEvents(new JoinListener(this), this);
		
		
		
		pm.registerEvents(new SniperL(this), this);
		pm.registerEvents(new FishermanL(this), this);
	}
	
	public void setupConfig() {
		File sf = new File(getDataFolder() + "/settings.yml");
		if(!sf.exists()) {
			log.log(Level.WARNING, "Settings file not found. Creating one.");
			try {
	            sf.createNewFile();
	            FileConfiguration cfg = YamlConfiguration.loadConfiguration(sf);
	            cfg.set("death-messages", true);
	            try {
	            	cfg.save(sf);
	            } catch(IOException e) {
	            	log.log(Level.SEVERE, "Couldn't save settings file!!");
	            }
	            
	            Settings.deathmessages = true;
            } catch (IOException e) {
	            log.log(Level.SEVERE, "Couldn't create settings file!!");
            }
		} else {
			
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(sf);
			
			Settings.deathmessages = cfg.getBoolean("death-messages");
			
			log.log(Level.INFO, "Settings loaded.");
		}
		
	}
}
