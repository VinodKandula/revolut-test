/*
=> make it simple
*/
CREATE TABLE ACCOUNT
(
    ID             BIGINT IDENTITY NOT NULL PRIMARY KEY,
    NAME           VARCHAR(100)   NOT NULL,
    BALANCE        NUMERIC(16, 2) NOT NULL
);

CREATE TABLE TRANSACTION
(
    ID              BIGINT IDENTITY NOT NULL PRIMARY KEY,
    FROM_ACCOUNT_ID BIGINT         NOT NULL,
    TO_ACCOUNT_ID   BIGINT         NOT NULL,
    AMOUNT          NUMERIC(10, 2) NOT NULL
);

ALTER TABLE TRANSACTION
    ADD FOREIGN KEY (FROM_ACCOUNT_ID) REFERENCES ACCOUNT (ID);

ALTER TABLE TRANSACTION
    ADD FOREIGN KEY (TO_ACCOUNT_ID) REFERENCES ACCOUNT (ID);