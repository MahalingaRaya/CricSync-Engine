DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS ball_by_ball;
DROP TABLE IF EXISTS tournament_teams;
DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS tournaments;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    skills VARCHAR(255),
    rate_per_match INT DEFAULT 0,
    availability_status VARCHAR(20) DEFAULT 'AVAILABLE'
);

CREATE TABLE tournaments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    status VARCHAR(20) DEFAULT 'UPCOMING'
);

CREATE TABLE matches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tournament_id BIGINT,
    team_a VARCHAR(100) NOT NULL,
    team_b VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'UPCOMING',
    runs_a INT DEFAULT 0,
    wickets_a INT DEFAULT 0,
    balls_a INT DEFAULT 0,
    runs_b INT DEFAULT 0,
    wickets_b INT DEFAULT 0,
    balls_b INT DEFAULT 0,
    current_innings INT DEFAULT 1,
    winner_id VARCHAR(100),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id) ON DELETE CASCADE
);

CREATE TABLE tournament_teams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tournament_id BIGINT,
    team_name VARCHAR(100) NOT NULL,
    matches_played INT DEFAULT 0,
    matches_won INT DEFAULT 0,
    matches_lost INT DEFAULT 0,
    points INT DEFAULT 0,
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id) ON DELETE CASCADE
);

CREATE TABLE ball_by_ball (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_id BIGINT,
    innings_number INT NOT NULL,
    over_number INT NOT NULL,
    ball_number INT NOT NULL,
    runs_scored INT NOT NULL,
    extra_runs INT DEFAULT 0,
    is_wicket BOOLEAN DEFAULT FALSE,
    commentary_text TEXT,
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_id BIGINT,
    professional_id BIGINT,
    role_requested VARCHAR(30) NOT NULL,
    booking_status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE,
    FOREIGN KEY (professional_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_match_status ON matches(status);
CREATE INDEX idx_ball_match ON ball_by_ball(match_id);
