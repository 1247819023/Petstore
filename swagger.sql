create table `order`(
	oid int primary key auto_increment,
	per_id int,
	qiantity int,
	ship_date datetime,
	ostatus enum('placed', 'approved', 'delivered'),
	complete bit
);
create table `user`(
	uid int primary key auto_increment,
	user_name varchar(20),
	first_name varchar(20),
	last_name varchar(20),
	email varchar(20),
	`password` varchar(20),
	phone varchar(20),
	user_status int
);
create table category(
	cid int primary key auto_increment,
	cname varchar(20)
);
create table tag(
	tid int primary key auto_increment,
	tname varchar(20)
);
create table pet(
	pid int primary key auto_increment,
	cid int references category(cid),
	pname varchar(20),
	photo_urls varchar(20),
	tid int references tag(tid),
	pstatus enum('placed', 'approved', 'delivered')
);