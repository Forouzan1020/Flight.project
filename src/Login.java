import java.util.Scanner;

public class Login {

     Admin admin = new Admin();
     UserAction userAction = new UserAction();
     User[] users = new User[30];
     Flight[] flights = new Flight[30];
     Scanner cin = new Scanner(System.in);
     public static int loggedInIndex;

//  ====================================================================================================================

//  [ Start ]

     public void start() {

          newUser(users);
          newFlight(flights);
          flightDeafualt(flights);
          welcomeMenu();

     }

//  ====================================================================================================================

//  [ Sing up and sing in ]

     public void welcomeMenu() {

          int optionSing;

          System.out.println("[ Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up");

          optionSing = cin.nextInt();

          switch (optionSing) {

               case 1:
                    signIn();

               case 2:
                    signUp();

               default:
          }
     }

//  ====================================================================================================================

     private void signUp() {
          String name, pass;

          System.out.println("[ Enter your name ] ");

          name = cin.next();
          System.out.println("[ Enter your pass ] ");

          pass = cin.next();

          if ( checkSing(name, pass, users) < 0 )
          {

               for (int i = 0; i < 30; i++) {

                    if (users[i].getName() == null)
                    {

                         users[i].setName(name);

                         users[i].setPass(pass);

                         welcomeMenu();


                    }

               }

          }

          else
          {
               System.out.println("[ This user already exist ]");
               signUp();
          }

     }


//  ====================================================================================================================

     private void signIn()
     {
          String name, pass;


          System.out.println("[ Enter your name ] ");

          name = cin.next();

          if (checkName(name, users) == 0 || checkName(name, users) == 1)
          {

               System.out.println("[ Enter your pass ] ");

               pass = cin.next();



               if (checkSing(name, pass, users) == -1) {

                    admin.adminMenu(cin.nextInt(), flights);
               }

               else if (checkSing(name, pass, users) == -2)
               {

                    System.out.println("[ your password is not correct ]");
                    signIn();

               }

               else
               {
                    loggedInIndex=checkSing(name, pass, users);
                    userAction.passengerMenu( flights, users);

               }

          } else
          {
               System.out.println("[ This username not found ]");
               signIn();
          }


     }


//  ====================================================================================================================

//  [ Specify username , exist or not exist ]

     public int checkName(String nameUser, User[] users) {

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

     public int checkSing(String nameUser, String passUser, User[] users)
     {



          if ( ( nameUser.equals("admin") && passUser.equals("admin") ) || ( nameUser.equals("ADMIN") && passUser.equals("ADMIN") ) )
          {

               return -1;
          }

          for (int i = 0; i < 30; i++)
          {

               if (users[i].getName() != null)
               {

                    if (nameUser.equals(users[i].getName()) && passUser.equals(users[i].getPass()))
                    {

                         return i;


                    }

               }

          }

          return -2;

     }

     //**********************************************************************************

     public void newUser(User[] users) {


          for (int i = 0; i < 30; i++) {

               users[i] = new User();

          }

     }
     //**********************************************************************************

     public void flightDeafualt(Flight[] threeTickets)
     {

          threeTickets[0].setFlightId("WX-12");
          threeTickets[0].setOrigin("Yazd");
          threeTickets[0].setDestination("Tehran");
          threeTickets[0].setDate("1401-12-10");
          threeTickets[0].setTime("12:30");
          threeTickets[0].setPrice(700_000);
          threeTickets[0].setSeats(51);

          threeTickets[1].setFlightId("WZ-15");
          threeTickets[1].setOrigin("Mashhad");
          threeTickets[1].setDestination("Ahvaz");
          threeTickets[1].setDate("1401-12-11");
          threeTickets[1].setTime("08:00");
          threeTickets[1].setPrice(900_000);
          threeTickets[1].setSeats(245);

          threeTickets[2].setFlightId("BG-22");
          threeTickets[2].setOrigin("Shiraz");
          threeTickets[2].setDestination("Tabriz");
          threeTickets[2].setDate("1401-12-12");
          threeTickets[2].setTime("22:30");
          threeTickets[2].setPrice(1_100_000);
          threeTickets[2].setSeats(12);

     }

     //**********************************************************************************
     public void newFlight(Flight[] tickets)
     {

          for (int i = 0; i < 30; i++) {

               tickets[i] = new Flight();

          }

     }

     //**********************************************************************************
}
