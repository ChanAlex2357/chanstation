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
   heure DATE NOT NULL,
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
