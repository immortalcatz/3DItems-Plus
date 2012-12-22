package quintinity.api;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class ItemIconManager 
{
	private static HashMap<Integer, IIconHandler> icons = new HashMap<Integer, IIconHandler>();
	
	public static void registerIconHandler(IIconHandler handler, int id) 
	{
		if (icons.get(id) == null) {
			icons.put(id, handler);
		}
		else {
			System.out.println("*** Duplicated icon handler for " + id);
		}
	}
	
	public static IIconHandler getIconHandler(int id)
	{
		return icons.get(id);
	}
	
	public static int getIconForItem(ItemStack item)
	{
		if (icons.get(item.itemID) == null) {
			return item.getItem().getIconFromDamage(item.getItemDamage());
		}
		return icons.get(item.itemID).getIconIndex(item);
	}
	
	public static String getTextureFile(ItemStack item)
	{
		if (icons.get(item.itemID) == null) {
			return item.getItem().getTextureFile();
		}
		return icons.get(item.itemID).getTextureFile(item);
	}
}