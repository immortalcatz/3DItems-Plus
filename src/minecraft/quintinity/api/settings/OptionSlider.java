package quintinity.api.settings;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import quintinity.mods.mod3Ditems.settings.GuiSettings;

public class OptionSlider extends OptionButton 
{
    public float sliderValue = 1.0F;
    public boolean dragging = false;
    public String text;
    public GuiOptionPage parent;
    
	public OptionSlider(int id, String text, float value) 
	{
		super(id, text);
		this.text = text.split(":")[0] + ": ";
		sliderValue = Float.parseFloat(Double.toString(((value - 0.5) / 1.5)).substring(0, 3));
	}
	
	protected int getHoverState(boolean par1)
    {
        return 0;
    }
	
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);
                
                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }
                sliderValue = (float) (Math.round(sliderValue * 15.0) / 15.0);
                parent.actionPerformed(this);
            }
            //displayString = text.concat(Double.toString(((1.5 * sliderValue) + 0.5)).substring(0, 3));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }
	
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.mousePressed(par1Minecraft, par2, par3))
        {
            this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }
            sliderValue = (float) (Math.round(sliderValue * 15.0) / 15.0);
            //displayString = text.concat(Double.toString(((1.5 * sliderValue) + 0.5)).substring(0, 3));
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }
	
    public void mouseReleased(int par1, int par2)
    {
        this.dragging = false;
    }
}