USE proy_turismo;

SELECT
    e.idEmpleado AS idEmpleado,
    p.nombre AS NombreEmpleado,
    p.apellido AS ApellidoEmpleado,
    p.DNI AS DNIEmpleado,
    r.rol AS RolEmpleado,
    p.género AS GeneroEmpleado,
    a.nombre AS NombreAgencia

FROM EMPLEADO e

INNER JOIN PERSONA p
    ON e.PERSONA_idPERSONA = p.idPERSONA

INNER JOIN AGENCIA a
    ON e.AGENCIA_idAGENCIA = a.idAGENCIA

INNER JOIN ROL_EMPLEADO r
    ON e.idEMPLEADO = r.EMPLEADO_idEMPLEADO
    AND e.PERSONA_idPERSONA = r.EMPLEADO_PERSONA_idPERSONA

ORDER BY e.idEMPLEADO ASC;
    
