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
	public DSEListGeneric<String> getWatchlist() {
		// Create new instance to ensure deep copy - protects internal watchlist
		return new DSEListGeneric<String>(watchList);
	}
	
	/**
	 * Adds the company code to the watchlist if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean addWatchlist(String companyCode)
	{
		// Validate input and check for duplicates before adding
		if (companyCode != null && !watchList.contains(companyCode)) {
			return watchList.add(companyCode);
		}
		return false;
	}
	
	private String name;

	/**
	 * Name of the stock brokerage firm
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Should store the broker's name and ensure the broker is setup ready to use
	 * @param name
	 */
	public StockBroker(String name)
	{
		this.name = name;
	}
	
	/**
	 * Adds the Trade to the pendingTrades list if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean placeOrder(Trade order)
	{
		// Validate order and prevent duplicates in the priority queue
		if (order != null && !pendingTrades.contains(order)) {
				return pendingTrades.add(order);
		}
		return false;
	}
	
	/**
	 * Gets, removes, and returns the next trade to process
	 * @return Trade to process
	 */
	public Trade getNextTrade()
	{
		// PriorityQueue.poll() returns highest priority element (based on compareTo)
		// Returns null if queue is empty
		return pendingTrades.poll();
	}
	
	/**
	 * @return Number of pending trades
	 */
	public int getPendingTradeCount()
	{
		int count = pendingTrades.size();
	    return count;
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
