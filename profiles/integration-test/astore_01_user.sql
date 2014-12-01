INSERT INTO "USER" ("ID", "EMAIL", "PASSWORD", "SALT", "FIRST_NAME", "LAST_NAME", "PHONE", "ACTIVE", "DATE_CREATED", "TOKEN")
VALUES (1,
        'notreal@mail.ru',
        'f34ae573a52c6bed3c17e94b0d180268fd88123d282c780122a8e9695011c7c9',
        'f64bff18-6101-49ed-a3ad-c6fc75152837',
        'Oleg',
        'Sorokin',
        '123',
        0,
        '2014-11-27 18:20:13',
        'be497192-65db-4e56-bf54-2f7cc4df71e5');


INSERT INTO "USER" ("ID", "EMAIL", "PASSWORD", "SALT", "FIRST_NAME", "LAST_NAME", "PHONE", "ACTIVE", "DATE_CREATED", "TOKEN")
VALUES (2,
        'oleg@setco.ru',
        '54531464dc14d9ec00c3fc391445679b961c0f524ebdda09d9f4272d056f0253',
        '17ffad89-7e96-4538-98ab-be0f01c7eb8b',
        'Олег',
        'Сорокин',
        '7755529',
        0,
        '2014-10-28 19:00:08',
        'fe3301b3-3eb4-4260-8824-87e5de00928d');


INSERT INTO "USER" ("ID", "EMAIL", "PASSWORD", "SALT", "FIRST_NAME", "LAST_NAME", "PHONE", "ACTIVE", "DATE_CREATED", "TOKEN")
VALUES (3,
        'kavkaz@kavkaz',
        'f896fdd0d117f53140364532e519fc1c58d44e7376e9fb383656b952f4583e8b',
        '446d820c-ef2f-4c00-aaac-61ba83ac8d5b',
        'Kavkaz',
        'Manager',
        '123',
        0,
        '2014-11-27 18:14:47',
        '2320c12a-eb00-421e-b264-da03beda89cc');


INSERT INTO "USER" ("ID", "EMAIL", "PASSWORD", "SALT", "FIRST_NAME", "LAST_NAME", "PHONE", "ACTIVE", "DATE_CREATED", "TOKEN")
VALUES (4,
        'krym@krym',
        'b64d2ed9657666e6f80f69c3dd06db4c6a105dfbed5f3d951caa894857b635c0',
        '941cd3bc-0773-4659-a3ec-24a367104870',
        'Krym',
        'Manager',
        '123',
        0,
        '2014-11-27 18:15:33',
        'dbf84bec-d455-4545-8764-70f5dd097f02');

ALTER TABLE "USER" ALTER COLUMN "ID" RESTART WITH 5;