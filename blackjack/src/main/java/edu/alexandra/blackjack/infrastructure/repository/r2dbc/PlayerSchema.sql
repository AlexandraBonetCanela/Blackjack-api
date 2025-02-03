use blackjack;

CREATE TABLE player (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL UNIQUE,
    total_score DECIMAL(10,2) DEFAULT 0
)