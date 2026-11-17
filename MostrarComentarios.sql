USE PROY_TURISMO;

SELECT
    c.idCOMENTARIO AS idComentario,
    c.fecha AS FechaComentario,
    c.tipo AS TipoComentario,
    c.contenido AS ContenidoComentario,
    tour.nombre AS NombreTour,
    p.nombre AS NombreTurista,
    p.apellido AS ApellidoTurista,
    a.nombre AS NombreAgencia
    

FROM COMENTARIO c

INNER JOIN TURISTA t
    ON c.TURISTA_idTURISTA = t.idTURISTA

INNER JOIN PERSONA p
    ON t.PERSONA_idPERSONA = p.idPERSONA

INNER JOIN AGENCIA a
    ON c.AGENCIA_idAGENCIA = a.idAGENCIA

INNER JOIN RESERVA r
    ON r.TURISTA_TITULAR_idTURISTA = t.idTURISTA

INNER JOIN PROGRAMACIÓN_RESERVA pr
    ON r.`PROGRAMACIÓN_RESERVA_idProgramacion` = pr.idProgramacion

INNER JOIN TOUR tour
    ON pr.TOUR_idTOUR = tour.idTOUR

ORDER BY c.fecha DESC;

