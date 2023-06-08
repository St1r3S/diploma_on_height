insert into companies (id, company_name, corporate_email, corporate_phone)
values (default, 'Арканатех', 'arcantech@arcana.ua', '+380562963898');
insert into companies (id, company_name, corporate_email, corporate_phone)
values (default, 'Козей', 'cozey@gmail.com', '+380565956253');
insert into companies (id, company_name, corporate_email, corporate_phone)
values (default, 'Строман', 'stroman@gmail.com', '+380564573127');
insert into companies (id, company_name, corporate_email, corporate_phone)
values (default, 'Вінтхайзер', 'wint@gmail.com', '+380565387142');
insert into companies (id, company_name, corporate_email, corporate_phone)
values (default, 'Давіс', 'davis@ukr.net', '+380562694766');


insert into addresses (id, country, city, address, zip_code, last_update)
values (default, 'Україна', 'Київ', 'Довженка, 48', '01034', '2022-10-31 06:25:50');
insert into addresses (id, country, city, address, zip_code, last_update)
values (default, 'Україна', 'Імелін', 'Шатунка, 32', '01042', '2022-08-11 09:50:51');
insert into addresses (id, country, city, address, zip_code, last_update)
values (default, 'Україна', 'Миколаїв', 'Виноградна, 145', '54057', '2023-04-28 02:04:58');
insert into addresses (id, country, city, address, zip_code, last_update)
values (default, 'Україна', 'Батурин', 'Перемоги, 22', '01011', '2022-12-10 09:38:27');

insert into warehouses (id, classification, description, company_id, address_id, phone_number)
values (default, 'FG', 'Склад мікросхем для роботів', 1, 1, '+380562968421');
insert into warehouses (id, classification, description, company_id, address_id, phone_number)
values (default, 'FG', 'Склад мікросхем для роботів', 1, 2, '+380561232654');
insert into warehouses (id, classification, description, company_id, address_id, phone_number)
values (default, 'FG', 'Склад мікросхем для роботів', 1, 3, '+380562563218');
insert into warehouses (id, classification, description, company_id, address_id, phone_number)
values (default, 'FG', 'Склад мікросхем для роботів', 1, 4, '+380566544894');

insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'Rasberry PI', 98, 250, 'UAH', 1, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'Arduino m358', 191, 150, 'UAH', 1, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'XLMD 855', 246, 350, 'UAH', 1, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'AMX 740', 18, 140, 'UAH', 1, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'DB 855', 23, 80, 'UAH', 1, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'XM/LD 1344', 51, 460, 'UAH', 1, 1);

insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'AMD 256', 56, 180, 'UAH', 2, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'PRTR 551', 120, 640, 'UAH', 2, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'FXM 88', 211, 350, 'UAH', 2, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'MM/AX 12', 118, 140, 'UAH', 2, 1);

insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'AD-18', 113, 250, 'UAH', 3, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'LK-X 56', 56, 150, 'UAH', 3, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'SS/x 560', 41, 80, 'UAH', 3, 1);
insert into products (id, product_type, product_name, product_qty, unit_price, price_currency, warehouse_id, company_id)
values (default, 'TECH', 'VRM-55', 15, 460, 'UAH', 3, 1);

insert into users (id, user_role, first_name, last_name, email, phone_number, username, password_hash, birthday, gender,
                   department, post, company_id)
values (default, 'USER', 'Михайло', 'Зубанич', 'mikmuael@gmail.com', '+380661561591', 'mika_zub',
        '$2a$12$AJY7nBDyztnZZtGpCmVKDuGNQgmcNLjIy/fmaURoHJjdUOkcx0N3u',
        '2002-06-22', 'M', 'MGT', 'OWN', 1);

insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Олег', 'Прасюк', 'prasik@gmai.com', '+380661597415', 1, 'POST', 'Арбатська, 48', 'CARD',
        'Будь ласка, упакуйте все щільно');
insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Данил', 'Гончар', 'goncha@gmai.com', '+380661595128', 1, 'EXPR', 'Дубіна, 346', 'CASH',
        'Будь ласка, упакуйте все щільно');
insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Олена', 'Трудова', 'trud.O@gmai.com', '+380688451493', 1, 'PICK', 'Лягіна, 12', 'CASH',
        'Будь ласка, упакуйте все щільно');
insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Семен', 'Вацюк', 'vazyuk18@gmai.com', '+380665468413', 1, 'POST', 'Кумова, 57', 'CARD',
        'Будь ласка, упакуйте все щільно');
insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Христина', 'Жвава', 'jvavaX88@gmai.com', '+380551584615', 1, 'POST', 'Вальтера, 3', 'CARD',
        'Будь ласка, упакуйте все щільно');
insert into requests(id, first_name, last_name, email, phone_number, company_id, delivery_method, delivery_address,
                     payment_method, comment)
values (default, 'Абрам', 'Матейник', 'abrammat68@gmai.com', '+380668457411', 1, 'COUR', 'Приморська, 46', 'CASH',
        'Будь ласка, упакуйте все щільно');

insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'PD', '2023-06-04 03:04:17.562098', 1);
insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'CN', '2023-06-03 03:04:17.562098', 2);
insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'CN', '2023-06-02 03:04:17.562098', 3);
insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'PD', '2023-06-01 03:04:17.562098', 4);
insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'PP', '2023-06-03 03:04:17.562098', 5);
insert into completion_stage(id, request_status, completion_datetime, request_id)
VALUES (default, 'CN', '2023-06-03 02:04:17.562098', 6);

insert into products_requests(product_id, request_id, product_qty)
VALUES (1, 1, 6);
insert into products_requests(product_id, request_id, product_qty)
VALUES (2, 2, 4);
insert into products_requests(product_id, request_id, product_qty)
VALUES (4, 3, 1);
insert into products_requests(product_id, request_id, product_qty)
VALUES (6, 4, 2);
insert into products_requests(product_id, request_id, product_qty)
VALUES (5, 5, 5);
insert into products_requests(product_id, request_id, product_qty)
VALUES (1, 6, 2);

insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Олег', 'Прасюк', 'prasik@gmai.com', '+380661597415', 'Автоматично створений контакт', 1);
insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Данил', 'Гончар', 'goncha@gmai.com', '+380661595128', 'Автоматично створений контакт', 1);
insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Олена', 'Трудова', 'trud.O@gmai.com', '+380688451493', 'Автоматично створений контакт', 1);
insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Семен', 'Вацюк', 'vazyuk18@gmai.com', '+380665468413', 'Автоматично створений контакт', 1);
insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Христина', 'Жвава', 'jvavaX88@gmai.com', '+380551584615', 'Автоматично створений контакт', 1);
insert into contacts(id, first_name, last_name, email, phone_number, contact_description, company_id)
values (default, 'Абрам', 'Матейник', 'abrammat68@gmai.com', '+380668457411', 'Автоматично створений контакт', 1);