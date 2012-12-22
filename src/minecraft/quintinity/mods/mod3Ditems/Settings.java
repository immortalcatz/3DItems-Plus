package quintinity.mods.mod3Ditems;
import java.io.*;
import java.util.HashMap;
import quintinity.api.config.ConfigFile;

public class Settings extends ConfigFile
{
	private static boolean renderIn3D = true;
	private static boolean renderFrameIn3D = true;
    private static float itemScale = 1.0F; 
    private static float blockScale = 1.0F; 
    private static boolean itemBobbing = true;
    private static boolean itemRotation = true;
    public static boolean use3Dblocks = true;
    private static float renderDistance = 1F;
    //private static HashMap<Float, String> txt = new HashMap<Float, String>();
    
    public Settings(File file) 
    {
		super(file);
	}
    
    public static boolean getRenderIn3D()
    {
    	return renderIn3D;
    }
    
    public static void setRenderIn3D(boolean b)
    {
    	renderIn3D = b;
    }
    
    public static boolean getRenderFrameIn3D()
    {
    	return renderFrameIn3D;
    }
    
    public static void setRenderFrameIn3D(boolean b)
    {
    	renderFrameIn3D = b;
    }
    
    public static float getItemScale()
    {
    	return itemScale;
    }
    
    public static void setItemScale(float scale)
    {
    	itemScale = scale;
    }
    
    public static float getBlockScale()
    {
    	return blockScale;
    }
    
    public static void setBlockScale(float scale)
    {
    	blockScale = scale;
    }
    
    public static boolean getItemBobbing()
    {
    	return itemBobbing;
    }
    
    public static void setItemBobbing(boolean b)
    {
    	itemBobbing = b;
    }
    
    public static boolean getItemRotation()
    {
    	return itemRotation;
    }
    
    public static void setItemRotation(boolean b)
    {
    	itemRotation = b;
    }
    
    public static float getRenderDistance()
    {
    	return renderDistance;
    }
    
    public static void setRenderDistance(float dist)
    {
    	renderDistance = dist;
    }
    
    public void writeHeader(PrintWriter writer)
    {
    	writer.println("# 3DItems Settings #");
    	writer.println("");
    }
    
    public void writeFooter(PrintWriter writer)
    {
    }
}