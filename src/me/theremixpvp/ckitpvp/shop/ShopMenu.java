package me.theremixpvp.ckitpvp.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ShopMenu implements Listener {
	
	private String name;
    private int size;
    private OptionClickEventHandler handler;
    private Plugin plugin;
   
    private String[] optionNames;
    private ItemStack[] optionIcons;
   
    public ShopMenu(String name, int size, OptionClickEventHandler handler, Plugin plugin) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.plugin = plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
   
    public ShopMenu setOption(int position, ItemStack i, String name, String... info) {
        optionNames[position] = name;
        optionIcons[position] = setItemNameAndLore(i, name, info);
        return this;
    }
    
    public ShopMenu setOption(int position, ItemStack i, String name, String info, List<String> desc) {
    	optionNames[position] = name;
    	optionIcons[position] = setItemNameAndLore(i, name, info, desc);
    	return this;
    }
   
    public void open(Player player) {
    	if(player.getOpenInventory() != null) player.closeInventory();
        Inventory inventory = Bukkit.createInventory(player, size, name);
        for (int i = 0; i < optionIcons.length; i++) {
            if (optionIcons[i] != null) {
                inventory.setItem(i, optionIcons[i]);
            }
        }
        player.openInventory(inventory);
    }
   
    public void destroy() {
        HandlerList.unregisterAll(this);
        handler = null;
        plugin = null;
        optionNames = null;
        optionIcons = null;
    }
   
    @EventHandler(priority=EventPriority.MONITOR)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals(name)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            if (slot >= 0 && slot < size && optionNames[slot] != null) {
                Plugin plugin = this.plugin;
                OptionClickEvent e = new OptionClickEvent((Player)event.getWhoClicked(), event.getCurrentItem(), slot, optionNames[slot]);
                handler.onOptionClick(e);
                if (e.willClose()) {
                    final Player p = (Player)event.getWhoClicked();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            p.closeInventory();
                        }
                    }, 1);
                }
                if (e.willDestroy()) {
                    destroy();
                }
            }
        }
    }
   
    private ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
            im.setDisplayName(name);
            im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }
    
    private ItemStack setItemNameAndLore(ItemStack item, String name, String info, List<String> desc) {
        ItemMeta im = item.getItemMeta();
            im.setDisplayName(name);
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(info);
            for(int i = 0; i < desc.size(); i++) {
            	lore.add(desc.get(i));
            }
            im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }
    
    public interface OptionClickEventHandler {
    	public void onOptionClick(OptionClickEvent event);

    }
   
}

