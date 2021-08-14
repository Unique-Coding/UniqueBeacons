package ga.uniquecoding.uniquebeacons;

import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import org.bukkit.block.Beacon;

import java.util.UUID;

public class BeaconManager
{
    public static int getTier(Beacon beacon)
    {
        return BeaconConfig.get().getInt("tier");
    }

    public static void setTier(UUID uuid, Integer count, Beacon beacon, Integer newTier)
    {
        for(count = 1; count <= 10;count++) {
            BeaconConfig.get().set(uuid.toString() + ".beacons." + count + ".tier", newTier);
            return;
        }
    }

}
