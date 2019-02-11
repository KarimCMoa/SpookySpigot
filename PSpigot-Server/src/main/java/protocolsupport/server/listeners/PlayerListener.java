package protocolsupport.server.listeners;

import net.minecraft.server.PlayerList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import protocolsupport.api.tab.TabAPI;

public class PlayerListener implements Listener {

  public PlayerListener() {
    Bukkit.getPluginManager().registerEvents(this, JavaPlugin.getPlugin(JavaPlugin.class));
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    TabAPI.sendHeaderFooter(event.getPlayer(), TabAPI.getDefaultHeader(), TabAPI.getDefaultFooter());
  }
}
