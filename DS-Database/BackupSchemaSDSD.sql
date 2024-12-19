--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2024-12-19 17:27:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 17054)
-- Name: aula; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.aula (
    id bigint NOT NULL,
    aire_acondicionado boolean,
    aula_disponible boolean NOT NULL,
    maximo_alumnos integer NOT NULL,
    piso character varying(255),
    tipo_pizzarron character varying(255)
);


ALTER TABLE public.aula OWNER TO admin_ds;

--
-- TOC entry 219 (class 1259 OID 17053)
-- Name: aula_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.aula ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.aula_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 17061)
-- Name: aula_informatica; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.aula_informatica (
    canion boolean,
    cant_pc integer NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.aula_informatica OWNER TO admin_ds;

--
-- TOC entry 222 (class 1259 OID 17066)
-- Name: aula_multimedia; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.aula_multimedia (
    canion boolean,
    computadora boolean,
    televisor boolean,
    ventiladores boolean,
    id bigint NOT NULL
);


ALTER TABLE public.aula_multimedia OWNER TO admin_ds;

--
-- TOC entry 223 (class 1259 OID 17071)
-- Name: aula_sin_recursos; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.aula_sin_recursos (
    descripcion character varying(255),
    ventiladores boolean,
    id bigint NOT NULL
);


ALTER TABLE public.aula_sin_recursos OWNER TO admin_ds;

--
-- TOC entry 227 (class 1259 OID 17154)
-- Name: dia_reserva; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.dia_reserva (
    id bigint NOT NULL,
    duracion integer NOT NULL,
    fecha_reserva date NOT NULL,
    hora_inicio time(6) without time zone NOT NULL,
    id_aula bigint NOT NULL,
    id_reserva bigint NOT NULL
);


ALTER TABLE public.dia_reserva OWNER TO admin_ds;

--
-- TOC entry 226 (class 1259 OID 17153)
-- Name: dia_reserva_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.dia_reserva ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.dia_reserva_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 17083)
-- Name: periodo; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.periodo (
    id bigint NOT NULL,
    cuatrimestre character varying(255),
    fecha_fin date NOT NULL,
    fecha_inicio date NOT NULL
);


ALTER TABLE public.periodo OWNER TO admin_ds;

--
-- TOC entry 224 (class 1259 OID 17082)
-- Name: periodo_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.periodo ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.periodo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 229 (class 1259 OID 17160)
-- Name: reserva; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.reserva (
    id bigint NOT NULL,
    cant_alumnos integer NOT NULL,
    fecha_registro timestamp(6) without time zone NOT NULL,
    id_asignatura bigint NOT NULL,
    id_docente bigint NOT NULL,
    nombre_asignatura character varying(255),
    nombre_docente character varying(255),
    id_bedel bigint NOT NULL
);


ALTER TABLE public.reserva OWNER TO admin_ds;

--
-- TOC entry 230 (class 1259 OID 17167)
-- Name: reserva_esporadica; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.reserva_esporadica (
    id bigint NOT NULL
);


ALTER TABLE public.reserva_esporadica OWNER TO admin_ds;

--
-- TOC entry 228 (class 1259 OID 17159)
-- Name: reserva_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.reserva ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.reserva_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 17172)
-- Name: reserva_periodica; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.reserva_periodica (
    id bigint NOT NULL,
    id_periodo bigint NOT NULL
);


ALTER TABLE public.reserva_periodica OWNER TO admin_ds;

--
-- TOC entry 216 (class 1259 OID 16968)
-- Name: usuario_administrador; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.usuario_administrador (
    id bigint NOT NULL,
    apellido character varying(255) NOT NULL,
    contrasenia character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    usuario character varying(255) NOT NULL
);


ALTER TABLE public.usuario_administrador OWNER TO admin_ds;

--
-- TOC entry 215 (class 1259 OID 16967)
-- Name: usuario_administrador_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.usuario_administrador ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.usuario_administrador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16976)
-- Name: usuario_bedel; Type: TABLE; Schema: public; Owner: admin_ds
--

CREATE TABLE public.usuario_bedel (
    id bigint NOT NULL,
    apellido character varying(255) NOT NULL,
    contrasenia character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    usuario character varying(255) NOT NULL,
    estado boolean NOT NULL,
    turno integer
);


ALTER TABLE public.usuario_bedel OWNER TO admin_ds;

--
-- TOC entry 217 (class 1259 OID 16975)
-- Name: usuario_bedel_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_ds
--

ALTER TABLE public.usuario_bedel ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.usuario_bedel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4687 (class 2606 OID 17065)
-- Name: aula_informatica aula_informatica_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_informatica
    ADD CONSTRAINT aula_informatica_pkey PRIMARY KEY (id);


--
-- TOC entry 4689 (class 2606 OID 17070)
-- Name: aula_multimedia aula_multimedia_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_multimedia
    ADD CONSTRAINT aula_multimedia_pkey PRIMARY KEY (id);


