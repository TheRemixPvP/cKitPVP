package me.theremixpvp.ckitpvp.listeners.kits;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class RusherEnch extends Enchantment {
	
	public RusherEnch(int id) {
	    super(id);
    }

	@Override
    public String getName() {
	    return "Swift";
    }

	@Override
    public int getMaxLevel() {
	    // TODO Auto-generated method stub
	    return 5;
    }

	@Override
    public int getStartLevel() {
	    // TODO Auto-generated method stub
	    return 1;
    }

	@Override
    public EnchantmentTarget getItemTarget() {
	    // TODO Auto-generated method stub
	    return EnchantmentTarget.ARMOR_FEET;
    }

	@Override
    public boolean conflictsWith(Enchantment other) {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public boolean canEnchantItem(ItemStack item) {
	    // TODO Auto-generated method stub
	    return true;
    }

}
