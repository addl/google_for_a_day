CREATE SEQUENCE seq_word;
CREATE SEQUENCE seq_web_page;
CREATE TABLE word (
  id     numeric(19, 0) NOT NULL, 
  lexeme varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE web_page (
  id      numeric(19, 0) NOT NULL, 
  wordid  numeric(19, 0) NOT NULL, 
  matches int4 NOT NULL, 
  url     varchar(255) NOT NULL, 
  title   varchar(255) NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE web_page ADD CONSTRAINT FKweb_page19253 FOREIGN KEY (wordid) REFERENCES word (id);
