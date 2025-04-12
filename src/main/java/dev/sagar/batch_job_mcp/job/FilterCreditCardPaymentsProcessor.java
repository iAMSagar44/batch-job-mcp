package dev.sagar.batch_job_mcp.job;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.simple.JdbcClient;

class FilterCreditCardPaymentsProcessor implements ItemProcessor<FinancialTransaction, FinancialTransaction> {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory
		.getLogger(FilterCreditCardPaymentsProcessor.class);

	private final JdbcClient jdbcClient;

	public FilterCreditCardPaymentsProcessor(DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
	}

	@Override
	public FinancialTransaction process(FinancialTransaction transaction) throws Exception {
		if (transaction.transaction_detail().toLowerCase().contains("hsbc")) {
			logger.info("Filtering credit card payment transaction and updating the Credit Card Payments table: {}",
					transaction);
			jdbcClient.sql("INSERT INTO credit_card_payments_test (amount, date, transaction_detail) VALUES (?, ?, ?)")
				.param(transaction.amount())
				.param(transaction.date())
				.param(transaction.transaction_detail())
				.update();
			return null; // Filter out transactions with "HSBC" in the transaction detail.
							// These are
							// credit card payments and expenses around it are recorded in
							// the database.
		}
		else {
			return transaction; // Keep other transactions.
		}
	}

}
