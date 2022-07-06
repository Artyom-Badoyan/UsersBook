
CREATE TABLE `books` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `author_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);


CREATE TABLE `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(54) NOT NULL,
  `surname` VARCHAR(54) NOT NULL,
  `email` VARCHAR(54) NOT NULL UNIQUE,
  `password` VARCHAR(54) NOT NULL,
  `age` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

