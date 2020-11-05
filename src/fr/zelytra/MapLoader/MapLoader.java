package fr.zelytra.MapLoader;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.zelytra.MapLoader.Commands.Commands;
import fr.zelytra.MapLoader.Listener.OnChunkLoad;
import fr.zelytra.MapLoader.Utils.Message;

public class MapLoader extends JavaPlugin {
	// Color console
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static MapLoader plugin;
	public static HashMap<Integer,BukkitTask> task = new HashMap<Integer,BukkitTask>();
	public void onEnable() {
		plugin = this;

		getCommand("maploader").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new OnChunkLoad(), this);		
		System.out.println(Message.prefix_console() + "Is UP !");
	}
	public void onDisable() {
		this.getServer().getScheduler().cancelTasks(this);
	}

	public static MapLoader getPlugin() {
		return plugin;
	}

}
