import java.util.Scanner;

public class Login {

     Admin admin = new Admin();
     UserAction userAction = new UserAction();
     FlightAction flightAction = new FlightAction();
     User[] users = new User[30];
     Flight[] flights = new Flight[30];
     Scanner cin = new Scanner(System.in);
     public static int loggedInIndex;
     public static String done = "done";

//  ====================================================================================================================

//  [ Start ]

     public void start() {

          newUser(users);
          newFlight(flights);
          flightAction.flightDefault(flights);
          welcomeMenu();

     }

//  ====================================================================================================================

//  [ Sing up and sing in ]

     public void welcomeMenu() {

          int optionSing;

          System.out.println("[ Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up ");

          optionSing = cin.nextInt();

          switch (optionSing) {

               case 1:{
                    signIn();
                    break;}
               case 2:{
                    signUp();
                    break;}
               default:welcomeMenu();
          }
          welcomeMenu();
     }

//  ====================================================================================================================

     private void signUp() {

          String name, pass;


          System.out.println("[ Enter your name ] ");

          name = cin.next();

          System.out.println("[ Enter your pass ] \n [ Or if you want back enter X ] ");

          pass = cin.next();

          if ( pass.equals("X") ){

               welcomeMenu();
          }

          else if ( checkSing(name, pass, users) == -2 )
          {

               for (int i = 0; i < 30; i++) {

                    if (users[i] == null )
                    {

                         users[i].setName(name);

                         users[i].setPass(pass);

                         Login.loggedInIndex = i;

                         System.out.println(Login.done);

                         userAction.passengerMenu(flights,users);

                         break;
                    }

               }

          }
          else
          {
               System.out.println("[ This user already exist ]");

               try{Thread.sleep(500);}catch(InterruptedException e) {};

               welcomeMenu();
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

                    admin.adminMenu( flights);
               }

               else if (checkSing(name, pass, users) == -2)
               {

                    System.out.println("[ your password is not correct ]");
                    try{Thread.sleep(500);}catch(InterruptedException e) {};
                    welcomeMenu();

               }

               else
               {
                    loggedInIndex=checkSing(name, pass, users);
                    userAction.passengerMenu( flights, users);

               }

          } else
          {
               System.out.println("[ This username not found ]");
               try{Thread.sleep(500);}catch(InterruptedException e) {};
               welcomeMenu();
          }


     }


//  ====================================================================================================================

//  [ Specify username , exist or not exist ]

     public int checkName(String nameUser, User[] users) {


          if (nameUser.equals("Admin")) {

               return 1;

          }

          for (int i = 0; i < 30; i++) {

               if (users[i] != null && users[i].getName() != null) {

                    if (nameUser.equals(users[i].getName())) {


                         return  0;


                    }

               }

          }

          return 2;
     }
//  ====================================================================================================================

//  [ Specify user , exist or not exist ]

     public int checkSing(String nameUser, String passUser, User[] users)
     {



          if ( ( nameUser.equals("Admin") && passUser.equals("Admin") )  )
          {

               return -1;
          }

          for (int i = 0; i < 30; i++)
          {

               if (users[i] !=  null && users[i].getName() != null)
               {

                    if (nameUser.equals(users[i].getName()) && passUser.equals(users[i].getPass()))
                    {

                         return i;


                    }

               }

          }

          return -2;

     }

//  ====================================================================================================================

//  [ New Users ]

     public void newUser(User[] users) {


          for (int i = 0; i < 30; i++) {

               users[i] = new User();

          }

     }

//  ====================================================================================================================

     //  [ New flights ]

     public void newFlight(Flight[] flights)
     {

          for (int i = 0; i < 30; i++) {

               flights[i] = new Flight();

          }

     }


}
