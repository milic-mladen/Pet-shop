/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`petshop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `petshop`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) VALUES 
(1,'Mladen','Milic','mladen','mladen123'),
(2,'Kosta','Milic','kosta','kosta123');



DROP TABLE IF EXISTS `Kupac`;

CREATE TABLE `Kupac` (
  `KupacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ImeKupca` VARCHAR(30) NOT NULL,
  `PrezimeKupca` VARCHAR(30) NOT NULL,
  `KontaktTelefon` VARCHAR(50) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `Status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KupacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Kupac`(`KupacID`,`ImeKupca`,`PrezimeKupca`,`KontaktTelefon`,`Adresa`,`Status`) VALUES 
(1,'Stevan', 'Vasiljevic', '0631231234', 'Jurija Gagarina 113', 'BRONZE'),
(2,'Nikola', 'Mladenovic', '0654645434', 'Kraljice Marije 12', 'SILVER'),
(3,'Andjela', 'Milovukovic', '0641235153', 'Ruzveltova 33', 'GOLD');




DROP TABLE IF EXISTS `Dobavljac`;

CREATE TABLE `Dobavljac` (
  `DobavljacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivDobavljaca` VARCHAR(50) NOT NULL,
  `Drzava` VARCHAR(50) NOT NULL,
  `KontaktOsoba` VARCHAR(50) NOT NULL,
  `Kontakt` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`DobavljacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Dobavljac`(`DobavljacID`,`NazivDobavljaca`,`Drzava`,`KontaktOsoba`, `Kontakt`) VALUES 
(1,'Nelt', 'Srbija', 'Milica Konstantinovic', '0631231234'),
(2,'Unconditional', 'Hrvatska', 'Dragan Bulatovic', '0654645434');



DROP TABLE IF EXISTS `KategorijaProizvoda`;

CREATE TABLE `KategorijaProizvoda` (
  `KategorijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivKategorije` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KategorijaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `KategorijaProizvoda`(`KategorijaID`,`NazivKategorije`) VALUES 
(1,'Igracka'),
(2,'Hrana za pse'),
(3,'Hrana za macke');



DROP TABLE IF EXISTS `Proizvod`;

CREATE TABLE `Proizvod` (
  `ProizvodID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivProizvoda` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(50) NOT NULL,
  `Cena` VARCHAR(50) NOT NULL,
  `KategorijaID` BIGINT(10) UNSIGNED NOT NULL,	
  `DobavljacID` BIGINT(10) UNSIGNED NOT NULL,	
  PRIMARY KEY (`ProizvodID`),
  CONSTRAINT `fk_kategorija_id` FOREIGN KEY (`KategorijaID`) REFERENCES `KategorijaProizvoda` (`KategorijaID`),
  CONSTRAINT `fk_dobavljac_id` FOREIGN KEY (`DobavljacID`) REFERENCES `Dobavljac` (`DobavljacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT  INTO `Proizvod`(`ProizvodID`,`NazivProizvoda`,`Opis`,`Cena`, `KategorijaID`, `DobavljacID`) VALUES 
(1,'Angry Bird Crveni', 'Crvena pticica.', 500, 1, 1),
(2,'Teg koska', 'Gumena igracka.', 700, 1, 2),
(3,'N&D', 'Premium hrana za pse.', 600, 2, 1),
(4,'Orijen', 'Super premium hrana za sve.', 1000, 2, 2),
(5,'Whiskas', 'Premium hrana za macke.', 300, 3, 1),
(6,'Friskies', 'Super premium hrana za macke.', 600, 3, 2);



DROP TABLE IF EXISTS `Porudzbina`;

CREATE TABLE `Porudzbina` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `DatumDostave` DATE NOT NULL,
  `IznosBezPopusta` DECIMAL(10,2) NOT NULL,
  `Popust` DECIMAL(10,2) NOT NULL,
  `IznosSaPopustom` DECIMAL(10,2) NOT NULL,
  `KupacID` BIGINT(10) UNSIGNED NOT NULL,	
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,	
  PRIMARY KEY (`PorudzbinaID`),
  CONSTRAINT `fk_kupac_id` FOREIGN KEY (`KupacID`) REFERENCES `Kupac` (`KupacID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Porudzbina`(`PorudzbinaID`,`DatumVreme`,`DatumDostave`,`IznosBezPopusta`,`Popust`,`IznosSaPopustom`,`KupacID`, `AdministratorID`) VALUES 
(1,'2022-03-03 10:55:02','2022-03-10',2500,5,2375,1,1);


DROP TABLE IF EXISTS `StavkaPorudzbine`;

CREATE TABLE `StavkaPorudzbine` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `RbStavke` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `CenaStavke` DECIMAL(10,2) NOT NULL,
  `ProizvodID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`,`RbStavke`),
  CONSTRAINT `fk_porudzbina_id` FOREIGN KEY (`PorudzbinaID`) REFERENCES `Porudzbina` (`PorudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_proizvod_id` FOREIGN KEY (`ProizvodID`) REFERENCES `Proizvod` (`ProizvodID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaPorudzbine`(`PorudzbinaID`,`RbStavke`,`Kolicina`,`CenaStavke`, `ProizvodID`) VALUES 
(1,1,1,500,1),
(1,2,2,2000,4);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
