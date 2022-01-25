# encuesta

La base de datos fue realizada en postgres
CODIGO SQL: 
	CREATE TABLE ENCUESTA (
	ID SERIAL NOT NULL,
	NOMBRE VARCHAR(100) NOT NULL
);
	COMMENT ON TABLE ENCUESTA IS 'Tabla para almacenar la encuesta';
	COMMENT ON COLUMN ENCUESTA.ID IS 'Identificacion de la encuesta';
	COMMENT ON COLUMN ENCUESTA.NOMBRE IS 'Nombre de la encuesta';
		
	CREATE TABLE PREGUNTA (
	ID SERIAL NOT NULL,
	DESCRIPCION VARCHAR(100) NOT NULL,
	TIPOPREGUNTA VARCHAR(20) NOT NULL,
	ID_ENCUESTA INTEGER NOT NULL
);
	COMMENT ON TABLE PREGUNTA IS 'Tabla para almacenar preguntas de la encuesta';
	COMMENT ON COLUMN PREGUNTA.ID IS 'Identificacion de la pregunta';
	COMMENT ON COLUMN PREGUNTA.DESCRIPCION IS 'Descripcion de la pregunta';
	COMMENT ON COLUMN PREGUNTA.TIPOPREGUNTA IS 'Tipo de pregunta ya sea abierta o multiple';
	COMMENT ON COLUMN PREGUNTA.ID_ENCUESTA IS 'Identificador de conexion a la tabla encuesta';
		
	CREATE TABLE RESPUESTA (
    ID SERIAL NOT NULL,
    ID_PREGUNTA INTEGER NOT NULL,
    DESCRIPCION VARCHAR(100) NOT NULL,
    RESPUESTA_CORRECTA INTEGER NOT NULL
 );
 
	COMMENT ON TABLE RESPUESTA IS 'Tabla para almacenar datos de las respuestas a preguntas con opcion multiple';
	COMMENT ON COLUMN RESPUESTA.ID IS 'Indentificacion de respuesta';
	COMMENT ON COLUMN RESPUESTA.ID_PREGUNTA IS 'Identificado de conexion con tabla pregunta';
	COMMENT ON COLUMN RESPUESTA.DESCRIPCION IS 'Descripcion de la respuesta';
	COMMENT ON COLUMN RESPUESTA.RESPUESTA_CORRECTA IS 'Respuesta correcta de la pregunta';
	
	ALTER TABLE ENCUESTA
	ADD PRIMARY KEY (ID);
	
	ALTER TABLE PREGUNTA
	ADD PRIMARY KEY (ID);
	ALTER TABLE PREGUNTA
	ADD FOREIGN KEY (ID_ENCUESTA) REFERENCES ENCUESTA(ID);
	
	ALTER TABLE RESPUESTA
	ADD PRIMARY KEY (ID);
	ALTER TABLE RESPUESTA
	ADD FOREIGN KEY (ID_PREGUNTA) REFERENCES PREGUNTA(ID);
  
  
  Se tiene un archivo llamado application-dev.yml el cual tiene la informacion para la conexion a base de datos, alli se cambia de ser necesario 
  
  Se enviara al correo unos archivos postman para la facil ejecucion de las pruebas
  
  POSIBLES MEJORAS: 
  Tener un xml con los scripts de sql, asi cuando hayan muchos scripts se realizan cambios en codigo mas sencillo
  
  Realizacion de test unit en dao con base de datos h2
