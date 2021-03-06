CREATE TABLE Student (

    ID  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    FIRST_NAME VARCHAR2(25) NOT NULL,
    LAST_NAME VARCHAR2(25) NOT NULL,
    DOB DATE NOT NULL,
    NATIONAL_ID NUMBER NOT NULL,
    
    CONSTRAINT PK_STUDNET PRIMARY KEY (ID)
)

/

CREATE TABLE Course (
    ID  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    CODE VARCHAR2(2) NOT NULL,
    NAME VARCHAR2(2) NOT NULL,
    DESCRIPTION VARCHAR2(4000),
    
    CONSTRAINT PK_COURSE PRIMARY KEY (ID)
)

/

CREATE TABLE Registration(
    ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    student_id NUMBER NOT NULL,
    course_id NUMBER NOT NULL,

    CONSTRAINT LINK_CS PRIMARY KEY (student_id,course_id)
)

/

ALTER TABLE Registration
    ADD CONSTRAINT student_id_FK
   FOREIGN KEY (student_id)
   REFERENCES student (id);
ALTER TABLE Registration
    ADD CONSTRAINT COURSE_id_FK
   FOREIGN KEY (COURSE_id)
   REFERENCES Course (id);
   
   ALTER TABLE Student
    ADD CONSTRAINT NATIONAL_ID_UQ
   UNIQUE (NATIONAL_ID);   