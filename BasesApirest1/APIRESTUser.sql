-- Crear el usuario --
CREATE USER apirest IDENTIFIED BY api
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;

-- Dar privilegios básicos para conectarse y tranajar con sus tablas --
GRANT CREATE SESSION TO apirest; -- Permiso para hacer Login
GRANT CREATE TABLE TO apirest; -- Permiso para crear tablas
GRANT CREATE SEQUENCE TO apirest; -- Permiso para crear secuencias
GRANT CREATE VIEW TO apirest; -- Permiso para crear pistas
GRANT CREATE TRIGGER TO apirest; -- Permiso para crear Triggers
GRANT CREATE PROCEDURE TO apirest; -- Permiso para crear funciones y procedimientos almacenados
GRANT CREATE MATERIALIZED VIEW TO apirest; -- Permiso para crear vistas materializadas
GRANT CREATE DATABASE LINK TO apirest; -- Permiso para crear databse link
GRANT CREATE SYNONYM TO apirest; -- Permiso para crear sinónimos

-- Rol resource
GRANT RESOURCE TO apirest; 