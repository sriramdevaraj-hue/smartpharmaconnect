-- 1. Create schema and assign owner
CREATE SCHEMA IF NOT EXISTS auth;

-- 2. Grant privileges to pharma (optional but recommended)
GRANT ALL PRIVILEGES ON SCHEMA auth TO pharma;

-- 3. Create users table
CREATE TABLE IF NOT EXISTS auth._user (
    id UUID PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    full_name TEXT NOT NULL,
    role TEXT NOT NULL CHECK (role IN ('PHARMACIST', 'CUSTOMER', 'ADMIN')),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS auth.refresh_tokens (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    token TEXT NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL
);