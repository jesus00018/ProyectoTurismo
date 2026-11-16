USE PROY_TURISMO;

SELECT
    c.idCOMENTARIO AS idComentario,
    c.fecha AS FechaComentario,
    c.tipo AS TipoComentario,
    c.contenido AS ContenidoComentario,
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

ORDER BY c.fecha DESC;