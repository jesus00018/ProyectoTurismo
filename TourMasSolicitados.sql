USE PROY_TURISMO;

SELECT
    t.idTOUR AS ID_Tour,
    t.nombre AS Nombre_Tour,

    COUNT(DISTINCT r.idRESERVA) AS Cantidad_Reservas,

    COALESCE(SUM(r.cantidad_personas), 0) AS Total_Personas

FROM TOUR t

LEFT JOIN PROGRAMACIÓN_RESERVA pr
    ON t.idTOUR = pr.TOUR_idTOUR

LEFT JOIN RESERVA r
    ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion
    AND r.estado <> 'CANCELADA'

GROUP BY
    t.idTOUR,
    t.nombre

ORDER BY
    Cantidad_Reservas DESC,
    Total_Personas DESC;