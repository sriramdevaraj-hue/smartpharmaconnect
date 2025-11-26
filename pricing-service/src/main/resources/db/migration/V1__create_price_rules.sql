-- 1. Create schema and assign owner
CREATE SCHEMA IF NOT EXISTS pricing;

-- 2. Grant privileges to pharma
GRANT ALL PRIVILEGES ON SCHEMA pricing TO pharma;

-- 3. Create price_rules table
CREATE TABLE IF NOT EXISTS pricing.price_rules (
    id SERIAL PRIMARY KEY,
    base_price BIGINT NOT NULL,
    discount_percent NUMERIC(4,2) DEFAULT 0.00,
    effective_from DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS pricing.price_history (
    id SERIAL PRIMARY KEY,
    medicine_id UUID NOT NULL,
    final_price BIGINT NOT NULL,
    calculated_at TIMESTAMPTZ DEFAULT NOW()
);