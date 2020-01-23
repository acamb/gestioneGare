INSERT INTO PUBLIC.ARCIERI(ID, NOME, COGNOME, SESSO,DTYPE) VALUES
  (65, 'Pippo', 'Pippo', 'M','Arciere'),
  (66, 'Pluto', 'Pluto', 'M','Arciere'),
  (67, 'Paperino', 'Paperino', 'M','Arciere'),
  (68, 'Paperina', 'Paperina', 'F','Arciere');
INSERT INTO PUBLIC.CONFIGURAZIONE(ID,DATA_ANNO_SOCIETARIO) VALUES(1,'1/10');
INSERT INTO PUBLIC.TIPI_GARA (ID,NOME,DESCRIZIONE) VALUES
 (1,'FIOCCHI','Fiocchi'),
 (2,'INDOOR','Indoor');
INSERT INTO PUBLIC.DIVISIONI (ID,DESCRIZIONE,DEFAULT_GROUP,ENABLED) VALUES
(1,'Olimpico',1,1),
(2,'Compound',1,1);
