USE PROY_TURISMO;

SELECT
    r.idRESERVA AS ID_Reserva,

    p.nombre AS Nombre_Turista,
    p.apellido AS Apellido_Turista,
    p.DNI AS DNI_Turista,

    t.nombre AS Nombre_Tour,
    t.precio AS Precio_Por_Persona,

    r.cantidad_personas AS Cantidad_Personas,

    (t.precio * r.cantidad_personas) AS Precio_Total,

    pr.fecha AS Fecha_Tour,
    pr.horario AS Horario_Tour,

    r.estado AS Estado_Reserva

FROM RESERVA r

INNER JOIN TURISTA tu
    ON r.TURISTA_TITULAR_idTURISTA = tu.idTURISTA

INNER JOIN PERSONA p
    ON tu.PERSONA_idPERSONA = p.idPERSONA

INNER JOIN PROGRAMACIÓN_RESERVA pr
    ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion

INNER JOIN TOUR t
    ON pr.TOUR_idTOUR = t.idTOUR

ORDER BY pr.fecha DESC, p.apellido;