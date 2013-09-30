package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VisionMasterL implements Listener {
	
	Main main;
	
	public VisionMasterL(Main plugin) {
		plugin = main;
	}
	
	private ArrayList<Player> sc = new ArrayList<Player>();
	
	@EventHandler
	public void StarShoot(PlayerInteractEvent e) {
		PData pd = PDUtils.getByName(e.getPlayer().getName());
		if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("VisionMaster") && e.getPlayer().getItemInHand().getType() == Material.NETHER_STAR) {
			final Player p = e.getPlayer();
			if(sc.contains(p)) {
				p.sendMessage(ChatColor.RED + "Please wait a second to shoot your VisionArrow again!");
				return;
			}
			Arrow a = p.launchProjectile(Arrow.class);
			a.setMetadata("isVisionArrow", new FixedMetadataValue(main.p, true));
			sc.add(p);
			Bukkit.getScheduler().scheduleSyncDelayedTask(main.p, new Runnable() {
				@Override
				public void run() {
					sc.remove(p);
				}
			}, 60);
		}
	}
	
	@EventHandler
	public void VisionArrowHit(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow && e.getDamager().hasMetadata("isVisionArrow")) {
			if(e.getEntity() instanceof Player) {
				Player hit = (Player) e.getEntity();
				hit.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 2));
			}
		}
	}

}
