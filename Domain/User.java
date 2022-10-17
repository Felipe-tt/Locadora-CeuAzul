package Domain;

import DataInfraestructure.Get;

public class User {
    public int ID;
    public String Name = "";
    public String CPF = "";
    public String Email = "";
    public String Password = "";
    public String Type = "";
    public Double Balance = 0.0;

    public void setVariables(int ID) {
        Name = Get.Row("Name", ID);
        CPF = Get.Row("CPF", ID);
        Email = Get.Row("Email", ID);
        Password = Get.Row("Password", ID);    
        Type = Get.Row("Type", ID);
        Balance = Get.BalanceRow("Balance", ID);
        this.ID = ID;
    }

    public String getsetUser(String getorset, String Entity, String Change) { //change var fica vazia caso seja get
        if (getorset == "Get"){
            if (Entity == "Name") {
                return Name;
            } else if (Entity == "CPF") {
                return CPF;
            } else if (Entity == "Email") {
                return Email;
            } else if (Entity == "Type") {
                return Type;
            }
        }
        else if(getorset == "Set"){
            if (Entity == "Name") {
                Name = Change;
            } else if (Entity == "CPF") {
                CPF = Change;
            } else if (Entity == "Email") {
                Email = Change;
            } else if (Entity == "Type") {
                Type = Change;
            }
        }
        return "";
    }
}