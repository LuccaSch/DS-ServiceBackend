-- Crear las tablas base primero--
CREATE TABLE usuario_administrador (
    id_administrador BIGINT NOT NULL UNIQUE,
    usuario_adm VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(40),
    apellido VARCHAR(40),
    password CHAR(60) NOT NULL,
    CONSTRAINT pk_usuario_administrador PRIMARY KEY(id_administrador)
);

CREATE TABLE usuario_bedel (
    id_bedel BIGINT NOT NULL UNIQUE,
    usuario_bed VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(40),
    apellido VARCHAR(40),
    password CHAR(60) NOT NULL,
    estado BOOLEAN NOT NULL,
    turno INTEGER NOT NULL,
    CONSTRAINT pk_usuario_bedel PRIMARY KEY(id_bedel)
);

CREATE TABLE aula (
    id_aula BIGINT NOT NULL UNIQUE,
    estado BOOLEAN NOT NULL,
    maximo_alumnos INTEGER NOT NULL,
    piso VARCHAR(60),
    tipo_pizarron TEXT,
    aire_acondicionado BOOLEAN,
    CONSTRAINT pk_aula PRIMARY KEY(id_aula),
    CONSTRAINT chk_maximo_alumnos_positivo CHECK (maximo_alumnos > 0)
);

--tienen referencias a las anteriores--
CREATE TABLE aula_sin_recursos (
    id_aula BIGINT NOT NULL UNIQUE,
    ventiladores BOOLEAN,
    descripcion TEXT,
    CONSTRAINT pk_aula_sin_recursos PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_sin_recursos FOREIGN KEY(id_aula) REFERENCES aula(id_aula) ON DELETE CASCADE
);

CREATE TABLE aula_multimedia (
    id_aula BIGINT NOT NULL UNIQUE,
    canion BOOLEAN,
    computadora BOOLEAN,
    ventiladores BOOLEAN,
    CONSTRAINT pk_aula_multimedia PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_multimedia FOREIGN KEY(id_aula) REFERENCES aula(id_aula) ON DELETE CASCADE
);

CREATE TABLE aula_informatica (
    id_aula BIGINT NOT NULL UNIQUE,
    cant_pc INTEGER,
    canion BOOLEAN,
    CONSTRAINT pk_aula_informatica PRIMARY KEY(id_aula),
    CONSTRAINT fk_aula_informatica FOREIGN KEY(id_aula) REFERENCES aula(id_aula) ON DELETE CASCADE
);

CREATE TABLE periodo (
    id_periodo BIGINT NOT NULL UNIQUE,
    cuatrimestre TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT pk_periodo PRIMARY KEY(id_periodo),
    CONSTRAINT chk_fechas_compatibles CHECK (fecha_inicio <= fecha_fin)
);

CREATE TABLE reserva (
    id_reserva BIGINT NOT NULL UNIQUE,
    id_bedel BIGINT NOT NULL,
    id_docente BIGINT NOT NULL,
    id_asignatura BIGINT NOT NULL,
    nombre_docente VARCHAR(40),
    nombre_asignatura VARCHAR(40),
    cant_alumnos INTEGER NOT NULL,
    fecha_reserva TIMESTAMP,
    CONSTRAINT pk_reserva PRIMARY KEY(id_reserva),
    CONSTRAINT fk_reserva_bedel FOREIGN KEY(id_bedel) REFERENCES usuario_bedel(id_bedel) ON DELETE SET NULL,
    CONSTRAINT chk_cant_alumnos_positivos CHECK (cant_alumnos > 0)
);

-- por ultimo --

CREATE TABLE reserva_periodica (
    id_periodica BIGINT NOT NULL UNIQUE,
    id_reserva BIGINT NOT NULL UNIQUE,
    id_periodo BIGINT NOT NULL,
    CONSTRAINT pk_reserva_periodica PRIMARY KEY(id_periodica),
    CONSTRAINT fk_reserva_periodica FOREIGN KEY(id_reserva) REFERENCES reserva(id_reserva) ON DELETE CASCADE,
    CONSTRAINT fk_periodo_reserva FOREIGN KEY(id_periodo) REFERENCES periodo(id_periodo)
);

CREATE TABLE dia_reserva_periodica (
    id_dia_periodica BIGINT NOT NULL UNIQUE,
    id_aula BIGINT NOT NULL,
    id_periodica BIGINT NOT NULL,
    dia TEXT,
    hora_inicio TIME NOT NULL,
    duracion INTEGER NOT NULL,
    CONSTRAINT pk_dia_reserva_periodica PRIMARY KEY(id_dia_periodica),
    CONSTRAINT fk_dia_reserva_aula FOREIGN KEY(id_aula) REFERENCES aula(id_aula) ON DELETE SET NULL,
    CONSTRAINT fk_dia_reserva_periodica FOREIGN KEY(id_periodica) REFERENCES reserva_periodica(id_periodica) ON DELETE CASCADE,
    CONSTRAINT chk_duracion_positiva CHECK (duracion > 0)
);

CREATE TABLE dia_reserva(
	id_dia_reserva BIGINT PRIMARY KEY,
	id_reserva BIGINT NOT NULL,
	id_aula BIGINT NOT NULL,
	fecha_reserva DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	duracion INT NOT NULL,
	CONSTRAINT fk_reserva FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva) ON DELETE CASCADE,
	CONSTRAINT fk_aula FOREIGN KEY (id_aula) REFERENCES aula(id_aula) ON DELETE SET NULL
);

CREATE TABLE reserva_esporadica (
    id_esporadica BIGINT NOT NULL UNIQUE,
    id_reserva BIGINT NOT NULL UNIQUE,
    CONSTRAINT pk_reserva_esporadica PRIMARY KEY(id_esporadica),
    CONSTRAINT fk_reserva_esporadica FOREIGN KEY(id_reserva) REFERENCES reserva(id_reserva) ON DELETE CASCADE
);

CREATE TABLE dia_reserva_esporadica (
    id_dia_esporadica BIGINT NOT NULL UNIQUE,
    id_esporadica BIGINT NOT NULL,
    id_aula BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    duracion INTEGER NOT NULL,
    CONSTRAINT pk_dia_reserva_esporadica PRIMARY KEY(id_dia_esporadica),
    CONSTRAINT fk_dia_esporadica FOREIGN KEY(id_esporadica) REFERENCES reserva_esporadica(id_esporadica) ON DELETE CASCADE,
    CONSTRAINT fk_dia_esporadica_aula FOREIGN KEY(id_aula) REFERENCES aula(id_aula) ON DELETE SET NULL,
    CONSTRAINT chk_duracion_positiva CHECK (duracion > 0)
);