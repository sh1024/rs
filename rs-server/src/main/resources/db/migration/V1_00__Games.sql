CREATE TABLE games (
  id                        bigserial primary key,
  version                   INTEGER,
  created_at                TIMESTAMP,
  updated_at                TIMESTAMP,
  name                      VARCHAR(256),
  description               VARCHAR(512)
);

