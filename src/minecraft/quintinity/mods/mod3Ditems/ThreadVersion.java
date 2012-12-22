package quintinity.mods.mod3Ditems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ThreadVersion implements Runnable 
{
	public void run()
	{
		try {
	    	URL url = new URL("http://dl.dropbox.com/u/14129028/3DItems/version.txt");
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line = reader.readLine();
	        reader.close();
	        Mod3DItems.instance.latestVersion = new Version(line);
	        if (Mod3DItems.instance.latestVersion.isValid && Mod3DItems.instance.latestVersion.isNewerThan(Mod3DItems.instance.currentVersion)) {
	        	Mod3DItems.instance.updateAvailable = true;
	        }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
