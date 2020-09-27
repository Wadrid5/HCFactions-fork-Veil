package net.bfcode.bfhcf.faction.argument.staff;

import java.util.Collections;
import java.util.List;
import org.bukkit.command.ConsoleCommandSender;

import net.bfcode.bfbase.util.command.CommandArgument;
import net.bfcode.bfhcf.HCFaction;
import net.bfcode.bfhcf.faction.type.Faction;
import net.bfcode.bfhcf.faction.type.PlayerFaction;

import java.util.UUID;
import org.bukkit.Bukkit;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FactionBanArgument extends CommandArgument
{
    private HCFaction plugin;
    
    public FactionBanArgument(HCFaction plugin) {
        super("ban", "Tempbans every faction member.");
        this.plugin = plugin;
        this.permission = "hcf.command.faction.argument." + this.getName();
    }
    
    public String getUsage(String label) {
        return '/' + label + ' ' + this.getName() + " <factionName> <time:(e.g. 1h2s)> <reason>";
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: " + this.getUsage(label));
            return true;
        }
        Faction faction = this.plugin.getFactionManager().getContainingFaction(args[1]);
        if (!(faction instanceof PlayerFaction)) {
            sender.sendMessage(ChatColor.RED + "Player faction named or containing member with IGN or UUID " + args[1] + " not found.");
            return true;
        }
        PlayerFaction playerFaction = (PlayerFaction)faction;
        String extraArgs = HCFaction.SPACE_JOINER.join((Object[])Arrays.copyOfRange(args, 2, args.length));
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        for (UUID uuid : playerFaction.getMembers().keySet()) {
            String commandLine = "tempban " + uuid.toString() + " " + extraArgs;
            sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Executing " + ChatColor.RED + commandLine);
            console.getServer().dispatchCommand(sender, commandLine);
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lExecuting command to ban the faction " + playerFaction.getName()));
        Bukkit.getServer().broadcastMessage("§eThe faction §6§l" + playerFaction.getName() + " §ehas been tempban for §6§l" + extraArgs);
        return true;
    }
    
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return (args.length == 2) ? null : Collections.emptyList();
    }
}