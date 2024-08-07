package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() { // Returns name variable.
		return this.name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name){ // Constructs and initialises variables.
		this.name = name;
		this.companies = new HashMap<String, ListedCompany>();
		this.announcements = new DSEListGeneric<String>();
		this.brokers = new DSEListGeneric<StockBroker>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company){
		if(company == null) { // Checks if company is a valid ListedCompany object.
			return false;
		}
		else if(this.companies.containsKey(company.getCode())) { // Checks if the company is already present in the Company HashMap.
			return false;
		}
		else { // Adds company to the HashMap.
			this.companies.put(company.getCode(), company);
			return true;
		}
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker){
		if (broker == null) { // Checks if Broker is a valid StockerBroker Object.
			return false;
		}
		else if (this.brokers.contains(broker)) { // Checks if Broker is already in Broker List.
			return false;
		}
		else { // Adds broker.
			this.brokers.add(broker);
			return true;
		}
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException{
		int totalTrades = 0;
		for (int broker = 0; broker < this.brokers.size(); broker++) { // Loops over each StockBroker in the BrokerList.
			if (this.brokers.get(broker).getPendingTradeCount() > 0) { // Checks if the Broker has a trade.
				Trade trade = this.brokers.get(broker).getNextTrade();
					if (this.companies.containsKey(trade.listedCompanyCode) == true) { // Checks if the Trade Company is valid.
						this.companies.get(trade.listedCompanyCode).processTrade(trade.getShareQuantity());
						totalTrades++;
					}
					else {
						throw new UntradedCompanyException(trade.listedCompanyCode); // If the company is in the CompanyList and exception is thrown.
					}
			}
		}
		return totalTrades;
	}
	
	public int runCommandLineExchange(Scanner sc){
		return 0;
	}
}
