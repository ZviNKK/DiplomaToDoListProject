CREATE TABLE IF NOT EXISTS users
(
    id
    BIGINT
    PRIMARY
    KEY
    GENERATED
    BY
    DEFAULT AS
    IDENTITY,
    email
    VARCHAR
    NOT
    NULL,
    phone
    VARCHAR
    NULL,
    password
    VARCHAR
    NOT
    NULL
)