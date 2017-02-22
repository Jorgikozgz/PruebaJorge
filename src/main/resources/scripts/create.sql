create table IF NOT EXISTS AUTHOR(
	IDAUTHOR int auto_increment primary key,
	NAMEAUTHOR VARCHAR(50),
	DATEOFBIRTH DATE
	);
	
create table IF NOT EXISTS BOOK(
	IDBOOK int auto_increment primary key,
	TITLE VARCHAR(50),
	ISBN int,
	IDAUTHOR int
	);
	
	