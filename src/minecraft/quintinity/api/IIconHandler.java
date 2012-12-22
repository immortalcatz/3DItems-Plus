package quintinity.api;
import net.minecraft.item.ItemStack;

public interface IIconHandler 
{
	public int getIconIndex(ItemStack item);
	
	public String getTextureFile(ItemStack item);
}