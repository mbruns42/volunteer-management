CREATE TABLE Person (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255),
    EMail varchar(255),
    Birthday date,
    EntryDate date,
    InsDate date,
    InsBy varchar(255),
    UpdDate date,
    UpdBy varchar(255)
);

 CREATE TABLE Team (
    TeamId int,
    TeamName varchar(255),
    TeamDesc varchar(1023),
	InsDate date,
    InsBy varchar(255),
    UpdDate date,
    UpdBy varchar(255)
);

 CREATE TABLE PersonTeam (
    TeamId int,
    PersonId int,
	InsDate date,
    InsBy varchar(255),
    UpdDate date,
    UpdBy varchar(255)
);

 CREATE TABLE MailingList (
    ListId int,
    ListName varchar(255),
    ListDesc varchar(1023),
	InsDate date,
    InsBy varchar(255),
    UpdDate date,
    UpdBy varchar(255)
);

 CREATE TABLE MailingListPerson (
	ListId int,
    PersonId int,
	InsDate date,
    InsBy varchar(255),
    UpdDate date,
    UpdBy varchar(255)
);