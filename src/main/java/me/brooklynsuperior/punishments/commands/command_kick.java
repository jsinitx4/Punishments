package me.brooklynsuperior.punishments.commands;

import me.brooklynsuperior.punishments.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command_kick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.GRAY + "Supply a username to kick");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(ChatColor.GRAY + "Player not found");
            return true;
        }

        StringBuilder message = new StringBuilder()
                .append(ChatColor.GRAY)
                .append("You've been kicked from this server")
                .append("\nKicked by: ")
                .append(ChatColor.GREEN)
                .append(sender.getName());

        String reason = "No reason provided";
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
            message.append(ChatColor.GRAY);
            message.append("\nReason: ")
                    .append(ChatColor.GREEN)
                    .append(reason);
        }
        if (sender.hasPermission("punishments.kick")) {
            player.kickPlayer(message.toString());
            Utils.broadcast(ChatColor.GREEN + sender.getName() + ChatColor.GRAY +" » Kicking " + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " with reason: " + "'" + ChatColor.GREEN + reason + ChatColor.GRAY + "'");
        } else {
            sender.sendMessage(Utils.chatcolor("&7You do not have valid permissions to run this command"));
        }
        return true;
    }
}