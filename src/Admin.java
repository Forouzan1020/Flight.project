import java.util.Objects;
import java.util.Scanner;

public class Admin {

    TicketsAction ticketsAction = new TicketsAction();

    public  void  admin( int optionAdmin, TicketsInformation [] tickets){

        String flightId  ;
        int save =0 , trueContinue = 0;
        Scanner cin = new Scanner(System.in);

        switch (optionAdmin){
            case 1:{

//              [ Add tickets ]

                System.out.println("[ Enter flightId ]");

                flightId = cin.next();

                if (ticketsAction.checkTicket(tickets , flightId) == true) {

                    for (int i = 0; i < 30; i++) {

                        if (tickets[i].getFlightId() == null){

                            tickets[i].setFlightId(flightId);

                            save = i ;

                            trueContinue = 1;

                            break;
                        }

                    }

                    break;

                }
                if (trueContinue == 1) {

                    System.out.println("[ Enter origin ]");

                    tickets[save].setOrigin(cin.next());

                    System.out.println(" [ Enter destination ]");

                    tickets[save].setDestination(cin.next());

                    System.out.println("[ Enter date ]");

                    tickets[save].setDate(cin.next());

                    System.out.println(" [ Enter time ]");

                    tickets[save].setTime(cin.next());

                    System.out.println("[ Enter price ]");

                    tickets[save].setPrice(cin.nextInt());

                    System.out.println("[ Enter seats ]");

                    tickets[save].setSeats(cin.nextInt());

                    break;

                }
            }










        }
    }
}
