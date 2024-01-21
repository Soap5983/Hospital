create table patients(
id int not null primary key auto_increment,
nameOfPatient varchar(20),
password varchar(20),
diagnosis varchar(100),
payment int,
roomNumber int
);

create table doctors(
id int not null primary key auto_increment,
nameOfDoctor varchar(20),
UIN bigint,
password varchar(20),
specialty varchar(20)
);



create table assignments(
id int not null primary key auto_increment,
patientID int,
doctorID int,
);


--some passwords for testing
--Luba: password1234, cGFzc3dvcmQxMjM0
--Krassimir: PASSWORD01234, ???UEFTU1dPUkQwMTIzNDU2Nzg=