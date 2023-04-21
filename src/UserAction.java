import java.util.Scanner;

public class UserAction {

//    FlightAction flightAction = new FlightAction();
    Scanner cin = new Scanner(System.in);


    public void passengerMenu(Flight[] flights, User[] users,  Admin admin , UserAction userAction , FlightAction flightAction ) {

        Login login = new Login();

        int userOption;


        System.out.println("[ Passenger menu option ] \n\n[1] Change password \n[2] Search flight tickets \n[3] Booking tickets\n[4] Ticket cancellation \n[5] Booked tickets \n[6] Add charge \n[0] sing out ");

        userOption = cin.nextInt();

        switch (userOption) {

            case 1: {
                changePassword(users ,flights ,admin , userAction,flightAction);
                break;
            }

            case 2: {
                searchFlight(users ,flights ,admin , userAction,flightAction);
                break;
            }

            case 3: {
                bookingTicket(flights ,users ,admin , userAction,flightAction);
                break;
            }

            case 4: {
                ticketCancellation(users ,flights ,admin , userAction,flightAction);
                break;
            }

            case 5: {
                bookedTicket(users ,flights ,admin , userAction,flightAction);
                break;
            }

            case 6: {
                addCharge(users ,flights ,admin , userAction,flightAction);
                break;
            }

            case 0: {

                login.welcomeMenu(admin ,  userAction ,  flightAction ,  users ,  flights);
                break;
            }

            default: passengerMenu(flights , users ,admin , userAction,flightAction);

        }
        passengerMenu(flights , users ,admin , userAction,flightAction);

    }
//  ====================================================================================================================

//    [ Change Password ]

    private void changePassword(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {
        System.out.println("[ Enter new password ]");
        String newPass;
        newPass = cin.next();

        users[Login.loggedInIndex].setPass(newPass);
        System.out.println(Login.done);
        try{Thread.sleep(500);}catch(InterruptedException e) {};
        passengerMenu(flights , users ,admin , userAction,flightAction);

    }
//  ====================================================================================================================

//    [ Add charge ]

    private void addCharge(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {

        System.out.println("[ Enter the amount you want to charge ]");

        users[Login.loggedInIndex].setBudget(users[Login.loggedInIndex].getBudget() + cin.nextInt());
        System.out.println(Login.done);
        System.out.printf("[ Your budget : %d ]" , users[Login.loggedInIndex].getBudget());
        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            passengerMenu(flights , users ,admin , userAction,flightAction);
        }

    }
//  ====================================================================================================================

//    [ Booking ticket ]

    private void bookingTicket(Flight[] flights, User[] users, Admin admin , UserAction userAction , FlightAction flightAction) {
        int flightIndex;
        String flightId;

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

            }
        }



        System.out.println("[ Enter the flightId ]");

        flightId = cin.next();

        flightIndex = flightAction.checkFlight(flights, flightId);

        if (flightIndex == -1) {
            System.out.println("[ Flight not found ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            bookedTicket(users ,flights ,admin , userAction,flightAction);
        }

        if (seats(flightId, flights)) {


            for (int i = 0; i < 15; i++) {

                if (users[Login.loggedInIndex].tickets[i] == null) {

                    users[Login.loggedInIndex].tickets[i] = new Ticket();
                    users[Login.loggedInIndex].tickets[i].setTicketId((864200 + Ticket.ticketGeneratorCounter));
                    users[Login.loggedInIndex].tickets[i].setFlightId(flightId);
                    users[Login.loggedInIndex].setBudget(users[Login.loggedInIndex].getBudget() - flights[flightIndex].getPrice());
                    Ticket.ticketGeneratorCounter++;
                    System.out.println(Login.done);
                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    break;
                }

            }

        } else {

            System.out.println("[ There are not enough seats ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
        }

        passengerMenu(flights , users ,admin , userAction,flightAction);

    }

//  ====================================================================================================================

//  [ Ticket cancellation ]

    private void ticketCancellation(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {

        int i;
        i = searchTicket(users);

        if (i >= 0) {

            users[Login.loggedInIndex].tickets[i].setTicketId(0);
            users[Login.loggedInIndex].tickets[i].setFlightId(null);
            users[Login.loggedInIndex].tickets = null;
            System.out.println(Login.done);
            try{Thread.sleep(500);}catch(InterruptedException e) {};

        }
        passengerMenu(flights , users ,admin , userAction,flightAction);
    }

//  ====================================================================================================================

//    [ Booked tickets ]

    private void bookedTicket(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {


        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                for (int j = 0; j < 15; j++) {

                    if (users[Login.loggedInIndex].tickets[j] != null && users[Login.loggedInIndex].tickets[j].getFlightId().equals(flights[i].getFlightId())) {

                        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice());

                    }
                }
            }
        }
        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            passengerMenu(flights , users ,admin , userAction,flightAction);
        }

    }

