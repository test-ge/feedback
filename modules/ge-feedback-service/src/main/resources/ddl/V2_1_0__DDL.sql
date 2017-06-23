CREATE TABLE feedBack (
     id         BIGINT      NOT NULL,
     comment    VARCHAR(255) NOT NULL,
     page       VARCHAR(255),
     rate       CHAR(35),
     created    TIMESTAMP   NOT NULL,
     updated    TIMESTAMP   NOT NULL
);

ALTER TABLE feedBack
    ADD CONSTRAINT pk_feedBack
    UNIQUE (id);

CREATE SEQUENCE sq_feedBack
    START 1
    MINVALUE 1
    CACHE 1;

CREATE TABLE IF NOT EXISTS  tocken (
    id              BIGINT      NOT NULL,
    tocken          VARCHAR(50),
    ip              VARCHAR(50),
    feedBack_id      BIGINT,
    created         TIMESTAMP   NOT NULL,
    updated         TIMESTAMP   NOT NULL
);


ALTER TABLE tocken
    ADD CONSTRAINT pk_tocken
    UNIQUE (id);
    
CREATE SEQUENCE sq_tocken
    START 1
    MINVALUE 1
    CACHE 1;

ALTER TABLE tocken
    ADD CONSTRAINT fk_tocken_feedBack
    FOREIGN KEY (feedBack_id)
    REFERENCES feedback(id) ON DELETE CASCADE;


