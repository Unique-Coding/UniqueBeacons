package ga.uniquecoding.uniquebeacons.managers;

import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import org.bukkit.block.Beacon;

public class BeaconManager {
    public static int getTier(Beacon beacon) {
        return BeaconConfig.get().getInt("beacons." + beacon.getLocation().getWorld().getName() + "(x:" + beacon.getLocation().getBlockX() + ",y:" + beacon.getLocation().getBlockY() + ",z:" + beacon.getLocation().getBlockZ() + ").tier");
    }

    public static void setTier(Beacon beacon, Integer newTier) {
        BeaconConfig.get().set("beacons." + beacon.getLocation().getWorld().getName() + "(x:" + beacon.getLocation().getBlockX() + ",y:" + beacon.getLocation().getBlockY() + ",z:" + beacon.getLocation().getBlockZ() + ").tier", newTier);
        BeaconConfig.save();
    }

    public static void upgrade(Beacon beacon) {
        int upgradedTier = getTier(beacon) + 1;

        BeaconConfig.get().set("beacons." + beacon.getLocation().getWorld().getName() + "(x:" + beacon.getLocation().getBlockX() + ",y:" + beacon.getLocation().getBlockY() + ",z:" + beacon.getLocation().getBlockZ() + ").tier", upgradedTier);
        BeaconConfig.save();
    }

    public static int getNextTier(Beacon beacon) {
        return getTier(beacon) + 1;
    }

    public static int getPrice(Beacon beacon)
    {
        return switch (getTier(beacon)) {
            case 1 -> 10000;
            case 2 -> 20000;
            case 3 -> 30000;
            default -> 0;
        };
    }
}
