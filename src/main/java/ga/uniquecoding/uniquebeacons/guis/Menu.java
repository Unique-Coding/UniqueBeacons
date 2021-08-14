package ga.uniquecoding.uniquebeacons.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import ga.uniquecoding.uniquebeacons.managers.BeaconManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    public static void openMainMenu(Player player, Beacon b)
    {
        Gui gui = Gui.gui()
                .title(Component.text("Beacon"))
                .rows(3)
                .create();

        GuiItem guiBeacon = ItemBuilder.from(Material.BEACON).asGuiItem();
        GuiItem guiSunflower = ItemBuilder.from(Material.SUNFLOWER).asGuiItem();

        var upgrade = guiSunflower.getItemStack();
        var upgrademeta = guiSunflower.getItemStack().getItemMeta();
        var beacon = guiBeacon.getItemStack();
        var beaconmeta= guiBeacon.getItemStack().getItemMeta();
        beaconmeta.setDisplayName(ChatColor.AQUA + "Beacon Core");
        List<String> loreBeacon = new ArrayList<>();
        loreBeacon.add(ChatColor.GRAY + "Tier: " + ChatColor.GREEN + BeaconManager.getTier(b));
        loreBeacon.add(ChatColor.GRAY + "Radius: 100x100");
        beaconmeta.setLore(loreBeacon);

        beacon.setItemMeta(beaconmeta);

        gui.setItem(13, guiBeacon);

        upgrademeta.setDisplayName(ChatColor.RED + "Upgrade to Tier " + BeaconManager.getTier(b) + 1);
        List<String> loreUpgrade = new ArrayList<>();
        loreUpgrade.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "$1,000");
        loreUpgrade.add("");
        loreUpgrade.add(ChatColor.YELLOW + "Click to upgrade!");

        upgrademeta.setLore(loreUpgrade);
        upgrade.setItemMeta(upgrademeta);

        gui.setItem(16, guiSunflower);

        gui.getFiller().fill(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).asGuiItem());
        gui.setDefaultClickAction(event ->
        {
            event.setCancelled(true);
        });

        gui.addSlotAction(16, event -> {

            if (BeaconManager.getTier(b) == 1)
            {
                BeaconManager.setTier(b, 2);
                player.sendMessage(ChatColor.GREEN + "You upgraded your beacon to tier " + BeaconManager.getTier(b));
                gui.updateItem(13, beacon);
            } else if (BeaconManager.getTier(b) == 2)
            {
                BeaconManager.setTier(b, 3);
                player.sendMessage(ChatColor.GREEN + "You upgraded your beacon to tier " + BeaconManager.getTier(b));
                gui.updateItem(13, beacon);
            }
        });

        gui.open(player);
    }

}
