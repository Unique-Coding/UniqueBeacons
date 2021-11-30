package ga.uniquecoding.uniquebeacons.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import ga.uniquecoding.uniquebeacons.UniqueBeacons;
import ga.uniquecoding.uniquebeacons.managers.BeaconManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GUI
{
    public static void openMainMenu(Player player, Beacon b)
    {
        Gui main = Gui.gui()
                .title(Component.text("Beacon"))
                .rows(3)
                .create();

        GuiItem beacon = ItemBuilder.from(Material.BEACON).asGuiItem();
        GuiItem sunflower = ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
                BeaconManager.upgrade(b);

                main.close(player);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        openMainMenu(player, b);
                    }

                }.runTaskLater(UniqueBeacons.getPlugin(UniqueBeacons.class), 2);
        });

        /*
        Customizing the gui items (displayname, lore)
         */

        //Beacon (beacon information)
        List<String> beaconLore = new ArrayList<>();

        int moreRadius = 100 * 2;

        ItemStack beaconItemStack = beacon.getItemStack();
        ItemMeta beaconItemMeta = beaconItemStack.getItemMeta();

        beaconItemMeta.setDisplayName(ChatColor.AQUA + "Beacon Core");
        beaconItemStack.setItemMeta(beaconItemMeta);

        beaconLore.add(ChatColor.GRAY + "Tier: " + ChatColor.GREEN + BeaconManager.getTier(b));
        beaconLore.add(ChatColor.GRAY + "Radius: 100x100");
        beaconItemStack.setLore(beaconLore);

        main.setItem(13, beacon);

        //Sunflower (Beacon upgrading)
        List<String> sunflowerLore = new ArrayList<>();

        ItemStack sunflowerItemStack = sunflower.getItemStack();
        ItemMeta sunflowerItemMeta = sunflowerItemStack.getItemMeta();

        sunflowerItemMeta.setDisplayName(ChatColor.RED + "Upgrade to Tier " + BeaconManager.getNextTier(b));
        sunflowerItemStack.setItemMeta(sunflowerItemMeta);

        sunflowerLore.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "$" + BeaconManager.getPrice(b));
        sunflowerLore.add("");
        sunflowerLore.add(ChatColor.YELLOW + "Click to upgrade");
        sunflowerItemStack.setLore(sunflowerLore);

        main.setItem(16, sunflower);

        /*
        The gui filler, actions such as cancelling the item moving event.
         */

        main.getFiller().fill(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).asGuiItem());
        main.setDefaultClickAction(event ->
        {
            event.setCancelled(true);
        });

        main.open(player);
    }

}
