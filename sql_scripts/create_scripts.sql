create table patients(
id int not null primary key auto_increment,
nameOfPatient varchar(20),
password varchar(20),
diagnosisID int,
paymentID int,
roomID int
);

create table doctors(
id int not null primary key auto_increment,
nameOfDoctor varchar(20),
UIN bigint,
password varchar(20),
specialty varchar(20)
);
--relation patient:doctor = n:m--
--relation patient:nurse = n:m--
--relation patient:ward boy = n:m--
create table diagnoses(
id int not null primary key auto_increment,
disease varchar(40),
prescription varchar(40)
);

create table rooms(
id int not null primary key auto_increment,
roomNumber int,
numberOfBeds int,
type varchar(40) NOT NULL CHECK (type in('ICU', 'MEDICAL_SURGICAL', 'MATERNITY_CARE', 'MENTAL_HEALTH', 'CONSULTATION'))
);

create table nurses(
id int not null primary key auto_increment,
nameOfNurse varchar(20),
UIN bigint,
doctorID int,
password varchar(20)
);
--relation doctor:nurse = 1:n--
create table wardBoys(
id int not null primary key auto_increment,
nameOfWardBoy varchar(20),
UIN bigint,
doctorID int,
password varchar(20)
);
--relation doctor:wardBoy = 1:n--
create table assignments(
id int not null primary key auto_increment,
patientID int,
doctorID int,
nurseID int,
wardBoyID int
);

create table payments(
id int not null primary key auto_increment,
type varchar (40) NOT NULL CHECK (type IN('cash', 'E-Banking'))
);

create table emergencyCare(
id int not null primary key auto_increment,
assignmentID int,
roomID int,
timeOfArrival timestamp
);

create table supportGroup(
id int not null primary key auto_increment,
roomID int,
firstPatientID int,
secondPatientID int,
thirdPatientID int,
nurseID int
);

create table supportCall(
doctorID int,
patientID int
);

--some passwords for testing
--Luba: password1234, cGFzc3dvcmQxMjM0
--Krassimir: PASSWORD012345678, UEFTU1dPUkQwMTIzNDU2Nzg=