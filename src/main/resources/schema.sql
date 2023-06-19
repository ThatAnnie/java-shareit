DROP TABLE IF EXISTS items, users, bookings, comments CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50)   NOT NULL,
    email VARCHAR(50)   NOT NULL,
    CONSTRAINT uc_user_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS items
(
    item_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50)   NOT NULL,
    description VARCHAR(250)   NOT NULL,
    is_available BOOLEAN   NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_items_owner_id FOREIGN KEY(user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    item_id BIGINT NOT NULL,
    booker_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_bookings_item_id FOREIGN KEY(item_id) REFERENCES items (item_id) ON DELETE CASCADE,
    CONSTRAINT fk_bookings_user_id FOREIGN KEY(booker_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
    comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text VARCHAR(500) NOT NULL,
    item_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_comments_item_id FOREIGN KEY(item_id) REFERENCES items (item_id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_user_id FOREIGN KEY(author_id) REFERENCES users (user_id) ON DELETE CASCADE
);