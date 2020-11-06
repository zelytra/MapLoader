package fr.zelytra.MapLoader.ChunkGenerator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import fr.zelytra.MapLoader.MapLoader;
import fr.zelytra.MapLoader.Utils.Function;
import fr.zelytra.MapLoader.Utils.Message;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		// Player can't execut commands
		if (Function.isplayer(sender)) {
			Player player = (Player) sender;
			player.sendMessage(Message.prefix_player() + "§eYou cannot do this command as a player. Use your CMD.");
			return false;
		}
		if (arg.length == 0) {
			System.out
					.println(Message.prefix_console() + MapLoader.ANSI_YELLOW + "Syntax error" + MapLoader.ANSI_RESET);
			return false;
		}
		// Starter command
		if (arg[0].equalsIgnoreCase("start")) {
			if (arg.length < 3) {
				System.out.println(
						Message.prefix_console() + MapLoader.ANSI_YELLOW + "Syntax error" + MapLoader.ANSI_RESET);
				System.out.println(Message.prefix_console() + MapLoader.ANSI_YELLOW + "/ml start [world] [radius]"
						+ MapLoader.ANSI_RESET);
				return false;

			}
			//Test for not multiple task generation running at the same time
			if (!(MapLoader.task.isEmpty())) {
				if (Bukkit.getScheduler().isCurrentlyRunning(MapLoader.task.get(0).getTaskId())) {
					System.out.println(Message.prefix_console() + MapLoader.ANSI_YELLOW
							+ "A generation is already started..." + MapLoader.ANSI_RESET);
					return false;
				}
			}
			TaskGenerator task = new TaskGenerator((Integer.parseInt(arg[2]) / 16), (Integer.parseInt(arg[2]) / 16), 1,
					arg[1]);
			System.out.println(
					Message.prefix_console() + MapLoader.ANSI_GREEN + "Task started..." + MapLoader.ANSI_RESET);
			task.run();

		}
		if (arg[0].equalsIgnoreCase("stop")) {
			if (arg.length >= 0) {
				if (MapLoader.task.containsKey(0)) {
					MapLoader.task.put(1, null);
					Log.info(Message.prefix_console() + MapLoader.ANSI_GREEN + "Task stoped." + MapLoader.ANSI_RESET);
				}

				return true;
			}
		}
		if (arg[0].equalsIgnoreCase("help")) {
			if (arg.length != 1) {
				System.out.println(Message.prefix_console() + MapLoader.ANSI_YELLOW + "Syntax error please do /ml help"
						+ MapLoader.ANSI_RESET);
			}
			System.out.println("|-----------------" + MapLoader.ANSI_CYAN + "MapLoader" + MapLoader.ANSI_RESET
					+ "----------------|");
			System.out.println("|--------------" + MapLoader.ANSI_CYAN + "Made by Zelytra" + MapLoader.ANSI_RESET
					+ "-------------|");
			System.out.println("|------------------------------------------|");
			System.out.println("|-/ml start" + MapLoader.ANSI_YELLOW + " [world_name] [radius_in_block]"
					+ MapLoader.ANSI_RESET + "-|");
			System.out.println("|-/ml stop                                -|");
			System.out.println("|------------------------------------------|");
		}

		return true;
	}
}
