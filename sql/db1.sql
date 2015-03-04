-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.22-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных java_html_parser
DROP DATABASE IF EXISTS `java_html_parser`;
CREATE DATABASE IF NOT EXISTS `java_html_parser` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `java_html_parser`;


-- Дамп структуры для таблица java_html_parser.categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы java_html_parser.categories: ~10 rows (приблизительно)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
REPLACE INTO `categories` (`id`, `name`, `url`, `enable`) VALUES
	(51, 'Собаки, щенки', 'dogs/', b'1'),
	(52, 'Кошки, котята', 'cats/', b'1'),
	(53, 'Птицы', 'birds/', b'1'),
	(54, 'Грызуны', 'rodents/', b'1'),
	(55, 'Рептилии', 'reptile/', b'1'),
	(56, 'Рыбки', 'fish/', b'1'),
	(57, 'Насекомые', 'insects/', b'1'),
	(58, 'Лошади', 'horses/', b'1'),
	(59, 'Домашний скот', 'Домашний-скот/', b'1'),
	(60, 'Экзотические животные', 'exotic/', b'1');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;


-- Дамп структуры для таблица java_html_parser.owners
DROP TABLE IF EXISTS `owners`;
CREATE TABLE IF NOT EXISTS `owners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы java_html_parser.owners: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `owners` DISABLE KEYS */;
/*!40000 ALTER TABLE `owners` ENABLE KEYS */;


-- Дамп структуры для таблица java_html_parser.pets
DROP TABLE IF EXISTS `pets`;
CREATE TABLE IF NOT EXISTS `pets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` text,
  `date` datetime DEFAULT NULL,
  `categories_id` int(11) DEFAULT NULL,
  `owners_id` int(11) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pets_owners` (`owners_id`),
  KEY `fk_pets_categories` (`categories_id`),
  CONSTRAINT `fk_pets_categories` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pets_owners` FOREIGN KEY (`owners_id`) REFERENCES `owners` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы java_html_parser.pets: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
