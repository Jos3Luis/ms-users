INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES 
  ('17574e7b-2e3e-4aaf-8072-5e6e33d50abf','Jose Luis Quispe', 'jose@quispe.org', 'hunter2',
   CURRENT_TIMESTAMP, NULL, NULL, 'sample-token', TRUE);

INSERT INTO phones (id, number, citycode, countrycode, user_id)
VALUES 
  ('c0a89101-0000-0000-0000-660005500001', '987654321', '1', '56', '17574e7b-2e3e-4aaf-8072-5e6e33d50abf');