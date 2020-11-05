package fr.zelytra.MapLoader.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Function {
	public static boolean isplayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			return false;
		}
	}

	public static void createFolder(String FileName) {
		File folder = new File(FileName);
		if (!folder.exists()) {
			folder.mkdir();
			System.out.println(Message.prefix_console() + "File [" + FileName + "] created.");
		}
	}
	
	@SuppressWarnings("null")
	public static boolean createConfigFile(String File, String FileName) {

		File file = new File(File + FileName + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);
			config.set("Home.MaxHome", 2);
			config.set("Home.Delay", 5);
			config.set("Spawn.Delay", 5);
			config.set("TPA.Delay", 5);		

			try {
				config.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}	

	public static boolean deleteFile(String FileName) {
		File file = new File(FileName);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}	

}
