USE `astore`;
SET SESSION sql_mode='NO_AUTO_VALUE_ON_ZERO';

DELETE FROM `ordered_ticket`;
DELETE FROM `order`;
DELETE FROM `supporting_document`;
DELETE FROM `ticket`;
DELETE FROM `category`;
DELETE FROM `route`;

DELETE FROM `user_has_role`;
DELETE FROM `user`;
DELETE FROM `role`;

INSERT INTO `route` (`ID`, `NAME`) VALUES (1,'kavkaz_krym');
INSERT INTO `route` (`ID`, `NAME`) VALUES (2,'krym_kavkaz');

INSERT INTO `category` (`ID`, `NAME`) VALUES (1,'man');
INSERT INTO `category` (`ID`, `NAME`) VALUES (2,'vehicle');

INSERT INTO `user` (`ID`, `EMAIL`, `PASSWORD`, `SALT`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`, `TOKEN`) VALUES (0,'example@example.com',NULL,NULL,'anonymous','anonumpus','0',0,'2014-10-18 20:00:00',NULL);
INSERT INTO `user` (`ID`, `EMAIL`, `PASSWORD`, `SALT`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`, `TOKEN`) VALUES (1,'notreal@mail.ru','54531464dc14d9ec00c3fc391445679b961c0f524ebdda09d9f4272d056f0253','17ffad89-7e96-4538-98ab-be0f01c7eb8b','Oleg','Sorokin','+7111222333',0,'2014-10-19 08:30:19',NULL);
INSERT INTO `user` (`ID`, `EMAIL`, `PASSWORD`, `SALT`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`, `TOKEN`) VALUES (2,'oleg@setco.ru','3486c878a001d07c04e073d0b669c755e36af506634231234cfe4e78216073ae','2aa8162c-0d5d-4611-aa10-5e668e190feb','Олег','Сорокин','123',0,'2014-10-27 07:42:29','0e7c5997-6768-4bec-aab4-6e9f2769f293');
INSERT INTO `user` (`ID`, `EMAIL`, `PASSWORD`, `SALT`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `ACTIVE`, `DATE_CREATED`, `TOKEN`) VALUES (3,'aa@aa','69192ef0eb6421079a32565aba3c6a4424b44f4290f02c3b3e7962ae6965c664','3dae84d9-e7e9-499c-ada9-bea55851e7b8','Дмитрий','Кулаков','1111',0,'2014-10-27 11:04:41','6919807b-4b7c-48f0-936a-69e075d23794');

INSERT INTO `role` (`ID`, `NAME`) VALUES (1,'admin');
INSERT INTO `role` (`ID`, `NAME`) VALUES (2,'user');

INSERT INTO `user_has_role` (`USER_ID`, `ROLE_ID`) VALUES (1,1);
INSERT INTO `user_has_role` (`USER_ID`, `ROLE_ID`) VALUES (1,2);

INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (1,'adult',162.00,'','2014-12-19 11:36:38','',1);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (2,'child1',0.00,'','2014-12-19 11:36:38','',1);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (3,'child2',81.00,'','2014-12-19 11:36:38','',1);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (4,'child3',162.00,'','2014-12-19 11:36:45','',1);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (5,'avto1',1190.00,'','2014-12-19 11:36:38','',2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (6,'avto2',1688.00,'','2014-12-19 11:36:38','',2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (7,'avto3',2486.00,'','2014-12-19 08:35:41',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (8,'trailer1',623.00,'','2014-12-19 08:35:02',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (9,'trailer2',1069.00,'','2014-12-19 08:35:02',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (10,'trailer3',1704.00,'','2014-12-19 08:35:02',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (11,'trailer4',2267.00,'','2014-12-19 08:35:02',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (12,'bus1',2632.00,'','2014-12-19 08:31:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (13,'bus2',3919.00,'','2014-12-19 08:31:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (14,'bus3',5057.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (15,'bus4',5057.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (16,'trailer101',2401.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (17,'trailer102',3073.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (18,'trailer103',3887.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (19,'trailer104',5733.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (20,'trailer105',19470.00,'','2014-12-19 08:32:57',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (21,'skuter',462.00,'','2014-12-19 08:27:09',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (22,'tricycle',514.00,'','2014-12-19 08:27:09',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (23,'skuter1',623.00,'','2014-12-19 08:27:09',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (24,'atv',547.00,'','2014-12-19 08:27:09',NULL,2);
INSERT INTO `ticket` (`ID`, `NAME`, `PRICE`, `DESCRIPTION`, `LAST_UPDATE`, `DATA_LABEL`, `CATEGORY_ID`) VALUES (25,'bike',109.00,'','2014-12-19 08:27:09',NULL,2);

INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (1,'Паспорт гражданина Российской федерации',1,0);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (2,'Паспорт моряка',1,1);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (3,'Общегражданский заграничный паспорт гражданин',1,2);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (4,'Паспорт иностранного гражданина',1,3);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (5,'Свидетельство о рождении',1,4);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (6,'Удостоверение личности военнослужащего',1,5);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (7,'Удостоверение личности лица без гражданства',1,6);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (8,'Временное удостоверение личности, выдаваемое ',1,7);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (9,'Военный билет военнослужащего срочной службы',1,8);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (10,'Вид на жительство иностранного гражданина или',1,9);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (11,'Справка об освобождении из мест лишения свобо',1,10);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (12,'Паспорт гражданина СССР',1,11);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (13,'Паспорт дипломатический',1,12);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (14,'Паспорт служебный (кроме паспорта моряка и ди',1,13);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (15,'Свидетельство о возвращении из стран СНГ',1,14);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (16,'Справка об утере паспорта',1,15);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (17,'Удостоверение депутата',1,16);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (25,'СТС',2,25);
INSERT INTO `supporting_document` (`ID`, `NAME`, `CATEGORY_ID`, `DOC_TYPE`) VALUES (26,'ПТС',2,26);

