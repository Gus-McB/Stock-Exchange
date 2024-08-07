package unisa.dse.a2.students;

public class ListedCompany {

	/**
	 * The full name of the company
	 */
	private String name;
	
	public String getName() { // Returns the Name variable.
		return this.name;
	}

	/**
	 * The listing code of the company
	 */
	private String code;
	
	public String getCode() { // Returns the code variable.
		return this.code;
	}

	/**
	 * Current price of the company after last trade
	 */
	private int currentPrice;
	
	public int getCurrentPrice() { // Returns the currentPrice variable.
		return this.currentPrice;
	}
	
	public ListedCompany(String code, String name, int currentPrice) { // Constructor.
		this.code = code;
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	/**
	 * Processing a trade should increase the current price of the company by 
	 *    quantity / 100
	 * A company's price CANNOT go below 1
	 * 
	 * @param quantity
	 * @return 
	 * @return the price after adjustment
	 */
	public double processTrade(int quantity) {
		double price = quantity/100; // Share price is calculated.
			this.currentPrice += price; // Share price is added to the currentPrice.
		if (this.currentPrice < 1) { // Checks price is above 1.
			this.currentPrice = 1;
			return this.currentPrice;
		}
		return price;
	}
}
