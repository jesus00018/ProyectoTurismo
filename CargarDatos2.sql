
USE PROY_TURISMO;

START TRANSACTION;

-- =====================================================
-- 1. AGENCIAS
-- =====================================================

INSERT INTO AGENCIA (dirección, nombre, email)
VALUES
('Av. Libertador 850', 'Explora Argentina', 'info@exploraargentina.com'),
('San Martín 1200', 'Viajes del Mundo', 'contacto@viajesdelmundo.com');

SET @agencia1 = (
    SELECT idAGENCIA FROM AGENCIA
    WHERE email = 'info@exploraargentina.com'
);

SET @agencia2 = (
    SELECT idAGENCIA FROM AGENCIA
    WHERE email = 'contacto@viajesdelmundo.com'
);


-- =====================================================
-- 2. PERSONAS
-- =====================================================

INSERT INTO PERSONA
(nombre, apellido, DNI, email, nacionalidad, provincia, ciudad, `género`, dirección)
VALUES
('Federico', 'Sánchez', 40111222, 'federico@gmail.com', 'Argentina', 'Buenos Aires', 'La Plata', 'Masculino', 'Calle 12 450'),
('Camila', 'Torres', 41222333, 'camila@gmail.com', 'Argentina', 'Buenos Aires', 'Mar del Plata', 'Femenino', 'Av. Colón 750'),
('Luciano', 'Díaz', 42333444, 'luciano@gmail.com', 'Argentina', 'Mendoza', 'Mendoza', 'Masculino', 'San Martín 350'),
('Valentina', 'Castro', 43444555, 'valentina@gmail.com', 'Argentina', 'Córdoba', 'Villa Carlos Paz', 'Femenino', 'Belgrano 820'),
('Gabriel', 'Morales', 44555666, 'gabriel@gmail.com', 'Argentina', 'Río Negro', 'Bariloche', 'Masculino', 'Moreno 650'),
('Florencia', 'Acosta', 45666777, 'florencia@gmail.com', 'Argentina', 'Neuquén', 'Neuquén', 'Femenino', 'Roca 220');

SET @p1 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 40111222);
SET @p2 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 41222333);
SET @p3 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 42333444);
SET @p4 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 43444555);
SET @p5 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 44555666);
SET @p6 = (SELECT idPERSONA FROM PERSONA WHERE DNI = 45666777);


-- =====================================================
-- 3. TELÉFONOS DE PERSONAS
-- =====================================================

INSERT INTO `TELÉFONO_PERSONA`
(idTELÉFONO_PERSONA, teléfono, PERSONA_idPERSONA)
VALUES
(900001, 2215123456, @p1),
(900002, 2235123456, @p2),
(900003, 2615123456, @p3),
(900004, 3541512345, @p4),
(900005, 2945123456, @p5),
(900006, 2995123456, @p6);


-- =====================================================
-- 4. TELÉFONOS DE AGENCIAS
-- =====================================================

INSERT INTO `TELÉFONO_AGENCIA`
(teléfono, idTELÉFONO_AGENCIA, AGENCIA_idAGENCIA)
VALUES
(2214556677, 900001, @agencia1),
(2234667788, 900002, @agencia2);


-- =====================================================
-- 5. EMPLEADOS
-- =====================================================

INSERT INTO EMPLEADO
(usuario, contraseña, AGENCIA_idAGENCIA, PERSONA_idPERSONA)
VALUES
('federico_admin', 'clave123', @agencia1, @p1),
('camila_guia', 'clave456', @agencia2, @p2);

SET @e1 = (
    SELECT idEMPLEADO FROM EMPLEADO
    WHERE usuario = 'federico_admin'
);

SET @e2 = (
    SELECT idEMPLEADO FROM EMPLEADO
    WHERE usuario = 'camila_guia'
);


-- =====================================================
-- 6. TURISTAS
-- =====================================================

INSERT INTO TURISTA
(patología, incapacidad, alergía, contraseña, PERSONA_idPERSONA)
VALUES
(NULL, NULL, NULL, 'turista123', @p3),
(NULL, NULL, 'Maní', 'turista456', @p4),
('Asma', NULL, NULL, 'turista789', @p5),
(NULL, NULL, NULL, 'turista321', @p6);

SET @t1 = (SELECT idTURISTA FROM TURISTA WHERE PERSONA_idPERSONA = @p3);
SET @t2 = (SELECT idTURISTA FROM TURISTA WHERE PERSONA_idPERSONA = @p4);
SET @t3 = (SELECT idTURISTA FROM TURISTA WHERE PERSONA_idPERSONA = @p5);
SET @t4 = (SELECT idTURISTA FROM TURISTA WHERE PERSONA_idPERSONA = @p6);


-- =====================================================
-- 7. ROLES DE EMPLEADOS
-- Un empleado tiene un solo rol en esta carga.
-- =====================================================

INSERT INTO ROL_EMPLEADO
(rol, EMPLEADO_idEMPLEADO, EMPLEADO_PERSONA_idPERSONA)
VALUES
('Administrador', @e1, @p1),
('Guía turístico', @e2, @p2);


-- =====================================================
-- 8. TOURS
-- =====================================================

INSERT INTO TOUR
(nombre, descripción, lugar, duración, precio, disponibilidad, AGENCIA_idAGENCIA)
VALUES
('Excursión al Cerro de los Siete Colores',
 'Recorrido turístico por los paisajes de Purmamarca',
 'Purmamarca', '05:00:00', 45000.00, 'Disponible', @agencia1),

