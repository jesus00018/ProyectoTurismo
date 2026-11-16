USE PROY_TURISMO;

-- ==========================================
-- 1. AGENCIA
-- ==========================================

INSERT INTO AGENCIA
(dirección, nombre, email)
VALUES
('Av. San Martín 250', 'Turismo Patagonia', 'info@patagonia.com'),
('Belgrano 450', 'Aventuras del Sur', 'info@aventuras.com');


-- ==========================================
-- 2. PERSONA
-- ==========================================

INSERT INTO PERSONA
(nombre, apellido, DNI, email, nacionalidad,
 provincia, ciudad, género, dirección)
VALUES
('Carlos', 'Gómez', 30111222, 'carlos@gmail.com',
 'Argentina', 'Buenos Aires', 'La Plata', 'Masculino', 'Calle 10 123'),

('Laura', 'Pérez', 31222333, 'laura@gmail.com',
 'Argentina', 'Buenos Aires', 'Mar del Plata', 'Femenino', 'Av. Colón 456'),

('Martín', 'López', 32333444, 'martin@gmail.com',
 'Argentina', 'Río Negro', 'Bariloche', 'Masculino', 'Mitre 789'),

('Ana', 'Rodríguez', 33444555, 'ana@gmail.com',
 'Argentina', 'Neuquén', 'San Martín de los Andes', 'Femenino', 'Roca 321'),

('Pedro', 'Fernández', 34555666, 'pedro@gmail.com',
 'Argentina', 'Chubut', 'Puerto Madryn', 'Masculino', 'Belgrano 654'),

('Sofía', 'Martínez', 35666777, 'sofia@gmail.com',
 'Argentina', 'Buenos Aires', 'La Plata', 'Femenino', 'Diagonal 80 987'),

('Diego', 'Ramírez', 36777888, 'diego@gmail.com',
 'Argentina', 'Santa Cruz', 'El Calafate', 'Masculino', 'San Martín 147');


-- ==========================================
-- 3. TELÉFONO_PERSONA
-- ==========================================

INSERT INTO `TELÉFONO_PERSONA`
(idTELÉFONO_PERSONA, teléfono, PERSONA_idPERSONA)
VALUES
(1, 221445566, 1),
(2, 223556677, 2),
(3, 294455667, 3),
(4, 299566778, 4),
(5, 280477889, 5),
(6, 221588990, 6),
(7, 290266778, 7);


-- ==========================================
-- 4. TELÉFONO_AGENCIA
-- ==========================================

INSERT INTO `TELÉFONO_AGENCIA`
(teléfono, idTELÉFONO_AGENCIA, AGENCIA_idAGENCIA)
VALUES
(1144556677, 1, 1),
(1144667788, 2, 2);


-- ==========================================
-- 5. EMPLEADO
-- ==========================================

INSERT INTO EMPLEADO
(usuario, contraseña, AGENCIA_idAGENCIA, PERSONA_idPERSONA)
VALUES
('cgomez', 'clave123', 1, 1),
('lperez', 'clave456', 1, 2),
('smartinez', 'clave789', 2, 6);


-- ==========================================
-- 6. ROL_EMPLEADO
-- Un solo rol por empleado
-- ==========================================

INSERT INTO ROL_EMPLEADO
(rol, EMPLEADO_idEMPLEADO, EMPLEADO_PERSONA_idPERSONA)
VALUES
('Administrador', 1, 1),
('Guía turístico', 2, 2),
('Gerente', 3, 6);


-- ==========================================
-- 7. TURISTA
-- ==========================================

INSERT INTO TURISTA
(patología, incapacidad, alergía, contraseña, PERSONA_idPERSONA)
VALUES
(NULL, NULL, NULL, 'turista123', 3),
(NULL, NULL, 'Polen', 'turista456', 4),
(NULL, NULL, NULL, 'turista789', 5),
('Asma', NULL, NULL, 'turista321', 7);


