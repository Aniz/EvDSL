CREATE TABLE `User`(
	idUser int(10) NOT NULL, 
	password varchar(255) NOT NULL, 
	nameUser varchar(255) NOT NULL, 
	email varchar(255) NOT NULL,
	filiation varchar(255) NOT NULL, 
		typeUser varchar(255) NOT NULL,
				
		aaaa varchar(255) NOT NULL,
		bbbb varchar(255) NOT NULL,
	        
PRIMARY KEY (idUser));

CREATE TABLE Speaker (
	idUser int(10) NOT NULL, 
	biography varchar(255) NOT NULL, 
	  
PRIMARY KEY (idUser),
FOREIGN KEY (idUser) REFERENCES `User` (idUser) ON DELETE CASCADE);

CREATE TABLE Reviewer (
	idUser int(10) NOT NULL, 
	knowledgeArea varchar(255) NOT NULL,
	  
PRIMARY KEY (idUser),
FOREIGN KEY (idUser) REFERENCES `User` (idUser) ON DELETE CASCADE);

CREATE TABLE Event (
	idEvent int(10) NOT NULL AUTO_INCREMENT, 
	eventName varchar(255), 
	period varchar(255), 
	place varchar(255), 
	institution varchar(255), 
	sponsors varchar(255), 
		typeEvent varchar(255) NOT NULL,
				
		link varchar(255) NOT NULL,
		aaaa varchar(255) NOT NULL,
		bbbb varchar(255) NOT NULL,
	  
PRIMARY KEY (idEvent));

CREATE TABLE Activity (
	idActivity int(10) NOT NULL AUTO_INCREMENT, 
	idEvent int(10) NOT NULL, 
	nameActivity varchar(255), 
	descriptionActivity varchar(255) NOT NULL, 
	value float NOT NULL, 
	hourlyLoad float NOT NULL, 
	`date` varchar(255) NOT NULL, 
	hour varchar(255), 
	numberOfParticipants int(10), 
	registrationLimit int(10), 
	typeActivity varchar(255) NOT NULL,
				
	aaaa varchar(255) NOT NULL,
	bbbb varchar(255) NOT NULL,
	  
PRIMARY KEY (idActivity),
FOREIGN KEY (idEvent) REFERENCES Event (idEvent) ON DELETE CASCADE);

CREATE TABLE Organizer (
	idUser int(10) NOT NULL, 
	  
PRIMARY KEY (idUser),
FOREIGN KEY (idUser) REFERENCES `User` (idUser) ON DELETE CASCADE);

