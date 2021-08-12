package ga.uniquecoding.uniquebeacons.events;

import ga.uniquecoding.uniquebeacons.BeaconManager;
import ga.uniquecoding.uniquebeacons.UniqueBeacons;
import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;

public class BeaconListener implements Listener
{
    @EventHandler
    public void beaconOpen(PlayerInteractEvent event)
    {
        var player = event.getPlayer();
        var block = event.getClickedBlock();
        var plugin = UniqueBeacons.getPlugin(UniqueBeacons.class);

        if (block == null)
        {
            return;
        }

        if (block.getState() instanceof Beacon)
        {

            var beacon = (Beacon) block.getState();


            if (beacon.getTier() >= 1)
            {

                player.sendMessage("your mom gay.");

            }
        }
    }

    @EventHandler
    public void beaconOwner(BlockPlaceEvent event) throws IOException {
        var player = event.getPlayer();
        var block = event.getBlockPlaced();
        var uuid = player.getUniqueId();
        var config = BeaconConfig.get();


        var x = block.getX();
        var y = block.getY();
        var z = block.getZ();


        int count;

        for(count = 1; count <= 100;count++)

        if (block.getType().equals(Material.BEACON))
        {
           config.createSection(uuid.toString() + ".beacons." + count+ ".tier.xyz");
           config.set(uuid.toString() + ".beacons." + count+ ".tier", 1);
           config.set(uuid.toString() + ".beacons." + count+ ".xyz", x + ", " + y + ", " + z);

           BeaconConfig.save();

        }
    }
}
