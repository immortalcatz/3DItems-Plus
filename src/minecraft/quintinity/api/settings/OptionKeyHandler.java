package quintinity.api.settings;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class OptionKeyHandler extends KeyHandler
{
	public Minecraft minecraft;
	
	public OptionKeyHandler(KeyBinding[] keyBindings) 
	{
		super(keyBindings, new boolean[]{ true });
		minecraft = FMLClientHandler.instance().getClient();
		
	}

	public String getLabel() 
	{
		return "OptionKey";
		
	}

	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
	{
		if (minecraft.currentScreen == null) {
			minecraft.displayGuiScreen(new GuiMoreOptions(minecraft.currentScreen));
		}
	}

	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
	{
	}

	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.CLIENT);
	}
}
