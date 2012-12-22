package quintinity.mods.mod3Ditems.blocks;
import java.lang.reflect.*;
import java.util.HashMap;
import net.minecraft.src.Block;

public class BlockOverrider 
{
	
	public static HashMap<Integer, Integer> fieldIDs = new HashMap<Integer, Integer>();
	
	public static void overrideBlock(Block orig, Block newBlock)
	{
		try
		{
			int oldID = newBlock.blockID;
			newBlock.setBlockName(orig.getBlockName().substring(5));
			newBlock.blockIndexInTexture = orig.blockIndexInTexture;
			setBlockID(newBlock, orig.blockID);
			Field[] fields = Block.class.getDeclaredFields();
			Field field = Block.class.getDeclaredFields()[fieldIDs.get(orig.blockID)];
			if (field != null) {
				Field modifiers = Field.class.getDeclaredField("modifiers");
				modifiers.setAccessible(true);
			    modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			    field.set(null, newBlock);
			    Block.blocksList[orig.blockID] = newBlock;
			    Block.blocksList[oldID] = null;
			}
			else {
				System.out.println("*** FIELD IS NULL ***");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setBlockID(Block block, int id)
	{
		try {
			Class c = getBlockClass(block.getClass());
			Field idField = c.getDeclaredFields()[170]; //index of block id field
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
		    modifiers.setInt(idField, idField.getModifiers() & ~Modifier.FINAL);
		    idField.setInt(block, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Class getBlockClass(Class c)
	{
		if (c != Block.class) {
			return getBlockClass(c.getSuperclass());
		}
		else {
			return c;
		}
	}
	
	public static void init()
	{
		fieldIDs.put(Block.web.blockID, 53);
		fieldIDs.put(Block.waterlily.blockID, 134);
	}
}