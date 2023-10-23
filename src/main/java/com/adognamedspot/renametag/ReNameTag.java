package com.adognamedspot.renametag;


import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.adognamedspot.renametag.api.SignGUI;
import com.adognamedspot.renametag.api.SignGUIAction;

import net.md_5.bungee.api.ChatColor;


public final class ReNameTag extends JavaPlugin implements Listener {
	
	
    @Override
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(this, this);
    }
    
    @Override
    public void onDisable() {
    	// TODO Make this do something
    }
    
	@EventHandler
    public void onRightClick(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
    	if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    		if (player.getInventory().getItemInMainHand().getType().equals(Material.NAME_TAG)) {
    			processTag(player);
        	}
    	}
    }
	
	private void processTag(Player player) {
		ItemStack tag = player.getInventory().getItemInMainHand();
		ItemMeta meta = tag.getItemMeta();
		doGUI(player, splitStr(reverseColorString(meta.getDisplayName())));
	}

	private String[] splitStr(String dn) {
		String[] newStr;
		
		if (dn.length() < 15) {
			newStr = new String[] { dn.substring(0, dn.length()), null, null, null };
		} else if (dn.length() < 30) {
			newStr = new String[] { dn.substring(0, 15), dn.substring(15, dn.length()), null, null };
		} else if (dn.length() < 45) {
			newStr = new String[] { dn.substring(0, 15), dn.substring(15, 30), dn.substring(30, dn.length()), null };
		} else if (dn.length() < 60) {
			newStr = new String[] {dn.substring(0, 15), dn.substring(15, 30), dn.substring(30, 45), dn.substring(45, dn.length()) };
		} else {
			newStr = new String[] {dn.substring(0, 15), dn.substring(15, 30), dn.substring(30, 45), dn.substring(45, 60)};
		}
		return newStr;
	}
	
	private void doGUI(Player player, String[] existing) {
		SignGUI gui = SignGUI.builder()
			    // set specific line, starting index is 0
			    .setLine(0, existing[0])
			    .setLine(1, existing[1])
			    .setLine(2, existing[2])
			    .setLine(3, existing[3])

			    // set the sign type
			    .setType(Material.DARK_OAK_SIGN)

			    // set the sign color
			    .setColor(DyeColor.WHITE)

			    // set the handler/listener (called when the player finishes editing)
			    .setHandler((p, result) -> {
			        // get all lines
			    	String line0 = result.getLine(0);
			    	String line1 = result.getLine(1);
			    	String line2 = result.getLine(2);
			    	String line3 = result.getLine(3);
			        
			    	if (line0.isEmpty() && line1.isEmpty() && line2.isEmpty() && line3.isEmpty()) {
			    		// Sign is blank, remove Custom Name
			    		return List.of(SignGUIAction.run(() -> renameTag(player, null)));
			    	} else {
			    		return List.of(SignGUIAction.run(() -> renameTag(player, line0+line1+line2+line3)));
			    	}
			    })

			    // build the SignGUI
			    .build();

			// open the sign
			gui.open(player);
	}

	private void renameTag(Player player, String str) {
		// TODO Auto-generated method stub
		ItemStack Tag = player.getInventory().getItemInMainHand();
		ItemMeta Tag_Meta = Tag.getItemMeta();
		
		if (str == null) {
			// Remove Existing Name
			Tag_Meta.setDisplayName(null);
			Tag.setItemMeta(Tag_Meta);
			return;
		}
		// Set New Name
		Tag_Meta.setDisplayName(parseColorString(str));
		Tag.setItemMeta(Tag_Meta);
	}

	public static String parseColorString(String value) {
		return ChatColor.translateAlternateColorCodes('&', value);
	}
	
	public static String reverseColorString(String value) {
		return value.replace("§", "&");
	}

}



