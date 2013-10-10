package me.theremixpvp.ckitpvp.listeners;

import me.theremixpvp.ckitpvp.Main;
import me.theremixpvp.ckitpvp.PDUtils;
import me.theremixpvp.ckitpvp.PData;
import me.theremixpvp.ckitpvp.utils.Settings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(PDUtils.getByName(e.getPlayer().getName()) == null) {
			PData pd = new PData(e.getPlayer().getName());
			Main.pdata.put(e.getPlayer().getName(), pd);
			if(e.getMessage().equalsIgnoreCase("kitlist")) {
				e.getPlayer().sendMessage("" + Settings.kits);
			}
		}
	}

}
