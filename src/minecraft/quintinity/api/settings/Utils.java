package quintinity.api.settings;

import static quintinity.api.TextColor.LIME;
import static quintinity.api.TextColor.RED;

public class Utils 
{
	private Utils() {}
	
	public static String booleanToString(boolean b) 
    {
    	if (b) { return LIME + "ON"; }
    	return RED + "OFF";
    }
    
    public static String trimFloat(float f)
    {
    	return Float.toString(f).substring(0, 3);
    }
}