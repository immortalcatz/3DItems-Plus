package quintinity.mods.mod3Ditems.settings;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import quintinity.mods.mod3Ditems.Mod3DItems;
import quintinity.mods.mod3Ditems.RenderItem3D;

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
    	if (type.equals(EnumSet.of(TickType.RENDER))) {
	        Mod3DItems.instance.onTickInGame(minecraft);
	        if (messages.size() > 0 && minecraft.thePlayer != null) {
	        	for (int i = 0; i < messages.size(); i++) {
	        		minecraft.thePlayer.sendChatToPlayer(messages.get(i));
	        		messages.remove(i);
	        	}
	        }
	        if (minecraft.currentScreen != null && minecraft.currentScreen instanceof GuiMainMenu) {
	        	if (!(RenderManager.instance.entityRenderMap.get(EntityItem.class) instanceof RenderItem3D)) {
	        		RenderManager.instance.entityRenderMap.put(EntityItem.class, new RenderItem3D());
	        		System.out.println("Overriding item renderer");
	        	}
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