import java.util.Scanner;

public class UserAction {

    FlightAction flightAction = new FlightAction();
    Ticket [] tickets = new  Ticket[15];
    Scanner cin = new Scanner(System.in);


    public void passengerMenu(Flight[] flights , User[] user)
    {

        Login welcome = new Login();

        int userOption;


        System.out.println("[ Passenger menu option ] \n\n[1] Change password \n[2] Search flight tickets \n[3] Booking tickets\n[4] Ticket cancellation \n[5] Booked tickets \n[6] Add charge \n[0] sing out ");

        userOption = cin.nextInt();

        switch (userOption){

            case 1:
            {
                changePassword(user ,flights );
                break;

            }

            case 2:
            {
                searchFlight();
                break;
            }

            case 3:
            {
              bookingTicket(flights , user);
              break;
            }

            case 4:
            {
                ticketCancellation(user);
                break;
            }

            case 5:
            {
                bookedTicket();
                break;
            }

            case 6:
            {
                addCharge(user);
                break;
            }

            case 0:
            {
                welcome.welcomeMenu();
                break;
            }

        }

    }
//  ====================================================================================================================

//    [ Change Password ]

    private void changePassword(User[] user , Flight[] flights )
    {
        System.out.println("[ Enter new password ]");
        String newPass ;
        newPass = cin.next();

        user[Login.loggedInIndex].setPass(newPass);
        passengerMenu(flights , user);

    }
//  ====================================================================================================================

//    [ Add charge ]

    private void addCharge(User [] users) {

        System.out.println("[ Enter the amount you want to charge ]");

        users[Login.loggedInIndex].setBudget(users[Login.loggedInIndex].getBudget()+cin.nextInt());
    }
//  ====================================================================================================================

//    [ Booking ticket ]

    private void bookingTicket(Flight [] flights , User [] users) {
        int flightIndex;
        String flightId;
        System.out.println("[ Enter the flightId ]");

        flightId = cin.next();
        flightIndex = flightAction.checkFlight(flights , flightId) ;

        if (flightIndex == -1) {
            System.out.println("[ Flight not found ]");
            bookedTicket();
        }



        for (int i = 0; i < 15; i++) {

            if (users[Login.loggedInIndex].tickets[i] == null ) {
                tickets[i] = new Ticket();
                tickets[i].setTicketId((864200 + Ticket.ticketGeneratorCounter));
                tickets[i].setFlightId(flightId);

                break;
            }

        }


        Ticket.ticketGeneratorCounter++;

        passengerMenu(flights , users);

    }

//  ====================================================================================================================

//  [ Ticket cancellation ]

    private void ticketCancellation(User [] users) {

        int i;
        i = searchTicket(users) ;

        if ( i >= 0){

           users[Login.loggedInIndex].tickets[i].setTicketId(0);
           users[Login.loggedInIndex].tickets[i].setFlightId(null);
           users[Login.loggedInIndex].tickets = null;

        }
    }

//  ====================================================================================================================

//    [ Booked tickets ]

    private void bookedTicket() {

    }


//  ====================================================================================================================

//    [ Search flight ]

    private void searchFlight() {

    }
//  ====================================================================================================================

//    [ Search ticket ]

    private int searchTicket(  User [] users) {

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



 }
