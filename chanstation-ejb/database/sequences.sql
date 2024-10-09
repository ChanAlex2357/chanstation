-- Sequence and Function for type_carburant
CREATE SEQUENCE type_carburant_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_TYPE_CARBURANT_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT type_carburant_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for carburant
CREATE SEQUENCE carburant_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_CARBURANT_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT carburant_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for cuve
CREATE SEQUENCE cuve_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_CUVE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT cuve_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for pompe
CREATE SEQUENCE pompe_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_POMPE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompe_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for pompiste
CREATE SEQUENCE pompiste_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_POMPISTE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompiste_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for prelevement_pompe
CREATE SEQUENCE prelevement_pompe_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_PRELEVEMENT_POMPE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT prelevement_pompe_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for inventaire
CREATE SEQUENCE inventaire_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_INVENTAIRE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT inventaire_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for type_mvt
CREATE SEQUENCE type_mvt_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_TYPE_MVT_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT type_mvt_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for equivalence
CREATE SEQUENCE equivalence_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_EQUIVALENCE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT equivalence_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for prelevement_cuve
CREATE SEQUENCE prelevement_cuve_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_PRELEVEMENT_CUVE_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT prelevement_cuve_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/

-- Sequence and Function for mvt_stock
CREATE SEQUENCE mvt_stock_seq START WITH 1;

CREATE OR REPLACE FUNCTION GET_NEXT_MVT_STOCK_ID
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT mvt_stock_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
