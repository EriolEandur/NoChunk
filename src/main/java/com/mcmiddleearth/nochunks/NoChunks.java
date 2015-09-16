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

import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan <dallen@dallen.xyz>
 */
public class NoChunks extends JavaPlugin implements Listener{
    
    boolean all = false;
    
    List<String> worlds = new ArrayList<String>();
    
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        if(!this.getConfig().contains("worlds")){
            this.getLogger().warning("no worlds selected, all worlds will be blocked!");
            all = true;
        }else{
            worlds = this.getConfig().getStringList("worlds");
        }
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e){
        if(e.isNewChunk() && 
          (worlds.contains(e.getWorld().getName()) || all)){
            e.getChunk().unload(false, false);
        }
    }
}
