package quintinity.mods.mod3Ditems.client;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;
import quintinity.mods.mod3Ditems.CommonProxy;
import quintinity.mods.mod3Ditems.blocks.BlockRenderer;

public class ClientProxy extends CommonProxy
{
	public static TickHandler handler;
	
    public void load()
    {
    	handler = new TickHandler();
        TickRegistry.registerTickHandler(handler, Side.CLIENT);
        BlockRenderer.init();
        //RenderingRegistry.registerBlockHandler(BlockRenderer.lilyPadID, new BlockRenderer(false));
        //RenderingRegistry.registerBlockHandler(BlockRenderer.cobwebID, new BlockRenderer(false));
        //ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
    }
}