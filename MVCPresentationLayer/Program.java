package MVCPresentationLayer;

import MVCPresentationLayer.Controllers.UserController;

class Program {
    public static void main(String[] args) {
        UserController U = new UserController();
        int userOption = 1;
        Return Return = new Return();
        UserOptions userOptions = new UserOptions();
        UserController userController = new UserController();
        userController.showIndex(userOption, Return.LoginOptions(), U);
        userOptions.Show();
        
    }
}