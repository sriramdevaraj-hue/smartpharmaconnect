-- 1. Create schema
CREATE SCHEMA IF NOT EXISTS orders;

-- 2. Grant privileges to your Postgres user
GRANT ALL PRIVILEGES ON SCHEMA orders TO pharma;

-- 3. Create table
CREATE TABLE IF NOT EXISTS orders.orders (
    id UUID PRIMARY KEY,
    customer_id UUID,
    medicine_id UUID,
    quantity INT,
    total_price BIGINT,
    status TEXT NOT NULL CHECK (status IN ('PLACED', 'DISPATCHED', 'DELIVERED')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
