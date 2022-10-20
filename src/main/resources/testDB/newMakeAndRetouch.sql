create table detailexpend(
    dno int primary key auto_increment,
    detailuse varchar(100),
    dvmemo varchar(200),
    price int,
    dvusedate datetime,
    dvno varchar(20),
    divcd varchar(20),
    constraint foreign_divcd_insertexpend foreign key (divcd) references accountcode(divcd),
    constraint foreign_dvno_insertexpend foreign key (dvno) references expendinformation(dvno)
)engine=innoDB default character set utf8;

alter table file drop dvno;

alter table file add dno int;

alter table file add constraint foreign_dno_file foreign key (dno) references detailexpend(dno);
