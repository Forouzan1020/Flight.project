public class TicketsAction {

    public  void  newTickets(TicketsInformation [] tickets){


        for (int i = 0; i < 30; i++) {

            tickets[i] = new TicketsInformation();

        }

    }


//  ====================================================================================================================

//  [ Three Ticket in start ]

    public  void ticketsDefault(TicketsInformation[] threeTickets){

        threeTickets[0].setFlightId("WX-12");
        threeTickets[0].setOrigin("Yazd");
        threeTickets[0].setDestination("Tehran");
        threeTickets[0].setDate("1401-12-10");
        threeTickets[0].setTime("12:30");
        threeTickets[0].setPrice(700_000);
        threeTickets[0].setSeats(51);

        threeTickets[1].setFlightId("WZ-15");
        threeTickets[1].setOrigin("Mashhad");
        threeTickets[1].setDestination("Ahvaz");
        threeTickets[1].setDate("1401-12-11");
        threeTickets[1].setTime("08:00");
        threeTickets[1].setPrice(900_000);
        threeTickets[1].setSeats(245);

        threeTickets[2].setFlightId("BG-22");
        threeTickets[2].setOrigin("Shiraz");
        threeTickets[2].setDestination("Tabriz");
        threeTickets[2].setDate("1401-12-12");
        threeTickets[2].setTime("22:30");
        threeTickets[2].setPrice(1_100_000);
        threeTickets[2].setSeats(12);

    }

//  ====================================================================================================================

//  [ Specify tickets with flightId ]

    public  boolean checkTicket(TicketsInformation [] tickets , String flightId){

        for (int i = 0; i < 30; i++) {

            if (tickets[i] != null && tickets[i].equals(flightId)){

                return  true;

            }

        }

        return  false;

    }

}
