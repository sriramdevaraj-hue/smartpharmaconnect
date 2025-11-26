-- 1. Create schema and set owner
CREATE SCHEMA IF NOT EXISTS notifications;

-- 2. Grant privileges (optional but safe)
GRANT ALL PRIVILEGES ON SCHEMA notifications TO pharma;

-- 3. Create table
CREATE TABLE IF NOT EXISTS notifications.notifications (
    id UUID PRIMARY KEY,
    ref_id UUID,
    message TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);