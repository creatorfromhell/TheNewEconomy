package com.github.tnerevival.commands.credit;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.tnerevival.TNE;
import com.github.tnerevival.commands.TNECommand;
import com.github.tnerevival.core.Message;
import com.github.tnerevival.utils.AccountUtils;
import com.github.tnerevival.utils.MISCUtils;

public class CreditInventoryCommand extends TNECommand {
	
	public CreditInventoryCommand(TNE plugin) {
		super(plugin);
	}

	@Override
	public String getName() {
		return "inventory";
	}

	@Override
	public String[] getAliases() {
		return new String[0];
	}

	@Override
	public String getNode() {
		return "tne.credit.inventory";
	}

	@Override
	public boolean console() {
		return false;
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] arguments) {
		if(arguments.length == 1) {
			Player player = (Player)sender;
			HashMap<String, Long> credits = AccountUtils.getAccount(MISCUtils.getID(player)).getTimes(arguments[0]);
			sender.sendMessage(ChatColor.WHITE + "Time Credits for inventory \"" + arguments[0] +"\".");
			sender.sendMessage(ChatColor.WHITE + "World ~ Time Credits(in seconds)");
			sender.sendMessage(ChatColor.WHITE + "==============================");
			if(credits.size() > 0) {
				for(String world : credits.keySet()) {
					sender.sendMessage(ChatColor.WHITE + world + " ~ " + credits.get(world));
				}
				return true;
			} else {
				Message insufficient = new Message("Messages.Credit.Empty");
				insufficient.addVariable("$type",  arguments[0]);
				player.sendMessage(insufficient.translate());
				return false;
			}
		}
		help(sender);
		return false;
	}

	@Override
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "/credit inventory <inventory> - View time credits for <inventory> in every world.");
	}
}