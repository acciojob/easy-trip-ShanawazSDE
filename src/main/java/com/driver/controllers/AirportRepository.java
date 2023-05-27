package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AirportRepository {
    Map<String,Airport> airportMap = new HashMap<>();
    Map<Integer,Flight> flightMap = new HashMap<>();
    Map<Integer,Passenger> passengerMap = new HashMap<>();
    Map<Integer, Integer> passengerFlightMap = new HashMap<>();
    Map<Integer,Map<Integer,Integer>> flightCustFareMap = new HashMap<>();
    public void addAirport(Airport airport) {
        airportMap.put(airport.getAirportName(),airport);
    }

    public Collection<Airport> getAllAirports() {
        return airportMap.values();
    }

    public void addFlight(Flight flight) {
        flightMap.put(flight.getFlightId(), flight);
    }

    public Collection<Flight> getAllFlights() {
        return flightMap.values();
    }

    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(), passenger);
    }

    public Airport getAirportByName(String airportName) {
        return airportMap.get(airportName);
    }

    public Set<Integer> getPassengersWhoBookedFlights() {
        return passengerFlightMap.keySet();
    }

    public Flight getFlightById(Integer flightId) {
        if(flightMap.containsKey(flightId))
       return flightMap.get(flightId);

        return null;
    }

    public int getCurrBookingsOfFlight(Integer flightId) {
        int count = 0;
        for(int fid : passengerFlightMap.values()){
            if(fid == flightId)count++;
        }
        return count;
    }

    public void bookATicket(Integer flightId, Integer passengerId) {
        passengerFlightMap.put(passengerId,flightId);

    }

    public boolean passengerFlightMapHasKeyValuePair(Integer flightId, Integer passengerId) {
        if(!passengerFlightMap.containsKey(passengerId) || !passengerFlightMap.get(passengerId).equals(flightId))
            return false;
        return true;
    }

    public void cancelATicket(Integer flightId, Integer passengerId) {
        passengerFlightMap.remove(passengerId);
    }

    public boolean passengerAlreadyBookedThisFlight(Integer flightId, Integer passengerId) {
        if(!passengerFlightMap.containsKey(passengerId) || !passengerFlightMap.get(passengerId).equals(flightId)){
            return false;
        }
        return true;
    }

    public Set<Integer> getAllPassengers() {
        return passengerFlightMap.keySet();
    }

    public Map<Integer, Integer> getCustFareMapForFlight(Integer flightId) {
        return flightCustFareMap.get(flightId);
    }

    public void addCustFareToFlightCustFareMap(Integer flightId, Integer passengerId, int flightFare) {
        flightCustFareMap.computeIfAbsent(flightId,fid -> new HashMap<Integer,Integer>());
        Map<Integer,Integer> custFareMap = flightCustFareMap.get(flightId);
        custFareMap.put(passengerId,flightFare);
    }

    public void deleteCustFareToFlightCustFareMap(Integer flightId, Integer passengerId) {
        Map<Integer,Integer> custFareMap = flightCustFareMap.get(flightId);
        custFareMap.remove(passengerId);
    }
}
