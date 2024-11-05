-- Crear las tablas base primero--
CREATE TABLE usuario_administrador (
    id BIGINT NOT NULL UNIQUE,
    usuario VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(40),
    apellido VARCHAR(40),
    contrasenia VARCHAR(60) NOT NULL,
    CONSTRAINT pk_usuario_administrador PRIMARY KEY(id)
);

CREATE TABLE usuario_bedel (
    id BIGINT NOT NULL UNIQUE,
    usuario VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(40),
    apellido VARCHAR(40),
    contrasenia VARCHAR(60) NOT NULL,
    estado BOOLEAN NOT NULL,
    turno INTEGER NOT NULL,
    CONSTRAINT pk_usuario_bedel PRIMARY KEY(id)
);

CREATE TABLE aula (
    id BIGINT NOT NULL UNIQUE,
    estado BOOLEAN NOT NULL,
    maximo_alumnos INTEGER NOT NULL,
    piso VARCHAR(60),
    tipo_pizarron TEXT,
    aire_acondicionado BOOLEAN,
    CONSTRAINT pk_aula PRIMARY KEY(id),
    CONSTRAINT chk_maximo_alumnos_positivo CHECK (maximo_alumnos > 0)
);

--tienen referencias a las anteriores--
CREATE TABLE aula_sin_recursos (
    id_aula BIGINT NOT NULL UNIQUE,
    ventiladores BOOLEAN,
    descripcion TEXT,
    CONSTRAINT pk_aula_sin_recursos PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_sin_recursos FOREIGN KEY(id_aula) REFERENCES aula(id) ON DELETE CASCADE
);

CREATE TABLE aula_multimedia (
    id_aula BIGINT NOT NULL UNIQUE,
    canion BOOLEAN,
    computadora BOOLEAN,
    ventiladores BOOLEAN,
    CONSTRAINT pk_aula_multimedia PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_multimedia FOREIGN KEY(id_aula) REFERENCES aula(id) ON DELETE CASCADE
);

CREATE TABLE aula_informatica (
    id_aula BIGINT NOT NULL UNIQUE,
    cant_pc INTEGER,
    canion BOOLEAN,
    CONSTRAINT pk_aula_informatica PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_informatica FOREIGN KEY(id_aula) REFERENCES aula(id) ON DELETE CASCADE
);

CREATE TABLE periodo (
    id BIGINT NOT NULL UNIQUE,
    cuatrimestre TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT pk_periodo PRIMARY KEY(id),
    CONSTRAINT chk_fechas_compatibles CHECK (fecha_inicio <= fecha_fin)
);

CREATE TABLE reserva (
    id BIGINT NOT NULL UNIQUE,
    id_bedel BIGINT NOT NULL,
    id_docente BIGINT NOT NULL,
    id_asignatura BIGINT NOT NULL,
    nombre_docente VARCHAR(40),
    nombre_asignatura VARCHAR(40),
    cant_alumnos INTEGER NOT NULL,
    fecha_reserva TIMESTAMP,
    CONSTRAINT pk_reserva PRIMARY KEY(id),
    CONSTRAINT fk_reserva_bedel FOREIGN KEY(id_bedel) REFERENCES usuario_bedel(id) ON DELETE SET NULL,
    CONSTRAINT chk_cant_alumnos_positivos CHECK (cant_alumnos > 0)
);

-- por ultimo --

CREATE TABLE reserva_periodica (
    id_reserva BIGINT NOT NULL UNIQUE,
    id_periodo BIGINT NOT NULL,
    CONSTRAINT pk_reserva_periodica PRIMARY KEY(id_reserva),
    CONSTRAINT fk_reserva_periodica FOREIGN KEY(id_reserva) REFERENCES reserva(id) ON DELETE CASCADE,
    CONSTRAINT fk_periodo_reserva FOREIGN KEY(id_periodo) REFERENCES periodo(id)
);

CREATE TABLE reserva_esporadica(
    id_reserva BIGINT NOT NULL UNIQUE,
    CONSTRAINT pk_reserva_esporadica PRIMARY KEY(id_reserva),
    CONSTRAINT fk_reserva FOREIGN KEY(id_reserva) REFERENCES reserva(id) ON DELETE CASCADE
);

CREATE TABLE dia_reserva(
	id BIGINT PRIMARY KEY,
	id_reserva BIGINT NOT NULL,
	id_aula BIGINT NOT NULL,
	fecha_reserva DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	duracion INT NOT NULL,
	CONSTRAINT fk_reserva FOREIGN KEY (id_reserva) REFERENCES reserva(id) ON DELETE CASCADE,
	CONSTRAINT fk_aula FOREIGN KEY (id_aula) REFERENCES aula(id) ON DELETE SET NULL
);