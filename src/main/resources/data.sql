DELETE FROM partner_mapping;
DELETE FROM customers;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO customers (login, password, balance, last_name, first_name, middle_name, enabled)
VALUES
  ('abc', '$2a$10$//Qolv9hoJK1coC0rkDo4OdvWOtc8em6qJ.hdqnOyJT.SMiEj16Xq', 1000, 'Иванов', 'Иван',
   'Иванович', TRUE),
  ('def', '$2a$10$Fue5h7hmiSVcBRQLREzMfefyhZfv.AUn0OUuZUpfvu/zUnreulz1e', 5000, 'Johnson', 'John',
   'Jay', TRUE),
  ('ghi', '$2a$10$yGcdpygkCN1Ei0.HM2jDhuUb2fGsRlS2A6syb0HX2W2ydXw7atz9W', 200, 'Xian', 'Xion',
   'Xian', TRUE),
  ('xyz', '$2a$10$OV4/veNOnydoaGwL3SSuuODovkU.yLEuojWJlzCMpyoOlntCPOCsa', 0, '', '', '', FALSE);

INSERT INTO partner_mapping (customer_id, partner_id, partner_customer_id, last_name, first_name, middle_name, avatar_image)
VALUES
  (100000, 'id1', 'sid1', 'Иванов', 'Иван', 'Иванович', NULL),
  (100000, 'id2', 'sid2', 'Иванов', 'Иван', NULL, NULL),
  (100000, 'id3', 'sid3', NULL, 'Иван', 'Иванович', X'010001000100010001'),
  (100001, 'id1', 'sid4', 'Johnson', 'Johny', 'CoolNoy', X'010001000100010001'),
  (100001, 'id5', 'sid5', 'Cool', 'Prog', 'Master', X'010001000100010001'),
  (100002, 'id4', 'sid6', 'China', NULL, NULL, NULL);