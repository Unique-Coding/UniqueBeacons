package ga.uniquecoding.uniquebeacons.managers;

import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import org.bukkit.block.Beacon;

public class BeaconManager
{
    public static int getTier(Beacon beacon)
    {
        return BeaconConfig.get().getInt("beacons." + beacon.getLocation().getWorld().getName() + "(x:" + beacon.getLocation().getBlockX() + ",y:" + beacon.getLocation().getBlockY() + ",z:" + beacon.getLocation().getBlockZ() + ").tier");
    }

    public static void setTier(Beacon beacon, Integer newTier)
    {
        BeaconConfig.get().set("beacons." + beacon.getLocation().getWorld().getName() + "(x:" + beacon.getLocation().getBlockX() + ",y:" + beacon.getLocation().getBlockY() + ",z:" + beacon.getLocation().getBlockZ() + ").tier", newTier);
        BeaconConfig.save();
    }

}
