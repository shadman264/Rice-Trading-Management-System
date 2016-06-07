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
public class RevenueDetailsController implements Initializable {

    @FXML
    private VBox ReRevenueV;
    @FXML
    private TextField ReRemarks;
    @FXML
    private VBox ReBankCashV;
    @FXML
    private VBox ReAmountV;
    @FXML
    private TextField ReLp;
    @FXML
    private Button EditButt;
    @FXML
    private Button DeleteButt;

    private AutoCompleteTextField ReBankCash;
    private AutoCompleteTextField ReRevenue;
    private IntegerTextField ReAmount;

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

        ReBankCash = new AutoCompleteTextField();
        ReBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        ReBankCash.setMaxSize(150, 25);
        ReBankCash.setPromptText("BANK/CASH");

        ReRevenue = new AutoCompleteTextField();
        ReRevenue.setEntries(database.getSuggestionList("SELECT * FROM REVENUE;", "REVENUE"));
        ReRevenue.setMaxSize(150, 25);
        ReRevenue.setPromptText("REVENUE");

        ReRevenueV.getChildren().add(ReRevenue);
        ReBankCashV.getChildren().add(ReBankCash);
        ReAmount = new IntegerTextField();
        ReAmount.setPromptText("AMOUNT");
        ReAmountV.getChildren().add(ReAmount);

    }

    @FXML
    private void EditButtClick(ActionEvent event) {
        if ("EDIT".equals(EditButt.getText())) {
            EditButt.setText("SAVE");
            DeleteButt.setText("CANCEL");
            //code to make textfields editable
        } else {
            //code to save edited data
        }
    }

    @FXML
    private void DeleteButtClick(ActionEvent event) {
        if ("DELETE".equals(DeleteButt.getText())) {
            //code to delete entry
        } else {
            //code to bring initial data
        }
    }

}
