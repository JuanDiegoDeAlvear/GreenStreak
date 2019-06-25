--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.2

-- Started on 2019-03-24 21:39:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 601 (class 1247 OID 16425)
-- Name: ID; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public."ID" AS bigint NOT NULL DEFAULT 0;


ALTER DOMAIN public."ID" OWNER TO postgres;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 601
-- Name: DOMAIN "ID"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public."ID" IS 'The id domain contains all of the possible values for the ids used in the main.application';


--
-- TOC entry 608 (class 1247 OID 16434)
-- Name: age; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.age AS integer NOT NULL DEFAULT 0;


ALTER DOMAIN public.age OWNER TO postgres;

--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 608
-- Name: DOMAIN age; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.age IS 'This will be the domain of all possible ages.';


--
-- TOC entry 617 (class 1247 OID 16440)
-- Name: category; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.category AS character varying NOT NULL;


ALTER DOMAIN public.category OWNER TO postgres;

--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 617
-- Name: DOMAIN category; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.category IS 'Domain of all the different categories within the options.';


--
-- TOC entry 614 (class 1247 OID 16438)
-- Name: date; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.date AS date NOT NULL;


ALTER DOMAIN public.date OWNER TO postgres;

--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 614
-- Name: DOMAIN date; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.date IS 'Domain off all the possible dates.';


--
-- TOC entry 604 (class 1247 OID 16431)
-- Name: email; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.email AS character varying NOT NULL
	CONSTRAINT valid_email CHECK (((VALUE)::text ~~ '%@%'::text));


ALTER DOMAIN public.email OWNER TO postgres;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 604
-- Name: DOMAIN email; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.email IS 'This will contain all of the possible emails from the user.';


--
-- TOC entry 611 (class 1247 OID 16436)
-- Name: options; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.options AS character(1) NOT NULL;


ALTER DOMAIN public.options OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 611
-- Name: DOMAIN options; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.options IS 'Will contain all of the options offered to the costumer.';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 200 (class 1259 OID 16569)
-- Name: consumption_post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consumption_post (
    username character varying NOT NULL,
    consume character varying NOT NULL,
    state boolean NOT NULL,
    date timestamp without time zone NOT NULL,
    "consID" bigint NOT NULL
);


ALTER TABLE public.consumption_post OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16757)
-- Name: consumption_post_consID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."consumption_post_consID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."consumption_post_consID_seq" OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 207
-- Name: consumption_post_consID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."consumption_post_consID_seq" OWNED BY public.consumption_post."consID";


--
-- TOC entry 197 (class 1259 OID 16527)
-- Name: energy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.energy (
    "typeHouse" character varying NOT NULL,
    "amountRoomates" integer NOT NULL,
    "amountEnergy" integer NOT NULL,
    "greenEnergy" boolean NOT NULL,
    "efficientLighting" boolean NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.energy OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE energy; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.energy IS 'information stored through the energy survey';


--
-- TOC entry 198 (class 1259 OID 16540)
-- Name: food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.food (
    "amountOrganicFood" integer NOT NULL,
    "amountLocalFood" integer NOT NULL,
    "amountWastedFood" integer NOT NULL,
    "amountFoodPackaged" integer NOT NULL,
    "amountFoodCompost" integer NOT NULL,
    "dietChoice" character varying NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.food OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16648)
-- Name: food_post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.food_post (
    username character varying NOT NULL,
    meal character varying NOT NULL,
    type character varying NOT NULL,
    date timestamp without time zone,
    "postID" bigint NOT NULL
);


ALTER TABLE public.food_post OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16732)
-- Name: food_post_postID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."food_post_postID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."food_post_postID_seq" OWNER TO postgres;

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 205
-- Name: food_post_postID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."food_post_postID_seq" OWNED BY public.food_post."postID";


--
-- TOC entry 208 (class 1259 OID 16773)
-- Name: friendship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friendship (
    usuario_one_id bigint NOT NULL,
    usuario_two_id bigint NOT NULL,
    status smallint NOT NULL,
    action_user_id bigint NOT NULL,
    CONSTRAINT smaller_than_constraint CHECK ((usuario_one_id < usuario_two_id))
);


ALTER TABLE public.friendship OWNER TO postgres;

--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 208
-- Name: CONSTRAINT smaller_than_constraint ON friendship; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT smaller_than_constraint ON public.friendship IS 'Check that the usuario_one_ user id is smaller than the usuario_two_id.';


--
-- TOC entry 202 (class 1259 OID 16661)
-- Name: lifestyle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lifestyle (
    "howOftenBigPurchase" character varying NOT NULL,
    "weeklyWaste" character varying NOT NULL,
    recycle character varying NOT NULL,
    "applianceUse" character varying NOT NULL,
    "aveShowerTime" integer NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.lifestyle OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16553)
