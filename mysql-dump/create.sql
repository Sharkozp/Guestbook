CREATE TABLE users
(
    user_id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username   VARCHAR(40),
    pwd_digest VARCHAR(255),
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    is_admin   CHAR(1) DEFAULT '0',

    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE guestbooks
(
    guestbook_id  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name          VARCHAR(40),
    description   VARCHAR(255),
    display_order integer,

    CONSTRAINT pk_guestbook PRIMARY KEY (guestbook_id)
);

CREATE TABLE messages
(
    message_id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    msg_text      TEXT,
    for_all       CHAR(1),
    time_creation DATETIME DEFAULT current_timestamp(),
    is_new4admin  char(1),
    author_id     VARCHAR(80),
    author_ip     VARCHAR(30),
    phone         VARCHAR(80),
    email         VARCHAR(80),
    icq           VARCHAR(40),
    answer_text   TEXT,
    answer_name   VARCHAR(80),
    time_answer   DATETIME,
    guestbook_id  BIGINT,

    CONSTRAINT pk_message PRIMARY KEY (message_id),
    CONSTRAINT fk_guestbook_id FOREIGN KEY (guestbook_id) REFERENCES guestbooks (guestbook_id) ON DELETE CASCADE,
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE moderators
(
    moderator_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id      BIGINT,
    guestbook_id BIGINT,

    CONSTRAINT pk_moderator PRIMARY KEY (moderator_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_guestbook_id FOREIGN KEY (guestbook_id) REFERENCES guestbooks (guestbook_id) ON DELETE CASCADE
);

INSERT INTO users (user_id, username, pwd_digest, is_admin) values (1, 'root', 'yrcdZab1wbOKbOgV5TYcpU4/qYwjryzJJKMW00E0jYE=', '1');
INSERT INTO guestbooks VALUES ('gbsystem', 'Default guestbook', 1), ('education', 'Book education', 2);
INSERT INTO moderators (user_id, guestbook_id) VALUES (1, 1);