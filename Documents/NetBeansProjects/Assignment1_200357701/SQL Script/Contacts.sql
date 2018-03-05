DROP DATABASE IF EXISTS contacts_db;
create database contacts_db;
use contacts_db;

DROP TABLE IF EXISTS contacts;
create table contacts
 (
 ContactID INT NOT NULL auto_increment PRIMARY KEY,
 firstName VARCHAR (30),
 lastName VARCHAR (30),
 phone VARCHAR(14),
 address VARCHAR(80),
 birthday DATE,
 imageFile VARCHAR(100)
 
 );
 SELECT * FROM contacts;
 
 INSERT INTO contacts(firstName,lastName,phone,address,birthday,imageFile) VALUES
 ('Tanveer','Kalia','705-970-7430','317 Grove Street', '1998-11-13','DefaultImage');
  SELECT * FROM contacts;
  
  
  
  
  