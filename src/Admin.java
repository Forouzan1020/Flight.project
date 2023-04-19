import java.util.Objects;
import java.util.Scanner;

public class Admin {

    TicketsAction ticketsAction = new TicketsAction();

    public void admin(int optionAdmin, TicketsInformation[] tickets) {

        String flightId, flightChange;
        int optionUpdate;
        boolean loop1 = true , loop2 = true;
        Scanner cin = new Scanner(System.in);

        switch (optionAdmin) {
            case 1: {

//              [ Add tickets ]

                do {


                    System.out.println("[ Enter flightId ]");

                    flightId = cin.next();

                    if (ticketsAction.checkTicket(tickets, flightId) == true) {

                        for (int i = 0; i < 30; i++) {

                            if (tickets[i].getFlightId() == null) {

                                tickets[i].setFlightId(flightId);

                                System.out.println("[ Enter origin ]");

                                tickets[i].setOrigin(cin.next());

                                System.out.println(" [ Enter destination ]");

                                tickets[i].setDestination(cin.next());

                                System.out.println("[ Enter date ]");

                                tickets[i].setDate(cin.next());

                                System.out.println(" [ Enter time ]");

                                tickets[i].setTime(cin.next());

                                System.out.println("[ Enter price ]");

                                tickets[i].setPrice(cin.nextInt());

                                System.out.println("[ Enter seats ]");

                                tickets[i].setSeats(cin.nextInt());

                                break;


                            }

                        }

                    } else {

                        System.out.println("[ This ticket is exist ]");
                    }

                } while (loop1);

            }

            case 2: {

//              [ Update tickets ]


                do {

                    System.out.println("[ Which ticket do you want to change ]");

                    flightChange = cin.next();

                    if (findTicket(flightChange, tickets) != 50) {

                        int flyId;

                        flyId = findTicket(flightChange, tickets);

                        System.out.println("[ Which feature do you want to change ] \n\n [1] FlightId \n [2] Origin \n [3] Destination \n [4] Date \n [5] Time \n [6] Price \n [7] Seats");

                        optionUpdate = cin.nextInt();

                        switch (optionUpdate) {
                            case 1: {
                                System.out.println("[ Enter new FlightId ]");
                                tickets[flyId].setFlightId(cin.next());
                                break;
                            }

                            case 2: {
                                System.out.println("[ Enter new Origin ]");
                                tickets[flyId].setOrigin(cin.next());
                                break;
                            }
                            case 3:{
                                System.out.println("[ Enter new Destination ]");
                                tickets[flyId].setDestination(cin.next());
                                break;
                            }
                            case 4:{
                                System.out.println("[ Enter new Date ]");
                                tickets[flyId].setDate(cin.next());
                                break;
                            }
                            case 5:{
                                System.out.println("[ Enter new Time ]");
                                tickets[flyId].setTime(cin.next());
                                break;
                            }
                            case 6: {
                                System.out.println("[ Enter new Price ]");
                                tickets[flyId].setPrice(cin.nextInt());
                                break;
                            }
                            case 7:{
                                System.out.println("[ Enter new Seats ]");
                                tickets[flyId].setSeats(cin.nextInt());
                                break;
                            }
                            }

                        }else {
                        System.out.println("[ Not find ]");
                    }
                }while (loop2);
            }
        }

    }




//  ====================================================================================================================

//  [ Find the desired ticket ]

    public int findTicket(String flightId , TicketsInformation [] tickets) {

        int flyId = 50;

        for (int i = 0; i < 30; i++) {

            if (tickets[i] != null && flightId.equals(tickets[i].getFlightId())) {

                flyId = i ;

            } else {
                flyId = 50;
            }

        }

        return flyId;
    }


}
