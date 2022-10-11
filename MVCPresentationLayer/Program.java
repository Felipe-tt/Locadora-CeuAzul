package MVCPresentationLayer;
import MVCPresentationLayer.Controllers.UserController;

class Program {
    public static void main(String[] args) {
        UserOptions userOptions;
        UserController U = new UserController();
        int userOption = 1;
        Return Return = new Return();
        UserController userController = new UserController();
        userController.showIndex(userOption, Return.LoginOptions(), U);
        userOptions.Show(); 
        
    }
}