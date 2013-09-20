package me.theremixpvp.ckitpvp.listeners.kits;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishermanL implements Listener {
	
	Main main;
	
	public FishermanL(Main plugin) {
		plugin = main;
	}
	
	@EventHandler
    public void onPlayerFished(final PlayerFishEvent event) {
            final Player player = event.getPlayer();
            if(PDUtils.getByName(player.getName()).getKit().equalsIgnoreCase("fisherman")) {
            if (event.getCaught() instanceof Player) {
                    final Player caught = (Player) event.getCaught();
                            if (player.getItemInHand().getType() == Material.FISHING_ROD) {
                                    caught.teleport(player.getLocation());
                            }
                    }
            }
    }

}
