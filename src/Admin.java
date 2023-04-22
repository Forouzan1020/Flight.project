import java.util.Scanner;

public class Admin {

//    FlightAction flightAction = new FlightAction();

    Scanner cin = new Scanner(System.in);


    public void adminMenu(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {


        System.out.println("[ Admin menu option ] \n\n");
        System.out.println("[1] Add \n[2] Update \n[3] Remove \n[4] Flight schedules \n[0] Sing out");
        int optionAdmin;
        optionAdmin = cin.nextInt();

        switch (optionAdmin) {
            case 1: {

                addFlight(users ,flights ,admin , userAction,flightAction);
                break;

            }
            case 2: {

                editFlight(users ,flights ,admin , userAction,flightAction);
                break;

            }


            case 3: {

                removeFlight(users ,flights ,admin , userAction,flightAction);
                break;

            }

            case 4: {

                printSchedules(users ,flights ,admin , userAction,flightAction);
                break;

            }

            case 5: {
                Login login = new Login();
                login.start();
                break;

            } case 0:{

                Login login = new Login();

                login.welcomeMenu(admin ,  userAction ,  flightAction ,  users ,  flights);
                break;
            }
            default:adminMenu(users ,flights ,admin , userAction,flightAction);

        }
        adminMenu(users ,flights ,admin , userAction,flightAction);

    }

//  ====================================================================================================================
//  [ Edit flight ]

    private void editFlight(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {

        String flightChange ;

        int optionUpdate;

        System.out.println("[ Which flights do you want to change ]");

        flightChange =cin.next();


        if(findFlight(flightChange, flights)) {


            for (int i = 0; i < 30; i++) {

                if (flights[i] != null) {

                    if (flightChange.equals(flights[i].getFlightId())) {


                        System.out.println("[ Which feature do you want to change ] \n\n [1] FlightId \n [2] Origin \n [3] Destination \n [4] Date \n [5] Time \n [6] Price \n [7] Seats \n [0] Back");

                        optionUpdate = cin.nextInt();

                        switch (optionUpdate) {
                            case 1: {
                                System.out.println("[ Enter new FlightId ]");
                                flights[i].setFlightId(cin.next());
                                System.out.println(Login.done);
                                break;
                            }

                            case 2: {
                                System.out.println("[ Enter new Origin ]");
                                flights[i].setOrigin(cin.next());
                                System.out.println(Login.done);
                                break;
                            }
                            case 3: {
                                System.out.println("[ Enter new Destination ]");
                                flights[i].setDestination(cin.next());
                                System.out.println(Login.done);
                                break;
                            }
                            case 4: {
                                System.out.println("[ Enter new Date ]");
                                flights[i].setDate(cin.next());
                                System.out.println(Login.done);
                                break;
                            }
                            case 5: {
                                System.out.println("[ Enter new Time ]");
                                flights[i].setTime(cin.next());
                                System.out.println(Login.done);
                                break;
                            }
                            case 6: {
                                System.out.println("[ Enter new Price ]");
                                flights[i].setPrice(cin.nextInt());
                                System.out.println(Login.done);
                                break;
                            }
                            case 7: {
                                System.out.println("[ Enter new Seats ]");
                                flights[i].setSeats(cin.nextInt());
                                System.out.println(Login.done);
                                break;
                            }
                            case 0:{
                                adminMenu(users ,flights ,admin , userAction,flightAction);
                                break;
                            }
                            default:editFlight(users ,flights ,admin , userAction,flightAction);
                        }

                        adminMenu(users ,flights ,admin , userAction,flightAction);
                    }
                }
            }


        } else

        {
            System.out.println("[ Not found ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            editFlight(users ,flights ,admin , userAction,flightAction);
        }


    }

//  ====================================================================================================================
//  [ Add flight ]

    private void addFlight(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        String flightId;

        System.out.println("[ Enter flightId ] \n[ If you want back enter X ]");

        flightId = cin.next();

        if (flightId.equals("X")) {

            adminMenu(users ,flights ,admin , userAction,flightAction);

        }
        else if (flightAction.checkFlight(flights , flightId) == -1)
        {


            for (int i = 0; i < 30; i++)
            {

                if (flights[i].getFlightId() == null)
                {

                    flights[i].setFlightId(flightId);

                    System.out.println("[ Enter origin ]");

                    flights[i].setOrigin(cin.next());

                    System.out.println(" [ Enter destination ]");

                    flights[i].setDestination(cin.next());

                    System.out.println("[ Enter date ]");

                    flights[i].setDate(cin.next());

                    System.out.println(" [ Enter time ]");

                    flights[i].setTime(cin.next());

                    System.out.println("[ Enter price ]");

                    flights[i].setPrice(cin.nextInt());

                    System.out.println("[ Enter seats ]");

                    flights[i].setSeats(cin.nextInt());

                    System.out.println(Login.done);

                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    adminMenu(users ,flights ,admin , userAction,flightAction);
                }
            }
        }

        else
        {
            System.out.println("[ This flights is exist ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            addFlight(users ,flights ,admin , userAction,flightAction);
        }
    }

//  ====================================================================================================================
//  [ Remove flight ]


    private void removeFlight(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {

        String flightRemove;

        System.out.println("[ Enter the flightId you want remove ]");

        flightRemove = cin.next();

        if (findFlight(flightRemove, flights)) {

            for (int i = 0; i < 30; i++) {
                if (flights[i].getFlightId().equals(flightRemove)) {

                    flights[i].setFlightId(null);
                    flights[i].setOrigin(null);
                    flights[i].setDestination(null);
                    flights[i].setDate(null);
                    flights[i].setTime(null);
                    flights[i].setPrice(0);
                    flights[i].setSeats(0);
                    System.out.println(Login.done);
                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    adminMenu(users ,flights ,admin , userAction,flightAction);
                }
            }


        } else {
            System.out.println("[ This ticket does not exist ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            adminMenu(users ,flights ,admin , userAction,flightAction);
        }

    }


//  ====================================================================================================================

//  [ Find the desired flight ]

    public boolean findFlight(String flightId , Flight[] flight)
    {

        for (int i = 0; i < 30; i++) {

            if (flight[i].getFlightId() != null) {

                if (flightId.equals(flight[i].getFlightId())) {

                    return true;

                }
            }

        }

        return false;
    }

//  ====================================================================================================================

//  [ Print flight schedules ]

    public void printSchedules( User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {
        Scanner cin = new Scanner(System.in);

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

            }
        }

        System.out.println("[ Enter X if you want back ]");

        if (cin.next().equals("X") ) {

            adminMenu(users ,flights ,admin , userAction,flightAction);
        }

    }


}