-- ==========================================
-- 8. TOUR
-- ==========================================

INSERT INTO TOUR
(nombre, descripción, lugar, duración, precio,
 disponibilidad, AGENCIA_idAGENCIA)
VALUES
('Excursión al lago',
 'Paseo guiado por el lago y sus alrededores',
 'Bariloche', '04:00:00', 25000.00, 'Disponible', 1),

('Circuito de montaña',
 'Recorrido turístico por los paisajes de montaña',
 'San Martín de los Andes', '06:00:00', 35000.00, 'Disponible', 1),

('Visita a cascada',
 'Caminata guiada hasta una cascada natural',
 'El Bolsón', '03:00:00', 18000.00, 'Disponible', 2),

('City tour',
 'Recorrido por los principales atractivos de la ciudad',
 'El Calafate', '02:00:00', 12000.00, 'Disponible', 2);


-- ==========================================
-- 9. PROGRAMACIÓN_RESERVA
-- ==========================================

INSERT INTO `PROGRAMACIÓN_RESERVA`
(fecha, horario, cupo_maximo, estado,
 EMPLEADO_idEMPLEADO, EMPLEADO_PERSONA_idPERSONA, TOUR_idTOUR)
VALUES
('2026-12-01', '09:00', 15, 'ABIERTA', 2, 2, 1),
('2026-12-02', '08:30', 20, 'ABIERTA', 2, 2, 2),
('2026-12-03', '10:00', 15, 'ABIERTA', 1, 1, 3),
('2026-12-04', '14:00', 12, 'ABIERTA', 3, 6, 4),
('2026-12-05', '09:30', 25, 'ABIERTA', 3, 6, 1);


-- ==========================================
-- 10. RESERVA
-- ==========================================

INSERT INTO RESERVA
(cod_reserva, cantidad_personas, estado,
 PROGRAMACIÓN_RESERVA_idProgramacion,
 TURISTA_TITULAR_idTURISTA)
VALUES
(1001, 2, 'Confirmada', 1, 1),
(1002, 1, 'Pendiente', 2, 3),
(1003, 3, 'Confirmada', 3, 1),
(1004, 2, 'Confirmada', 4, 2),
(1005, 1, 'Pendiente', 5, 4);


-- ==========================================
-- 11. RESERVA_TURISTA
-- Turistas que participan en cada reserva
-- ==========================================

INSERT INTO RESERVA_TURISTA
(RESERVA_idRESERVA, TURISTA_idTURISTA, TURISTA_PERSONA_idPERSONA)
VALUES
(1, 1, 3),
(1, 2, 4),
(2, 3, 5),
(3, 1, 3),
(3, 4, 7),
(3, 2, 4),
(4, 2, 4),
(4, 3, 5),
(5, 4, 7);


-- ==========================================
-- 12. COMENTARIO
-- ==========================================

INSERT INTO COMENTARIO
(fecha, contenido, tipo, TURISTA_idTURISTA, AGENCIA_idAGENCIA)
VALUES
('2026-12-02', 'Muy buena experiencia y excelente atención',
 'OPINION', 1, 1),

('2026-12-03', 'El recorrido comenzó con demora',
 'QUEJA', 2, 1),

('2026-12-04', 'Sería bueno agregar más paradas al recorrido',
 'SUGERENCIA', 3, 2);


-- ==========================================
-- VERIFICAR LOS DATOS
-- ==========================================

SELECT * FROM AGENCIA;
SELECT * FROM PERSONA;
SELECT * FROM `TELÉFONO_PERSONA`;
SELECT * FROM `TELÉFONO_AGENCIA`;
SELECT * FROM EMPLEADO;
SELECT * FROM ROL_EMPLEADO;
SELECT * FROM TURISTA;
SELECT * FROM TOUR;
SELECT * FROM `PROGRAMACIÓN_RESERVA`;
SELECT * FROM RESERVA;
SELECT * FROM RESERVA_TURISTA;
SELECT * FROM COMENTARIO;