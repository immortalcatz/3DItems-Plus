package quintinity.mods.mod3Ditems;

public class Version 
{
    public int majorVersion = 0;
    public int minorVersion = 0;
    public int revisionVersion = 0;
    public boolean isValid = false;
    
    public Version(String v)
    {
    	try {
    		String[] values = v.split(":");
    		majorVersion = Integer.parseInt(values[0]);
    		minorVersion = Integer.parseInt(values[1]);
    		if (values.length >= 3) {
    			revisionVersion = Integer.parseInt(values[2]);
    		}
    		isValid = true;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		isValid = false;
    	}
    }
    
    public Version(int major, int minor, int rev)
    {
    	majorVersion = major;
    	minorVersion = minor;
    	revisionVersion = rev;
    }
    
    public boolean isNewerThan(Version version)
    {
    	if (majorVersion > version.majorVersion) {
    		return true;
    	}
    	else if (majorVersion == version.majorVersion && minorVersion > version.minorVersion) {
    		return true;
    	}
    	else if (majorVersion == version.majorVersion && minorVersion == version.minorVersion && revisionVersion > version.revisionVersion) {
    		return true;
    	}
    	return false;
    }
    
    public String toString()
    {
    	return majorVersion + "." + minorVersion + "." + revisionVersion;
    }
}