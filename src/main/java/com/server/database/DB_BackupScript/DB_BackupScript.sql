--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.2

-- Started on 2019-04-12 12:52:23

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
-- TOC entry 603 (class 1247 OID 33430)
-- Name: ID; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public."ID" AS bigint NOT NULL DEFAULT 0;


ALTER DOMAIN public."ID" OWNER TO postgres;

--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 603
-- Name: DOMAIN "ID"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public."ID" IS 'The id domain contains all of the possible values for the ids used in the application';


--
-- TOC entry 606 (class 1247 OID 33432)
-- Name: age; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.age AS integer NOT NULL DEFAULT 0;


ALTER DOMAIN public.age OWNER TO postgres;

--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 606
-- Name: DOMAIN age; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.age IS 'This will be the domain of all possible ages.';


--
-- TOC entry 609 (class 1247 OID 33434)
-- Name: category; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.category AS character varying NOT NULL;


ALTER DOMAIN public.category OWNER TO postgres;

--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 609
-- Name: DOMAIN category; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.category IS 'Domain of all the different categories within the options.';


--
-- TOC entry 612 (class 1247 OID 33436)
-- Name: date; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.date AS date NOT NULL;


ALTER DOMAIN public.date OWNER TO postgres;

--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 612
-- Name: DOMAIN date; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.date IS 'Domain off all the possible dates.';


--
-- TOC entry 615 (class 1247 OID 33438)
-- Name: email; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.email AS character varying NOT NULL
	CONSTRAINT valid_email CHECK (((VALUE)::text ~~ '%@%'::text));


ALTER DOMAIN public.email OWNER TO postgres;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 615
-- Name: DOMAIN email; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.email IS 'This will contain all of the possible emails from the user.';


--
-- TOC entry 619 (class 1247 OID 33441)
-- Name: options; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.options AS character(1) NOT NULL;


ALTER DOMAIN public.options OWNER TO postgres;

--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 619
-- Name: DOMAIN options; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON DOMAIN public.options IS 'Will contain all of the options offered to the costumer.';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 33442)
-- Name: achievements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.achievements (
    "firstPost" boolean NOT NULL,
    "firstFriend" boolean NOT NULL,
    hundkm boolean NOT NULL,
    "fiveDayRecycle" boolean NOT NULL,
    "topRank" boolean NOT NULL,
    "lowEco" boolean NOT NULL,
    username character varying NOT NULL,
    "fiveDaysv" boolean NOT NULL,
    "carblSix" boolean NOT NULL,
    "locaL" boolean NOT NULL,
    "loggeD" boolean NOT NULL
);


ALTER TABLE public.achievements OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 33448)
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
-- TOC entry 198 (class 1259 OID 33454)
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
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 198
-- Name: consumption_post_consID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."consumption_post_consID_seq" OWNED BY public.consumption_post."consID";


--
-- TOC entry 199 (class 1259 OID 33456)
-- Name: energy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.energy (
    "typeHouse" character varying NOT NULL,
    "amountRoomates" integer NOT NULL,
    "greenEnergy" boolean NOT NULL,
    "efficientLighting" boolean NOT NULL,
    username character varying NOT NULL,
    "amountEnergy" character varying NOT NULL
);


ALTER TABLE public.energy OWNER TO postgres;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 199
-- Name: TABLE energy; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.energy IS 'information stored through the energy survey';


--
-- TOC entry 200 (class 1259 OID 33462)
-- Name: food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.food (
    organic character varying NOT NULL,
    area character varying NOT NULL,
    wasted character varying NOT NULL,
    packaged character varying NOT NULL,
    compost character varying NOT NULL,
    diet_choice character varying NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.food OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 33468)
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
-- TOC entry 202 (class 1259 OID 33474)
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
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 202
-- Name: food_post_postID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."food_post_postID_seq" OWNED BY public.food_post."postID";


--
-- TOC entry 203 (class 1259 OID 33476)
-- Name: friendship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friendship (
    "firstUsername" character varying NOT NULL,
    "secondUsername" character varying NOT NULL,
    status integer NOT NULL,
    "actionUsername" character varying NOT NULL
);


