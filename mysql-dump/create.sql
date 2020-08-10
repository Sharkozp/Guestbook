CREATE TABLE t_user
(
    username       VARCHAR(40),
    pwd_digest     VARCHAR(255),
    last_name      VARCHAR(128),
    first_name     VARCHAR(128),
    is_admin          CHAR(1) DEFAULT '0',

    CONSTRAINT pk_user PRIMARY KEY (username)
);

CREATE TABLE t_guestbook
(
    name            VARCHAR(40),
    description     VARCHAR(255),
    display_order   integer,

    CONSTRAINT pk_guestbook_mod PRIMARY KEY (name)
);

CREATE TABLE t_message
(
    id                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    guestbook_name      VARCHAR(40),
    msg_text            TEXT,
    for_all             CHAR(1),
    time_creation       timestamp,
    is_new4admin        char(1),
    author_name         VARCHAR(80),
    author_ip           VARCHAR(30),
    phone               VARCHAR(80),
    e_mail              VARCHAR(80),
    icq                 VARCHAR(40),
    answer_text         TEXT,
    answer_name         VARCHAR(80),
    time_answer         timestamp DEFAULT current_timestamp(),

    CONSTRAINT pk_message PRIMARY KEY (id),
    CONSTRAINT fk_guestbook_mes FOREIGN KEY (guestbook_name) REFERENCES t_guestbook(name) ON DELETE CASCADE
);

CREATE TABLE t_moderator
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username       VARCHAR(40),
    guestbook_name VARCHAR(40),

    CONSTRAINT pk_moderator PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES t_user(username) ON DELETE CASCADE,
    CONSTRAINT fk_guestbook_mod FOREIGN KEY (guestbook_name) REFERENCES t_guestbook(name) ON DELETE CASCADE
);

INSERT INTO t_user (username,pwd_digest,admin) values ('root','yrcdZab1wbOKbOgV5TYcpU4/qYwjryzJJKMW00E0jYE=','1');
INSERT INTO t_guestbook VALUES ('gbsystem','Default guestbook',1);
INSERT INTO t_guestbook VALUES ('education','Book education',2);
INSERT INTO t_moderator (username,guestbook_name) VALUES ('root', 'gbsystem');