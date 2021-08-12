package ga.uniquecoding.uniquebeacons;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import ga.uniquecoding.uniquebeacons.commands.BeaconCommand;
import ga.uniquecoding.uniquebeacons.events.BeaconListener;
import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class UniqueBeacons extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        BeaconConfig.setup();
        BeaconConfig.get().options().copyDefaults(true);
        BeaconConfig.save();

        CommandAPI.onEnable(this);

        new BeaconCommand().register();

        getServer().getPluginManager().registerEvents(new BeaconListener(), this);

    }

    @Override
    public void onLoad()
    {
        CommandAPI.onLoad(new CommandAPIConfig());
    }

}