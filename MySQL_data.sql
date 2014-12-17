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

INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (0, 'Паспорт гражданиа Российской Федерации', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (1, 'Паспорт моряка', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (2, 'Общегражданский заграничный паспорт гражданин', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (3, 'Паспорт иностранного гражданина', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (4, 'Свидетельство о рождении', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (5, 'Удостоверение личности военнослужащего', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (6, 'Удостоверение личности лица без гражданства', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (7, 'Временное удостоверение личности, выдаваемое ', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (8, 'Военный билет военнослужащего срочной службы', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (9, 'Вид на жительство иностранного гражданина или', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (10, 'Справка об освобождении из мест лишения свобо', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (11, 'Паспорт гражданина СССР', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (12, 'Паспорт дипломатический', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (13, 'Паспорт служебный (кроме паспорта моряка и ди', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (14, 'Свидетельство о возвращении из стран СНГ', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (15, 'Справка об утере паспорта', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (16, 'Удостоверение депутата', 1);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (19, 'СТС', 2);
INSERT INTO `supporting_document` (`DOC_TYPE`, `NAME`, `CATEGORY_ID`) VALUES (20, 'ПТС', 2);