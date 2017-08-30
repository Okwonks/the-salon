--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.8
-- Dumped by pg_dump version 9.5.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: albert
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    stylistid integer
);


ALTER TABLE clients OWNER TO albert;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: albert
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO albert;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: albert
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: albert
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying,
    speciality character varying,
    image character varying
);


ALTER TABLE stylists OWNER TO albert;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: albert
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO albert;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: albert
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: albert
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: albert
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: albert
--

COPY clients (id, name, stylistid) FROM stdin;
7	Sheila	4
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: albert
--

SELECT pg_catalog.setval('clients_id_seq', 7, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: albert
--

COPY stylists (id, name, speciality, image) FROM stdin;
4	Jane	braiding	https://i.pinimg.com/736x/42/01/06/420106f62e30726aaefe8fa1a822a623--fun-hairstyles-medium-hairstyles.jpg
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: albert
--

SELECT pg_catalog.setval('stylists_id_seq', 4, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: albert
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: albert
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

