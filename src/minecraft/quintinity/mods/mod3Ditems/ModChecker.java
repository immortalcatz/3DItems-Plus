package quintinity.mods.mod3Ditems;
import java.util.ArrayList;
import quintinity.mods.mod3Ditems.modhelpers.ModRenderHelper;

public class ModChecker 
{
	 public static boolean checkOptifineInstallation()
	 {
		 try
	     {
			 Class.forName("Config", false, ModChecker.class.getClassLoader());
	         Class.forName("GuiAnimationSettingsOF", false, ModChecker.class.getClassLoader());
	         Class.forName("GuiQualitySettingsOF", false, ModChecker.class.getClassLoader());
	         Class.forName("GuiPerformanceSettingsOF", false, ModChecker.class.getClassLoader());
	         System.out.println("[3DItems] Optifine detected!");
	         return true;
	     }
	     catch (ClassNotFoundException e)
	     {
	    	 System.out.println("[3DItems] Optifine not installed!");
	         return false;
	     }
	 }
	 
	 public static boolean checkMCPatcherInstallation()
	 {
		 try
	     {
			 Class.forName("com.pclewis.mcpatcher.MCPatcherUtils", false, ModChecker.class.getClassLoader());
	         Class.forName("com.pclewis.mcpatcher.mod.TileSize", false, ModChecker.class.getClassLoader());
	         System.out.println("[3DItems] MCPatcher detected!");
	         return true;
	     }
	     catch (ClassNotFoundException e)
	     {
	    	 System.out.println("[3DItems] MCPatcher not installed!");
	         return false;
	     }
	 }
	 
	 public static boolean checkBuildCraftInstallation()
	 {
		 try
	     {
			 Class.forName("buildcraft.BuildCraftCore", false, ModChecker.class.getClassLoader());
			 Class.forName("buildcraft.BuildCraftTransport", false, ModChecker.class.getClassLoader());
			 Class.forName("buildcraft.transport.render.RenderPipe", false, ModChecker.class.getClassLoader());
	         System.out.println("[3DItems] BuildCraft Transport detected!");
	         return true;
	     }
	     catch (ClassNotFoundException e)
	     {
	         return false;
	     }
	 }
	 
	 public static boolean checkIronChestsInstallation()
	 {
		 try
	     {
			 Class.forName("cpw.mods.ironchest.IronChest", false, ModChecker.class.getClassLoader());
			 Class.forName("cpw.mods.ironchest.client.TileEntityIronChestRenderer", false, ModChecker.class.getClassLoader());
			 ArrayList<String> tileClasses = ModRenderHelper.getIronChestTEs();
			 for (int i = 0; i < tileClasses.size(); i++) {
				 Class.forName(tileClasses.get(i), false, ModChecker.class.getClassLoader());
			 }
	         System.out.println("[3DItems] IronChests2 detected!");
	         return true;
	     }
	     catch (ClassNotFoundException e)
	     {
	         return false;
	     }
	 }
}