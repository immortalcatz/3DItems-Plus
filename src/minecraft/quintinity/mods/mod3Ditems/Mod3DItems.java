package quintinity.mods.mod3Ditems;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import quintinity.api.*;
import quintinity.api.Version.VersionCheckResult;
import quintinity.api.settings.SettingsAPI;
import quintinity.mods.mod3Ditems.blocks.*;
import quintinity.mods.mod3Ditems.modhelpers.*;
import quintinity.mods.mod3Ditems.settings.*;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.SpriteHelper;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraftforge.client.*;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;

@Mod(modid = "3ditems", name = "3DItems", version = "1.6.2", dependencies = "after:settingsapi")
public class Mod3DItems implements IVersionChecker
{
    public static Mod3DItems instance;
    
    public static boolean optifineInstalled = false;
    public static boolean mcpatcherInstalled = false;
    public static boolean buildcraftInstalled = false; //BuildCraft mod
    public static boolean ironchestsInstalled = false; //IronChests2 mod
    
    public static RenderItem originalRenderer;
    public static boolean rotateInvItems = false;
    public static float angle = 0.0F;
    @SidedProxy(clientSide = "quintinity.mods.mod3Ditems.settings.ClientProxy", serverSide = "quintinity.mods.mod3Ditems.CommonProxy")
    public static CommonProxy proxy;
    public int renderers = 0;
    public Settings settings;
    public Version currentVersion = new Version(1, 6, 2);
    public Version latestVersion;
    public final String versionURL = "http://dl.dropbox.com/u/14129028/3DItems/version.txt";
    public boolean updateAvailable = false;
    private boolean messageSent = false;
    public OptionHandler handler;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
    	instance = this;
    	RenderHelper3D.init();
    	loadSettings();
    	
