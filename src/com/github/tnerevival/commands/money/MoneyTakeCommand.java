package com.github.tnerevival.commands.money;

import com.github.tnerevival.core.transaction.TransactionType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.tnerevival.TNE;
import com.github.tnerevival.commands.TNECommand;
import com.github.tnerevival.core.Message;
import com.github.tnerevival.utils.AccountUtils;
import com.github.tnerevival.utils.MISCUtils;

public class MoneyTakeCommand extends TNECommand {

	public MoneyTakeCommand(TNE plugin) {
		super(plugin);
	}

	@Override
	public String getName() {
		return "take";
	}

	@Override
	public String[] getAliases() {
		return new String[0];
	}

	@Override
	public String getNode() {
		return "tne.money.take";
	}

	@Override
	public boolean console() {
		return true;
	}
	
	@Override
	public boolean execute(CommandSender sender, String command, String[] arguments) {
		if(arguments.length >= 2) {
			String world = (arguments.length == 3)? arguments[2] : TNE.instance.defaultWorld;
			Double value = Double.valueOf(arguments[1].replace(TNE.instance.api.getString("Core.Currency.Decimal", world, MISCUtils.getID(getPlayer(sender)).toString()), "."));
			if(value < 0) {
				new Message("Messages.Money.Negative").translate(world, sender);
				return false;
			}
			if(getPlayer(sender, arguments[0]) != null) {
				if(AccountUtils.transaction(MISCUtils.getID(getPlayer(sender, arguments[0])).toString(), MISCUtils.getID(getPlayer(sender)).toString(), AccountUtils.round(value), TransactionType.MONEY_REMOVE, world)) {
          Message took = new Message("Messages.Money.Took");
          took.addVariable("$amount", MISCUtils.formatBalance(world, AccountUtils.round(value)));
          took.addVariable("$player", arguments[0]);
          took.translate(world, sender);
          return true;
        }
			}
		}
		help(sender);
		return false;
	}

	@Override
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "/money take <player> <amount> [world] - make some of <player>'s money vanish into thin air");
	}
	
}