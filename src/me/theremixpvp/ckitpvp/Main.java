package me.theremixpvp.ckitpvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.theremixpvp.ckitpvp.cmds.Bank;
import me.theremixpvp.ckitpvp.cmds.Credits;
import me.theremixpvp.ckitpvp.cmds.Hat;
import me.theremixpvp.ckitpvp.cmds.Kit;
import me.theremixpvp.ckitpvp.cmds.Kits;
import me.theremixpvp.ckitpvp.cmds.More;
import me.theremixpvp.ckitpvp.cmds.Soup;
import me.theremixpvp.ckitpvp.cmds.Stats;
import me.theremixpvp.ckitpvp.cmds.Test;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Acrobat;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Dodge;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_EZ;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Fisherman;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Grappler;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Hulk;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Lucky;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Ninja;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_PVP;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Rusher;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Sniper;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_Tank;
import me.theremixpvp.ckitpvp.cmds.kits.Kit_VisionMaster;
import me.theremixpvp.ckitpvp.listeners.DeathListener;
import me.theremixpvp.ckitpvp.listeners.JoinListener;
import me.theremixpvp.ckitpvp.listeners.SoupL;
import me.theremixpvp.ckitpvp.listeners.kits.AcrobatL;
import me.theremixpvp.ckitpvp.listeners.kits.DodgeL;
import me.theremixpvp.ckitpvp.listeners.kits.FishermanL;
import me.theremixpvp.ckitpvp.listeners.kits.GrapplerL;
import me.theremixpvp.ckitpvp.listeners.kits.HulkL;
import me.theremixpvp.ckitpvp.listeners.kits.LuckyL;
import me.theremixpvp.ckitpvp.listeners.kits.NinjaL;
import me.theremixpvp.ckitpvp.listeners.kits.RusherL;
import me.theremixpvp.ckitpvp.listeners.kits.SniperL;
import me.theremixpvp.ckitpvp.listeners.kits.VisionMasterL;
import me.theremixpvp.ckitpvp.shop.ShopCmd;
import me.theremixpvp.ckitpvp.shop.ShopMenu;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	
	public static HashMap<String, PData> pdata = new HashMap<String, PData>();
	public static ArrayList<Player> usedkit = new ArrayList<Player>();
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public static Plugin p;
	
	public void onEnable() {
		p = this;
		executors();
		listeners();
		loadConfig();
		
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
		saveConfig();
	}
	
	public void executors() {
		getCommand("pvp").setExecutor(new Kit_PVP(this));
		getCommand("tank").setExecutor(new Kit_Tank(this));
		getCommand("fisherman").setExecutor(new Kit_Fisherman(this));
		getCommand("grappler").setExecutor(new Kit_Grappler(this));
		getCommand("hulk").setExecutor(new Kit_Hulk(this));
		getCommand("dodge").setExecutor(new Kit_Dodge(this));
		getCommand("lucky").setExecutor(new Kit_Lucky(this));
		getCommand("ez").setExecutor(new Kit_EZ(this));
		getCommand("rusher").setExecutor(new Kit_Rusher(this));
		getCommand("sniper").setExecutor(new Kit_Sniper(this));
		getCommand("visionmaster").setExecutor(new Kit_VisionMaster(this));
		getCommand("acrobat").setExecutor(new Kit_Acrobat(this));
		getCommand("ninja").setExecutor(new Kit_Ninja(this));
		getCommand("stats").setExecutor(new Stats(this));
		getCommand("credits").setExecutor(new Credits(this));
		getCommand("kits").setExecutor(new Kits(this));
		getCommand("kit").setExecutor(new Kit(this));
		getCommand("bank").setExecutor(new Bank(this));
		getCommand("soup").setExecutor(new Soup(this));
		getCommand("test").setExecutor(new Test(this));
		getCommand("hat").setExecutor(new Hat(this));
		getCommand("more").setExecutor(new More(this));
		getCommand("shop").setExecutor(new ShopCmd());
		//getCommand("ss").setExecutor(new SuperSoup(this));
	}
	
	public void listeners() {
		pm.registerEvents(new DeathListener(this), this);
		pm.registerEvents(new JoinListener(this), this);
		pm.registerEvents(new SoupL(this), this);
		pm.registerEvents(new Test(this), this);
		//pm.registerEvents(ShopMenu, this);
		
		
		pm.registerEvents(new FishermanL(this), this);
		pm.registerEvents(new GrapplerL(this), this);
		pm.registerEvents(new HulkL(this), this);
		pm.registerEvents(new DodgeL(this), this);
		pm.registerEvents(new LuckyL(this), this);
		pm.registerEvents(new RusherL(this), this);
		pm.registerEvents(new SniperL(this), this);
		pm.registerEvents(new VisionMasterL(this), this);
		pm.registerEvents(new AcrobatL(this), this);
		pm.registerEvents(new NinjaL(this), this);
	}
	
	public void loadConfig() {
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
			Settings.kits = cfg.getStringList("kits");
			
			log.log(Level.INFO, "Settings loaded.");
		}
		
	}
	
	public void saveConfig() {
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
			
			cfg.set("death-messages", Settings.deathmessages);
			cfg.set("kits", Settings.kits);
			try {
	            cfg.save(sf);
            } catch (IOException e) {
	            e.printStackTrace();
            }
			log.log(Level.INFO, "Settings saved.");
		}
	}
}
