package quintinity.mods.mod3Ditems.modhelpers;
import java.lang.reflect.Field;
import java.util.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import quintinity.mods.mod3Ditems.ItemRenderer3D;
import quintinity.mods.mod3Ditems.Mod3DItems;
import quintinity.mods.mod3Ditems.ModChecker;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;

public class ModRenderHelper 
{
	private static Field itemField;
	private static Class pipeRenderClass;
	private static ArrayList<String> ironChestTE = new ArrayList<String>();
	
	public static boolean isBuildCraftItem(EntityItem item)
	{
		if (Mod3DItems.buildcraftInstalled && item != null && item.worldObj == null) {
			if (pipeRenderClass != null) {
				try {
					EntityItem dummyItem = (EntityItem)itemField.get(null);
					if (item.item.itemID == dummyItem.item.itemID) {
						return true;
					}
					else {
						return false;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			System.out.println("Class failed to load!");
			return false;
		}
		return false;
	}

	public static ArrayList<String> getIronChestTEs()
	{
		return ironChestTE;
	}
	
	public static void init() 
	{
		BuildCraftRenderHelper.init();
		try {
			pipeRenderClass = Mod3DItems.loadClass("buildcraft.transport.render.RenderPipe");
			if (pipeRenderClass != null) {
				itemField = pipeRenderClass.getDeclaredField("dummyEntityItem");
				itemField.setAccessible(true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		ironChestTE.add("cpw.mods.ironchest.TileEntityCopperChest");
		ironChestTE.add("cpw.mods.ironchest.TileEntityCrystalChest");
		ironChestTE.add("cpw.mods.ironchest.TileEntityDiamondChest");
		ironChestTE.add("cpw.mods.ironchest.TileEntityGoldChest");
		ironChestTE.add("cpw.mods.ironchest.TileEntityIronChest");
		ironChestTE.add("cpw.mods.ironchest.TileEntitySilverChest");
	}
}