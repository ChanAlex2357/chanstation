CREATE TABLE type_carburant(
   id_type_carburant VARCHAR(255),
   libelle VARCHAR(255) NOT NULL,
   dsce VARCHAR(255),
   PRIMARY KEY(id_type_carburant)
);

CREATE TABLE carburant(
   id_carburant VARCHAR(255),
   nom VARCHAR(255) NOT NULL,
   desce VARCHAR(255),
   pu_vente DECIMAL(15,2) NOT NULL,
   pu_achat DECIMAL(15,2) NOT NULL,
   id_type_carburant VARCHAR(255) NOT NULL,
   PRIMARY KEY(id_carburant),
   FOREIGN KEY(id_type_carburant) REFERENCES type_carburant(id_type_carburant)
);

CREATE TABLE cuve(
   id_cuve VARCHAR(255),
   nom VARCHAR(255) NOT NULL,
   capacite DECIMAL(15,2) NOT NULL,
   id_carburant VARCHAR(255) NOT NULL,
   PRIMARY KEY(id_cuve),
   FOREIGN KEY(id_carburant) REFERENCES carburant(id_carburant)
);

CREATE TABLE pompe(
   id_pompe VARCHAR(255),
   nom VARCHAR(255) NOT NULL,
   id_cuve VARCHAR(255) NOT NULL,
   PRIMARY KEY(id_pompe),
   FOREIGN KEY(id_cuve) REFERENCES cuve(id_cuve)
);

CREATE TABLE pompiste(
   id_pompiste VARCHAR(255),
   nom VARCHAR(255) NOT NULL,
   PRIMARY KEY(id_pompiste)
);

CREATE TABLE prelevement(
   id_prelevement VARCHAR(255),
   daty DATE NOT NULL,
   heure TIME NOT NULL,
   compteur DECIMAL(15,2) NOT NULL,
   id_prelevement_anterieure VARCHAR(255),
   id_pompiste VARCHAR(255) NOT NULL,
   id_pompe VARCHAR(255) NOT NULL,
   PRIMARY KEY(id_prelevement),
   FOREIGN KEY(id_pompiste) REFERENCES pompiste(id_pompiste),
   FOREIGN KEY(id_pompe) REFERENCES pompe(id_pompe)
);


CREATE TABLE type_mvt(
   Id_type_mvt VARCHAR(255) ,
   labelle VARCHAR(255)  NOT NULL,
   PRIMARY KEY(Id_type_mvt)
);

CREATE TABLE limit_cuve(
   id_limit_cuve VARCHAR(255) ,
   limit NUMBER(15,2)   NOT NULL,
   qte NUMBER(15,2)   NOT NULL,
   id_cuve VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_limit_cuve),
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
