package fr.zelytra.MapLoader.ChunkGenerator;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import fr.zelytra.MapLoader.MapLoader;
import fr.zelytra.MapLoader.Utils.Message;

public class TaskGenerator {
	
	int commandX = 10;
	int commandZ = 10;
	int nbTask = 1;	
	int iddelaclasse;
	int nbr = 0;
	
	long Time = 0;
	
	double Speed = 0;
	
	Object objet = new Object();
	BukkitTask task = null;
	Plugin plugin = MapLoader.getPlugin();
	World world = plugin.getServer().getWorld("world");
	
	
	public TaskGenerator(int sizeX,int sizeZ,int nbTask,String World) {
		this.commandX = sizeX;
		this.commandZ = sizeZ;
		this.nbTask= nbTask;
		this.world = plugin.getServer().getWorld(World);		
		
	}
	
	
	public void run() {
		for (int i = 0; i < nbTask; i++) {
			task = Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
				MapLoader.task.put(0, task);
				runTask();

			});

		}
	}

	public void runTask() {
		int id = 0;
		synchronized (objet) {
			id = iddelaclasse;
			iddelaclasse++;
		}
		Time = System.currentTimeMillis();
		int chunktot = (commandX * 2 + 1) * (commandZ * 2 + 1);

		for (int x = -commandX; x <= commandX; x++) {
			for (int z = -commandZ; z <= commandZ; z++) {
				if (((x + commandX) * (commandZ * 2 + 1) + z + commandZ) % nbTask == id) {
					 if (!MapLoader.task.containsKey(1)){
						Chunk chunk;
						synchronized (objet) {
							chunk = world.getChunkAt(x, z);
							nbr++;
							if (nbr % 200 == 0) {
								double progression = (nbr * 100.0) / chunktot;
								double progress = Math.round((progression * 100.0) / 100.0);
								Speed = 0;
								Speed = Math.abs(nbr / ((Time / 1000.0) - (System.currentTimeMillis() / 1000)));
								Speed = Math.round((Speed * 100.0) / 100.0);
								Log.info(Message.prefix_console() + MapLoader.ANSI_CYAN + "Generating: " + "["
										+ MapLoader.ANSI_RESET + nbr + MapLoader.ANSI_CYAN + "/" + MapLoader.ANSI_RESET
										+ chunktot + MapLoader.ANSI_CYAN + "] | [" + MapLoader.ANSI_RESET + progress
										+ "%" + MapLoader.ANSI_CYAN + "] | [" + MapLoader.ANSI_RESET + Speed
										+ "Chunks/s" + MapLoader.ANSI_CYAN + "]" + MapLoader.ANSI_RESET);
							}
						} 
						Bukkit.getScheduler().runTask(plugin, () -> {
							chunk.load(true);
							while (!chunk.isLoaded())
								try {
									Thread.sleep((long) 0.1);
								} catch (InterruptedException e) {
								}
							chunk.unload(true);
						});

					}

				}
			}
		}

		MapLoader.task.clear();
		Time = Math.round(((System.currentTimeMillis() - Time) * 100.0) / 100.0);
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;

		long elapsedHours = Time / hoursInMilli;
		Time = Time % hoursInMilli;

		long elapsedMinutes = Time / minutesInMilli;
		Time = Time % minutesInMilli;

		long elapsedSeconds = Time / secondsInMilli;
		Log.info(Message.prefix_console() + MapLoader.ANSI_GREEN + "Map generation finish in ["
				+ MapLoader.ANSI_RESET + elapsedHours + MapLoader.ANSI_CYAN + "h" + MapLoader.ANSI_RESET
				+ elapsedMinutes + MapLoader.ANSI_CYAN + "m" + MapLoader.ANSI_RESET + elapsedSeconds
				+ MapLoader.ANSI_CYAN + "s]" + MapLoader.ANSI_RESET);
	}
}


