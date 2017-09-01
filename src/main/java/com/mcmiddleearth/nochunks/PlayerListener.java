/*
 * Copyright (C) 2017 MCME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mcmiddleearth.nochunks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 *
 * @author Eriol_Eandur
 */
public class PlayerListener implements Listener {
    
    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        if(!NoChunks.isAllowed(event.getPlayer(), event.getTo())) {
            event.setCancelled(true);
            sendMovementNotAllowedMessage(event.getPlayer());
        }
    }
    
    @EventHandler
    public void playerTeleport(PlayerTeleportEvent event) {
        if(!NoChunks.isAllowed(event.getPlayer(), event.getTo())) {
            event.setCancelled(true);
            sendTeleportNotAllowedMessage(event.getPlayer());
        }
    }
    
    private void sendMovementNotAllowedMessage(Player player) {
        player.sendMessage(ChatColor.RED+"[WorldBorder] You have reached the border of the allowed map area.");
    }
    
    private void sendTeleportNotAllowedMessage(Player player) {
        player.sendMessage(ChatColor.RED+"[WorldBorder] Your destination is not in the allowed map area.");
    }
   
}
