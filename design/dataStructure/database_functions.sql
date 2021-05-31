-- Create a view for the Flights Route table that shows Origin and Destination Airport instead of their IDs
create or replace view flightRoutesView (flightRouteID, originAirportCode, destinationAirportCode) as
select first_conversion.id, first_conversion.iata_code, airports.iata_code
from (select flight_routes.id, airports.iata_code, flight_routes.destination_airport_id
      from flight_routes
               join airports on flight_routes.origin_airport_id = airports.id) as first_conversion
         join airports on first_conversion.destination_airport_id = airports.id
order by first_conversion.id;

-- Create a view for the Flights table that shows Origin and Destination Airport instead of Flight Route ID
-- and shows the Airplane Model instead of the Airplane ID
create or replace view flightsView
            (flightID, originAirport, destinationAirport,
             departureTime, arrivalTime, airplaneModel, basePrice)
as
select first_conversion.id,
       flightRoutesView.originAirportCode,
       flightRoutesView.destinationAirportCode,
       first_conversion.departure_time,
       first_conversion.arrival_time,
       first_conversion.model,
       first_conversion.base_price
from (select flights.id, flights.departure_time, flights.arrival_time, airplanes.model, flight_route_id, base_price
      from flights
               join airplanes on flights.airplane_id = airplanes.id) as first_conversion
         join flightRoutesView on first_conversion.flight_route_id = flightRoutesView.flightRouteID
order by first_conversion.id;

-- Create a view for Customers which finds out which Persons are Customers base on the Persons ID on the Bookings table.
-- It also gives extra attributes related to the Customers like firstName, lastName, ...
create or replace view customersView(customerID, firstName, lastName, email, birthDate, gender) as
select persons.*
from persons
         join bookings on persons.id = bookings.person_id
order by persons.id;

-- Create a view for Passengers which finds out which Persons are Passengers base on the Persons ID on the Persons table
-- It also gives extra attributes related to the Passengers like firstName, lastName, passportNumber, ...
create or replace view passengersView
            (passengerID, firstName, lastName, passportNumber,
             email, birthDate, gender)
as
select passengers.id,
       persons.first_name,
       persons.last_name,
       passengers.passport_number,
       persons.email,
       persons.birth_date,
       persons.gender
from passengers
         join persons on passengers.person_id = persons.id
order by passengers.id;

create or replace view airplaneSchedule (airplaneCode, departureTime, arrivalTime) as
select airplanes.airplane_code,
       (flights.departure_time - (60 * interval '1 minute')) as depTime,
       (flights.arrival_time + (60 * interval '1 minute'))   as arrTime
from flights
         join airplanes on flights.airplane_id = airplanes.id;


create or replace view airportsView (airportID, iataCode, fullName, city, country) as
select airports.id, airports.iata_code, airports.full_name, airports.city, airports.country
from airports
order by airports.id;

create or replace view airplanesView (airplaneID, model, airplaneCode, capacity) as
select airplanes.id, airplanes.model, airplanes.airplane_code, airplanes.capacity
from airplanes
order by airplanes.id;

-- Function that returns a flightRouteID for a given originAirport and destinationAirport
create or replace function getFlightRouteID(originAirport text, destinationAirport text)
    returns flight_routes.id%type as
$$
declare
    originAirportID      airports.id%type;
    destinationAirportID airports.id%type;
    flightRouteID        flight_routes.id%type;
begin
    select airports.id
    into originAirportID
    from airports
    where airports.iata_code = originAirport;

    select airports.id
    into destinationAirportID
    from airports
    where airports.iata_code = destinationAirport;

    select flight_routes.id
    into flightRouteID
    from flight_routes
    where destination_airport_id = destinationAirportID
      and origin_airport_id = originAirportID;

    return flightRouteID;
end;
$$ language plpgsql;

-- Function that returns an airplaneID for a given airplaneModel
create or replace function getAirplaneID(airplaneModel text)
    returns airplanes.id%type as
$$
declare
    airplaneID airplanes.id%type;
begin

    select airplanes.id
    into airplaneID
    from airplanes
    where airplanes.model = airplaneModel;

    return airplaneID;
end;
$$ language plpgsql;

-- Function that returns a trigger. It allows to insert flights into the Flights table through
-- the Flights view (flightsTable)
create or replace function flight_insert()
    returns trigger as
$$
declare
    flightRouteID flight_routes.id%type;
    airplaneID    airplanes.id%type;
    flight        flights%rowtype;
begin

    select *
    into flightRouteID
    from getFlightRouteID(new.originAirport, new.destinationAirport);

    select *
    into airplaneID
    from getAirplaneID(new.airplaneModel);

    insert into flights (departure_time, arrival_time, airplane_id, flight_route_id, base_price)
    values (new.departureTime, new.arrivalTime, airplaneID, flightRouteID, new.basePrice);
    new.flightID = (select max(flights.id) from flights);
    return new;

end;
$$ language plpgsql;

-- Trigger that fires when trying to insert data through the Flight view (flightsTable)
create trigger insert_flight
    instead of insert
    on flightsView
    for each row
execute procedure flight_insert();


--Create booking Use Case
create or replace view bookingsView(bookingId, personId, userId, timeOfBooking) as
select *
from bookings;