('Navegación por el Canal Beagle',
 'Paseo en barco por el canal y sus alrededores',
 'Ushuaia', '03:00:00', 65000.00, 'Disponible', @agencia2),

('Visita al Parque Nacional',
 'Excursión guiada por senderos naturales',
 'Iguazú', '06:00:00', 55000.00, 'Disponible', @agencia1);

SET @tour1 = (
    SELECT idTOUR FROM TOUR
    WHERE nombre = 'Excursión al Cerro de los Siete Colores'
);

SET @tour2 = (
    SELECT idTOUR FROM TOUR
    WHERE nombre = 'Navegación por el Canal Beagle'
);

SET @tour3 = (
    SELECT idTOUR FROM TOUR
    WHERE nombre = 'Visita al Parque Nacional'
);


-- =====================================================
-- 9. PROGRAMACIÓN DE RESERVAS
-- =====================================================

INSERT INTO `PROGRAMACIÓN_RESERVA`
(fecha, horario, cupo_maximo, estado,
 EMPLEADO_idEMPLEADO, EMPLEADO_PERSONA_idPERSONA, TOUR_idTOUR)
VALUES
('2026-12-10', '09:00', 15, 'ABIERTA', @e1, @p1, @tour1),
('2026-12-11', '10:00', 12, 'ABIERTA', @e2, @p2, @tour2),
('2026-12-12', '08:30', 20, 'ABIERTA', @e1, @p1, @tour3),
('2026-12-15', '09:00', 18, 'ABIERTA', @e2, @p2, @tour1);

SET @prog1 = (
    SELECT idProgramacion FROM `PROGRAMACIÓN_RESERVA`
    WHERE fecha = '2026-12-10' AND TOUR_idTOUR = @tour1
);

SET @prog2 = (
    SELECT idProgramacion FROM `PROGRAMACIÓN_RESERVA`
    WHERE fecha = '2026-12-11' AND TOUR_idTOUR = @tour2
);

SET @prog3 = (
    SELECT idProgramacion FROM `PROGRAMACIÓN_RESERVA`
    WHERE fecha = '2026-12-12' AND TOUR_idTOUR = @tour3
);

SET @prog4 = (
    SELECT idProgramacion FROM `PROGRAMACIÓN_RESERVA`
    WHERE fecha = '2026-12-15' AND TOUR_idTOUR = @tour1
);


-- =====================================================
-- 10. RESERVAS
-- =====================================================

INSERT INTO RESERVA
(cod_reserva, cantidad_personas, estado,
 `PROGRAMACIÓN_RESERVA_idProgramacion`,
 `TURISTA_TITULAR_idTURISTA`)
VALUES
(9001, 2, 'CONFIRMADA', @prog1, @t1),
(9002, 3, 'CONFIRMADA', @prog1, @t2),
(9003, 1, 'PENDIENTE', @prog2, @t3),
(9004, 2, 'CONFIRMADA', @prog3, @t4),
(9005, 3, 'CONFIRMADA', @prog4, @t1),
(9006, 1, 'CANCELADA', @prog4, @t4);


-- =====================================================
-- 11. TURISTAS PARTICIPANTES DE CADA RESERVA
-- =====================================================

SET @r1 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9001);
SET @r2 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9002);
SET @r3 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9003);
SET @r4 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9004);
SET @r5 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9005);
SET @r6 = (SELECT idRESERVA FROM RESERVA WHERE cod_reserva = 9006);

INSERT INTO RESERVA_TURISTA
(RESERVA_idRESERVA, TURISTA_idTURISTA, TURISTA_PERSONA_idPERSONA)
VALUES
(@r1, @t1, @p3),
(@r1, @t2, @p4),

(@r2, @t2, @p4),
(@r2, @t3, @p5),
(@r2, @t4, @p6),

(@r3, @t3, @p5),

(@r4, @t4, @p6),
(@r4, @t1, @p3),

(@r5, @t1, @p3),
(@r5, @t2, @p4),
(@r5, @t3, @p5),

(@r6, @t4, @p6);


-- =====================================================
-- 12. COMENTARIOS
-- =====================================================

INSERT INTO COMENTARIO
(fecha, contenido, tipo, TURISTA_idTURISTA, AGENCIA_idAGENCIA)
VALUES
('2026-12-13',
 'Excelente experiencia, el guía fue muy amable.',
 'OPINION', @t1, @agencia1),

('2026-12-14',
 'El recorrido estuvo muy bueno, pero faltó tiempo.',
 'SUGERENCIA', @t2, @agencia2),

('2026-12-15',
 'La excursión comenzó con demora.',
 'QUEJA', @t3, @agencia1),

('2026-12-16',
 'Muy buena organización y atención.',
 'OPINION', @t4, @agencia2);


COMMIT;


-- =====================================================
-- 13. VERIFICACIÓN DE TODAS LAS TABLAS
-- =====================================================

SELECT * FROM AGENCIA;
SELECT * FROM PERSONA;
SELECT * FROM `TELÉFONO_PERSONA`;
SELECT * FROM `TELÉFONO_AGENCIA`;
SELECT * FROM EMPLEADO;
SELECT * FROM TURISTA;
SELECT * FROM ROL_EMPLEADO;
SELECT * FROM TOUR;
SELECT * FROM `PROGRAMACIÓN_RESERVA`;
SELECT * FROM RESERVA;
SELECT * FROM RESERVA_TURISTA;
SELECT * FROM COMENTARIO;
