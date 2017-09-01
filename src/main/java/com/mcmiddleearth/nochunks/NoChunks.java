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

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan <dallen@dallen.xyz>, Eriol_Eandur
 */
public class NoChunks extends JavaPlugin implements Listener{
    
    private static ConfigurationSection borderConfig;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        borderConfig =getConfig().getConfigurationSection("borders");
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
    
    public static boolean isAllowed(Player player, Location loc) {
        World world = player.getWorld();
        if(player.hasPermission("worldborder.ignore") || !borderConfig.contains(world.getName())) {
            return true;
        }
        ConfigurationSection worldConfig = borderConfig.getConfigurationSection(world.getName());
        if((worldConfig.contains("xMin") && loc.getBlockX()<worldConfig.getInt("xMin"))
                || (worldConfig.contains("xMax") && loc.getBlockX()>worldConfig.getInt("xMax"))) {
//Logger.getGlobal().info("xTest: "+loc.getBlockX()+" "+ worldConfig.get("xMin")+" "+worldConfig.get("xMax"));
            return false;
        }
        if((worldConfig.contains("yMin") && loc.getBlockY()<worldConfig.getInt("yMin"))
                || (worldConfig.contains("yMax") && loc.getBlockY()>worldConfig.getInt("yMax"))) {
//Logger.getGlobal().info("yTest: "+loc.getBlockY()+" "+ worldConfig.get("yMin")+" "+worldConfig.get("yMax"));
            return false;
        }
        if((worldConfig.contains("zMin") && loc.getBlockZ()<worldConfig.getInt("zMin"))
                || (worldConfig.contains("zMax") && loc.getBlockZ()>worldConfig.getInt("zMax"))) {
//Logger.getGlobal().info("zTest: "+loc.getBlockZ()+" "+ worldConfig.get("zMin")+" "+worldConfig.get("zMax"));
            return false;
        }
        return true;
    }
    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String GenId) {
        return new VoidGenerator();
    }

}

    