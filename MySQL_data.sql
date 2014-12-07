CREATE DATABASE IF NOT EXISTS `astore`;
USE `astore`;
INSERT INTO `category` (`ID`, `NAME`) VALUES (1,'man');
INSERT INTO `category` (`ID`, `NAME`) VALUES (2,'vehicle');

INSERT INTO `route` (`ID`, `NAME`) VALUES (1,'kavkaz_krym');
INSERT INTO `route` (`ID`, `NAME`) VALUES (2,'krym_kavkaz');

INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (1,'adult',100.00,'','2014-09-22 01:29:51',1,'');
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (2,'invalid',50.00,'','2014-09-22 01:29:51',1,'');
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (3,'child',30.00,'','2014-09-22 01:29:51',1,'');
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (4,'mini_car',150.00,'Length < 3m','2014-09-22 01:30:08',2,'');
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (5,'car',200.00,'','2014-09-22 01:29:51',2,'');
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `CATEGORY_ID`, `DATA_LABEL`) VALUES (6,'truck',500.00,'','2014-09-22 01:29:51',2,'');

INSERT INTO `role` (`ID`, `NAME`) VALUES (1,'admin');
INSERT INTO `role` (`ID`, `NAME`) VALUES (2,'user');
SET SESSION sql_mode='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `user` (`ID`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`) VALUES (0,'example@example.com','anonymous','anonumpus','0',0,'2014-10-19 00:00:00');
INSERT INTO `user` (`ID`, `EMAIL`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`, `SALT`) VALUES (1,'notreal@mail.ru','54531464dc14d9ec00c3fc391445679b961c0f524ebdda09d9f4272d056f0253','Oleg','Sorokin','+7111222333',0,'2014-10-19 12:30:19', '17ffad89-7e96-4538-98ab-be0f01c7eb8b');
INSERT INTO `user_has_role` (`USER_ID`, `ROLE_ID`) VALUES (1,1);
INSERT INTO `user_has_role` (`USER_ID`, `ROLE_ID`) VALUES (1,2);

INSERT INTO `supporting_document` (`ID`, `NAME`) VALUES (1,'rf_passport');
INSERT INTO `supporting_document` (`ID`, `NAME`) VALUES (2,'passport');

INSERT INTO `supporting_document_field` (`ID`, `NAME`, `REGEXP`, `SUPPORTING_DOCUMENT_ID`) VALUES (1, 'number', '', 1);
INSERT INTO `supporting_document_field` (`ID`, `NAME`, `REGEXP`, `SUPPORTING_DOCUMENT_ID`) VALUES (2, 'date_of_issue', '', 1);

INSERT INTO `supporting_document_field` (`ID`, `NAME`, `REGEXP`, `SUPPORTING_DOCUMENT_ID`) VALUES (3, 'number', '', 2);
INSERT INTO `supporting_document_field` (`ID`, `NAME`, `REGEXP`, `SUPPORTING_DOCUMENT_ID`) VALUES (4, 'date_of_issue', '', 2);
INSERT INTO `supporting_document_field` (`ID`, `NAME`, `REGEXP`, `SUPPORTING_DOCUMENT_ID`) VALUES (5, 'valid_until', '', 2);

INSERT INTO `ticket_has_supporting_document` (`TICKET_ID`, `SUPPORTING_DOCUMENT_ID`) VALUES (1, 1);
INSERT INTO `ticket_has_supporting_document` (`TICKET_ID`, `SUPPORTING_DOCUMENT_ID`) VALUES (1, 2);
INSERT INTO `ticket_has_supporting_document` (`TICKET_ID`, `SUPPORTING_DOCUMENT_ID`) VALUES (2, 1);
INSERT INTO `ticket_has_supporting_document` (`TICKET_ID`, `SUPPORTING_DOCUMENT_ID`) VALUES (2, 2);
