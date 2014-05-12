package com.mcmiddleearth.chunks.util;

import com.mcmiddleearth.chunks.NoChunk;
import java.util.logging.Logger;

public class Util
{
  private static final Logger log = Logger.getLogger("Minecraft");
  
  public static void info(String msg)
  {
    log.info("[NoChunk] " + msg);
  }
  
  public static void warning(String msg)
  {
    log.warning("[NoChunk] " + msg);
  }
  
  public static void severe(String msg)
  {
    log.severe("[NoChunk] " + msg);
  }
  
  public static void debug(String msg)
  {
    if (NoChunk.isDebuging) {
      log.info("[NoChunk] [DEBUG] " + msg);
    }
  }
}
