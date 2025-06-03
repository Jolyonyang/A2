package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
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
	public SecuritiesExchange(String name)
	{
		this.name = name;
		this.brokers = new DSEListGeneric<StockBroker>();
		this.announcements = new DSEListGeneric<String>();
		this.companies = new HashMap<String, ListedCompany>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		if (company == null || companies.containsKey(company.getCode())) {
            return false;
        }
        companies.put(company.getCode(), company);
        return true;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		if (broker == null || brokers.contains(broker)) {
            return false;
        }
        return brokers.add(broker);
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
	public int processTradeRound()
	{
		int tradeCount = 0;

        // Loop through all brokers in the exchange to process one trade from each
        for (int i = 0; i < brokers.size(); i++) {
            StockBroker broker = brokers.get(i);
            
            // Get the next highest priority trade from this broker's queue
            Trade trade = broker.getNextTrade();

            // Skip brokers with no pending trades
            if (trade == null) continue;

            // Validate that the company being traded exists on this exchange
            String companyCode = trade.getCompanyCode();
            ListedCompany company = companies.get(companyCode);

            if (company == null) {
                // Throw exception if trying to trade unlisted company
                throw new UntradedCompanyException(companyCode);
            }

            // Capture price before trade execution for announcement
            int priceBefore = company.getCurrentPrice();
            
            // Execute the trade - this will update the company's stock price
            company.processTrade(trade.getShareQuantity());

            // Create formal announcement of completed trade
            String announcement = String.format("Trade: %d %s @ %d via %s",
                    trade.getShareQuantity(),
                    companyCode,
                    priceBefore,  // Show pre-trade price
                    broker.getName()
            );

            // Add announcement to exchange's public record
            announcements.add(announcement);
            tradeCount++;
        }

        return tradeCount;
	}
	
	public int runCommandLineExchange(Scanner sc)
	{
		return 0;
	}
}
