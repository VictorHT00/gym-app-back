INSERT INTO costo_planes(descripcion, costo) VALUES('2023', 300);
INSERT INTO costo_planes(descripcion, costo) VALUES('2024', 500);
INSERT INTO costo_planes(descripcion, costo) VALUES('dia', 100);
INSERT INTO costo_planes(descripcion, costo) VALUES('Insc', 50);

INSERT INTO usuarios (username, password, enabled, nombre, apellido) VALUES ('admin', '$2a$10$px4z9snfy78mEsjavNdT1Oi/i0ufJFhYUAGXbLWySJO4K5ScuW4/6', 1, 'Miguel', 'Islas');
INSERT INTO usuarios (username, password, enabled, nombre, apellido) VALUES ('alan', '$2a$10$2G6/5HAu4eF0YmRXJRP0GukGqoMMmkmJWF4OeScVE7MBBrgj06cqC', 1, 'Alan', 'Barraza');

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 1);

INSERT INTO clientes(no_cliente, nombre, apellido, telefono, fecha_inscripcion, meses_suscripcion, siguiente_pago, pagos_consecutivos, costo_id) VALUES (20, 'Laura', 'Gonzalez Lopez', '771234567', '2023-10-15', 1, '2023-12-15', 0, 1);
INSERT INTO clientes(no_cliente, nombre, apellido, telefono, fecha_inscripcion, meses_suscripcion, siguiente_pago, pagos_consecutivos, costo_id) VALUES (10, 'Juan', 'Perez Martinez', '771234580', '2023-11-21', 1, '2024-12-21', 1, 1);
INSERT INTO clientes(no_cliente, nombre, apellido, telefono, fecha_inscripcion, meses_suscripcion, siguiente_pago, pagos_consecutivos, costo_id) VALUES (40, 'Carlos', 'Ramirez Rojas', '771234529', '2023-12-20', 1, '2023-12-20', 1, 1);
INSERT INTO clientes(no_cliente, nombre, apellido, telefono, fecha_inscripcion, meses_suscripcion, siguiente_pago, pagos_consecutivos, costo_id) VALUES (30, 'Jose Roberto', 'Soto Soto', '771234521', '2024-01-25', 1, null, 1, 1);


INSERT INTO mensualidades (cliente_id, fecha_pago, dias, meses, cobro, total) VALUES (1, '2023-10-16',0, 1, 300.0, 300.0);

INSERT INTO bitacoras(cliente_id, fecha, estado, operacion) VALUES(1, '2023-12-16', 1, 3);