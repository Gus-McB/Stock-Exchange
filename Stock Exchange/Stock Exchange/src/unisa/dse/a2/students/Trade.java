package unisa.dse.a2.students;

public class Trade implements Comparable<Trade> {

	/*
	 * Don't modify the tradeID or created values, as they're used to simplify the tests
	 * You may be required to use the "created" value in some parts of your code.
	 */
	private long tradeId = -1;
	private long created;

	/**
	 * @return 
	 * @return Track the moment in time this Trade was created
	 */
	public long getCreated(){ // Returns the created variable.
		return this.created;
	}
	
	public String listedCompanyCode;

	/**
	 * @return 
	 * @return The company's code
	 */
	public String getCompanyCode() { // Returns the listedCompanyCode.
		return this.listedCompanyCode;
	}
	
	private int shareQuantity;

	/**
	 * @return 
	 * @return The quantity of shares to trade
	 */
	public int getShareQuantity() { // Returns the shareQuantity.
		return this.shareQuantity;
	}

	private StockBroker broker;

	/**
	 * @return 
	 * @return The broker associated with this trade
	 */
	public StockBroker getStockBroker() { //Returns the StockBroker.
		return this.broker;
	}


	/***
	 * Do not modify this constructor, it is used for testing purposes only
	 */
	public Trade(StockBroker broker, int id){
		created = System.nanoTime(); //do not change this
		tradeId = id; //do not change this
		try { Thread.sleep(100); } catch (Exception x) {}
	}
	
	/***
	 * Create a new trade with the associated broker, company, and share quantity
	 * DO NOT change the current created and tradeId code
	 * 
	 * @param broker
	 * @param listedCompanyCode
	 * @param shareQuantity
	 */
	public Trade(StockBroker broker, String listedCompanyCode, int shareQuantity){ // Constructor.
		created = System.nanoTime(); //do not change this
		tradeId = System.nanoTime(); //do not change this
		try { Thread.sleep(100); } catch (Exception x) {}
		this.broker = broker;
		this.shareQuantity = shareQuantity;
		this.listedCompanyCode = listedCompanyCode;
	}
	
	/**
	 * Compares one trade to another trade
	 * 
	 * If we have two trades, A and B, and we examine the company in each trade:
	 *  - if A and B are BOTH on their broker's watchlists, they are equal (return 0)
	 *  - if A is on their brokers list, but B is not on B's brokers list (return 1)
	 *  - if B is on their brokers list, but A is not on A's brokers list (return -1)
	 *  - Otherwise, if neither trade is on their broker's list, then compare 
	 *  		the "created" field, returning -1 if "this" is smaller, 0 if equal, 
	 *  		or 1 if greater
	 *  
	 * @return The ordering priority of the trade
	 */
	public int compareTo(Trade t){
		if (this.broker.getWatchlist().contains(this.listedCompanyCode) == true && t.broker.getWatchlist().contains(t.listedCompanyCode) == true) { // Checks if bother trades are in their brokers watchList.
			return 0;
		}
		else if(this.broker.getWatchlist().contains(this.listedCompanyCode) == true && t.broker.getWatchlist().contains(t.listedCompanyCode) == false) { // Checks if this trade is in the brokers watchList and the other trade is not in the other brokers watchList.
			return 1;
		}
		else if(this.broker.getWatchlist().contains(this.listedCompanyCode) == false && t.broker.getWatchlist().contains(t.listedCompanyCode) == true) { // Checks if this trade is not in this brokers watchList and the other trade is in the other brokers watchList.
			return -1;
		}
		else if (this.broker.getWatchlist().contains(this.listedCompanyCode) == false && t.broker.getWatchlist().contains(t.listedCompanyCode) == false){ // Checks if both trades are not in either brokers watchList.
			if (this.created > t.created) { // Checks if this created is greater than the other created.
				return 1;
			}
			else if(this.created < t.created) { // Checks if the other created is greater than this created.
				return -1;
			}
			else {
				return 0;
			}
		}
		return 0;
	}
	

	/***
	 * Do not modify this toString, it is used for testing purposes
	 */
	@Override
	public String toString() {
		return ""+tradeId;
	}

	/***
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
		Trade other = (Trade) obj;
		if (tradeId != other.tradeId)
			return false;
		return true;
	}
	
}
