CREATE TABLE IF NOT EXISTS tasks
(
    id
    BIGINT
    PRIMARY
    KEY
    GENERATED
    BY
    DEFAULT AS
    IDENTITY,
    user_id
    BIGINT
    NOT
    NULL,
    title
    VARCHAR
    NOT
    NULL,
    FOREIGN
    KEY
(
    user_id
)
    REFERENCES users
(
    id
)
    )