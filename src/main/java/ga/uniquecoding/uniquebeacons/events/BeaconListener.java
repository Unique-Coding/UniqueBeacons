package ga.uniquecoding.uniquebeacons.events;

import ga.uniquecoding.uniquebeacons.UniqueBeacons;
import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import ga.uniquecoding.uniquebeacons.guis.GUI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    event.setCancelled(true);
                    GUI.openMainMenu(player, beacon);
                }

            }
            else if (beacon.getTier() == 0)
            {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You can't get into the beacon core if the beacon isn't active!");
                }
            }
        }
    }

    @EventHandler
    public void createData(BlockPlaceEvent event) {
        var block = event.getBlockPlaced();
        var config = BeaconConfig.get();


        var x = block.getX();
        var y = block.getY();
        var z = block.getZ();


        if (block.getType().equals(Material.BEACON))
        {
           config.createSection("beacons." + block.getLocation().getWorld().getName() + "(x:" + x + ",y:" + y + ",z:" + z + ").tier");
           config.set("beacons." + block.getLocation().getWorld().getName() + "(x:" + x + ",y:" + y + ",z:" + z + ").tier", 1);

           BeaconConfig.save();

        }
    }

    @EventHandler
    public void removeData(BlockBreakEvent event) {
        var block = event.getBlock();
        var config = BeaconConfig.get();


        var x = block.getX();
        var y = block.getY();
        var z = block.getZ();


        if (block.getType().equals(Material.BEACON))
        {
            config.set("beacons." + block.getLocation().getWorld().getName() + "(x:" + x + ",y:" + y + ",z:" + z + ")", null);

            BeaconConfig.save();

        }
    }
}
