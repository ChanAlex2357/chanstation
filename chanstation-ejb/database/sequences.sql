CREATE SEQUENCE type_carburant_seq START WITH 1;
CREATE SEQUENCE cuve_seq START WITH 1;
CREATE SEQUENCE pompiste_seq START WITH 1;
CREATE SEQUENCE pompe_seq START WITH 1;
CREATE SEQUENCE carburant_seq START WITH 1;
CREATE SEQUENCE prelevement_seq START WITH 1;
CREATE SEQUENCE stock_seq START WITH 1;
CREATE SEQUENCE unite_seq START WITH 1;


CREATE OR REPLACE FUNCTION GET_SEQ_TYPE_CARBURANT
   RETURN NUMBER
IS
   retour   NUMBER;
BEGIN
   SELECT TYPE_CARBURANT_SEQ.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_CUVE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT cuve_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_POMPISTE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompiste_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_POMPE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT pompe_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_CARBURANT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT carburant_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_PRELEVEMENT
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT prelevement_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_STOCK
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT stock_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
CREATE OR REPLACE FUNCTION GET_SEQ_UNITE
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT unite_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/