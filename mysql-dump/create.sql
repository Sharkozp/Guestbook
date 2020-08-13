CREATE TABLE users
(
    user_id    INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(40),
    pwd_digest VARCHAR(255),
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    is_admin   CHAR(1) DEFAULT '0'
);

CREATE TABLE guestbooks
(
    guestbook_id  INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(40),
    description   VARCHAR(255),
    display_order INT DEFAULT '0'
);

CREATE TABLE moderators
(
    moderator_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id      INT SIGNED NOT NULL,
    guestbook_id INT SIGNED NOT NULL,

    CONSTRAINT fk_moderators_user_id_users_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_moderators_guestbook_id_guestbooks_guestbook_id FOREIGN KEY (guestbook_id) REFERENCES guestbooks (guestbook_id) ON DELETE CASCADE
);

CREATE TABLE messages
(
    message_id    INT PRIMARY KEY AUTO_INCREMENT,
    message_text  TEXT,
    for_all       CHAR(1) DEFAULT '1',
    time_creation DATETIME DEFAULT current_timestamp(),
    is_new4admin  CHAR(1) DEFAULT '0',
    author_name   VARCHAR(80),
    author_ip     VARCHAR(30),
    phone         VARCHAR(80),
    email         VARCHAR(80),
    icq           VARCHAR(40),
    answer_text   TEXT,
    moderator_id  INT SIGNED NOT NULL,
    time_answer   DATETIME,
    guestbook_id  INT SIGNED NOT NULL,

    CONSTRAINT fk_messages_moderator_id_moderators_moderator_id FOREIGN KEY (moderator_id) REFERENCES moderators (moderator_id) ON DELETE CASCADE,
    CONSTRAINT fk_messages_guestbook_id_guestbooks_guestbook_id FOREIGN KEY (guestbook_id) REFERENCES guestbooks (guestbook_id) ON DELETE CASCADE
);

INSERT INTO users (user_id, username, pwd_digest, is_admin) values (1, 'root', 'yrcdZab1wbOKbOgV5TYcpU4/qYwjryzJJKMW00E0jYE=', '1');
INSERT INTO guestbooks (name, description, display_order) VALUES ('gbsystem', 'Default guestbook', 1), ('education', 'Book education', 2);
INSERT INTO moderators (user_id, guestbook_id) VALUES (1, 1);