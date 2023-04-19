import java.lang.reflect.Array;

public class MergeUT {
//    [ To connect tickets with the user ]

    private String userId;
    private Array flightId;
    private int ticketsId;


    public  void singOut(){
        Menu menu = new Menu();
        menu.menu();
    }

    public void accessAdmin(int optionAdmin, TicketsInformation[] tickets){
        Admin admin = new Admin();
        admin.admin(optionAdmin , tickets);
    }

}
