package unisa.dse.a2.students;

import java.util.PriorityQueue;

public class StockBroker {

	/**
	 * List of pending trades to be completed. Must store a generic type.
	 */
	private PriorityQueue<Trade> pendingTrades = new PriorityQueue<Trade>();
	
	/**
	 * List of stocks this stock broker is "watching"
	 */
	private DSEListGeneric<String> watchList = new DSEListGeneric<String>();

	/**
	 * returns a DEEP copy of the watchlist. Changes to the list returned from here
	 * should NOT change the list stored by this broker
	 * @return
	 */
	public DSEListGeneric<String> getWatchlist() { // Returns deep copy of watchList.
		return new DSEListGeneric<String>(this.watchList);
	}
	
	/**
	 * Adds the company code to the watchlist if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean addWatchlist(String companyCode) {
		if (this.watchList.contains(companyCode) == true) { // Checks company code does not already exist.
			return false;
		}
		this.watchList.add(companyCode);
		if (this.watchList.contains(companyCode)) { // Checks the company was successfully added.
			return true;
		}
		return false;
	}
	
	private String name;

	/**
	 * Name of the stock brokerage firm
	 * @return
	 */
	public String getName() { //Returns name Variable.
		return this.name;
	}
	
	/**
	 * Should store the broker's name and ensure the broker is setup ready to use
	 * @param name
	 */
	public StockBroker(String name) { // Set StockBroker name.
		this.name = name;
	}
	
	/**
	 * Adds the Trade to the pendingTrades list if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean placeOrder(Trade order) {
		if (order == null) { // Checks if order is null.
			return false;
		}
		for (int i = 0; i < this.pendingTrades.size(); i++){ // Loops over each pendingTrade.
			if (pendingTrades.contains(order) == true) { // Checks it does not already exist.
				return false;
			}
		}
		this.pendingTrades.add(order);
		return true;
	}
	
	/**
	 * Gets, removes, and returns the next trade to process
	 * @return Trade to process
	 */
	public Trade getNextTrade(){ // Returns and removes next Trade from pendingTrades.
		return pendingTrades.poll();
	}
	
	/**
	 * @return Number of pending trades
	 */
	public int getPendingTradeCount(){ // Returns the pendingTrades size.
		return pendingTrades.size();
	}

	/**
	 * Do not modify this equals, it is used for testing purposes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockBroker other = (StockBroker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
