create table if not exists airports
(
    iata_code text not null unique check (length(iata_code) = 3),
    full_name text not null,
    country   text not null,
    city      text not null,
    id        int generated always as identity primary key
);

create or replace view airports_view ("airportID", "iataCode", "fullName", city, country) as
select airports.id, airports.iata_code, airports.full_name, airports.city, airports.country
from airports;

create table if not exists airplanes
(
    id            int generated always as identity primary key,
    model         text not null,
    airplane_code text not null unique,
    capacity      int  not null
);

create or replace view airplanes_view ("airplaneID", "airplaneCode", model, capacity) as
select airplanes.id, airplanes.airplane_code, airplanes.model, airplanes.capacity 
from airplanes;

create table if not exists user_types
(
    id   int generated always as identity primary key,
    name text not null
);

create or replace view user_types_view ("userTypeID", name) as 
	select user_types.id, user_types."name" 
	from user_types;

create table if not exists users
(
    id        int generated always as identity primary key,
    user_name text not null,
    user_type int  not null references user_types (id)
);

create or replace view users_view ("userID", "userName", "userType") as 
	select users.id, users.user_name, users.user_type
	from users;

create table if not exists seat_types
(
    id          int generated always as identity primary key,
    name        text not null,
    extra_price int check (extra_price >= 0)
);

create or replace view seat_types_view ("seatTypeID", name, "extraPrice") as 
	select seat_types.id, seat_types."name", seat_types.extra_price
	from seat_types;

create table if not exists seats
(
    id           int generated always as identity primary key,
    seat_type_id int  not null references seat_types (id),
    seat_number  text not null check (length(seat_number) <= 3),
    airplane_id  int  not null references airplanes (id)
);

create or replace view seats_view ("seatID", "seatTypeId", "seatNumber", "airplaneId") as 
	select seats.id, seats.seat_type_id, seats.seat_number, seats.airplane_id 
	from seats;

create table if not exists meal_types
(
    id   int generated always as identity primary key,
    name text not null
);

create or replace view meal_types_view ("mealTypeID", name) as 
	select meal_types.id, meal_types."name" 
	from meal_types;

create table if not exists flight_routes
(
    id                     int generated always as identity primary key,
    origin_airport_id      int not null references airports (id),
    destination_airport_id int not null references airports (id)
);

create or replace view flight_routes_view ("flightRouteID", "originAiportCode", "destinationAiportCode") as 
	select first_conversion.id, first_conversion.iata_code , airports.iata_code 
	from (select flight_routes.id, airports.iata_code, flight_routes.destination_airport_id
		  from flight_routes join airports on flight_routes.origin_airport_id = airports.id) as first_conversion
	join airports on first_conversion.destination_airport_id = airports.id;

create table if not exists flights
(
    id              int generated always as identity primary key,
    departure_time  timestamp not null,
    arrival_time    timestamp not null,
    airplane_id     int       not null references airplanes (id),
    flight_route_id int       not null references flight_routes (id),
    base_price      int       not null check (base_price >= 0)
);

create or replace view flights_view ("flightID", "originAirport", "destinationAirport", 
							"departureTime", "arrivalTime", "basePrice") 
as
	select flights.id, flight_routes_view."originAiportCode", flight_routes_view."destinationAiportCode", 
		   flights.departure_time, flights.arrival_time, flights.base_price 
	from flights join flight_routes_view on flights.flight_route_id = flight_routes_view."flightRouteID";

create table if not exists flight_seats
(
    id        int generated always as identity primary key,
    seat_id   int                  not null references seats (id),
    flight_id int                  not null references flights (id),
    available boolean default true not null
);

create or replace view flight_seats_view ("flightSeatID", "seatID", "flightID", available ) as 
	select flight_seats.id, flight_seats.seat_id, flight_seats.flight_id, flight_seats.available 
	from flight_seats;

create table if not exists persons
(
    id         int generated always as identity primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null,
    birth_date date not null,
    gender     text not null check (length(gender) = 1)
);

create or replace view customers_view("customerID", "firstName", "lastName", email, "birthDate", gender) as
select persons.*
from persons join bookings on persons.id = bookings.person_id;

create table if not exists passengers
(
    id              int generated always as identity primary key,
    passport_number text not null unique check (length(passport_number) <= 9),
    person_id       int  not null references persons (id)
);

create or replace view passengers_view ("passengerID", "firstName", "lastName", "passportNumber", 
										email, "birthDate", gender)
as
	select passengers.id, persons.first_name, persons.last_name, passengers.passport_number,
		   persons.email, persons.birth_date, persons.gender 
	from passengers join persons on passengers.person_id = persons.id;

create table if not exists bookings
(
    id              int generated always as identity primary key,
    person_id       int       not null references persons (id),
    user_id         int       not null references users (id),
    time_of_booking timestamp not null
);

create or replace view bookings_view ("bookingID", "personID", "userID", "bookingTime") as 
	select bookings.id, bookings.person_id, bookings.user_id, bookings.time_of_booking 
	from bookings;

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

create or replace view tickets_view ("ticketID", "passengerID", "flightSeatID", "cabinBaggage", "checkedBaggage", 
									 "mealID", "bookingID", "pricePaid", "cancelled")	 
as 
	select tickets.id, tickets.passenger_id, tickets.flight_seat_id, tickets.cabin_baggage, tickets.checked_baggage,
	       tickets.meal_id, tickets.booking_id, tickets.price_paid, tickets.canceled 
	from tickets;

