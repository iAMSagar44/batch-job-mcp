-- Table: credit_card_payments
CREATE TABLE IF NOT EXISTS credit_card_payments(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "date" date NOT NULL,
    amount double precision NOT NULL,
    transaction_detail varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

-- Table: credit_transactions
CREATE TABLE IF NOT EXISTS credit_transactions(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "date" date NOT NULL,
    amount double precision NOT NULL,
    transaction_detail varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

-- Table: financial_transaction
CREATE TABLE IF NOT EXISTS financial_transaction(
    amount double precision NOT NULL,
    "date" date NOT NULL,
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    category varchar(255) NOT NULL,
    transaction_detail varchar(255) NOT NULL,
    transaction_type varchar(255) NOT NULL DEFAULT 'DEBIT'::character varying,
    PRIMARY KEY(id)
);

-- Table: categories
CREATE TABLE IF NOT EXISTS categories(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    category varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);