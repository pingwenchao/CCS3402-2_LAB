INSERT INTO users(email, name, password) VALUES
    ('admin@gmail.com','admin','$2a$10$vKBm7ZthY6XEPOnOR0Wq0ux8v2bygAArpYpUbyvE0VwG6A.36ai9G');
INSERT INTO users(email, name, password) VALUES
    ('test@gmail.com','John','$2a$10$vKBm7ZthY6XEPOnOR0Wq0ux8v2bygAArpYpUbyvE0VwG6A.36ai9G');
INSERT INTO users(email, name, password) VALUES
    ('abc@gmail.com', 'Alex','$2a$10$vKBm7ZthY6XEPOnOR0Wq0ux8v2bygAArpYpUbyvE0VwG6A.36ai9G');
INSERT INTO users(email, name, password) VALUES
    ('def@gmail.com', 'Boss', '$2a$10$vKBm7ZthY6XEPOnOR0Wq0ux8v2bygAArpYpUbyvE0VwG6A.36ai9G');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users_roles(role_id, user_id) VALUES (1,1);
INSERT INTO users_roles(role_id, user_id) VALUES (2,2);
INSERT INTO users_roles(role_id, user_id) VALUES (2,3);
INSERT INTO users_roles(role_id, user_id) VALUES (2,4);
INSERT INTO users_roles(role_id, user_id) VALUES (1,4);