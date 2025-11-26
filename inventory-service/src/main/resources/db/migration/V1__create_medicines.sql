-- INVENTORY-SERVICE
-- 1. Create schema
CREATE SCHEMA IF NOT EXISTS inventory;

-- 2. Grant privileges to the DB user (pharma)
GRANT ALL PRIVILEGES ON SCHEMA inventory TO pharma;

-- 3. Create table
CREATE TABLE IF NOT EXISTS inventory.medicines (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    category TEXT,
    stock INT NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
