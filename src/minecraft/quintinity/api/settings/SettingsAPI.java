package quintinity.api.settings;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import quintinity.api.config.ConfigFile;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="settingsapi", name="SettingsAPI", version="1.0")
public class SettingsAPI 
{
	public HashMap<GuiOptionButton, IOptionHandler> buttonToHandlerMap = new HashMap<GuiOptionButton, IOptionHandler>();
	private ArrayList<IOptionHandler> handlers = new ArrayList<IOptionHandler>();
	public ArrayList<GuiOptionButton> buttons = new ArrayList<GuiOptionButton>();
	private static SettingsAPI instance;
	private final int WIDTH = 0;
	private final int HEIGHT = 1;
	private static ArrayList<IOptionHandler> handlersToAdd = new ArrayList<IOptionHandler>();
	public static boolean guiAPIinstalled = false;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e)
	{
		instance = this;
		TickRegistry.registerTickHandler(new RefactorGui(), Side.CLIENT);
		setModData(e.getModMetadata());
	}
	
	@PostInit
	public void modsLoaded(FMLPostInitializationEvent e)
	{
		guiAPIinstalled = checkGuiAPIInstallation();
		handlers.addAll(handlersToAdd);
		handlersToAdd.clear();
		ArrayList<GuiOptionButton> nonLinkButtons = new ArrayList<GuiOptionButton>();
		ArrayList<GuiOptionButton> linkButtons = new ArrayList<GuiOptionButton>();
		System.out.println("Registered " + handlers.size() + " option handlers.");
		if (handlers.size() > 0) {
			for (int i = 0; i < handlers.size(); i++) {
				IOptionHandler handler = handlers.get(i);
				ArrayList<GuiOptionButton> currentsButtons = new ArrayList<GuiOptionButton>();
				handler.registerButtons(currentsButtons);
				for (int b = 0; b < currentsButtons.size(); b++) {
					GuiOptionButton button = currentsButtons.get(b);
					setPrivateSize(button, WIDTH, 150);
					setPrivateSize(button, HEIGHT, 20);
					buttonToHandlerMap.put(button, handler);
					if (button.isLink()) { linkButtons.add(button); }
					else {nonLinkButtons.add(button); }
				}
			}
		}
		buttons.addAll(nonLinkButtons);
		buttons.addAll(linkButtons);
	}
	
	public void reloadHandlers()
	{
		modsLoaded(null);
	}
	
	public static void registerOptionHandler(IOptionHandler handler)
	{
		try {
			instance.handlers.add(handler);
		}
		catch (Exception e) {
			handlersToAdd.add(handler);
		}
	}
	
	public static SettingsAPI getInstance()
	{
		return instance;
	}
	
	private void setPrivateSize(GuiOptionButton button, int index, int value)
    {
    	try 
    	{
	    	Class<?> clazz = button.getClass().getSuperclass();
	    	Field field = clazz.getDeclaredFields()[index];
	    	field.setAccessible(true);
	    	field.setInt(button, value);
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
	
	private boolean checkGuiAPIInstallation()
	 {
		 try
	     {
			 Class.forName("GuiApiHelper", false, this.getClass().getClassLoader());
	         return true;
	     }
	     catch (ClassNotFoundException e)
	     {
	    	 return false;
	     }
	 }
	
    public void setModData(ModMetadata data)
    {
    	data.autogenerated = false;
    	ArrayList<String> authors = new ArrayList<String>();
    	authors.add("\u00a7cQuintinity");
    	data.authorList = authors;
    	data.url = "\u00a73bit.ly/Y1Gh3V";
    	data.description = "\u00a72API that allows mods to let their users edit settings in-game by adding methods to create custom option pages.";
    }
}