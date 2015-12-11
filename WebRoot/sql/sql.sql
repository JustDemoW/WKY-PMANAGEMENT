
create table PM_WKY_MODULE_FILE 
(
   MODULE_ID            varchar (25)                      not null,
   DIRECTORY            varchar (255)                     null,
   FILE_NAME            varchar (255)                     not null,
   constraint PK_PM_WKY_MODULE_FILE primary key clustered (MODULE_ID, FILE_NAME)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


    create table PM_WKY_MODULE(
       ID VARCHAR(25) not null,
       DIRECTORY VARCHAR(255),
       AUTHOR VARCHAR(35),
       VERSION VARCHAR(15),
       URL VARCHAR(255),
       CLASS_NAME VARCHAR(55),
       MODULE_TYPE VARCHAR(35),
       MODULE_ID VARCHAR(35),
       MENU_PATH VARCHAR(255),
       LAUNCHER_ICON_CLS VARCHAR(35),
       LAUNCHER_SHORTCUT_ICON_CLS VARCHAR(255),
       LAUNCHER_TEXT VARCHAR(55),
       LAUNCHER_TOOLTIP VARCHAR(100),
       ACTIVE VARCHAR(1),
       PROLOAD VARCHAR(1),
       DESCRIPTION VARCHAR(255),
       SORT_ORDER VARCHAR(16),
        constraint PK_PM_WKY_MODULE primary key clustered (ID)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table PM_WKY_USER
(
   USER_NAME            	varchar (64)                     not null,
   USER_PASSWORD            varchar (64)                     not null,
   USER_NICKNAME	VARCHAR(64),
   USER_SEX			VARCHAR(1),
   USER_QX		VARCHAR(1),
   USER_LASTLOGINTIME 	VARCHAR(14) not null,
   USER_LASTLOGINIP	VARCHAR(20) not null,
   constraint PK_PM_WKY_USER primary key clustered (USER_NAME)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table PM_WKY_NETACCOUNT
(
	ACCOUNT_ID            	varchar (25)                      not null,
	ACCOUNT_NAME            	varchar (64)                     not null,
	ACCOUNT_PASSWORD            varchar (64)                     not null,
	ACCOUNT_SECPASSWORD            varchar (64),
	ACCOUNT_LRSJ	VARCHAR(14) not null,
	ACCOUNT_LRR		VARCHAR(25) not null,
	ACCOUNT_LRRXM 	VARCHAR(16) ,
	ACCOUNT_SFKY	VARCHAR(1) default '1' not null,
	ACCOUNT_NETKEYWORDS 	VARCHAR(256),
	ACCOUNT_NETURL	VARCHAR(2000),
	ACCOUNT_BZ 		VARCHAR(2500),
   constraint PK_PM_WKY_NETACCOUNT primary key clustered ( ACCOUNT_ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table PM_WKY_LOGINRECORD(
	RECORD_ID 	varchar(25) not null,
	RECORD_LOGINIP 	varchar(15) not null,
	RECORD_USERNANE varchar(64)	not null,
	RECORD_LOGINTIME varchar(14) not null,
	constraint PM_WKY_LOGINRECORD primary key clustered ( RECORD_ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table PM_WKY_CONSUMPTIONRECORD(
	RECORD_ID 	varchar(25) not null,
	RECORD_DEPOSITTYPE 	varchar(2) default '99' not null,
	RECORD_TYPE varchar(1)	not null,
	RECORD_CONSUMPTIONTYPE varchar(2)	default '99' not null,
	RECORD_CONSUMPTIONCREDIT double(10,2) not null,
	RECORD_REMARKS varchar(30) ,
	RECORD_USERNANE varchar(64)	not null,
	RECORD_TIME varchar(14) not null,
	constraint PM_WKY_CONSUMPTIONRECORD primary key clustered ( RECORD_ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table PM_WKY_DD;
create table PM_WKY_DD
(
  DD_ID        varchar(25) not null,
  DD_KEY        varchar(100) not null,
  DD_CODE_VALUE varchar(300),
  DD_VALUE      varchar(1000) not null,
  DD_PINYIN     varchar(1000),
  constraint PM_WKY_DD primary key clustered ( DD_ID )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;