-- Name: transportation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportation (
    "aveHoursFly" double precision NOT NULL,
    "aveKmPerDay" double precision NOT NULL,
    "typeOfCar" character varying NOT NULL,
    "mainModeTransport" character varying NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.transportation OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16674)
-- Name: transportation_post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportation_post (
    username character varying NOT NULL,
    type character varying NOT NULL,
    date timestamp without time zone NOT NULL,
    "transID" bigint NOT NULL,
    kilometers double precision NOT NULL
);


ALTER TABLE public.transportation_post OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16745)
-- Name: transportation_post_transID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."transportation_post_transID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."transportation_post_transID_seq" OWNER TO postgres;

--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 206
-- Name: transportation_post_transID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."transportation_post_transID_seq" OWNED BY public.transportation_post."transID";


--
-- TOC entry 196 (class 1259 OID 16515)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    username character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    "appUserID" bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.usuario IS 'User relation';


--
-- TOC entry 204 (class 1259 OID 16720)
-- Name: usuario_appUserID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."usuario_appUserID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."usuario_appUserID_seq" OWNER TO postgres;

--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuario_appUserID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."usuario_appUserID_seq" OWNED BY public.usuario."appUserID";


--
-- TOC entry 2751 (class 2604 OID 16759)
-- Name: consumption_post consID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post ALTER COLUMN "consID" SET DEFAULT nextval('public."consumption_post_consID_seq"'::regclass);


--
-- TOC entry 2752 (class 2604 OID 16734)
-- Name: food_post postID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post ALTER COLUMN "postID" SET DEFAULT nextval('public."food_post_postID_seq"'::regclass);


--
-- TOC entry 2753 (class 2604 OID 16747)
-- Name: transportation_post transID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post ALTER COLUMN "transID" SET DEFAULT nextval('public."transportation_post_transID_seq"'::regclass);


--
-- TOC entry 2750 (class 2604 OID 16722)
-- Name: usuario appUserID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN "appUserID" SET DEFAULT nextval('public."usuario_appUserID_seq"'::regclass);


--
-- TOC entry 2766 (class 2606 OID 16768)
-- Name: consumption_post consumption_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post
    ADD CONSTRAINT consumption_post_pkey PRIMARY KEY ("consID");


--
-- TOC entry 2756 (class 2606 OID 16524)
-- Name: usuario email_uniqueness_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT email_uniqueness_constraint UNIQUE (email);


--
-- TOC entry 2760 (class 2606 OID 16534)
-- Name: energy energy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.energy
    ADD CONSTRAINT energy_pkey PRIMARY KEY (username);


--
-- TOC entry 2762 (class 2606 OID 16547)
-- Name: food food_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT food_pkey PRIMARY KEY (username);


--
-- TOC entry 2768 (class 2606 OID 16743)
-- Name: food_post food_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post
    ADD CONSTRAINT food_post_pkey PRIMARY KEY ("postID");


--
-- TOC entry 2770 (class 2606 OID 16668)
-- Name: lifestyle lifestyle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lifestyle
    ADD CONSTRAINT lifestyle_pkey PRIMARY KEY (username);


--
-- TOC entry 2764 (class 2606 OID 16560)
-- Name: transportation transportation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation
    ADD CONSTRAINT transportation_pkey PRIMARY KEY (username);


--
-- TOC entry 2772 (class 2606 OID 16756)
-- Name: transportation_post transportation_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post
    ADD CONSTRAINT transportation_post_pkey PRIMARY KEY ("transID");


--
-- TOC entry 2758 (class 2606 OID 16526)
-- Name: usuario username_uniqueness_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT username_uniqueness_constraint UNIQUE (username);


--
-- TOC entry 2773 (class 1259 OID 16777)
-- Name: friendship_unique_pair; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX friendship_unique_pair ON public.friendship USING btree (usuario_one_id, usuario_two_id);


--
-- TOC entry 2777 (class 2606 OID 16577)
-- Name: consumption_post ConsumptionPost_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post
    ADD CONSTRAINT "ConsumptionPost_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2774 (class 2606 OID 16535)
-- Name: energy energy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.energy
    ADD CONSTRAINT energy_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2775 (class 2606 OID 16548)
-- Name: food energy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT energy_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2778 (class 2606 OID 16656)
-- Name: food_post food-post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post
    ADD CONSTRAINT "food-post_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2779 (class 2606 OID 16669)
-- Name: lifestyle lifestyle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lifestyle
    ADD CONSTRAINT lifestyle_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2780 (class 2606 OID 16682)
-- Name: transportation_post transportation-post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post
    ADD CONSTRAINT "transportation-post_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2776 (class 2606 OID 16561)
-- Name: transportation transportation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation
    ADD CONSTRAINT transportation_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


-- Completed on 2019-03-24 21:39:58

--
-- PostgreSQL database dump complete
--

