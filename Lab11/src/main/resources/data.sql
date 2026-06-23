-- PING WENCHAO 226969
-- Lab 11: Oracle Compatible Data Initialization
-- Removed MySQL-specific 'IGNORE' keyword to prevent ORA-00900 errors.

INSERT INTO department (id, name) VALUES (1, 'Information Technology');
INSERT INTO department (id, name) VALUES (2, 'Human Resources');
INSERT INTO department (id, name) VALUES (3, 'Finance');

INSERT INTO project (id, name) VALUES (1, 'AI System Development');
INSERT INTO project (id, name) VALUES (2, 'Mobile App Redesign');
INSERT INTO project (id, name) VALUES (3, 'E-Commerce Platform');