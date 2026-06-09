-- PING WENCHAO 226969
-- Pre-populate Departments safely without causing duplicate key exceptions
INSERT IGNORE INTO department (id, name) VALUES (1, 'Information Technology');
INSERT IGNORE INTO department (id, name) VALUES (2, 'Human Resources');
INSERT IGNORE INTO department (id, name) VALUES (3, 'Finance');

-- Pre-populate Projects safely without causing duplicate key exceptions
INSERT IGNORE INTO project (id, name) VALUES (1, 'AI System Development');
INSERT IGNORE INTO project (id, name) VALUES (2, 'Mobile App Redesign');
INSERT IGNORE INTO project (id, name) VALUES (3, 'E-Commerce Platform');