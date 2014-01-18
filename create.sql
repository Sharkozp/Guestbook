-- Создаем таблицу t_user
CREATE TABLE t_user
(
username       VARCHAR(40),
pwd_digest     VARCHAR(255),
last_name      VARCHAR(128),
first_name     VARCHAR(128),
admin          CHAR(1) DEFAULT '0',

CONSTRAINT pk_user PRIMARY KEY (username)

);

-- Создаем таблицу t_guestbook
CREATE TABLE t_guestbook
(
name            VARCHAR(40),
description     VARCHAR(255),
display_order   integer,

CONSTRAINT pk_guestbook_mod PRIMARY KEY (name)

);

-- Создаем таблицу t_message
CREATE TABLE t_message
(
id                  BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
guestbook_name      VARCHAR(40),
msg_text            VARCHAR(4096),
for_all             CHAR(1),
time_creation       timestamp,
is_new4admin        char(1),
author_name         VARCHAR(80),
author_ip           VARCHAR(30),
phone               VARCHAR(80),
e_mail              VARCHAR(80),
icq                 VARCHAR(40),
answer_text         VARCHAR(4096),
answer_name         VARCHAR(80),
time_answer         timestamp,

CONSTRAINT pk_message PRIMARY KEY (id),
CONSTRAINT fk_guestbook_mes FOREIGN KEY (guestbook_name) REFERENCES t_guestbook(name) ON DELETE CASCADE

);

-- Создаем таблицу t_moderator
CREATE TABLE t_moderator
(
  id             BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  username       VARCHAR(40),
  guestbook_name VARCHAR(40),

  CONSTRAINT pk_moderator PRIMARY KEY (id),
  CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES t_user(username) ON DELETE CASCADE,
  CONSTRAINT fk_guestbook_mod FOREIGN KEY (guestbook_name) REFERENCES t_guestbook(name) ON DELETE CASCADE

);

INSERT INTO T_USER (username,pwd_digest,admin) values ('root','-36118-23-16-640110-113-111-98128192102-37-7057-126-9-123','1');
INSERT INTO T_GUESTBOOK VALUES ('gbsystem','Запись для администрирования всеми книгами',1);
INSERT INTO T_GUESTBOOK VALUES ('education','Книга education',2);
INSERT INTO t_moderator (username,guestbook_name) VALUES ('root', 'gbsystem');
