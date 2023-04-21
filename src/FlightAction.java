import java.util.Scanner;

public class FlightAction {

//  ====================================================================================================================

//  [ Specify  flightId ]

    public int checkFlight(Flight[] flight , String flightId){

        for (int i = 0; i < 30; i++) {

            if (flight[i] != null && flight[i].getFlightId() != null ) {


                if (flightId.equals(flight[i].getFlightId())) {

                    return i;

                }

            }

        }

        return -1;

    }
//  ====================================================================================================================

//  [ Default three flight ]

    public void flightDefault(Flight[] threeFlights)
    {

        threeFlights[0].setFlightId("WX-12");
        threeFlights[0].setOrigin("Yazd");
        threeFlights[0].setDestination("Tehran");
        threeFlights[0].setDate("1401-12-10");
        threeFlights[0].setTime("12:30");
        threeFlights[0].setPrice(700_000);
        threeFlights[0].setSeats(51);

        threeFlights[1].setFlightId("WZ-15");
        threeFlights[1].setOrigin("Mashhad");
        threeFlights[1].setDestination("Ahvaz");
        threeFlights[1].setDate("1401-12-11");
        threeFlights[1].setTime("08:00");
        threeFlights[1].setPrice(900_000);
        threeFlights[1].setSeats(245);

        threeFlights[2].setFlightId("BG-22");
        threeFlights[2].setOrigin("Shiraz");
        threeFlights[2].setDestination("Tabriz");
        threeFlights[2].setDate("1401-12-12");
        threeFlights[2].setTime("22:30");
        threeFlights[2].setPrice(1_100_000);
        threeFlights[2].setSeats(12);

    }


}

