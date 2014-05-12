package com.mcmiddleearth.chunks.listeners;

import com.mcmiddleearth.chunks.util.Util;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class chunkListener
  implements Listener
{
  @EventHandler
  public void onChunkLoad(ChunkLoadEvent event)
  {
    if (event.isNewChunk())
    {
      Chunk newchunk = event.getChunk();
      newchunk.unload(false, false);
      Util.debug("Cancelled chunk generation of chunk (" + newchunk.getX() + "," + newchunk.getZ() + ")");
    }
  }
}