    	MinecraftForge.EVENT_BUS.register(this);
    	getLatestVersion();
    	setModData(event.getModMetadata());
    	handler = new OptionHandler();
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
    	//BlockOverrider.init();
    	//SettingsAPI.registerOptionHandler(new OptionHandler());
    	try {
			this.getClass().getResource("/quintinity/mods/mod3Ditems/logo.png").toString();
		} 
    	catch (Exception e) {
			System.out.println("*** MISSING LOGO FILE ***");
		}
    }

    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
    	event = null;
        proxy.load();
    	ModRenderHelper.init();
        optifineInstalled = ModChecker.checkOptifineInstallation();
        mcpatcherInstalled = ModChecker.checkMCPatcherInstallation();
        buildcraftInstalled = ModChecker.checkBuildCraftInstallation();
        ironchestsInstalled = ModChecker.checkIronChestsInstallation();
        System.out.println("[3DItems] Icon width: " + RenderHelper3D.getTextureWidth());
        //registerItemRenderers();
        originalRenderer = (RenderItem) RenderManager.instance.entityRenderMap.get(EntityItem.class);
        RenderManager.instance.entityRenderMap.put(EntityItem.class, new RenderItem3D());
        RenderManager.instance.entityRenderMap.put(EntityItemFrame.class, new ItemFrameRenderer());
        
        /*if (ironchestsInstalled) {
        	ArrayList<String> tileClasses = ModRenderHelper.getIronChestTEs();
        	for (int i = 0; i < tileClasses.size(); i++) {
        		Class tile = loadClass(tileClasses.get(i));
        		if (tile != null) {
        			IronChestsRenderHelper renderer = new IronChestsRenderHelper();
        			TileEntityRenderer.instance.specialRendererMap.put(tile, renderer);
        			renderer.setTileEntityRenderer(TileEntityRenderer.instance);
        		}
        	}
        }*/
    }
    
    public String format(String s)
    {
    	String fin = "";
    	String[] split = s.split(" ");
    	for (int i = 0; i < split.length; i++) {
    		String spl = split[i];
    		spl = spl.substring(0, 1).toUpperCase().concat(spl.substring(1).toLowerCase());
    		fin = fin.concat(spl + " ");
    	}
		fin = fin.trim();
    	return fin;
    }
    
    public void loadSettings()
    {
    	settings = new Settings(new File(Minecraft.getMinecraftDir() + "/config/3DItems.cfg"));
    	settings.load();
    	Settings.setRenderIn3D(settings.getProperty("Enabled", true));
    	Settings.setRenderFrameIn3D(settings.getProperty("3DFrameItems", true));
    	float iScale = settings.getProperty("ItemScale", 1.0F);
    	float bScale = settings.getProperty("BlockScale", 1.0F);
    	float dist = settings.getProperty("RenderDistance", 1F);
    	if (iScale > 2.0F || iScale < 0) { iScale = 1.0F; }
    	if (bScale > 2.0F || bScale < 0) { bScale = 1.0F; }
    	if (bScale > 3F || bScale < 1F) { bScale = 1F; }
    	Settings.setItemScale(iScale);
    	Settings.setBlockScale(bScale);
    	Settings.setRenderDistance(dist);
    	Settings.setItemBobbing(settings.getProperty("ItemBobbing", true));
    	Settings.setItemRotation(settings.getProperty("ItemRotation", true));
    	settings.save();
    }
    
    public void setModData(ModMetadata data)
    {
    	data.autogenerated = false;
    	ArrayList<String> authors = new ArrayList<String>();
    	authors.add("\u00a7cQuintinity");
    	data.authorList = authors;
    	data.url = "\u00a73bit.ly/NfmELZ";
    	data.description = "\u00a723DItems makes item entities on the ground render as a 3D model, plus a bunch of other stuff.";
    	data.logoFile = "/quintinity/mods/mod3Ditems/logo.png";
    }
    
    public void registerItemRenderers()
    {
    	IItemRenderer[] currentRenderers = new IItemRenderer[Item.itemsList.length];
    	try {
    		Field field = MinecraftForgeClient.class.getDeclaredFields()[0];
    		field.setAccessible(true);
    		currentRenderers = (IItemRenderer[])field.get(null);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	for (int i = 0; i < Item.itemsList.length; i++) {
    		Item item = Item.itemsList[i];
    		if (item != null) {
    			if (currentRenderers[i] == null) {
    				MinecraftForgeClient.registerItemRenderer(item.shiftedIndex, new ForgeRenderer());
    				renderers++;
    			}
    			else if (!currentRenderers[i].handleRenderType(new ItemStack(item), ItemRenderType.ENTITY)) {
    				MinecraftForgeClient.registerItemRenderer(item.shiftedIndex, new ForgeRenderer(currentRenderers[i]));
    				//renderers++;
    			}
    		}
    	}
    	System.out.println("[3DItems] Registered " + renderers + " item renderers");
    }
    
    @ForgeSubscribe
    public void entityJoinedWorld(EntityJoinWorldEvent event)
    {
    	if (event.entity instanceof EntityItem) {
    		EntityItem item = (EntityItem)event.entity;
    		item.renderDistanceWeight = 1.0D * Settings.getRenderDistance();
    	}
    }
    
    @ForgeSubscribe
    public void worldLoad(WorldEvent.Load event)
    {
    	if (event.world instanceof WorldClient && updateAvailable && !messageSent) {
        	ClientProxy.handler.sendMessage("A new \u00a7e3DItems \u00a7fversion is available: \u00a7e" + latestVersion.toString());
        	messageSent = true;
    	}
    } 
    
    public void getLatestVersion()
    {
    	new ScheduledThreadPoolExecutor(1).schedule(new ThreadVersion(this, currentVersion, versionURL), 10L, TimeUnit.MILLISECONDS);
    }
    
	public void versionChecked(VersionCheckResult result, Version latestVersion) 
	{
		this.latestVersion = latestVersion;
		if (result == VersionCheckResult.NEW_VERSION_AVAILIABLE) {
			updateAvailable = true;
		}
	}
	
    public boolean onTickInGame(Minecraft var1)
    {
        angle += 7.0F;
        if (angle >= 360.0F)
        {
            angle = 0.0F;
        }
        return true;
    }

    public static Class loadClass(String name)
    {
        try
        {
            Class c = Class.forName(name, false, Mod3DItems.class.getClassLoader());
            return c;
        }
        catch (ClassNotFoundException e)
        {
            return null;
        }      
    }
}