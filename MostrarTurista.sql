use proy_turismo;
/*1.Turistas ver sus datos personales*/
SELECT 
    t.idTURISTA,
    p.nombre,
    p.apellido,
    p.DNI,
    p.email,
    p.nacionalidad,
    p.provincia,
    p.ciudad,
    p.género,
    t.patología,
    t.alergía
FROM TURISTA t
INNER JOIN PERSONA p
    ON t.PERSONA_idPERSONA = p.idPERSONA;

    
