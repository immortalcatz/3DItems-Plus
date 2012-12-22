package quintinity.mods.mod3Ditems.client;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.client.Minecraft;
import quintinity.mods.mod3Ditems.Mod3DItems;

public class TickHandler implements ITickHandler
{
	private ArrayList<String> messages = new ArrayList<String>();
	private Minecraft minecraft;
	
	public TickHandler()
	{
		minecraft = FMLClientHandler.instance().getClient();
	}
	
    public void tickStart(EnumSet type, Object ... data)
    {
        Mod3DItems.instance.onTickInGame(FMLClientHandler.instance().getClient());
        if (messages.size() > 0 && minecraft.thePlayer != null) {
        	for (int i = 0; i < messages.size(); i++) {
        		minecraft.thePlayer.sendChatToPlayer(messages.get(i));
        		messages.remove(i);
        	}
        }
        
    }

    public void tickEnd(EnumSet type, Object ... data) {}

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.RENDER);
    }

    public String getLabel()
    {
        return null;
    }
    
    public void sendMessage(String msg)
    {
    	messages.add(msg);
    }
}