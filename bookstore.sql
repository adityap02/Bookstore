-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.24 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bookstore
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET armscii8 COLLATE armscii8_bin */;
USE `bookstore`;

-- Dumping structure for table bookstore.books
CREATE TABLE IF NOT EXISTS `books` (
  `base_price` decimal(38,2) DEFAULT NULL,
  `authors` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  `edition` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  `isbn` varchar(255) COLLATE armscii8_bin NOT NULL,
  `title` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table bookstore.books: ~0 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`base_price`, `authors`, `edition`, `isbn`, `title`) VALUES
	(50.00, 'Eric Freeman', '2nd Edition', '149207800X', 'Head First Design Patterns: Building Extensible and Maintainable Object-Oriented Software'),
	(60.00, 'Joshua Bloch', '1st Edition', '4782097', 'Effective Java');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table bookstore.book_inventory
CREATE TABLE IF NOT EXISTS `book_inventory` (
  `current_price` decimal(38,2) DEFAULT NULL,
  `transaction_count` int(11) DEFAULT NULL,
  `book_isbn` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  `id` varchar(255) COLLATE armscii8_bin NOT NULL,
  `state` enum('AVAILABLE','SOLD') COLLATE armscii8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6spo1pwbegy2y39lipc4ltlen` (`book_isbn`),
  CONSTRAINT `FK6spo1pwbegy2y39lipc4ltlen` FOREIGN KEY (`book_isbn`) REFERENCES `books` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table bookstore.book_inventory: ~11 rows (approximately)
DELETE FROM `book_inventory`;
/*!40000 ALTER TABLE `book_inventory` DISABLE KEYS */;
INSERT INTO `book_inventory` (`current_price`, `transaction_count`, `book_isbn`, `id`, `state`) VALUES
	(45.00, 1, '149207800X', '001', 'AVAILABLE'),
	(54.00, 1, '4782097', '06a272a3-d6', 'AVAILABLE'),
	(45.00, 1, '149207800X', '2', 'AVAILABLE'),
	(60.00, 0, '4782097', '4b03e8bb-6c63-4f0b-93d6-83e453483b57', 'AVAILABLE'),
	(60.00, 0, '4782097', '4dac093c-', 'AVAILABLE'),
	(60.00, 0, '4782097', '7cc7ab97-', 'AVAILABLE'),
	(60.00, 0, '4782097', '8cf95f71-', 'AVAILABLE'),
	(60.00, 0, '4782097', 'ccdb5634-', 'AVAILABLE'),
	(60.00, 0, '4782097', 'efd15f9a-9d', 'AVAILABLE'),
	(60.00, 0, '4782097', 'f6b1780d-', 'AVAILABLE'),
	(60.00, 0, '4782097', 'ffb85c66-', 'AVAILABLE');
/*!40000 ALTER TABLE `book_inventory` ENABLE KEYS */;

-- Dumping structure for table bookstore.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(38,2) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` enum('PURCHASE','BUYBACK','SELL') COLLATE armscii8_bin DEFAULT NULL,
  `inventory_item_id` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7itpv7qcvi8bc5bmg9f4vjbgv` (`inventory_item_id`),
  CONSTRAINT `FK7itpv7qcvi8bc5bmg9f4vjbgv` FOREIGN KEY (`inventory_item_id`) REFERENCES `book_inventory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Dumping data for table bookstore.transactions: ~14 rows (approximately)
DELETE FROM `transactions`;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` (`id`, `price`, `transaction_date`, `transaction_type`, `inventory_item_id`) VALUES
	(5, 45.00, '2024-10-30 00:00:49.914518', 'BUYBACK', '001'),
	(6, 50.00, '2024-10-30 00:01:21.607124', 'SELL', '2'),
	(7, 45.00, '2024-10-30 00:01:49.698890', 'BUYBACK', '2'),
	(8, 60.00, '2024-10-30 00:03:27.178668', 'PURCHASE', '4b03e8bb-6c63-4f0b-93d6-83e453483b57'),
	(9, 60.00, '2024-11-05 15:02:58.260120', 'PURCHASE', '7cc7ab97-'),
	(10, 60.00, '2024-11-05 16:26:44.496805', 'PURCHASE', '4dac093c-'),
	(11, 60.00, '2024-11-05 16:27:11.814058', 'PURCHASE', 'ccdb5634-'),
	(12, 60.00, '2024-11-05 16:27:23.147839', 'PURCHASE', 'f6b1780d-'),
	(13, 60.00, '2024-11-05 16:40:55.556821', 'PURCHASE', '8cf95f71-'),
	(14, 60.00, '2024-11-05 17:27:47.985740', 'PURCHASE', 'ffb85c66-'),
	(15, 60.00, '2024-11-05 18:32:06.685592', 'PURCHASE', '06a272a3-d6'),
	(16, 60.00, '2024-11-05 19:24:37.421650', 'SELL', '06a272a3-d6'),
	(17, 60.00, '2024-11-05 19:46:54.117738', 'PURCHASE', 'efd15f9a-9d'),
	(18, 54.00, '2024-11-05 20:07:11.461767', 'BUYBACK', '06a272a3-d6');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
