insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (1, '00000-00001', 'MIDDLE_WEIGHT', 'IDLE', 40, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (2, '00000-00002', 'LIGHT_WEIGHT', 'IDLE', 50, 300);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (3, '00000-00003', 'LIGHT_WEIGHT', 'IDLE', 60, 200);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (4, '00000-00004', 'LIGHT_WEIGHT', 'IDLE', 70, 320);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (5, '00000-00005', 'LIGHT_WEIGHT', 'IDLE', 80, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (6, '00000-00006', 'LIGHT_WEIGHT', 'IDLE', 90, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (7, '00000-00007', 'LIGHT_WEIGHT', 'IDLE', 100, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (8, '00000-00008', 'LIGHT_WEIGHT', 'IDLE', 40.5, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (9, '00000-00009', 'MIDDLE_WEIGHT', 'IDLE', 20, 400);
insert into drone (id, serial_number, model, state, battery_capacity, weight_limit) values (10, '00000-000010', 'HEAVY_WEIGHT', 'IDLE', 10.5, 500);

insert into medication (id, code, name, weight, picture_url) values (1, '000001', 'Medication 1', 30, 'http://images/picture/meidacations/1');
insert into medication (id, code, name, weight, picture_url) values (2, '000002', 'Medication 2', 50, 'http://images/picture/meidacations/2');
insert into medication (id, code, name, weight, picture_url) values (3, '000003', 'Medication 3', 130, 'http://images/picture/meidacations/3');
insert into medication (id, code, name, weight, picture_url) values (4, '000004', 'Medication 4', 40, 'http://images/picture/meidacations/4');
insert into medication (id, code, name, weight, picture_url) values (5, '000005', 'Medication 5', 20, 'http://images/picture/meidacations/5');

insert into drone_medication (drone_id, medication_id) values (1, 1), (1, 5);
insert into drone_medication (drone_id, medication_id) values (2, 1), (2, 4), (2, 5);
insert into drone_medication (drone_id, medication_id) values (3, 3);
insert into drone_medication (drone_id, medication_id) values (8, 1), (8, 2), (8, 3), (8, 4);