drop table if exists users, items, bookings, comments, requests;
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                                     name VARCHAR(255) NOT NULL,
                                     email VARCHAR(512) NOT NULL,
                                     CONSTRAINT pk_user PRIMARY KEY (id),
                                     CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS items (
                                     id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
                                     name VARCHAR(50) NOT NULL,
                                     description VARCHAR(255) NOT NULL,
                                     available BOOLEAN NOT NULL,
                                     owner BIGINT NOT NULL REFERENCES users(id),
                                     request BIGINT
);

CREATE TABLE IF NOT EXISTS bookings (
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    start_date   TIMESTAMP WITHOUT TIME ZONE           NOT NULL,
    end_date     TIMESTAMP WITHOUT TIME ZONE           NOT NULL,
    item_id BIGINT REFERENCES items (id)               NOT NULL,
    user_id BIGINT REFERENCES users (id)               NOT NULL,
    status  VARCHAR(10)                                NOT NULL DEFAULT 'WAITING'
);

CREATE TABLE IF NOT EXISTS comments (
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    text        VARCHAR(500)                            NOT NULL,
    item_id     BIGINT REFERENCES items (id)            NOT NULL,
    author_id   BIGINT REFERENCES users (id)            NOT NULL,
    created     TIMESTAMP WITHOUT TIME ZONE             NOT NULL
);

CREATE TABLE IF NOT EXISTS requests (
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description     VARCHAR(200)                            NOT NULL,
    requestor       BIGINT REFERENCES users (id)            NOT NULL,
    created         TIMESTAMP WITHOUT TIME ZONE             NOT NULL
);