package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class AcrobatL implements Listener {
	
	Main main;
	
	public AcrobatL(Main plugin) {
		plugin = main;
	}
	
	private ArrayList<Player> c = new ArrayList<Player>();
	
	@EventHandler
	public void UseAbility(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().getItemInHand().getType() == Material.STRING && !c.contains(e.getPlayer())) {
			final Player p = e.getPlayer();
			PData pd = PDUtils.getByName(p.getName());
			if(pd.getKit().equalsIgnoreCase("Acrobat")) {
				Vector v = p.getVelocity();
				v.add(p.getLocation().getDirection().multiply(2)).setY(1.35677288129321893892189981298138298989128989138928989312);
				p.setVelocity(v);
				c.add(p);
				Bukkit.getScheduler().scheduleSyncDelayedTask(main.p, new Runnable() {
					@Override
					public void run() {
						c.remove(p);
					}
				}, 40);
			}
		}
	}

}
