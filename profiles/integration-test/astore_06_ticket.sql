INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (1,'adult',100.00,'','2014-09-22 01:29:51','',1);
INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (2,'invalid',50.00,'','2014-09-22 01:29:51','',1);
INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (3,'child',30.00,'','2014-09-22 01:29:51','',1);
INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (4,'mini_car',150.00,'Length < 3m','2014-09-22 01:30:08','',2);
INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (5,'car',200.00,'','2014-09-22 01:29:51','',2);
INSERT INTO "TICKET" ("ID", "NAME", "PRICE", "DESCRIPTION", "LAST_UPDATE", "DATA_LABEL", "CATEGORY_ID") VALUES (6,'truck',500.00,'','2014-09-22 01:29:51','',2);
ALTER TABLE "TICKET" ALTER COLUMN "ID" RESTART WITH 7;