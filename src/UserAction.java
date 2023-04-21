import java.util.Scanner;

public class UserAction {

    FlightAction flightAction = new FlightAction();
    Scanner cin = new Scanner(System.in);


    public void passengerMenu(Flight[] flights, User[] user) {

        Login welcome = new Login();

        int userOption;


        System.out.println("[ Passenger menu option ] \n\n[1] Change password \n[2] Search flight tickets \n[3] Booking tickets\n[4] Ticket cancellation \n[5] Booked tickets \n[6] Add charge \n[0] sing out ");

        userOption = cin.nextInt();

        switch (userOption) {

            case 1: {
                changePassword(user, flights);
                break;
            }

            case 2: {
                searchFlight(flights, user);
                break;
            }

            case 3: {
                bookingTicket(flights, user);
                break;
            }

            case 4: {
                ticketCancellation(flights, user);
                break;
            }

            case 5: {
                bookedTicket(flights, user);
                break;
            }

            case 6: {
                addCharge(flights, user);
                break;
            }

            case 0: {
                welcome.welcomeMenu();
                break;
            }

            default: passengerMenu(flights,user);

        }
        passengerMenu(flights , user);

    }
//  ====================================================================================================================

//    [ Change Password ]

    private void changePassword(User[] user, Flight[] flights) {
        System.out.println("[ Enter new password ]");
        String newPass;
        newPass = cin.next();

        user[Login.loggedInIndex].setPass(newPass);
        System.out.println(Login.done);
        try{Thread.sleep(500);}catch(InterruptedException e) {};
        passengerMenu(flights, user);

    }
//  ====================================================================================================================

//    [ Add charge ]

    private void addCharge(Flight[] flight, User[] users) {

        System.out.println("[ Enter the amount you want to charge ]");

        users[Login.loggedInIndex].setBudget(users[Login.loggedInIndex].getBudget() + cin.nextInt());
        System.out.println(Login.done);
        System.out.printf("[ Your budget : %d ]" , users[Login.loggedInIndex].getBudget());
        System.out.println("[ Enter X if you want back ]");
        if (cin.next().equals("X")){

            passengerMenu(flight , users);
        }

    }
//  ====================================================================================================================

//    [ Booking ticket ]

    private void bookingTicket(Flight[] flights, User[] users) {
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
            bookedTicket(flights, users);
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

        passengerMenu(flights, users);

    }

//  ====================================================================================================================

//  [ Ticket cancellation ]

    private void ticketCancellation(Flight[] flights, User[] users) {

        int i;
        i = searchTicket(users);

        if (i >= 0) {

            users[Login.loggedInIndex].tickets[i].setTicketId(0);
            users[Login.loggedInIndex].tickets[i].setFlightId(null);
            users[Login.loggedInIndex].tickets = null;
            System.out.println(Login.done);
            try{Thread.sleep(500);}catch(InterruptedException e) {};

        }
        passengerMenu(flights, users);
    }

//  ====================================================================================================================

//    [ Booked tickets ]

    private void bookedTicket(Flight[] flights, User[] users) {


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

            passengerMenu(flights , users);
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

    private void searchFlight(Flight[] flights, User[] users) {

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int i = 0; i < 30; i++) {

            if (flights[i] != null && flights[i].getFlightId() != null) {

                System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15d|%-15d\n\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());

            }
        }

        System.out.println(" [ Choose how the tickets will be sorted ]\n\n [1] FlightId\n [2] Origin\n [3] Destination\n [4] Date\n [5] Time\n [6] Price\n [7] Seats\n [0] Bake ");
        switch (cin.nextInt()){
            case 1:{

            printFlightIdS(flights , users);
            break;

            }
            case 2:{

                printOriginS(flights , users);
                break;

            }
            case 3:{

                printDestinationS(flights , users);
                break;
            }
            case 4:{

                printDateS(flights , users);
                break;

            }
            case 5:{

                printTimeS(flights, users);
                break;

            }
            case 6:{

                printPriceS(flights , users);
                break;
            }
            case 7:{

                printSeatsS(flights , users);
                break;
            }
            case 0:{

            passengerMenu(flights , users);
            break;

            } default:searchFlight(flights , users);
        }
    }
//  ====================================================================================================================

//    [ Sort by flightId ]

    public  void printFlightIdS(Flight [] flights ,User [] users ){

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

            searchFlight(flights , users);
        }
    }
//  ====================================================================================================================

//    [ Sort by origin ]

    public  void printOriginS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }

    }

//  ====================================================================================================================

//    [ Sort by destination ]

    public  void printDestinationS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }

    }
//  ====================================================================================================================

//    [ Sort by date ]

    public  void printDateS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }

    }

//  ====================================================================================================================

//    [ Sort by time ]

    public  void printTimeS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }

    }

//  ====================================================================================================================

//    [ Sort by price ]

    public  void printPriceS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }

    }
    //  ====================================================================================================================

//    [ Sort by time ]

    public  void printSeatsS(Flight [] flights ,User [] users ) {


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

            searchFlight(flights , users);
        }
    }

}
