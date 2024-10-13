-- Suppression des tables en respectant les contraintes de clés étrangères
DROP TABLE prelevement_pompe CASCADE CONSTRAINTS;
DROP TABLE mvt_stock CASCADE CONSTRAINTS;
DROP TABLE prelevement_cuve CASCADE CONSTRAINTS;
DROP TABLE equivalence CASCADE CONSTRAINTS;
DROP TABLE inventaire CASCADE CONSTRAINTS;
DROP TABLE pompe CASCADE CONSTRAINTS;
DROP TABLE cuve CASCADE CONSTRAINTS;
DROP TABLE carburant CASCADE CONSTRAINTS;
DROP TABLE type_carburant CASCADE CONSTRAINTS;
DROP TABLE unite CASCADE CONSTRAINTS;
DROP TABLE pompiste CASCADE CONSTRAINTS;
DROP TABLE type_mvt CASCADE CONSTRAINTS;
DROP TABLE factureclient CASCADE CONSTRAINTS;

-- Suppression des séquences
DROP SEQUENCE pompiste_seq;
DROP SEQUENCE type_mvt_seq;
DROP SEQUENCE unite_seq;
DROP SEQUENCE type_carburant_seq;
DROP SEQUENCE carburant_seq;
DROP SEQUENCE cuve_seq;
DROP SEQUENCE pompe_seq;
DROP SEQUENCE prelevement_pompe_seq;
DROP SEQUENCE inventaire_seq;
DROP SEQUENCE equivalence_seq;
DROP SEQUENCE prelevement_cuve_seq;
DROP SEQUENCE mvt_stock_seq;
DROP SEQUENCE factureclient_seq;


-- Création des séquences
CREATE SEQUENCE pompiste_seq START WITH 1;
CREATE SEQUENCE type_mvt_seq START WITH 1;
CREATE SEQUENCE unite_seq START WITH 1;
CREATE SEQUENCE type_carburant_seq START WITH 1;
CREATE SEQUENCE carburant_seq START WITH 1;
CREATE SEQUENCE cuve_seq START WITH 1;
CREATE SEQUENCE pompe_seq START WITH 1;
CREATE SEQUENCE prelevement_pompe_seq START WITH 1;
CREATE SEQUENCE inventaire_seq START WITH 1;
CREATE SEQUENCE equivalence_seq START WITH 1;
CREATE SEQUENCE prelevement_cuve_seq START WITH 1;
CREATE SEQUENCE mvt_stock_seq START WITH 1;
CREATE SEQUENCE factureclient_seq START WITH 1;
CREATE TABLE pompiste(
   id_pompiste VARCHAR(255) ,
   nom VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_pompiste)
);

CREATE TABLE type_mvt(
   id_type_mvt VARCHAR(255) ,
   valeur NUMBER(10) NOT NULL,
   desce VARCHAR(255) ,
   PRIMARY KEY(id_type_mvt)
);

CREATE TABLE unite(
   id_unite VARCHAR(255) ,
   val VARCHAR(255)  NOT NULL,
   desce VARCHAR(255) ,
   PRIMARY KEY(id_unite)
);

CREATE TABLE type_carburant(
   id_type_carburant VARCHAR(255) ,
   libelle VARCHAR(255)  NOT NULL,
   dsce VARCHAR(255) ,
   id_unite VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_type_carburant),
   FOREIGN KEY(id_unite) REFERENCES unite(id_unite)
);

CREATE TABLE carburant(
   id_carburant VARCHAR(255) ,
   nom VARCHAR(255)  NOT NULL,
   desce VARCHAR(255) ,
   pu_vente NUMBER(15,2)   NOT NULL,
   pu_achat NUMBER(15,2)   NOT NULL,
   id_type_carburant VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_carburant),
   FOREIGN KEY(id_type_carburant) REFERENCES type_carburant(id_type_carburant)
);

