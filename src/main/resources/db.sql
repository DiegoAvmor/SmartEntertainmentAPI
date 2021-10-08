DROP SCHEMA IF EXISTS `smart_entertainment_api`;
CREATE SCHEMA IF NOT EXISTS `smart_entertainment_api`;
USE `smart_entertainment_api`;

CREATE TABLE `users` (
  `user_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `resource` (
  `resource_id` int PRIMARY KEY AUTO_INCREMENT,
  `resource_type` int,
  `image_url` varchar(255),
  `name` varchar(50),
  `description` varchar(200),
  `year_publish` int,
  `num_chapter` int
);

CREATE TABLE `resource_types` (
  `resoucer_type_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `user_resources` (
  `user_id` int,
  `resource_id` int,
  `status_id` int,
  `date_started` timestamp,
  `date_finished` timestamp,
  `is_favorite` bool,
  `current_chapter` int
);

CREATE TABLE `status` (
  `status_id` int PRIMARY KEY AUTO_INCREMENT,
  `status_name` varchar(15)
);

ALTER TABLE `resource` ADD FOREIGN KEY (`resource_type`) REFERENCES `resource_types` (`resoucer_type_id`);

ALTER TABLE `user_resources` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `user_resources` ADD FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`);

ALTER TABLE `user_resources` ADD FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`);