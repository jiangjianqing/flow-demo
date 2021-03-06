DROP TABLE if exists COMMON_ID_USER_ROLE;
DROP TABLE if exists COMMON_ID_ROLE;
DROP TABLE if exists COMMON_ID_USER;


CREATE TABLE IF NOT EXISTS  COMMON_ID_USER ( 
	ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT  /*ID列为无符号整型，该列值不可以为空，并不可以重复，而且自增。*/
    ,USER_NAME VARCHAR(100) NOT NULL 
    ,PASSWORD VARCHAR(255) NOT NULL
    ,SALT VARCHAR(255) NOT NULL
    ,FIRST_NAME VARCHAR(255)  
    ,LAST_NAME VARCHAR(255)  
    ,BIRTHDAY DATE
    ,EMAIL VARCHAR(255)  
    ,ENABLED tinyint(1) NOT NULL DEFAULT TRUE
    ,ACCOUNT_NON_EXPIRED tinyint(1) NOT NULL DEFAULT TRUE
    ,ACCOUNT_NON_LOCKED tinyint(1) NOT NULL DEFAULT TRUE
    ,CREDENTIALS_NON_EXPIRED tinyint(1) NOT NULL DEFAULT TRUE
    ,UNIQUE KEY uk_username (USER_NAME)	/*利用唯一键约束来使得用户名无法重复*/
) AUTO_INCREMENT = 100000; /*ID列从100开始自增*/

/*TRUNCATE TABLE table1;  如果想让自增ID从默认值开始只要执行该语句*/ 


CREATE TABLE IF NOT EXISTS  COMMON_ID_ROLE (
	ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT
    ,NAME VARCHAR(100) NOT NULL	COMMENT '角色名称'
    ,TYPE VARCHAR(100) NOT NULL	COMMENT '角色类型' /*assignment:普通角色 security-role：安全角色，也可以视为管理员*/
    ,PRIORITY int	COMMENT '角色优先级'
);


CREATE TABLE IF NOT EXISTS  COMMON_ID_USER_ROLE (
	USER_ID INT UNSIGNED NOT NULL
    ,ROLE_ID INT UNSIGNED NOT NULL
    ,UNIQUE KEY uk_userid_role_id (USER_ID,ROLE_ID)
    ,foreign key(USER_ID) references COMMON_ID_USER(ID)
    ,foreign key(ROLE_ID) references COMMON_ID_ROLE(ID)
);
