/* Creamos los usuarios*/
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('gestor','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('region1','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('centro1','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('region2','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `usuarios` (nombreusuario, clave, activo) VALUES ('centro2','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

/* Creamos los roles */
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (1,'ROLE_GESTOR', 0);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (2,'ROLE_REGION', 1);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (3,'ROLE_CENTRO', 1);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (4,'ROLE_REGION', 2);
INSERT INTO `roles` (usuario_id, rol, centro_region) VALUES (5,'ROLE_CENTRO', 2);

/* Datos de las regiones */ 
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100); 
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400); 
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500); 
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600); 
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300); 
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);
INSERT INTO regiones (denominacion, habitantes) VALUES('region uno', 100);
INSERT INTO regiones (denominacion, habitantes) VALUES('region dos', 200);
INSERT INTO regiones (denominacion, habitantes) VALUES('region tres', 300);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cuatro', 400);
INSERT INTO regiones (denominacion, habitantes) VALUES('region cinco', 500);
INSERT INTO regiones (denominacion, habitantes) VALUES('region seis', 600);

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
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('centro uno', 100, 1, 1);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('centro dos', 200, 1, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('centro tres', 300, 1, 1);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('centro cuatro', 400, 2, 2);
INSERT INTO centros (denominacion, pacientes, region_id, tipocentro_id) VALUES('centro cinco', 500, 2, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro cinco', 500, 1);
INSERT INTO centros (denominacion, pacientes, tipocentro_id) VALUES('centro seis', 600, 2);

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
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 100, 1, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 2000, 1, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 100, 2, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 200, 2, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 100, 3, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 200, 3, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 100, 4, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 200, 4, 13); 
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 100, 5, 12);
INSERT INTO datos_fecha (fecha, totalpruebas, centro_id, tipoprueba_id) VALUES('2017-08-19', 200, 5, 13); 

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

/* Datos de las preguntas */
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 1, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 1, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 1, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 1, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 1, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 1, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 1, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 1, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 2, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 2, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 2, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 2, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 2, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('20', 2, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Manolo', 2, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 2, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 3, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 3, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 3, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 3, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 3, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 3, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 3, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 3, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Mujer', 4, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 4, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 4, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 4, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 4, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('20', 4, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Manolo', 4, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 4, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 5, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 5, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 5, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 5, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 5, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 5, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 5, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 5, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('M', 6, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('40-49', 6, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 6, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 6, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Antígenos', 6, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('20', 6, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Manolo', 6, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 6, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 7, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 7, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 7, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 7, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 7, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 7, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 7, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 7, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 8, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 8, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 8, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 8, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 8, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 8, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 8, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 8, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 9, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 9, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 9, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 9, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 9, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 9, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 9, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 9, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 10, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 10, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 10, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 10, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 10, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 10, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 10, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 10, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 11, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 11, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 11, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 11, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 11, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 11, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 11, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 11, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 12, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 12, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 12, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 12, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 12, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 12, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 12, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 12, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 13, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 13, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 13, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 13, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 13, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 13, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 13, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 13, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 14, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 14, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 14, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 14, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 14, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 14, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 14, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 14, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 15, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 15, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 15, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 15, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 15, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 15, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 15, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 15, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 16, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 16, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 16, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 16, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 16, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 16, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 16, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 16, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 17, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 17, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 17, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 17, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 17, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 17, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 17, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 17, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 18, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 18, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 18, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 18, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 18, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 18, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 18, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 18, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 19, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 19, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 19, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 19, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 19, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 19, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 19, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 19, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 20, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 20, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 20, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 20, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 20, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 20, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 20, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 20, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 21, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 21, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 21, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 21, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 21, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 21, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 21, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 21, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 22, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 22, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 22, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 22, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 22, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 22, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 22, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 22, 8);

INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Varón', 23, 1);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('30-39', 23, 2);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Si', 23, 3);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('No', 23, 4);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Vírica', 23, 5);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('10', 23, 6);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('Felipe', 23, 7);
INSERT INTO datos (dato, datosperfil_id, pregunta_id) VALUES('2017-08-19', 23, 8);

 



