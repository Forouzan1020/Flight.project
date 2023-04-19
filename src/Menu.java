import java.util.Scanner;

public class Menu {

     Admin admin = new Admin();
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

          int optionSing, optionUser, optionAdmin ;

          String name, pass;

          boolean loop1 = true , loop2 = true , loop3 = true;

          System.out.println("[ Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up");

          optionSing = cin.nextInt();

          switch (optionSing) {

               case 1: {
                    do {

                    System.out.println("[ Enter your name ] ");

                    name = cin.next();



                         if (checkName(name, users) == 0 || checkName(name, users) == 1) {

                              loop1 = false;

                              do {

                                   System.out.println("[ Enter your pass ] ");

                                   pass = cin.next();

                                   if (checkSing(name, pass, users) == 0) {

                                        System.out.println("[ Passenger menu option ] \n\n ");
                                        System.out.println("[1] Change password \n[2] Search flight tickets \n[3] Bocking tickets \n[4] Ticket cancellation \n[5] Boocked ticket \n[6] Add charge \n[0] Sing out");

                                        optionUser = cin.nextInt();
                                        userAction.passengerAction(optionUser);

                                        loop2 = false;
                                        break;

                                   } else if (checkSing(name, pass, users) == 1) {

                                        System.out.println("[ Admin menu option ] \n\n");
                                        System.out.println("[1] Add \n[2] Update \n[3] Remove \n[4] Flight schedules \n[0] Sing out");

                                        optionAdmin = cin.nextInt();
                                        admin.admin(optionAdmin, tickets);

                                        loop2 = false;
                                        break;

                                   } else if (checkSing(name, pass, users) == 2) {

                                        System.out.println("[ your password is not correct ]");

                                   }

                              }while (loop2);

                         }else {
                                   System.out.println("[ This username not find ]");
                              }


                    } while (loop1);

                    break;
               }

               case 2: {

                    System.out.println("[ Enter your name ] ");

                    name = cin.next();

                    do {
                              System.out.println("[ Enter your pass ] ");

                              pass = cin.next();

                              if (checkSing(name , pass , users) == 2){

                                   for (int i = 0; i < 30; i++) {

                                        if (users[i] == null){

                                             users[i].setName(name);

                                             users[i].setPass(pass);

                                             loop3 =false;
                                        }

                                        break;
                                   }

                              }else {
                                   System.out.println("[ This user already exist ]");
                              }


                    }while (loop3);

                    break;
               }
          }
     }


//  ====================================================================================================================

//  [ Specify user , exist or not exist ]

     public int checkName(String nameUser, UserInformation[] users) {

          int specify = 2;

          if (nameUser.equals("Admin")) {

               specify = 1;

          }

          for (int i = 0; i < 30; i++) {

               if (users[i].getName() != null) {

                    if (nameUser.equals(users[i].getName())) {


                         specify = 0;
                         break;

                    }

               }

          }

          return specify;
     }
//  ====================================================================================================================

//  [ Specify user , exist or not exist ]

     public int checkSing(String nameUser,String passUser, UserInformation[] users) {

          int specify = 2;

           if (nameUser.equals("Admin") && passUser.equals("Admin")) {

               specify = 1;
           }

          for (int i = 0; i < 30; i++) {

               if (users[i].getName() != null) {

                    if (nameUser.equals(users[i].getName()) && passUser.equals( users[i].getPass())) {

                         specify = 0;
                         break;


                    }

               }

          }

          return specify;
     }

}
