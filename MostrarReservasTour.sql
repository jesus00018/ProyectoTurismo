use proy_turismo;
SELECT t.idTOUR         AS id_tour,
       t.nombre         AS nombre_tour,
       pr.fecha         AS fecha,
       pr.horario       AS horario,
       pr.estado        AS estado,
       pr.cupo_maximo   AS cupo_maximo,
       COALESCE(SUM(r.cantidad_personas), 0)                  AS cupos_ocupados,
       pr.cupo_maximo - COALESCE(SUM(r.cantidad_personas), 0) AS cupos_disponibles
FROM PROGRAMACIÓN_RESERVA pr
JOIN TOUR t ON t.idTOUR = pr.TOUR_idTOUR
LEFT JOIN RESERVA r
       ON r.PROGRAMACIÓN_RESERVA_idProgramacion = pr.idProgramacion
      AND r.estado <> 'Cancelada'
GROUP BY pr.idProgramacion, t.idTOUR, t.nombre, pr.fecha,
         pr.horario, pr.estado, pr.cupo_maximo
ORDER BY pr.fecha, pr.horario;