ALTER TABLE public.friendship OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 33482)
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
-- TOC entry 205 (class 1259 OID 33488)
-- Name: scores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scores (
    greenstreak bigint NOT NULL,
    score bigint NOT NULL,
    footprint double precision NOT NULL,
    ecofootprint double precision NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.scores OWNER TO postgres;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE scores; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.scores IS 'This relation will hold the information regarding the scoring system.';


--
-- TOC entry 206 (class 1259 OID 33494)
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
-- TOC entry 207 (class 1259 OID 33500)
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
-- TOC entry 208 (class 1259 OID 33506)
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
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 208
-- Name: transportation_post_transID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."transportation_post_transID_seq" OWNED BY public.transportation_post."transID";


--
-- TOC entry 209 (class 1259 OID 33508)
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
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.usuario IS 'User relation';


--
-- TOC entry 210 (class 1259 OID 33514)
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
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuario_appUserID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."usuario_appUserID_seq" OWNED BY public.usuario."appUserID";


--
-- TOC entry 2761 (class 2604 OID 33516)
-- Name: consumption_post consID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post ALTER COLUMN "consID" SET DEFAULT nextval('public."consumption_post_consID_seq"'::regclass);


--
-- TOC entry 2762 (class 2604 OID 33517)
-- Name: food_post postID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post ALTER COLUMN "postID" SET DEFAULT nextval('public."food_post_postID_seq"'::regclass);


--
-- TOC entry 2763 (class 2604 OID 33518)
-- Name: transportation_post transID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post ALTER COLUMN "transID" SET DEFAULT nextval('public."transportation_post_transID_seq"'::regclass);


--
-- TOC entry 2764 (class 2604 OID 33519)
-- Name: usuario appUserID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN "appUserID" SET DEFAULT nextval('public."usuario_appUserID_seq"'::regclass);


--
-- TOC entry 2766 (class 2606 OID 33521)
-- Name: achievements achievements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.achievements
    ADD CONSTRAINT achievements_pkey PRIMARY KEY (username);


--
-- TOC entry 2768 (class 2606 OID 33523)
-- Name: consumption_post consumption_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post
    ADD CONSTRAINT consumption_post_pkey PRIMARY KEY ("consID");


--
-- TOC entry 2785 (class 2606 OID 33525)
-- Name: usuario email_uniqueness_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT email_uniqueness_constraint UNIQUE (email);


--
-- TOC entry 2770 (class 2606 OID 33527)
-- Name: energy energy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.energy
    ADD CONSTRAINT energy_pkey PRIMARY KEY (username);


--
-- TOC entry 2772 (class 2606 OID 33529)
-- Name: food food_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT food_pkey PRIMARY KEY (username);


--
-- TOC entry 2774 (class 2606 OID 33531)
-- Name: food_post food_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post
    ADD CONSTRAINT food_post_pkey PRIMARY KEY ("postID");


--
-- TOC entry 2777 (class 2606 OID 33533)
-- Name: lifestyle lifestyle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lifestyle
    ADD CONSTRAINT lifestyle_pkey PRIMARY KEY (username);


--
-- TOC entry 2779 (class 2606 OID 33535)
-- Name: scores scores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT scores_pkey PRIMARY KEY (username);


--
-- TOC entry 2781 (class 2606 OID 33537)
-- Name: transportation transportation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation
    ADD CONSTRAINT transportation_pkey PRIMARY KEY (username);


--
-- TOC entry 2783 (class 2606 OID 33539)
-- Name: transportation_post transportation_post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post
    ADD CONSTRAINT transportation_post_pkey PRIMARY KEY ("transID");


--
-- TOC entry 2787 (class 2606 OID 33541)
-- Name: usuario username_uniqueness_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT username_uniqueness_constraint UNIQUE (username);


--
-- TOC entry 2775 (class 1259 OID 33542)
-- Name: friendship_unique_pair; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX friendship_unique_pair ON public.friendship USING btree ("firstUsername", "secondUsername");


--
-- TOC entry 2789 (class 2606 OID 33543)
-- Name: consumption_post ConsumptionPost_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumption_post
    ADD CONSTRAINT "ConsumptionPost_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2788 (class 2606 OID 33548)
-- Name: achievements achievements_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.achievements
    ADD CONSTRAINT achievements_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2790 (class 2606 OID 33553)
-- Name: energy energy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.energy
    ADD CONSTRAINT energy_fkey FOREIGN KEY (username) REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2791 (class 2606 OID 33558)
-- Name: food energy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT energy_fkey FOREIGN KEY (username) REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2792 (class 2606 OID 33563)
-- Name: food_post food-post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_post
    ADD CONSTRAINT "food-post_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2793 (class 2606 OID 33568)
-- Name: friendship friendship2_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendship
    ADD CONSTRAINT friendship2_fkey FOREIGN KEY ("secondUsername") REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2794 (class 2606 OID 33573)
-- Name: friendship friendship_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendship
    ADD CONSTRAINT friendship_fkey FOREIGN KEY ("firstUsername") REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 2794
-- Name: CONSTRAINT friendship_fkey ON friendship; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT friendship_fkey ON public.friendship IS 'Key relating the user with friendship';


--
-- TOC entry 2795 (class 2606 OID 33578)
-- Name: lifestyle liefestyle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lifestyle
    ADD CONSTRAINT liefestyle_fkey FOREIGN KEY (username) REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2796 (class 2606 OID 33583)
-- Name: scores score_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scores
    ADD CONSTRAINT score_fkey FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2798 (class 2606 OID 33588)
-- Name: transportation_post transportation-post_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation_post
    ADD CONSTRAINT "transportation-post_fkey" FOREIGN KEY (username) REFERENCES public.usuario(username);


--
-- TOC entry 2797 (class 2606 OID 33593)
-- Name: transportation transportation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportation
    ADD CONSTRAINT transportation_fkey FOREIGN KEY (username) REFERENCES public.usuario(username) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-04-12 12:52:24

--
-- PostgreSQL database dump complete
--

