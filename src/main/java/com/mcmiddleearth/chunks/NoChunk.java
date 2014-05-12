package com.mcmiddleearth.chunks;

import com.mcmiddleearth.chunks.listeners.chunkListener;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoChunk
  extends JavaPlugin
{
  public static boolean isDebuging;
  
  public void onEnable()
  {
    setupConfig();
    isDebuging = getConfig().getBoolean("general.debug");
    getServer().getPluginManager().registerEvents(new chunkListener(), this);
  }
  
  public void setupConfig()
  {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
}
