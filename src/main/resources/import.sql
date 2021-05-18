/* Creamos los usuarios*/  
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('gestor','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('reg_madrid','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('reg_andalucia','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('reg_cataluña','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_lapaz','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_moral','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_cv','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_clinic','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_ramblas','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_mar','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_csol','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_sjdios','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_caleta','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('cen_motril','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

/* Creamos los roles */
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (1,'ROLE_GESTOR', 0);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (2,'ROLE_REGION', 3);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (3,'ROLE_REGION', 1);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (4,'ROLE_REGION', 2);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (5,'ROLE_CENTRO', 1);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (6,'ROLE_CENTRO', 2);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (7,'ROLE_CENTRO', 3);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (8,'ROLE_CENTRO', 4);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (9,'ROLE_CENTRO', 5);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (10,'ROLE_CENTRO', 6);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (11,'ROLE_CENTRO', 7);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (12,'ROLE_CENTRO', 8);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (13,'ROLE_CENTRO', 9);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (14,'ROLE_CENTRO', 10);

/* Datos de las regiones */
INSERT INTO regiones (denominacion, habitantes) VALUES('Andalucía',8464411);
INSERT INTO regiones (denominacion, habitantes) VALUES('Cataluña',7780479);
INSERT INTO regiones (denominacion, habitantes) VALUES('Comunidad de Madrid',6779888);
INSERT INTO regiones (denominacion, habitantes) VALUES('Comunidad Valenciana',5057353);
INSERT INTO regiones (denominacion, habitantes) VALUES('Galicia',2701819);
INSERT INTO regiones (denominacion, habitantes) VALUES('Castilla y León',2394918);
INSERT INTO regiones (denominacion, habitantes) VALUES('País Vasco',2220504);
INSERT INTO regiones (denominacion, habitantes) VALUES('Canarias',2175952);
INSERT INTO regiones (denominacion, habitantes) VALUES('Castilla-La Mancha',2045221);
INSERT INTO regiones (denominacion, habitantes) VALUES('Región de Murcia',1511251);
INSERT INTO regiones (denominacion, habitantes) VALUES('Aragón',1329391);
INSERT INTO regiones (denominacion, habitantes) VALUES('Islas Baleares',1171543);
INSERT INTO regiones (denominacion, habitantes) VALUES('Extremadura',1063987);
INSERT INTO regiones (denominacion, habitantes) VALUES('Principado de Asturias',1018784);
INSERT INTO regiones (denominacion, habitantes) VALUES('Navarra',661197);
INSERT INTO regiones (denominacion, habitantes) VALUES('Cantabria',582905);
INSERT INTO regiones (denominacion, habitantes) VALUES('La Rioja',319914);
INSERT INTO regiones (denominacion, habitantes) VALUES('Melilla',87076);
INSERT INTO regiones (denominacion, habitantes) VALUES('Ceuta',84202);

/* Datos de las opciones disponibles */
INSERT INTO aux_opciones (opcion, tipo) VALUES('Centro de Salud', 'TIPO_CENTRO');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Hospital', 'TIPO_CENTRO');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Texto', 'TIPO_PREGUNTA', '');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Número', 'TIPO_PREGUNTA', '');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Fecha', 'TIPO_PREGUNTA', '');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Si/No', 'TIPO_PREGUNTA', 'OPC_LOGICA');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Sexo', 'TIPO_PREGUNTA', 'OPC_SEXO');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Edad', 'TIPO_PREGUNTA', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('Tipo de prueba', 'TIPO_PREGUNTA', 'OPC_TIPO_PRUEBA');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Si', 'OPC_LOGICA');
INSERT INTO aux_opciones (opcion, tipo) VALUES('No', 'OPC_LOGICA');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Vírica', 'OPC_TIPO_PRUEBA');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Antígenos', 'OPC_TIPO_PRUEBA');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Varón', 'OPC_SEXO');
INSERT INTO aux_opciones (opcion, tipo) VALUES('Mujer', 'OPC_SEXO');
INSERT INTO aux_opciones (opcion, tipo) VALUES('< 2', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('2-4', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('5-14', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('15-29', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('30-39', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('40-49', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('50-59', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('60-69', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('70-79', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo) VALUES('>=80', 'OPC_RANGO_EDAD');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('GESTOR', 'TIPO_ROL', 'ROLE_GESTOR');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('REGION', 'TIPO_ROL', 'ROLE_REGION');
INSERT INTO aux_opciones (opcion, tipo, opcionestabla) VALUES('CENTRO', 'TIPO_ROL', 'ROLE_CENTRO');

/* Datos de los centros */
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H.U. La Paz', 1000000, 3, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('CS Moralzarzal', 15000, 3, 1);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H.G. Collado Villalba', 500000, 3, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H. Clinic', 1000000, 2, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('CS Las Ramblas', 500000, 2, 1);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H. del Mar', 1000000, 2, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H.C. Costa del Sol', 600000, 1, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('H. San Juan de Dios', 750000, 1, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('CS La Caleta', 25000, 1, 1);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('CS Motril', 57000, 1, 1);

/* Datos de los tipos de preguntas del perfil */
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Sexo', 7);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Edad', 8);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Enfermedades Previas', 6);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Presenta Sintomas', 6);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Prueba de Contraste', 9);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Personas Perfil Citadas', 4);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Responsable Grupo Pruebas', 3);
INSERT INTO preguntas_perfil (denominacion, tipopregunta_id) VALUES('Fecha Comprobación', 5);

/* Datos de los totales de pruebas por fecha */
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-01', 1000, 1, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-01', 2000, 1, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-19', 100, 2, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-19', 200, 2, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-03-29', 500, 3, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-03-30', 400, 3, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-15', 2100, 4, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-15', 1200, 4, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-02', 1000, 5, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-02', 500, 5, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-13', 100, 7, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-05-13', 200, 7, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-20', 500, 8, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2021-04-20', 400, 8, 13); 

/* Datos de los totales de positivos por perfil */
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(1, 10);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 20);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 30);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 40);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 50);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 60);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(2, 70);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(3, 10);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(3, 20);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(4, 30);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(4, 40);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(5, 50);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(5, 60);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(6, 70);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(6, 10);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(7, 20);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(7, 30);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(8, 40);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(8, 50);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(9, 60);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(9, 70);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(10, 60);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(10, 70);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(11, 70);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(11, 10);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(12, 20);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(12, 30);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(13, 40);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(13, 50);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(14, 60);
INSERT INTO datos_perfiles (datosfecha_id, totalpositivos) VALUES(14, 70);

