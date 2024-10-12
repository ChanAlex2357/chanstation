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
