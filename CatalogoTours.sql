USE PROY_TURISMO;

SELECT
    t.idTOUR AS id_tour,
    t.nombre AS nombre_tour,
    t.precio AS precio,
    pr.fecha AS fecha,
    pr.horario AS horario,
    pr.cupo_maximo AS cupo_maximo,

    COALESCE(
        SUM(
            CASE
                WHEN r.idRESERVA IS NOT NULL
                     AND r.estado <> 'CANCELADA'
                THEN r.cantidad_personas
                ELSE 0
            END
        ), 0
    ) AS cupos_reservados,

    pr.cupo_maximo - COALESCE(
        SUM(
            CASE
                WHEN r.idRESERVA IS NOT NULL
                     AND r.estado <> 'CANCELADA'
                THEN r.cantidad_personas
                ELSE 0
            END
        ), 0
    ) AS cupos_disponibles

FROM PROGRAMACIÓN_RESERVA pr

INNER JOIN TOUR t
    ON t.idTOUR = pr.TOUR_idTOUR

LEFT JOIN RESERVA r
    ON r.PROGRAMACIÓN_RESERVA_idProgramacion = pr.idProgramacion

GROUP BY
    pr.idProgramacion,
    t.idTOUR,
    t.nombre,
    t.precio,
    pr.fecha,
    pr.horario,
    pr.cupo_maximo

ORDER BY
    t.nombre,
    pr.fecha,
    pr.horario;