package quintinity.api.settings;
import java.lang.reflect.Field;
import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.src.*;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.*;

public class RefactorGui implements ITickHandler
{
	public Minecraft minecraft;
	public ButtonMore button;
	
	public RefactorGui()
	{
		minecraft = FMLClientHandler.instance().getClient();
	}
	
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			if (minecraft.currentScreen != null && minecraft.currentScreen instanceof GuiOptions) {
				GuiOptions gui = (GuiOptions)minecraft.currentScreen;
				List list = (List)getPrivateValue(gui, 4);
				if (!SettingsAPI.guiAPIinstalled && list.size() < 14 && SettingsAPI.getInstance().buttons.size() > 0) {
					for (int i = 0; i < list.size(); i ++) {
						if (list.get(i) instanceof GuiButton) {
							GuiButton gbutton = (GuiButton) list.get(i);
							if (gbutton.id == 104) {
								gbutton.xPosition = gui.width / 2 - 152;
							}
						}
					}
					button = new ButtonMore(109, gui.width / 2 + 2, gui.height / 6 + 144 - 6, 150, 20, "Mod Options...");
					list.add(button);
				}
				else if (SettingsAPI.guiAPIinstalled && list.size() < 15 && SettingsAPI.getInstance().buttons.size() > 0) {
					for (int i = 0; i < list.size(); i ++) {
						if (list.get(i) instanceof GuiButton) {
							GuiButton gbutton = (GuiButton) list.get(i);
							if (gbutton.id == 200) {
								gbutton.yPosition = gui.height / 6 + 168 + 24;
							}
						}
					}
					button = new ButtonMore(109, gui.width / 2 - 152, gui.height / 6 + 144 + 24 - 6, 150, 20, "Mod Options...");
					list.add(button);
				}
			}
		}
	}
	
	private Object getPrivateValue(GuiOptions gui, int id)
	{
		try 
		{
			Class clazz = gui.getClass().getSuperclass();
			Field field = clazz.getDeclaredFields()[id];
			field.setAccessible(true);
			return field.get(gui);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
	}

	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.CLIENT);
	}

	public String getLabel() 
	{
		return "QuinOptions";
	}
}