--
-- TOC entry 4685 (class 2606 OID 17060)
-- Name: aula aula_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula
    ADD CONSTRAINT aula_pkey PRIMARY KEY (id);


--
-- TOC entry 4691 (class 2606 OID 17075)
-- Name: aula_sin_recursos aula_sin_recursos_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_sin_recursos
    ADD CONSTRAINT aula_sin_recursos_pkey PRIMARY KEY (id);


--
-- TOC entry 4695 (class 2606 OID 17158)
-- Name: dia_reserva dia_reserva_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.dia_reserva
    ADD CONSTRAINT dia_reserva_pkey PRIMARY KEY (id);


--
-- TOC entry 4693 (class 2606 OID 17087)
-- Name: periodo periodo_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.periodo
    ADD CONSTRAINT periodo_pkey PRIMARY KEY (id);


--
-- TOC entry 4699 (class 2606 OID 17171)
-- Name: reserva_esporadica reserva_esporadica_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva_esporadica
    ADD CONSTRAINT reserva_esporadica_pkey PRIMARY KEY (id);


--
-- TOC entry 4701 (class 2606 OID 17176)
-- Name: reserva_periodica reserva_periodica_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva_periodica
    ADD CONSTRAINT reserva_periodica_pkey PRIMARY KEY (id);


--
-- TOC entry 4697 (class 2606 OID 17166)
-- Name: reserva reserva_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);


--
-- TOC entry 4681 (class 2606 OID 16974)
-- Name: usuario_administrador usuario_administrador_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.usuario_administrador
    ADD CONSTRAINT usuario_administrador_pkey PRIMARY KEY (id);


--
-- TOC entry 4683 (class 2606 OID 16982)
-- Name: usuario_bedel usuario_bedel_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.usuario_bedel
    ADD CONSTRAINT usuario_bedel_pkey PRIMARY KEY (id);


--
-- TOC entry 4709 (class 2606 OID 17197)
-- Name: reserva_periodica fk589ljgm8tmhmfm4d613q9sl7n; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva_periodica
    ADD CONSTRAINT fk589ljgm8tmhmfm4d613q9sl7n FOREIGN KEY (id_periodo) REFERENCES public.periodo(id);


--
-- TOC entry 4708 (class 2606 OID 17192)
-- Name: reserva_esporadica fk80llhytcdp008op22mn8u9n5d; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva_esporadica
    ADD CONSTRAINT fk80llhytcdp008op22mn8u9n5d FOREIGN KEY (id) REFERENCES public.reserva(id);


--
-- TOC entry 4705 (class 2606 OID 17182)
-- Name: dia_reserva fk9dio52td5pn43n22pqhcwuuw1; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.dia_reserva
    ADD CONSTRAINT fk9dio52td5pn43n22pqhcwuuw1 FOREIGN KEY (id_reserva) REFERENCES public.reserva(id);


--
-- TOC entry 4707 (class 2606 OID 17187)
-- Name: reserva fkdmcx184nhg3a97uhju5wfx66c; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT fkdmcx184nhg3a97uhju5wfx66c FOREIGN KEY (id_bedel) REFERENCES public.usuario_bedel(id);


--
-- TOC entry 4706 (class 2606 OID 17177)
-- Name: dia_reserva fkdud73d9g11v4abdaiop107wgy; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.dia_reserva
    ADD CONSTRAINT fkdud73d9g11v4abdaiop107wgy FOREIGN KEY (id_aula) REFERENCES public.aula(id);


--
-- TOC entry 4710 (class 2606 OID 17202)
-- Name: reserva_periodica fkiy7mtslqi5p8nyq4nma4vgx8w; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.reserva_periodica
    ADD CONSTRAINT fkiy7mtslqi5p8nyq4nma4vgx8w FOREIGN KEY (id) REFERENCES public.reserva(id);


--
-- TOC entry 4703 (class 2606 OID 17113)
-- Name: aula_multimedia fkpahguiaipw9xvtekedqwsf2jo; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_multimedia
    ADD CONSTRAINT fkpahguiaipw9xvtekedqwsf2jo FOREIGN KEY (id) REFERENCES public.aula(id);


--
-- TOC entry 4704 (class 2606 OID 17118)
-- Name: aula_sin_recursos fkq6gfchhgearq6upnxgech41vk; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_sin_recursos
    ADD CONSTRAINT fkq6gfchhgearq6upnxgech41vk FOREIGN KEY (id) REFERENCES public.aula(id);


--
-- TOC entry 4702 (class 2606 OID 17108)
-- Name: aula_informatica fktjwfjcxwgaug2mxwldrdv6cgo; Type: FK CONSTRAINT; Schema: public; Owner: admin_ds
--

ALTER TABLE ONLY public.aula_informatica
    ADD CONSTRAINT fktjwfjcxwgaug2mxwldrdv6cgo FOREIGN KEY (id) REFERENCES public.aula(id);


--
-- TOC entry 2083 (class 826 OID 16866)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES TO admin_ds;


-- Completed on 2024-12-19 17:27:47

--
-- PostgreSQL database dump complete
--

