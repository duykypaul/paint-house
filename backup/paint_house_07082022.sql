--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

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

DROP DATABASE IF EXISTS paint_house;
--
-- Name: paint_house; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE paint_house WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


ALTER DATABASE paint_house OWNER TO postgres;

\connect paint_house

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
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category
(
    id   bigint                NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.category
    OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product
(
    id          bigint  NOT NULL,
    created_at  timestamp without time zone,
    created_by  character varying(255),
    delete_flag boolean NOT NULL,
    modified_at timestamp without time zone,
    modified_by character varying(255),
    category_id bigint,
    name        character varying(100),
    description text,
    meta_code   character varying(255),
    meta_type   character varying(255),
    packing     character varying(255)
);


ALTER TABLE public.product
    OWNER TO postgres;

--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_category_id_seq
    OWNER TO postgres;

--
-- Name: product_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_category_id_seq OWNED BY public.category.id;


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq
    OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role
(
    id   bigint NOT NULL,
    name character varying(20)
);


ALTER TABLE public.role
    OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq
    OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: tutorial; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tutorial
(
    id          bigint  NOT NULL,
    created_at  timestamp without time zone,
    created_by  character varying(255),
    delete_flag boolean NOT NULL,
    modified_at timestamp without time zone,
    modified_by character varying(255),
    description character varying(255),
    published   boolean,
    title       character varying(255)
);


ALTER TABLE public.tutorial
    OWNER TO postgres;

--
-- Name: tutorial_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tutorial_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tutorial_id_seq
    OWNER TO postgres;

--
-- Name: tutorial_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tutorial_id_seq OWNED BY public.tutorial.id;


--
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_role
    OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users
(
    id          bigint  NOT NULL,
    created_at  timestamp without time zone,
    created_by  character varying(255),
    delete_flag boolean NOT NULL,
    modified_at timestamp without time zone,
    modified_by character varying(255),
    email       character varying(50),
    is_enabled  boolean NOT NULL,
    password    character varying(120),
    username    character varying(20),
    name        character varying(255)
);


ALTER TABLE public.users
    OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq
    OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: tutorial id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tutorial
    ALTER COLUMN id SET DEFAULT nextval('public.tutorial_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category
VALUES (1, 'Sơn nội thất');
INSERT INTO public.category
VALUES (2, 'Sơn ngoại thất');
INSERT INTO public.category
VALUES (3, 'Sơn lót');
INSERT INTO public.category
VALUES (4, 'Sơn tính năng');
INSERT INTO public.category
VALUES (5, 'Sơn công nghiệp hệ nước');
INSERT INTO public.category
VALUES (6, 'Bột bả và chống thấm');


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product
VALUES (2, '2022-08-07 20:07:31', NULL, false, '2022-08-07 20:07:42', NULL, 1,
        'K009 – SƠN NỘI THẤT CAO CẤP SIÊU BÓNG RUBY', NULL, 'K009', 'IN GLOSSY RUBY', '18,5,1');
INSERT INTO public.product
VALUES (3, '2022-08-07 20:09:48', NULL, false, '2022-08-07 20:09:38', NULL, 1, 'K003 – SƠN NỘI THẤT CAO CẤP 5 IN 1',
        NULL, 'K003', 'MULTI FEATURES', '18,5');
INSERT INTO public.product
VALUES (4, '2022-08-06 22:12:30.453465', NULL, false, '2022-08-06 22:12:30.453465', NULL, 2,
        'KS06 – SƠN MEN SỨ NGOẠI THẤT SIÊU HẠNG DIAMOND', NULL, 'KS06', 'EXTRA LIFETIME', '10,5');
INSERT INTO public.product
VALUES (5, '2022-08-07 20:07:31', NULL, false, '2022-08-07 20:07:42', NULL, 2, 'K004 – SƠN NGOẠI THẤT CAO CẤP', NULL,
        'K004', 'EXTERIOR PREMIUM', '18,5');
INSERT INTO public.product
VALUES (6, '2022-08-07 20:09:48', NULL, false, '2022-08-07 20:09:38', NULL, 2, 'KS02 – SƠN MEN SỨ NGOẠI THẤT ORIGINAL',
        NULL, 'KS02', 'EXTRA LIFETIME', '18,5');
INSERT INTO public.product
VALUES (1, '2022-08-06 22:12:30.453465', NULL, false, '2022-08-07 23:32:45.473125', 'admin', 1,
        'KS01 – SƠN MEN SỨ NỘI THẤT SILVER', 'false', 'KS01', 'MULTI-PUR PRIMER', '10,5');
INSERT INTO public.product
VALUES (7, '2022-08-07 23:43:36.272959', 'admin', false, '2022-08-07 23:43:36.272959', 'admin', 3,
        'KP02 – SƠN LÓT CHỐNG KIỀM NGOẠI THẤT CAO CẤP', 'string', 'KP02', 'EXTERIOR PRIMER', '18,5');
INSERT INTO public.product
VALUES (8, '2022-08-07 23:50:50.61925', 'admin', false, '2022-08-07 23:50:50.61925', 'admin', 3,
        'KP08 – SƠN LÓT CHỐNG KIỀM NGOẠI THẤT CAO CẤP', 'string', 'KP08', 'EXTERIOR PRIMER', '18,5');


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role
VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role
VALUES (2, 'ROLE_MODERATOR');
INSERT INTO public.role
VALUES (3, 'ROLE_USER');


--
-- Data for Name: tutorial; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role
VALUES (1, 2);
INSERT INTO public.user_role
VALUES (1, 1);
INSERT INTO public.user_role
VALUES (1, 3);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users
VALUES (1, '2022-08-04 22:15:27.821674', NULL, false, '2022-08-04 22:15:27.821674', NULL, 'lminh9812@gmail.com', true,
        '$2a$10$TbRqbQxlqifKBct/b1V.tOGcvUhylGa9e0yMZhtCM33CP4VjN.OIK', 'admin', NULL);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_category_id_seq', 6, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 8, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: tutorial_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tutorial_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- Name: category product_category_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT product_category_pk PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: tutorial tutorial_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tutorial
    ADD CONSTRAINT tutorial_pkey PRIMARY KEY (id);


--
-- Name: users uk3g1j96g94xpk3lpxl2qbl985x; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk3g1j96g94xpk3lpxl2qbl985x UNIQUE (name);


--
-- Name: category uk46ccwnsi9409t36lurvtyljak; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT uk46ccwnsi9409t36lurvtyljak UNIQUE (name);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: product_category_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX product_category_index ON public.product USING btree (category_id);


--
-- Name: product_category_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX product_category_name_uindex ON public.category USING btree (name);


--
-- Name: product fk_product_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES public.category (id);


--
-- Name: user_role fka68196081fvovjhkek5m97n3y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role (id);


--
-- Name: user_role fkj345gk1bovqvfame88rcx7yyx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES public.users (id);


--
-- PostgreSQL database dump complete
--

