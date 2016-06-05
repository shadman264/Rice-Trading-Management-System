/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Anjan
 */
public class ExpenseDetailsController implements Initializable {
    @FXML
    private VBox EeExpenseV;
    @FXML
    private TextField EeRemarks;
    @FXML
    private VBox EeBankCashV;
    @FXML
    private VBox EeAmountV;
    @FXML
    private TextField EeLp;
    @FXML
    private Button EditButt;
    @FXML
    private Button DeleteButt;
    private AutoCompleteTextField EeBankCash;

    private AutoCompleteTextField EeExpense;

    
    
     private IntegerTextField EeAmount;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //connecting the database
        Database database = new Database();
        database.connect_database();
        EeBankCash = new AutoCompleteTextField();
        EeBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBankCash.setMaxSize(150, 25);
        EeBankCash.setPromptText("BANK/CASH");

        
        EeExpense = new AutoCompleteTextField();
        EeExpense.setEntries(database.getSuggestionList("SELECT * FROM EXPENSE;", "EXPENSE"));
        EeExpense.setMaxSize(150, 25);
        EeExpense.setPromptText("EXPENSES");

        
        EeExpenseV.getChildren().add(EeExpense);
        
        EeBankCashV.getChildren().add(EeBankCash);
       
        EeAmount = new IntegerTextField();
        EeAmount.setPromptText("AMOUNT");
        
        EeAmountV.getChildren().add(EeAmount);
        

    }    

    @FXML
    private void EditButtClick(ActionEvent event) {
        if("EDIT".equals(EditButt.getText())){
            EditButt.setText("SAVE");
            DeleteButt.setText("CANCEL");
            //code to make textfields editable
        }
        else{
            //code to save edited data
        }
    }

    @FXML
    private void DeleteButtClick(ActionEvent event) {
        if("DELETE".equals(DeleteButt.getText())){
            //code to delete entry
        }
        else{
            //code to bring initial data
        }
    }
    
}
