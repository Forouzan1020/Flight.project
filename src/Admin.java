import java.util.Scanner;

public class Admin {

    FlightAction flightAction = new FlightAction();

    Scanner cin = new Scanner(System.in);


    public void adminMenu( Flight[] flight) {


        System.out.println("[ Admin menu option ] \n\n");
        System.out.println("[1] Add \n[2] Update \n[3] Remove \n[4] Flight schedules \n[0] Sing out");
        int optionAdmin;
        optionAdmin = cin.nextInt();

        switch (optionAdmin) {
            case 1: {

                addFlight(flight);
                break;

            }
            case 2: {

                editFlight(flight);
                break;

            }


            case 3: {

                removeFlight(flight);
                break;

            }

            case 4: {

                printSchedules(flight);
                break;

            }

            case 5: {
                Login login = new Login();
                login.start();
                break;

            } case 0:{
                Login login = new Login();
                login.welcomeMenu();
                break;
            }
            default:adminMenu(flight);

        }
        adminMenu(flight);

    }

//  ====================================================================================================================
//  [ Edit flight ]

    private void editFlight(Flight[] flight) {

    String flightChange ;

    int optionUpdate;

    System.out.println("[ Which flight do you want to change ]");

    flightChange =cin.next();


        if(findFlight(flightChange, flight)) {


        for (int i = 0; i < 30; i++) {

            if (flight[i] != null) {

                if (flightChange.equals(flight[i].getFlightId())) {


                    System.out.println("[ Which feature do you want to change ] \n\n [1] FlightId \n [2] Origin \n [3] Destination \n [4] Date \n [5] Time \n [6] Price \n [7] Seats \n [0] Back");

                    optionUpdate = cin.nextInt();

                    switch (optionUpdate) {
                        case 1: {
                            System.out.println("[ Enter new FlightId ]");
                            flight[i].setFlightId(cin.next());
                            System.out.println(Login.done);
                            break;
                        }

                        case 2: {
                            System.out.println("[ Enter new Origin ]");
                            flight[i].setOrigin(cin.next());
                            System.out.println(Login.done);
                            break;
                        }
                        case 3: {
                            System.out.println("[ Enter new Destination ]");
                            flight[i].setDestination(cin.next());
                            System.out.println(Login.done);
                            break;
                        }
                        case 4: {
                            System.out.println("[ Enter new Date ]");
                            flight[i].setDate(cin.next());
                            System.out.println(Login.done);
                            break;
                        }
                        case 5: {
                            System.out.println("[ Enter new Time ]");
                            flight[i].setTime(cin.next());
                            System.out.println(Login.done);
                            break;
                        }
                        case 6: {
                            System.out.println("[ Enter new Price ]");
                            flight[i].setPrice(cin.nextInt());
                            System.out.println(Login.done);
                            break;
                        }
                        case 7: {
                            System.out.println("[ Enter new Seats ]");
                            flight[i].setSeats(cin.nextInt());
                            System.out.println(Login.done);
                            break;
                        }
                        case 0:{
                            adminMenu(flight);
                            break;
                        }
                        default:editFlight(flight);
                    }

                    adminMenu(flight);
                }
            }
        }


    } else

    {
        System.out.println("[ Not found ]");
        try{Thread.sleep(500);}catch(InterruptedException e) {};
        editFlight(flight);
    }


}

//  ====================================================================================================================
//  [ Add flight ]

    private void addFlight(Flight[] flight ) {


        String flightId;

        System.out.println("[ Enter flightId ] \n [ If you want back enter X ]");

        flightId = cin.next();

        if (flightId.equals("X")) {

        adminMenu(flight);

        }
        else if (flightAction.checkFlight(flight , flightId) == -1)
        {


            for (int i = 0; i < 30; i++)
            {

                if (flight[i].getFlightId() == null)
                {

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

                    System.out.println(Login.done);

                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    adminMenu(flight);
                }
            }
        }

        else
        {
            System.out.println("[ This flight is exist ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            addFlight(flight);
        }
    }

//  ====================================================================================================================
//  [ Remove flight ]


    private void removeFlight(Flight[] flight) {

        String flightRemove;

        System.out.println("[ Enter the flightId you want remove ]");

        flightRemove = cin.next();

        if (findFlight(flightRemove, flight)) {

            for (int i = 0; i < 30; i++) {
                if (flight[i].getFlightId().equals(flightRemove)) {

                    flight[i].setFlightId(null);
                    flight[i].setOrigin(null);
                    flight[i].setDestination(null);
                    flight[i].setDate(null);
                    flight[i].setTime(null);
                    flight[i].setPrice(0);
                    flight[i].setSeats(0);
                    System.out.println(Login.done);
                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    adminMenu(flight);
                }
            }


        } else {
            System.out.println("[ This ticket does not exist ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            adminMenu(flight);
        }

    }


//  ====================================================================================================================

//  [ Find the desired flight ]

    public boolean findFlight(String flightId , Flight[] flight)
    {



        for (int i = 0; i < 30; i++) {

            if (flight[i].getFlightId() != null) {

                if (flightId.equals(flight[i].getFlightId())) {

                    return  true;

                }
            }

        }

        return false;
    }

//  ====================================================================================================================

//  [ Print flight schedules ]

    public void printSchedules( Flight[] flights) {
        Scanner cin = new Scanner(System.in);

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

            }
        }

        System.out.println("[ Enter X if you want back ]");

        if (cin.next().equals("X") ) {

           adminMenu(flights);
        }

    }


}
