package quintinity.api.settings;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

public class OptionButton extends GuiButton 
{
	public GuiMoreOptions parent;
	private boolean isMouseOver = false;
	private long startTime;
	private boolean isLink = false;
	
	public OptionButton(int id, String text)
    {
        super(id, 0, 0, 150, 20, text);
    }
    
    public void drawButton(Minecraft minecraft, int x, int y)
    {
    	super.drawButton(minecraft,x, y);
        if (this.drawButton)
        {
        	FontRenderer fontRenderer = GuiMoreOptions.getFontRenderer();
            boolean mouseOver = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int hoverState = this.getHoverState(mouseOver);
            if (hoverState == 2 && !isMouseOver) {
            	startTime = System.currentTimeMillis();
            	isMouseOver = true;
            }
            else if (hoverState == 2 && isMouseOver) {
            	if (System.currentTimeMillis() - startTime > 1000) {
            		IOptionHandler handler = SettingsAPI.getInstance().buttonToHandlerMap.get(this);
            		//GuiMoreOptions.setCurrentDescription(handler.getDescription(this));
            	}
            }
            else if (hoverState != 2 && isMouseOver) {
            	isMouseOver = false;
            }
        }
    }
    
    public void setText(String text)
    {
    	this.displayString = text;
    }
    
    public void setIsLink(boolean opens)
    {
    	this.isLink = opens;
    }
    
    public boolean isLink()
    {
    	return isLink;
    }
}