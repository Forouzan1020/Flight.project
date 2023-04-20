import java.util.Scanner;

public class Admin {

    FlightAction ticketsAction = new FlightAction();
    Login login = new Login();
    Scanner cin = new Scanner(System.in);


    public void adminMenu(int optionAdmin, Flight[] flight) {


        System.out.println("[ Admin menu option ] \n\n");
        System.out.println("[1] Add \n[2] Update \n[3] Remove \n[4] Flight schedules \n[0] Sing out");

        switch (optionAdmin) {
            case 1: {

                addFlight(flight);

            }
            case 2: {

                editFlight(flight);

            }


            case 3: {

                removeFlight(flight);

            }

            case 4: {

                ticketsAction.printSchedules(flight);

            }

            case 5: {

                login.start();

            }

        }

    }

//  ====================================================================================================================
//              [ Edit flight ]

    private void editFlight(Flight[] flight) {

    String flightChange ;

    int optionUpdate;

    System.out.println("[ Which ticket do you want to change ]");

    flightChange =cin.next();


        if(findTicket(flightChange, flight)) {


        for (int i = 0; i < 30; i++) {

            if (flight[i] != null) {

                if (flightChange.equals(flight[i].getFlightId())) {


                    System.out.println("[ Which feature do you want to change ] \n\n [1] FlightId \n [2] Origin \n [3] Destination \n [4] Date \n [5] Time \n [6] Price \n [7] Seats");

                    optionUpdate = cin.nextInt();

                    switch (optionUpdate) {
                        case 1: {
                            System.out.println("[ Enter new FlightId ]");
                            flight[i].setFlightId(cin.next());
                            break;
                        }

                        case 2: {
                            System.out.println("[ Enter new Origin ]");
                            flight[i].setOrigin(cin.next());
                            break;
                        }
                        case 3: {
                            System.out.println("[ Enter new Destination ]");
                            flight[i].setDestination(cin.next());
                            break;
                        }
                        case 4: {
                            System.out.println("[ Enter new Date ]");
                            flight[i].setDate(cin.next());
                            break;
                        }
                        case 5: {
                            System.out.println("[ Enter new Time ]");
                            flight[i].setTime(cin.next());
                            break;
                        }
                        case 6: {
                            System.out.println("[ Enter new Price ]");
                            flight[i].setPrice(cin.nextInt());
                            break;
                        }
                        case 7: {
                            System.out.println("[ Enter new Seats ]");
                            flight[i].setSeats(cin.nextInt());
                            break;
                        }
                    }
                }
            }
        }


    } else

    {
        System.out.println("[ Not found ]");
    }


}

//  ====================================================================================================================
//              [ Add flight ]

    private void addFlight(Flight[] flight ) {


        String flightId;

        System.out.println("[ Enter flightId ]");

        flightId = cin.next();

        if (ticketsAction.checkFlight(flight, flightId) >= 0) {


            for (int i = 0; i < 30; i++) {

                if (flight[i].getFlightId() == null) {

                    flight[i].setFlightId(flightId);

                    System.out.println("[ Enter origin ]");

                    flight[i].setOrigin(cin.next());

                    System.out.println(" [ Enter destination ]");

                    flight[i].setDestination(cin.next());

                    System.out.println("[ Enter date ]");

                    flight[i].setDate(cin.next());

                    System.out.println(" [ Enter time ]");

                    flight[i].setTime(cin.next());

                    System.out.println("[ Enter price ]");

                    flight[i].setPrice(cin.nextInt());

                    System.out.println("[ Enter seats ]");

                    flight[i].setSeats(cin.nextInt());


                } else {

                    System.out.println("[ This ticket is exist ]");
                }

            }
        }

        }

//  ====================================================================================================================
//                [ Remove flight ]


    private void removeFlight(Flight[] flight) {

        String flightRemove;

        System.out.println("[ Enter the flightId you want remove ]");

        flightRemove = cin.next();

        if (findTicket(flightRemove, flight)) {

            for (int i = 0; i < 30; i++) {
                if (flight[i].getFlightId().equals(flightRemove)) {

                    flight[i].setFlightId(null);
                    flight[i].setOrigin(null);
                    flight[i].setDestination(null);
                    flight[i].setDate(null);
                    flight[i].setTime(null);
                    flight[i].setPrice(0);
                    flight[i].setSeats(0);

                }
            }


        } else {
            System.out.println("[ This ticket does not exist ]");
        }


    }


//  ====================================================================================================================

//  [ Find the desired ticket ]

    public boolean findTicket(String flightId , Flight[] flight)
    {

        boolean find = false;

        for (int i = 0; i < 30; i++) {

            if (flight[i].getFlightId() != null) {

                if (flightId.equals(flight[i].getFlightId())) {

                    find = true;
                    break;

                }
            }

        }

        return find;
    }


}
