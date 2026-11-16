use proy_turismo;
SELECT
    p.idProgramacion AS idProgramacion,
    t.nombre AS NombreTour,
    t.lugar AS LugarTour,
    t.precio AS PrecioTour,
    p.fecha AS FechaSalida,
    p.horario AS HorarioSalida,
    CONCAT(pe.nombre, ' ', pe.apellido) AS NombreGuia,
    p.estado AS EstadoSalida,
    p.cupo_maximo AS CupoMaximo,
    COALESCE(SUM(CASE WHEN r.estado NOT IN ('CANCELADA','POSPUESTA')
                      THEN r.cantidad_personas END), 0) AS CupoOcupado,
    p.cupo_maximo - COALESCE(SUM(CASE WHEN r.estado NOT IN ('CANCELADA','POSPUESTA')
                      THEN r.cantidad_personas END), 0) AS CupoDisponible,
    COUNT(CASE WHEN r.estado NOT IN ('CANCELADA','POSPUESTA')
               THEN r.idRESERVA END) AS CantidadReservas

FROM `PROGRAMACIÓN_RESERVA` p

INNER JOIN TOUR t
    ON p.TOUR_idTOUR = t.idTOUR

INNER JOIN EMPLEADO e
    ON p.EMPLEADO_idEMPLEADO = e.idEMPLEADO
    AND p.EMPLEADO_PERSONA_idPERSONA = e.PERSONA_idPERSONA

INNER JOIN PERSONA pe
    ON e.PERSONA_idPERSONA = pe.idPERSONA

LEFT JOIN RESERVA r
    ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = p.idProgramacion

GROUP BY p.idProgramacion, t.nombre, t.lugar, t.precio, p.fecha, p.horario,
         pe.nombre, pe.apellido, p.estado, p.cupo_maximo

ORDER BY p.fecha ASC, p.horario ASC;