CREATE TABLE activityspeaker (
idActivity int(10) NOT NULL, 
idUser int(10) NOT NULL, 
PRIMARY KEY (idActivity, idUser),
FOREIGN KEY (idUser) REFERENCES Speaker (idUser),
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

CREATE TABLE activityorganizer (
idActivity int(10) NOT NULL, 
idUser int(10) NOT NULL, 
PRIMARY KEY (idActivity, idUser),
FOREIGN KEY (idUser) REFERENCES Organizer (idUser),
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

CREATE TABLE activityuser (
idActivity int(10) NOT NULL, 
idUser int(10) NOT NULL, 
frequency float,
PRIMARY KEY (idActivity, idUser),
FOREIGN KEY (idUser) REFERENCES User (idUser),
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

CREATE TABLE submission(
	idSubmission int(10) AUTO_INCREMENT, 
	idActivity int(10),
	abstract varchar(255), 
	keywords varchar(255),
	title varchar(255), 
	attachment blob,
	  
PRIMARY KEY (idSubmission),
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

CREATE TABLE author(
	idAuthor int(10) NOT NULL AUTO_INCREMENT, 
	nameAuthor varchar(255) NOT NULL, 
	filiation varchar(255) NOT NULL, 
	email varchar(255) NOT NULL, 
	  
PRIMARY KEY (idAuthor));

/*
O autor que entra na tabela de submissionAuthor é o corresponding author, o usuario que ta logado no sistema.
*/

Create table submissionAuthor(
idAuthor int(10) NOT NULL,
idSubmission int(10) NOT NULL,
idActivity int(10) NOT NULL,
PRIMARY KEY (idAuthor,idSubmission,idActivity),
FOREIGN KEY (idAuthor) REFERENCES User (idUser),
FOREIGN KEY (idSubmission) REFERENCES submission (idSubmission) ON DELETE CASCADE,
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

Create table submissionUser(
idUser int(10) NOT NULL,
idSubmission int(10) NOT NULL,
idActivity int(10) NOT NULL,
PRIMARY KEY (idUser,idSubmission,idActivity),
FOREIGN KEY (idUser) REFERENCES User (idUser),
FOREIGN KEY (idSubmission) REFERENCES Submission (idSubmission) ON DELETE CASCADE,
FOREIGN KEY (idActivity) REFERENCES Activity (idActivity));

Create table Registration(
idRegistration int(10) NOT NULL AUTO_INCREMENT,
idUser int(10) NOT NULL,
idEvent int (10) NOT NULL,
totalValue float,  
PRIMARY KEY (idRegistration),
FOREIGN KEY (idUser) REFERENCES `User` (idUser),
FOREIGN KEY (idEvent) REFERENCES `Event` (idEvent));

CREATE TABLE `Checkingcopy`(
	idCheckingcopy int(10) NOT NULL AUTO_INCREMENT, 
	idRegistration int(10) NOT NULL,
	idUser int(10) NOT NULL,
	dateOfIssue varchar(255) NOT NULL, 
	  
PRIMARY KEY (idCheckingcopy),
FOREIGN KEY (idRegistration) REFERENCES Registration (idRegistration) ON DELETE CASCADE,
FOREIGN KEY (idUser) REFERENCES `User` (idUser));

CREATE TABLE `Review`(
idReview int(10) NOT NULL AUTO_INCREMENT, 
status varchar(255) NOT NULL, 
date varchar(255) NOT NULL, 
description1 varchar(255) NOT NULL, 
idSubmission int(10) NOT NULL,
round int(10) NOT NULL,
result varchar(255) NOT NULL,
PRIMARY KEY (idReview),
FOREIGN KEY (idSubmission) REFERENCES Submission (idSubmission) ON DELETE CASCADE);

Create table assignement(
	idUser int(10) NOT NULL,
	idReview int(10) NOT NULL,
	idSubmission int(10) NOT NULL,
	date varchar(255) NOT NULL, 
	  
PRIMARY KEY (idUser, idReview, idSubmission),
FOREIGN KEY (idUser) REFERENCES Reviewer (idUser) ON DELETE CASCADE,
FOREIGN KEY (idReview) REFERENCES Review (idReview) ON DELETE CASCADE,
FOREIGN KEY (idSubmission) REFERENCES Review (idSubmission) ON DELETE CASCADE);

Create table Payment(
	idPayment int(10) NOT NULL AUTO_INCREMENT,
	idRegistration int(10) NOT NULL,
	status varchar(255) NOT NULL,
	date varchar(255) NOT NULL,
	attachment blob,
	barcode varchar(255) NOT NULL,
	value float,
	typePayment varchar(255) NOT NULL,
				
	link varchar(255) NOT NULL,
	aaaa varchar(255) NOT NULL,
	bbbb varchar(255) NOT NULL,
	  
PRIMARY KEY (idPayment),
FOREIGN KEY (idRegistration) REFERENCES Registration (idRegistration));

Create table Receipt(
idReceipt int(10) NOT NULL AUTO_INCREMENT,
idPayment int(10) NOT NULL,
totalValue float,
dateOfIssue varchar(255) NOT NULL,
PRIMARY KEY (idReceipt),
FOREIGN KEY (idPayment) REFERENCES Payment (idPayment));

