package me.theremixpvp.ckitpvp.listeners.kits;

import java.util.ArrayList;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class GrapplerL implements Listener {
	
	Main main;
	
	public GrapplerL(Main plugin) {
		plugin = main;
	}
	
	public ArrayList<Player> cooldown = new ArrayList<Player>();
	
	//Vector direction = p1.getLocation().toVector().subtract(block.getLocation().toVector()).normalize();
	//p1.setVelocity(direction);
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		/* Called when player tries to fish. */
		Player player = event.getPlayer();
		PData pd = PDUtils.getByName(player.getName());
		if(pd.getKit() != null && pd.getKit().equalsIgnoreCase("grappler")) {
		
		// Continue if the hook is in the ground
			if (event.getState().equals(PlayerFishEvent.State.IN_GROUND)) {
				// Check permission
			
				// Get locations
				Location fm = player.getLocation();
				Location to = event.getHook().getLocation();

				// Teleport player a little off ground to prevent block friction
				//fm.setY(fm.getY()+0.5);
				player.teleport(fm);
			
				// Crunch numbers, nom nom nom
				double g = -0.08;
				double d = to.distance(fm);
				double t = d;
				double v_x = (1.0+0.07*t) * (to.getX()-fm.getX())/t;
				double v_y = (1.0+0.03*t) * (to.getY()-fm.getY())/t -0.5*g*t;
				double v_z = (1.0+0.07*t) * (to.getZ()-fm.getZ())/t;
			
				// Set player's velocity to get to destination
				Vector v = player.getVelocity();
				v.setX(v_x);
				v.setY(v_y);
				v.setZ(v_z);
				player.setVelocity(v);
			
			}
		}
	}

}
