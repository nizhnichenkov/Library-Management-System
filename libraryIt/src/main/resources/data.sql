INSERT INTO users(id, username, password, role) VALUES (1, 'slav', 'slav', 'librarian'), (2, 'tanmay', 'tanmay', 'librarian'), (3, 'sean', 'sean', 'member');

INSERT INTO items(id, title, type, genre, on_Loan, times_loaned) VALUES 
(1, 'Moby Dick', 'book', 'classic', FALSE, 1), 
(2, 'To Kill a Mockingbird', 'book', 'classic', FALSE, 1), 
(3, 'Goodfellas', 'dvd', 'crime', FALSE, 1), 
(4, 'Atrocity Exhibition', 'cd', 'hiphop', FALSE, 3), 
(5, 'Introduction to Algorithms', 'book', 'education', FALSE, 2),
(6, 'Design Patterns in Java', 'book', 'education', FALSE, 2), 
(7, 'True Romance', 'dvd', 'crime', FALSE, 4), 
(8, 'Dune', 'book', 'ficion', FALSE, 3), 
(9, 'Spaces', 'cd', 'electronic', FALSE, 1), 
(10, 'Cosmogramma', 'cd', 'electronic', FALSE, 5), 
(11, 'Rumours', 'cd', 'rock', FALSE, 4),
(12, 'Fear and Loathing in Las Vegas', 'dvd', 'comedy', FALSE, 3), 
(13, 'The Art of War', 'book', 'classic', FALSE, 2), 
(14, 'Parasite', 'dvd', 'drama', FALSE, 1), 
(15, 'The Ooz', 'cd', 'rock', FALSE, 2), 
(16, 'Crime & Punishment', 'book', 'crime', FALSE, 1),
(17, 'Kid A', 'cd', 'electronic', FALSE, 3), 
(18, 'Selected Ambient Works 85-92', 'cd', 'electronic', FALSE, 4), 
(19, 'The Wind Rises', 'movie', 'animation', FALSE, 2), 
(20, 'Prisoners of Geography', 'book', 'non-fiction', FALSE, 3), 
(21, 'Steal This Book', 'book', 'crime', FALSE, 4),
(22, 'Lullabies to Paralyze', 'cd', 'rock', FALSE, 3), 
(23, 'The Life of Brian', 'dvd', 'comedy', FALSE, 10), 
(24, 'Windows for Dummies', 'book', 'educaional', FALSE, 6), 
(25, 'To Pimp a Butterfly', 'cd', 'hiphop', FALSE, 7), 
(26, 'Oldboy', 'dvd', 'drama', FALSE, 4);

INSERT INTO histories(id, item_id, item_title, item_type, item_genre, user_id) VALUES (1, 1, 'To Kill a Mockingbird', 'book', 'classic', 0), (2, 7, 'Dune', 'book', 'ficion', 1), (3, 24, 'To Pimp a Butterfly', 'cd', 'hiphop', 1)