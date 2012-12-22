package quintinity.mods.mod3Ditems.blocks;
import java.util.List;
import java.util.Random;

import net.minecraft.src.*;

public class OverrideCobweb extends BlockWeb
{
    public OverrideCobweb(int id, int t)
    {
        super(id, t);
    }
    
    @Override
    public int getRenderType()
    {
        return BlockRenderer.cobwebID;
    }
}