public class FlightAction {

//  ====================================================================================================================

//  [ Specify  flightId ]

    public int checkFlight(Flight[] flight , String flightId){

        for (int i = 0; i < 30; i++) {

            if (flight[i] != null && !flightId.equals(flight[i].getFlightId())){

                return  i;

            }

        }

        return  -1;

    }
//  ====================================================================================================================

//  [ Print flight schedules ]

    public void printSchedules(Flight[] tickets){

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (tickets[i] != null && tickets[i].getFlightId() != null){

                 System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", tickets[i].getFlightId() , tickets[i].getOrigin() , tickets[i].getDestination() , tickets[i].getDate() , tickets[i].getTime() ,tickets[i].getPrice() , tickets[i].getSeats());

                }
            }

        }


}
