create table if not exists airports
(
    iata_code text not null unique check (length(iata_code) = 3),
    full_name text not null,
    country   text not null,
    city      text not null,
    id        int generated always as identity primary key
);

create table if not exists airplanes
(
    id            int generated always as identity primary key,
    model         text not null,
    airplane_code text not null unique,
    capacity      int  not null
);

create table if not exists user_types
(
    id   int generated always as identity primary key,
    name text not null
);

create table if not exists users
(
    id        int generated always as identity primary key,
    user_name text not null,
    user_type int  not null references user_types (id)
);

create table if not exists seat_types
(
    id          int generated always as identity primary key,
    name        text not null,
    extra_price int check (extra_price >= 0)
);

create table if not exists seats
(
    id           int generated always as identity primary key,
    seat_type_id int  not null references seat_types (id),
    seat_number  text not null check (length(seat_number) <= 3),
    airplane_id  int  not null references airplanes (id)
);

create table if not exists meal_types
(
    id   int generated always as identity primary key,
    name text not null
);

create table if not exists flight_routes
(
    id                     int generated always as identity primary key,
    origin_airport_id      int not null references airports (id),
    destination_airport_id int not null references airports (id)
);

create table if not exists flights
(
    id              int generated always as identity primary key,
    departure_time  timestamp not null,
    arrival_time    timestamp not null,
    airplane_id     int       not null references airplanes (id),
    flight_route_id int       not null references flight_routes (id),
    base_price      int       not null check (base_price >= 0)
);

create table if not exists flight_seats
(
    id        int generated always as identity primary key,
    seat_id   int                  not null references seats (id),
    flight_id int                  not null references flights (id),
    available boolean default true not null
);

create table if not exists persons
(
    id         int generated always as identity primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null,
    birth_date date not null,
    gender     text not null check (length(gender) = 1)
);

create table if not exists passengers
(
    id              int generated always as identity primary key,
    passport_number text not null unique check (length(passport_number) <= 9),
    person_id       int  not null references persons (id)
);

create table if not exists bookings
(
    id              int generated always as identity primary key,
    person_id       int       not null references persons (id),
    user_id         int       not null references users (id),
    time_of_booking timestamp not null
);

create table if not exists tickets
(
    id              int generated always as identity primary key,
    flight_seat_id  int                   not null references flight_seats (id),
    cabin_baggage   int     default 1     not null,
    checked_baggage int     default 1     not null,
    meal_id         int     default 1     not null references meal_types (id),
    booking_id      int                   not null references bookings (id),
    canceled        boolean default false not null,
    passenger_id    int                   not null references passengers (id),
    price_paid      int                   not null check (price_paid >= 0)
);

create or replace view view_customers(id, first_name, last_name, email, birth_date, gender) as
SELECT persons.*
FROM persons
JOIN bookings on persons.id = bookings.person_id;
