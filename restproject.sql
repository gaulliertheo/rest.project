SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS restproject DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE restproject;

DROP TABLE IF EXISTS book;
CREATE TABLE IF NOT EXISTS book (
  id int(11) NOT NULL,
  title varchar(45) COLLATE utf8_bin NOT NULL,
  author varchar(45) COLLATE utf8_bin NOT NULL,
  summary text COLLATE utf8_bin NOT NULL,
  pushingHouse varchar(45) COLLATE utf8_bin NOT NULL,
  publishingDate date DEFAULT NULL,
  kind varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO book (id, title, author, summary, pushingHouse, publishingDate, kind) VALUES
(2, 'One piece tome n1', 'Eiichirō Oda', 'Luffy c\'est un pirate. Il a des ami.e.s...', 'Shueisha', '1961-09-30', 'Manga'),
(3, 'One piece tome n4', 'Eiichirō Oda', 'Luffy c\'est un pirate. Il a des ami.e.s...', 'Shueisha', '1961-09-30', 'Manga'),
(9, 'One piece tome n2', 'Eiichirō Oda', 'Luffy c\'est un pirate. Il a des ami.e.s...', 'Shueisha', '1961-09-30', 'Manga'),
(10, 'One piece tome n667', 'Eiichirō Oda', 'Luffy c\'est un pirate. Il a des ami.e.s...', 'Shueisha', '1961-09-30', 'Manga'),
(12, 'One piece tome n43', 'Eiichirō Oda', 'Luffy c\'est un pirate. Il a des ami.e.s...', 'Shueisha', '1961-09-30', 'Manga');

DROP TABLE IF EXISTS comment;
CREATE TABLE IF NOT EXISTS `comment` (
  mediaID int(11) NOT NULL,
  userID int(11) NOT NULL,
  description text COLLATE utf8_bin,
  rate decimal(2,1) NOT NULL,
  PRIMARY KEY (mediaID,userID)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `comment` (mediaID, userID, description, rate) VALUES
(1, 2, 'J\'adore', '4.1'),
(1, 5, 'J\'adore encore plus', '4.5'),
(3, 1, 'J\'adore', '3.0'),
(3, 4, 'J\'adore encore plus', '3.6'),
(4, 3, 'J\'aime', '5.0'),
(4, 5, 'J\'adore plus ou moins. Je veux juste écrire surtout', '4.5'),
(4, 6, 'Bof', '4.6');

DROP TABLE IF EXISTS dvd;
CREATE TABLE IF NOT EXISTS dvd (
  id int(11) NOT NULL,
  name varchar(45) COLLATE utf8_bin NOT NULL,
  kind varchar(45) COLLATE utf8_bin DEFAULT NULL,
  director text COLLATE utf8_bin NOT NULL,
  publishingDate date DEFAULT NULL,
  listActor text COLLATE utf8_bin NOT NULL,
  summary text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO dvd (id, `name`, kind, director, publishingDate, listActor, summary) VALUES
(1, 'Intouchables', 'Comédie dramatique', 'Quelqu\'un', '2011-12-12', 'Omar Sy, Eric Judor', 'L\'histoire d\'un gars qui se lève un matin...'),
(6, 'Touchables', 'Comédie', 'Quelqu\'un', '2001-02-12', 'Omar Sy, Eric Judor', 'L\'histoire. Cherche pas...'),
(7, 'Unpeutouchables', ' Drame', 'Toujours quelqu\'un', '2011-12-12', 'Omar Sy, Eric Judor', 'Un gars, il se lève un matin et le soir il s\'assied...'),
(11, 'Untouchable', 'Comedy', 'Someone', '2011-12-10', 'Omar Sy, Eric Judor', 'This is the same story. BUT in english please');

DROP TABLE IF EXISTS media;
CREATE TABLE IF NOT EXISTS media (
  id int(11) NOT NULL AUTO_INCREMENT,
  userID int(11) DEFAULT NULL,
  globalRate decimal(2,1) NOT NULL,
  borrowed tinyint(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO media (id, userID, globalRate, borrowed) VALUES
(1, 1, '4.3', 0),
(2, 1, '0.0', 0),
(3, 2, '3.3', 0),
(4, 2, '4.7', 1),
(5, 2, '0.0', 0),
(6, 3, '0.0', 1),
(7, 5, '0.0', 0),
(8, 6, '0.0', 1),
(9, 6, '0.0', 0),
(10, 3, '0.0', 1),
(11, 1, '0.0', 1),
(12, 1, '0.0', 1);

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS `user` (
  id int(11) NOT NULL AUTO_INCREMENT,
  mail varchar(45) COLLATE utf8_bin NOT NULL,
  name varchar(45) COLLATE utf8_bin NOT NULL,
  city varchar(45) COLLATE utf8_bin NOT NULL,
  address varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY mail (mail)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `user` (id, mail, `name`, city, address) VALUES
(1, 'nuzumaki@gmail.com', 'Naruto', 'Konoha', '3 rue des vents'),
(2, 'hhyuga@gmail.com', 'Hinata', 'Konoha', '12 avenue timide'),
(3, 'oggy@gmail.com', 'Oggy', 'Minikeums', '45 rue cafardesque'),
(4, 'cewing@gmail.com', 'Clover', 'Gladis', '33 allée parla'),
(5, 'bbrief@gmail.com', 'Bulma', 'Namek', '21 rue capsule corporation'),
(6, 'dlexploratrice@gmail.com', 'Dora', 'LaJungle', '40 rue chipeur');

DROP TABLE IF EXISTS videogame;
CREATE TABLE IF NOT EXISTS videogame (
  id int(11) NOT NULL,
  name varchar(45) COLLATE utf8_bin NOT NULL,
  kind varchar(45) COLLATE utf8_bin DEFAULT NULL,
  developer varchar(45) COLLATE utf8_bin NOT NULL,
  publishingDate date DEFAULT NULL,
  support text COLLATE utf8_bin NOT NULL,
  description text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO videogame (id, `name`, kind, developer, publishingDate, support, description) VALUES
(4, 'Splinter Cell: Double Agent', 'La guerre', 'Ubisoft', '2006-10-17', 'Windows, PlayStation 2, Xbox, GameCube, PlayStation 3, Xbox 360, Wii, téléphone mobile', 'T\'es un gentil espion qui espionnes..'),
(5, 'Splinter Cell: Conviction', 'La guerre', 'Ubisoft', '2010-04-13', 'Windows, Mac OS X, Xbox 360 Android, iOS, Windows Phone, Bada', 'T\'espionnes toujours...'),
(8, 'Splinter Cell: Blacklist', 'La guerre', 'Ubisoft', '2013-08-20', 'Windows, PlayStation 3, Xbox 360, Wii U', 'Je crois que tu fais que ça...');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
