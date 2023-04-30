create table homee_user
(
    id              uuid not null
        primary key,
    about           varchar(255),
    email           varchar(255)
        constraint uk_qh1qjljx4hkbv0xfmwr1iflg9
            unique,
    first_name      varchar(255),
    last_logged_in  timestamp,
    last_name       varchar(255),
    password        varchar(30),
    registered_time timestamp,
    user_role       varchar(255),
    username        varchar(255),
    version         integer
);

create table space_group
(
    id            uuid not null
        primary key,
    about         varchar(255),
    name          varchar(255),
    homee_user_id uuid
        constraint fkmsnjry5vlfevyc67hk7w4pa9e
            references homee_user
);

create table space
(
    id             uuid not null
        primary key,
    about          varchar(255),
    name           varchar(255),
    version        integer,
    space_group_id uuid
        constraint fk7iev72073qcn6o10mimjdiimb
            references space_group,
    owner_id    uuid
);

create table device
(
    id             uuid not null
        primary key,
    about          varchar(255),
    created_at     timestamp,
    device_type    varchar(255),
    model          varchar(255),
    name           varchar(255),
    purchase_date  timestamp,
    purchase_price double precision,
    spot           varchar(255),
    updated_at     timestamp,
    warranty_end   timestamp,
    warranty_start timestamp,
    space_id       uuid
        constraint fktke1bcmyo41lxpv760pac4u1t
            references space,
    image_name     varchar(255)
);