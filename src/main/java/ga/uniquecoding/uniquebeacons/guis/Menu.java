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

public class MainMenu
{
    public static void openMainMenu(Player player, Beacon b)
    {
        Gui gui = Gui.gui()
                .title(Component.text("Beacon"))
                .rows(26)
                .create();

        GuiItem guiItem = ItemBuilder.from(Material.BEACON).asGuiItem();
        
        var beacon = guiItem.getItemStack();
        var beaconmeta= guiItem.getItemStack().getItemMeta();
        
        beaconmeta.setDisplayName(ChatColor.AQUA + "Beacon Core");

        List<String> lore = new ArrayList<>();
        
        lore.add("");
        lore.add(ChatColor.GRAY + "Tier: " + ChatColor.GREEN + BeaconManager.getTier((Beacon) b));
        lore.add(ChatColor.GRAY + "Speed:");
        
        
        beaconmeta.setLore(lore);

        beacon.setItemMeta(beaconmeta);

        gui.setItem(13, guiItem);
        

        gui.getFiller().fill(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).asGuiItem());
    }

}
