package quintinity.mods.mod3Ditems.blocks;
import java.util.List;
import net.minecraft.src.*;

public class OverrideLilyPad extends BlockLilyPad
{
    public OverrideLilyPad(int id, int t)
    {
        super(id, t);
    }

    @Override
    public int getRenderType()
    {
        return BlockRenderer.lilyPadID;
    }
    
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
}