package quintinity.api.settings;
import java.lang.reflect.Field;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.src.*;

public class ButtonMore extends GuiButton
{
	public Minecraft minecraft;
	
	public ButtonMore(int par1, int par2, int par3, String par4Str)
    {
        super(par1, par2, par3, 200, 20, par4Str);
        minecraft = FMLClientHandler.instance().getClient();
    }

    public ButtonMore(int par1, int par2, int par3, int par4, int par5, String par6Str)
    {
    	super(par1, par2, par3, par4, par5, par6Str);
    	minecraft = FMLClientHandler.instance().getClient();
    }
    
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
    	if (this.enabled && this.drawButton && par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height) {
    		if (minecraft.currentScreen != null) {
    			minecraft.displayGuiScreen(new GuiMoreOptions(minecraft.currentScreen));
    		}
    	}
		return false;
    }
}