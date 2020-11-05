package fr.zelytra.MapLoader.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import fr.zelytra.MapLoader.Utils.Message;

public class OnChunkLoad implements Listener {
	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e) {
		
		if(!e.isNewChunk()) {
			//System.out.println(Message.prefix_console()+"This chunk is already generate !");
			return;
		}
		
		//System.out.println("New chunk :X"+e.getChunk().getX()+"  Z"+e.getChunk().getZ());
	}
	

}
