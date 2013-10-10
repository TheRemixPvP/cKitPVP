package me.theremixpvp.ckitpvp.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OptionClickEvent {
	
	private Player player;
	private ItemStack itemClicked;
    private int position;
    private String name;
    private boolean close;
    private boolean destroy;
   
    public OptionClickEvent(Player player, ItemStack itemClicked, int position, String name) {
        this.player = player;
        this.itemClicked = itemClicked;
        this.position = position;
        this.name = name;
        this.close = true;
        this.destroy = false;
    }
   
    public Player getPlayer() {
        return player;
    }
    
    public ItemStack getItem() {
    	return itemClicked;
    }
   
    public int getPosition() {
        return position;
    }
   
    public String getName() {
        return name;
    }
   
    public boolean willClose() {
        return close;
    }
   
    public boolean willDestroy() {
        return destroy;
    }
   
    public void setWillClose(boolean close) {
        this.close = close;
    }
   
    public void setWillDestroy(boolean destroy) {
        this.destroy = destroy;
    }

}
