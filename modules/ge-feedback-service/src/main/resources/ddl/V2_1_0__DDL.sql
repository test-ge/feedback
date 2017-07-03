CREATE TABLE feedback (
     id         BIGINT      NOT NULL,
     comment    VARCHAR(255) NOT NULL,
     page       VARCHAR(255),
     rate       BIGINT      NOT NULL,
     created    TIMESTAMP   NOT NULL,
     updated    TIMESTAMP   NOT NULL
);

ALTER TABLE feedback
    ADD CONSTRAINT pk_feedback
    UNIQUE (id);

CREATE SEQUENCE sq_feedback
    START 1
    MINVALUE 1
    CACHE 1;

CREATE TABLE IF NOT EXISTS  token (
    id              BIGINT      NOT NULL,
    token           VARCHAR(50),
    ip              VARCHAR(50),
    created         TIMESTAMP   NOT NULL,
    updated         TIMESTAMP   NOT NULL
);


ALTER TABLE token
    ADD CONSTRAINT pk_token
    UNIQUE (id);
    
CREATE SEQUENCE sq_token
    START 1
    MINVALUE 1
    CACHE 1;