//  ====================================================================================================================

//    [ Search ticket for cancel]

    private int searchTicket(User[] users) {

        System.out.println("[ Enter the ID of the ticket you want to cancel ]");
        String ticketId;
        ticketId = cin.next();

        for (int j = 0; j < 15; j++) {

            if (users[Login.loggedInIndex].tickets[j] != null) {

                if (users[Login.loggedInIndex].tickets[j].equals(ticketId)) {

                    return j;

                }
            }
        }


        return -1;
    }

//  ====================================================================================================================

//    [ Search for enough seats ]

    public boolean seats(String flightId, Flight[] flights) {

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null) {

                if (flights[i].getFlightId().equals(flightId))

                    if (flights[i].getSeats() > 0) {

                        return true;
                    }
            }
        }

        return false;
    }

//  ====================================================================================================================

//    [ Search flight ]

    private void searchFlight(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction) {

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

            }
        }

        System.out.println(" [ Choose how the tickets will be sorted ]\n\n [1] FlightId\n [2] Origin\n [3] Destination\n [4] Date\n [5] Time\n [6] Price\n [7] Seats\n [0] Bake ");
        switch (cin.nextInt()){
            case 1:{

                printFlightIdS(users ,flights ,admin , userAction,flightAction);
                break;

            }
            case 2:{

                printOriginS(users ,flights ,admin , userAction,flightAction);
                break;

            }
            case 3:{

                printDestinationS(users ,flights ,admin , userAction,flightAction);
                break;
            }
            case 4:{

                printDateS(users ,flights ,admin , userAction,flightAction);
                break;

            }
            case 5:{

                printTimeS(users ,flights ,admin , userAction,flightAction);
                break;

            }
            case 6:{

                printPriceS(users ,flights ,admin , userAction,flightAction);
                break;
            }
            case 7:{

                printSeatsS( users ,flights ,admin , userAction,flightAction);
                break;
            }
            case 0:{

                passengerMenu(flights , users ,admin , userAction,flightAction);
                break;

            } default:searchFlight(users , flights   ,admin , userAction,flightAction);
        }
    }
//  ====================================================================================================================

//    [ Sort by flightId ]

    public  void printFlightIdS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction){

        System.out.println("[ Enter the flightId ]");
        String flightId;
        flightId = cin.next();
        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if (flights[i].getFlightId().equals(flightId)){

                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight( users ,flights ,admin , userAction,flightAction);
        }
    }
//  ====================================================================================================================

//    [ Sort by origin ]

    public  void printOriginS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the origin ]");
        String origin;
        origin = cin.next();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if (flights[i].getOrigin().equals(origin)){


                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight(users ,flights , admin , userAction,flightAction);
        }

    }

//  ====================================================================================================================

//    [ Sort by destination ]

    public  void printDestinationS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the destination ]");
        String destinstion;
        destinstion = cin.next();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if (flights[i].getDestination().equals(destinstion)){


                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight( users ,flights ,admin , userAction,flightAction);
        }

    }
//  ====================================================================================================================

//    [ Sort by date ]

    public  void printDateS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the date ]");
        String date;
        date= cin.next();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if (flights[i].getDate().equals(date)){

                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight(users ,flights , admin , userAction,flightAction);
        }

    }

//  ====================================================================================================================

//    [ Sort by time ]

    public  void printTimeS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the time ]");
        String time;
        time = cin.next();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if (flights[i].getTime().equals(time)){

                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight(users ,flights , admin , userAction,flightAction);
        }

    }

//  ====================================================================================================================

//    [ Sort by price ]

    public  void printPriceS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the time ]");
        int min , max;
        System.out.println("[ Minimum : ]");
        min = cin.nextInt();
        System.out.println("[ Maximum : ]");
        max = cin.nextInt();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null){

                if ( flights[i].getPrice() <= max && flights[i].getPrice() >= min ){

                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId() , flights[i].getOrigin() , flights[i].getDestination() , flights[i].getDate() , flights[i].getTime() ,flights[i].getPrice() , flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight(users ,flights , admin , userAction,flightAction);
        }

    }
    //  ====================================================================================================================

//    [ Sort by time ]

    public  void printSeatsS(User[] users, Flight[] flights, Admin admin , UserAction userAction , FlightAction flightAction ) {


        System.out.println("[ Enter the seats ]");
        int min, max;
        System.out.println("[ Minimum : ]");
        min = cin.nextInt();
        System.out.println("[ Maximum : ]");
        max = cin.nextInt();

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                if (flights[i].getSeats() <= max && flights[i].getSeats() >= min) {

                    System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

                }
            }
        }

        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            searchFlight(users ,flights , admin , userAction,flightAction);
        }
    }

}