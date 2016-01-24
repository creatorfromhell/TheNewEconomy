package com.github.tnerevival.core.event;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.tnerevival.utils.AccountUtils;
import com.github.tnerevival.utils.MISCUtils;

public class TNEFundsAddEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    
    private Boolean cancelled = false;
    
    private UUID id;
    
    private Double amount;
 
    public TNEFundsAddEvent(UUID id, Double amount) {
    	this.id = id;
    	this.amount = amount;
    }
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

	public boolean isCancelled() {
        return cancelled;
    }
 
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    public UUID getID() {
    	return this.id;
    }

	/**
	 * @return the player
	 */
	public OfflinePlayer getPlayer() {
		return MISCUtils.getPlayer(id);
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(OfflinePlayer player) {
		this.id = MISCUtils.getID(player);
	}

	/**
	 * @return The amount being added to the player's balance.
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount to be added to the player's balance.
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 * @return Returns the player's balance before the funds are added.
	 */
	public Double getPreviousBalance() {
		return AccountUtils.getFunds(this.id);
	}
	
	/**
	 * 
	 * @return Returns what the player's balance will be after the funds are added.
	 */
	public Double getNewBalance() {
		return (AccountUtils.getFunds(this.id) + getAmount());
	}
}