/* Datos de las preguntas */
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 1, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 1, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 1, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 1, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 1, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 1, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Pérez', 1, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 1, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 2, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 2, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 2, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 2, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 2, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 2, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('López', 2, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 2, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 3, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 3, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 3, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 3, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 3, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 3, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('González', 3, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 3, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 4, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 4, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 4, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 4, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 4, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 4, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Hernández', 4, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 4, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 5, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('60-69', 5, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 5, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 5, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 5, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 5, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 5, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 5, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 6, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 6, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 6, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 6, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 6, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 6, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Gómez', 6, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 6, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 7, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 7, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 7, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 7, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 7, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 7, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Fernández', 7, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 7, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 8, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('60-69', 8, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 8, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 8, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 8, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 8, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Rodríguez', 8, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 8, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 9, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 9, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 9, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 9, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 9, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 9, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Sánchez', 9, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 9, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 10, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 10, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 10, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 10, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 10, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 10, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Núñez', 10, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 10, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 11, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('50-59', 11, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 11, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 11, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 11, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 11, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('López', 11, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 11, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 12, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 12, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 12, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 12, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 12, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 12, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('López', 12, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 12, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 13, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 13, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 13, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 13, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 13, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 13, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('López', 13, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 13, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 14, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 14, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 14, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 14, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 14, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 14, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antúnez', 14, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 14, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 15, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 15, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 15, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 15, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 15, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 15, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Garcia A.', 15, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 15, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 16, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('50-59', 16, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 16, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 16, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 16, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 16, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Pérez D.', 16, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 16, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 17, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 17, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 17, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 17, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 17, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 17, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Pérez D.', 17, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 17, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 18, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 18, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 18, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 18, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 18, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 18, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Pérez D.', 18, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 18, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 19, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 19, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 19, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 19, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 19, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 19, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 19, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 19, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 20, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 20, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 20, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 20, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 20, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 20, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 20, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 20, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 21, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 21, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 21, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 21, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 21, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 21, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 21, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 21, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 22, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 22, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 22, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 22, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 22, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 22, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 22, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 22, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 23, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 23, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 23, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 23, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 23, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 23, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 23, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 23, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 24, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 24, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 24, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 24, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 24, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 24, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Pérez', 24, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 24, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 25, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 25, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 25, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 25, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 25, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 25, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('López', 25, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 25, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 26, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 26, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 26, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 26, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 26, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 26, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('González', 26, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 26, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 27, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 27, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 27, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 27, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 27, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 27, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Hernández', 27, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 27, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 28, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('60-69', 28, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 28, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 28, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 28, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 28, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('García', 28, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 28, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 29, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 29, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 29, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 29, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 29, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('200', 29, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Gómez', 29, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 29, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 30, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 30, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 30, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 30, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 30, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 30, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Fernández', 30, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 30, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 31, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('60-69', 31, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 31, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 31, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 31, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('100', 31, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Velázquez', 31, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2021-05-31', 31, 8);