CREATE TABLE cuve(
   id_cuve VARCHAR(255) ,
   nom VARCHAR(255)  NOT NULL,
   capacite NUMBER(15,2)   NOT NULL,
   id_carburant VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_cuve),
   FOREIGN KEY(id_carburant) REFERENCES carburant(id_carburant)
);

CREATE TABLE pompe(
   id_pompe VARCHAR(255) ,
   nom VARCHAR(255)  NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_pompe),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE prelevement_pompe(
   id_prelevement_pompe VARCHAR(255) ,
   daty DATE NOT NULL,
   heure VARCHAR(255)  NOT NULL,
   compteur NUMBER(15,2)   NOT NULL,
   id_prelevement_anterieure VARCHAR(255) ,
   id_pompiste VARCHAR(255)  NOT NULL,
   id_pompe VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_prelevement_pompe),
   FOREIGN KEY(id_pompiste) REFERENCES pompiste(id_pompiste),
   FOREIGN KEY(id_pompe) REFERENCES pompe(id_pompe)
);

CREATE TABLE inventaire(
   id_inventaire VARCHAR(255) ,
   quantite NUMBER(15,2)   NOT NULL,
   cmup NUMBER(15,2)   NOT NULL,
   montant NUMBER(15,2)   NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_inventaire),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE equivalence(
   id_equivalence VARCHAR(255) ,
   limit NUMBER(15,2)   NOT NULL,
   qte NUMBER(15,2)   NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_equivalence),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE prelevement_cuve(
   id_prelevement_cuve VARCHAR(255) ,
   daty DATE NOT NULL,
   limit NUMBER(15,2)   NOT NULL,
   qte NUMBER(15,2)   NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_prelevement_cuve),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE mvt_stock(
   id_mvt_stock VARCHAR(255) ,
   daty DATE NOT NULL,
   quantite NUMBER(15,2)   NOT NULL,
   pu NUMBER(15,2)   NOT NULL,
   montant NUMBER(15,2)   NOT NULL,
   id_type_mvt VARCHAR(255)  NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_mvt_stock),
   FOREIGN KEY(id_type_mvt) REFERENCES type_mvt(id_type_mvt),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE factureclient(
   id_factureclient VARCHAR(255) ,
   pu NUMBER(15,2)   NOT NULL,
   quantite NUMBER(15,2)   NOT NULL,
   daty DATE NOT NULL,
   id_prelevement_pompe VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_factureclient),
   FOREIGN KEY(id_prelevement_pompe) REFERENCES prelevement_pompe(id_prelevement_pompe)
);


-- Sequence and Function for type_carburant

CREATE OR REPLACE FUNCTION GET_SEQ_TYPE_CARBURANT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT type_carburant_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for carburant

CREATE OR REPLACE FUNCTION GET_SEQ_CARBURANT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT carburant_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for cuve

CREATE OR REPLACE FUNCTION GET_SEQ_CUVE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT cuve_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for pompe

CREATE OR REPLACE FUNCTION GET_SEQ_POMPE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompe_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for pompiste

CREATE OR REPLACE FUNCTION GET_SEQ_POMPISTE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompiste_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for prelevement_pompe

CREATE OR REPLACE FUNCTION GET_SEQ_PRELEVEMENT_POMPE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT prelevement_pompe_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for inventaire

CREATE OR REPLACE FUNCTION GET_SEQ_INVENTAIRE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT inventaire_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for type_mvt

CREATE OR REPLACE FUNCTION GET_SEQ_TYPE_MVT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT type_mvt_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for equivalence

CREATE OR REPLACE FUNCTION GET_SEQ_EQUIVALENCE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT equivalence_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for prelevement_cuve

CREATE OR REPLACE FUNCTION GET_SEQ_PRELEVEMENT_CUVE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT prelevement_cuve_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for mvt_stock

CREATE OR REPLACE FUNCTION GET_SEQ_MVT_STOCK
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT mvt_stock_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for unite

CREATE OR REPLACE FUNCTION GET_SEQ_UNITE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT unite_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

CREATE OR REPLACE FUNCTION GET_SEQ_FACTURE_CLIENT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT factureclient_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/