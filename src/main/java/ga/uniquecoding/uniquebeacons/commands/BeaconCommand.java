package ga.uniquecoding.uniquebeacons.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.StringTooltip;
import dev.jorel.commandapi.arguments.StringArgument;
import ga.uniquecoding.uniquebeacons.files.BeaconConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

public class BeaconCommand extends CommandAPICommand
{

    public BeaconCommand()
    {
        super("uniquebeacons");
        withAliases("beacon", "beacons");
        withArguments(new StringArgument("subcommand").replaceSuggestionsT( info -> new IStringTooltip[]
                {
                        StringTooltip.of("reload", "Reloads the plugin configurations.")
                }));

        executesPlayer(this::execute);
    }

    private void execute(CommandSender sender, Object[] args)
    {
        if (args[0].equals("reload"))
        {
            BeaconConfig.reload();

            sender.sendMessage(ChatColor.GREEN + "Successfully, reloaded all plugin configurations!");

        }
    }

}
