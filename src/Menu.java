import java.util.Scanner;

public class Menu {

     UserAction userAction = new UserAction();
     TicketsAction ticketsAction = new TicketsAction();
     UserInformation[] users = new UserInformation[30];
     TicketsInformation[] tickets = new TicketsInformation[30];


//  ====================================================================================================================

//  [ Start ]

     public void start() {

          userAction.newUser(users);
          ticketsAction.newTickets(tickets);
          ticketsAction.ticketsDefault(tickets);

     }

//  ====================================================================================================================

//  [ Menu ]

     public void menu() {

          Scanner cin = new Scanner(System.in);

          int optionSing , optionUser;

          String name, pass;

          System.out.println("[ Welcome to airline reservation system ] \n\n [ Menu option ] \n\n  [1] Sing in \n [2] Sing up \n\n");

          optionSing = cin.nextInt();

          switch (optionSing) {

               case 1: {

                    System.out.println("[ Enter your Name ] \n\n");

                    name = cin.next();

                    System.out.println("[ Enter your pass ] \n\n");

                    pass = cin.next();

                    if (checkSing(name , pass) == 0){

                         System.out.println("Passenger menu option \n\n");
                         System.out.println("[1] Change password \n [2] Search flight tickets \n [3] Bocking tickets \n [4] Ticket cancellation \n [5] Boocked ticket \n [6] Add charge \n [0] Sing out");

                         optionUser = cin.nextInt();
                         userAction.passengerAction(optionUser);

                    } else if (checkSing(name , pass) == 1) {

                         System.out.println("Admin menu option \n\n");
                         System.out.println("[1] Add \n [2] Update \n [3] Remove \n [4] Flight schedules \n [0] Sing out");

                    }


               }

               case 2: {

                    System.out.println("[ Enter your Name ] \n\n");

                    name = cin.next();

                    System.out.println("[ Enter your pass ] \n\n");

                    pass = cin.next();

               }
          }
     }


//  ====================================================================================================================

//  [ Specify user , exist or not exist ]

     public int checkSing(String nameUser, String passUser) {

          int specify = 3;

          for (int i = 0; i < 25; i++) {

               if (users[i] != null) {

                    if (users[i].equals(nameUser) && users[i].equals(passUser)) {

                         specify = 0;

                    } else if (nameUser.equals("Admin") && passUser.equals("Admin")) {

                         specify = 1;

                    } else {

                         specify = 2;

                    }

               }

          }

          return specify;
     }

}
