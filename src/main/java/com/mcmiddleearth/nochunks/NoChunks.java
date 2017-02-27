/*
 * This file is part of NoChunks.
 * 
 * NoChunks is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * NoChunks is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with NoChunks.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */
package com.mcmiddleearth.nochunks;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan <dallen@dallen.xyz>, Eriol_Eandur
 */
public class NoChunks extends JavaPlugin implements Listener{
    
    boolean all = false;
    
    Map<World, Rectangle> protectedWorlds = new HashMap<>();
    
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        if(this.getConfig().getBoolean("protectAll")){
            this.getLogger().warning("trying to block chunk generation in all worlds!");
            all = true;
        }
        if(this.getConfig().contains("worlds")) {
            ConfigurationSection config = this.getConfig().getConfigurationSection("worlds");
            for(String worldName:config.getKeys(false)) {
                World world = Bukkit.getWorld(worldName);
                if(world!=null) {
                    Rectangle borders = rectangleFromConfig(config.getConfigurationSection(worldName));
                    protectedWorlds.put(world, borders);
                }
            }
        }
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e){
        if(e.isNewChunk() && 
          (protectedWorlds.containsKey(e.getWorld()) || all)){
            e.getChunk().unload(false, true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e){
        if(!e.getPlayer().hasPermission("noChunks.ignore") && !isAllowed(e.getTo())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED+"[NoChunks] You reached the border of the allowed map area.");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        if(!e.getPlayer().hasPermission("noChunks.ignore") && !isAllowed(e.getTo())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED+"[NoChunks] Your teleportaion target is outside the allowed map area. Teleporation cancelled.");
        }
    }

    private boolean isAllowed(Location loc) {
        if(protectedWorlds.containsKey(loc.getWorld())) {
            Rectangle worldBorder = protectedWorlds.get(loc.getWorld());
            return worldBorder.contains(loc.getBlockX(),loc.getBlockZ());
        } else {
            return true;
        }
    }
    
    private Rectangle rectangleFromConfig(ConfigurationSection config) {
        int minX = config.getInt("minX");
        int maxX = config.getInt("maxX");
        int minZ = config.getInt("minZ");
        int maxZ = config.getInt("maxZ");
        return new Rectangle(minX, minZ, maxX-minX, maxZ-minZ);
    }
    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String GenId) {
        Logger.getGlobal().info("Get Void Generator");
        return new VoidGenerator();
    }

}

    