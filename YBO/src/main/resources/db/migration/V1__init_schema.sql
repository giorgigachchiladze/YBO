create table app_user (
    id uuid not null,
    password varchar(255),
    role varchar(255) check (role in ('USER','COMPANY')),
    username varchar(255),
    primary key (id)
);

create table company (
    id uuid not null,
    address varchar(255),
    company_description TEXT,
    name varchar(255),
    password varchar(255),
    phone_number varchar(255),
    role varchar(255) check (role in ('USER','COMPANY')),
    username varchar(255),
    primary key (id)
);

create table cv (
    birthday timestamp(6) with time zone,
    id uuid not null,
    user_id uuid unique,
    about_me TEXT,
    address varchar(255),
    education TEXT,
    email varchar(255),
    experience TEXT,
    first_name varchar(255),
    languages TEXT,
    last_name varchar(255),
    phone_number varchar(255),
    profession varchar(255),
    skills TEXT,
    primary key (id)
);

create table opening (
    company_id uuid not null,
    id uuid not null,
    address varchar(255),
    company_name varchar(255),
    experience_needed varchar(255),
    job_to_do TEXT,
    phone_number varchar(255),
    profession varchar(255),
    salary varchar(255),
    schedule varchar(255),
    primary key (id)
);

create table job_requests (
    cv_id uuid,
    id uuid not null,
    opening_id uuid,
    request_status varchar(255) check (request_status in ('APPROVED','REJECTED','PENDING')),
    primary key (id)
);


alter table if exists cv add constraint FK_cv_user foreign key (user_id) references app_user;
alter table if exists job_requests add constraint FK_job_cv foreign key (cv_id) references cv;
alter table if exists job_requests add constraint FK_job_opening foreign key (opening_id) references opening;
alter table if exists opening add constraint FK_opening_company foreign key (company_id) references company;