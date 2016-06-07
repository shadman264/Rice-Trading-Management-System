/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class ExpensesListController implements Initializable {
    @FXML
    private TextField ErBankCash;
    @FXML
    private TextField ErExpensesRevenueName;
    @FXML
    private DatePicker ErDateFrom;
    @FXML
    private DatePicker ErDateTo;
    @FXML
    private Button ESearchButt;
    @FXML
    private TableView<?> ETable;
    @FXML
    private TableColumn<?, ?> depPartyCol;
    @FXML
    private TableColumn<?, ?> depInstituteCol;
    @FXML
    private TableColumn<?, ?> depBankCashCol;
    @FXML
    private TableColumn<?, ?> depAmountCol;
    @FXML
    private TableColumn<?, ?> depLpCol;
    @FXML
    private Tab RSearchButt;
    @FXML
    private TableView<?> BdTable1;
    @FXML
    private TableColumn<?, ?> depPartyCol1;
    @FXML
    private TableColumn<?, ?> depInstituteCol1;
    @FXML
    private TableColumn<?, ?> depBankCashCol1;
    @FXML
    private TableColumn<?, ?> depAmountCol1;
    @FXML
    private TableColumn<?, ?> depLpCol1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
