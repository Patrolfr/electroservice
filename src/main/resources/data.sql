insert into categories values(1,'phone');
insert into categories values(2,'tv');
insert into categories values(3,'pc');
insert into categories values(4,'fringe');

insert into equipments (category_id, name, service_code, service_status, id) values (1, 'huawei p30 pro', 'ABC-123','WORKING', 1001);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'iphone x10', 'XYZ-123','WORKING', 1002);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'xiaomi u2', 'RAW-532','WORKING', 1003);
insert into equipments (category_id, name, service_code, service_status, id) values (1, 'google pixel 3', 'gOg-909','WORKING', 1004);

insert into equipments (category_id, name, service_code, service_status, id) values (2, 'samsung view', 'TVC-123','WORKING', 1005);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'phillips4k', 'TVZ-123','WORKING', 1006);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'lg plasma 2701', 'TVW-532','WORKING', 1007);
insert into equipments (category_id, name, service_code, service_status, id) values (2, 'stary kineskop', 'TVg-909','IN_SERVICE', 1008);

insert into parameters values(51,'headphones','yes');
insert into parameters values(52,'resolution','1920x1080');
insert into parameters values(53,'inchees','27');
insert into parameters values(54,'inchees','55');
insert into parameters values(55,'headphones','no');
insert into parameters values(56,'color','black');
insert into parameters values(57,'color','blue');
insert into parameters values(58,'color','white');
insert into parameters values(59,'color','red');
insert into parameters values(510,'resolution','4k');

insert into equipments_parameters values(1001,51);
insert into equipments_parameters values(1001,52);
insert into equipments_parameters values(1001,56);
insert into equipments_parameters values(1002,55);
insert into equipments_parameters values(1002,57);
insert into equipments_parameters values(1003,58);
insert into equipments_parameters values(1004,59);
insert into equipments_parameters values(1005,54);
insert into equipments_parameters values(1006,510);
insert into equipments_parameters values(1007,53);

