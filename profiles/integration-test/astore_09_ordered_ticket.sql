INSERT INTO "ORDERED_TICKET" ("ID", "SUPPORTING_DOCUMENT_DATA", "TICKET_ID", "ORDER_ID", "FIRST_NAME", "LAST_NAME", "MIDDLE_NAME", "DOB", "SUPPORTING_DOCUMENT_ID") VALUES (1,'11',1,1,'11','11','11','2014-12-28',12);
INSERT INTO "ORDERED_TICKET" ("ID", "SUPPORTING_DOCUMENT_DATA", "TICKET_ID", "ORDER_ID", "FIRST_NAME", "LAST_NAME", "MIDDLE_NAME", "DOB", "SUPPORTING_DOCUMENT_ID") VALUES (2,'123',2,2,'В','В','В','1961-09-25',12);
INSERT INTO "ORDERED_TICKET" ("ID", "SUPPORTING_DOCUMENT_DATA", "TICKET_ID", "ORDER_ID", "FIRST_NAME", "LAST_NAME", "MIDDLE_NAME", "DOB", "SUPPORTING_DOCUMENT_ID") VALUES (3,'111',1,2,'Олег','С','И','1961-08-03',9);

ALTER TABLE "ORDERED_TICKET" ALTER COLUMN "ID" RESTART WITH 4;