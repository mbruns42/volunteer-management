CREATE TABLE Person (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    state varchar(255),
    salutation varchar(255), 
	Birthday date,
    Address varchar(255),
    PostCode int,
    City varchar(255),
    phoneNumber varchar(255),
    phoneNumberMobile varchar(255),
    EMail varchar(255),
	HYIC Date, /* Hygieneinformation Corona neu seit 20.05.20 */
	HYB Date, /* Hygienebelehrung Gesundheitsamt */
	HYW Date, /* Hygienewiederholung im Gast-Haus_Januar */
	MoV boolean,
    MoN boolean,
    DiVo boolean,
    DiN boolean,
    MiV boolean,
    MiN boolean,
    DoV boolean,
    DoN boolean,
    FrV boolean,
    FrN boolean,
    SaV boolean, 
    SaN boolean, 
    SoV boolean,
    SoN boolean,
    workArea varchar(255),
    workArea2 varchar(255),
    workArea3 varchar(255),
    workArea4 varchar(255),
    workType varchar(255),
    SPR varchar(255),
	Notes varchar (1023),
	Qualification varchar(255),
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