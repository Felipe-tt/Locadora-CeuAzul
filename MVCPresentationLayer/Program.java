package MVCPresentationLayer;

import MVCPresentationLayer.Controllers.UserController;

class Program {
    public static void main(String[] args) {
        UserController U = new UserController();
        U.LoadOptions();
    }
}