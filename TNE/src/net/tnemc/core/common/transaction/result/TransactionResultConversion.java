package net.tnemc.core.common.transaction.result;

/**
 * The New Economy Minecraft Server Plugin
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by Daniel on 7/19/2017.
 */
public class TransactionResultConversion implements TransactionResult {

  @Override
  public String name() {
    return "conversion";
  }

  @Override
  public String initiatorMessage() {
    return "Messages.Money.Converted";
  }

  @Override
  public String recipientMessage() {
    return "";
  }

  @Override
  public boolean proceed() {
    return true;
  }
}