/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class StockListController implements Initializable {
    
    //Shadman AutoSuggetion starts here
    
    private AnchorPane SlAnchorPane;
    
    private AutoCompleteTextField SlSupplierName;
    private AutoCompleteTextField SlProductName;
    private AutoCompleteTextField SlStorageName;
    @FXML
    private TableColumn<TotalStockDB, String> TsProductNameCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsStorageCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsQuantityCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsUnitCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsTotalKgCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsToalMonCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsSupplierNameCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsOrderNoCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsProductNameCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsOrderQuantityCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsDeliveryQuantityCol;
    @FXML
    private TableColumn<UpcomingStockDB,String> UsUpcomingStockCol;
    
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdOrderNoCol;
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdProductNameCol;
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdCustomerCol;
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdOrderQuantityCol;
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdDeliveryQuantityCol;
    @FXML
    private TableColumn<PendingDeliveriesDB,String> PdPendingQuantityCol;
    @FXML
    private TableView<TotalStockDB> TsTable;
    @FXML
    private TableView<UpcomingStockDB> UsTable;
    @FXML
    private TableView<PendingDeliveriesDB> PdTable;
    @FXML
    private Button SlSearchButt;
    
    
    
    ObservableList<TotalStockDB> TsData =FXCollections.observableArrayList();
    ObservableList<TotalStockDB> TsTemp =FXCollections.observableArrayList();
    ObservableList<TotalStockDB> TsSearchTemp =FXCollections.observableArrayList();
    
    ObservableList<UpcomingStockDB> UsData =FXCollections.observableArrayList();
    ObservableList<UpcomingStockDB> UsTemp =FXCollections.observableArrayList();
    ObservableList<UpcomingStockDB> UsSearchTemp =FXCollections.observableArrayList();
    
    ObservableList<PendingDeliveriesDB> PdData =FXCollections.observableArrayList();
    ObservableList<PendingDeliveriesDB> PdTemp =FXCollections.observableArrayList();
    ObservableList<PendingDeliveriesDB> PdSearchTemp =FXCollections.observableArrayList();
    @FXML
    private VBox ClCustomerNameV;
    @FXML
    private TextField ErExpensesRevenueName;
    @FXML
    private DatePicker ClDateFrom;
    @FXML
    private DatePicker ClDateTo;
    @FXML
    private Button UsSearchButt;
    
    @FXML
    private Button SoSearchButt;
    
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connecting the database
        Database database=new Database();
        database.connect_database();
        
        DontDeleteDB dontDeleteDB= new DontDeleteDB();
        
        //SUPPLIER NAME
        SlSupplierName=new AutoCompleteTextField();
        SlSupplierName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;","SUPPLIER"));
        SlSupplierName.setMaxSize(149, 25);
        SlSupplierName.setLayoutX(25);
        SlSupplierName.setLayoutY(85);
        SlSupplierName.setPromptText("SUPPLIER NAME");
        
        
        
        //PRODUCT NAME
        SlProductName=new AutoCompleteTextField();
        SlProductName.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;","PRODUCT_NAME"));
        SlProductName.setMaxSize(149, 25);
        SlProductName.setLayoutX(195);
        SlProductName.setLayoutY(85);
        SlProductName.setPromptText("PRODUCT NAME");
       
        
        
        
        //STORAGE NAME
        SlStorageName=new AutoCompleteTextField();
        SlStorageName.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;","STORAGE"));
        SlStorageName.setMaxSize(149, 25);
        SlStorageName.setLayoutX(388);
        SlStorageName.setLayoutY(85);
        SlStorageName.setPromptText("STORAGE NAME");
       
       
        
        
        dontDeleteDB.connectDatabase();
        
        
        //TOTAL STOCK TABLE
        
        TsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        TsStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        TsQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        TsUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        TsTotalKgCol.setCellValueFactory(new PropertyValueFactory<>("totalKg2"));
        TsToalMonCol.setCellValueFactory(new PropertyValueFactory<>("totalMon2"));
        
        TsData.setAll(dontDeleteDB.getTotalStock("SELECT * FROM TOTAL_STOCK;"));
        
        TsTable.setItems(TsData);
        

        //UPCOMING STOCK TABLE
        
        UsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        UsSupplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        UsDeliveryQuantityCol.setCellValueFactory(new PropertyValueFactory<>("deliveryQuantity2"));
        UsOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        UsOrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("orderQuantity2"));
        UsUpcomingStockCol.setCellValueFactory(new PropertyValueFactory<>("upcomingQuantity2"));
        
        UsData.setAll(dontDeleteDB.getUpcomingStock("SELECT * FROM UPCOMING_STOCK;"));
        UsTable.setItems(UsData);
        
        
        
        //PENDING DELIVERIES TABLE
        
        PdProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PdCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        PdDeliveryQuantityCol.setCellValueFactory(new PropertyValueFactory<>("deliveryQuantity2"));
        PdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PdOrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("orderQuantity2"));
        PdPendingQuantityCol.setCellValueFactory(new PropertyValueFactory<>("pendingQuantity2"));
        
        
        PdData.setAll(dontDeleteDB.getPendingDeliveries("SELECT * FROM PENDING_DELIVERIES;"));
        PdTable.setItems(PdData);
        
        database.close_database();
        dontDeleteDB.closeDatabase();
        
    }    

    private void NewButtClick(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent p = FXMLLoader.load(getClass().getResource("NewStock.fxml"));
        stage=new Stage();
        Scene scene = new Scene(p);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        this.initialize(null, null);
    }

    private void StockListSearchButtClick(ActionEvent event) {
        
        //TOTAL STOCK SEARCH
        
        TsSearchTemp.setAll(TsData); 
        if(SlProductName.getText()!=null && !"".equals(SlProductName.getText()))TsGetProductNameSearch(SlProductName.getText());
        if(SlStorageName.getText()!=null && !"".equals(SlStorageName.getText()))TsGetStorageNameSearch(SlStorageName.getText());
        
        
        
        //UPCOMING STOCK SEARCH
        
        UsSearchTemp.setAll(UsData); 
        if(SlSupplierName.getText()!=null && !"".equals(SlSupplierName.getText()))UsGetSupplierNameSearch(SlSupplierName.getText());
        if(SlProductName.getText()!=null && !"".equals(SlProductName.getText()))UsGetProductNameSearch(SlProductName.getText());
        
        
        //PENDING DELIVERIES SEARCH
        
        PdSearchTemp.setAll(PdData); 
        if(SlProductName.getText()!=null && !"".equals(SlProductName.getText()))PdGetProductNameSearch(SlProductName.getText());

        
        
    }

    

    private void TsGetProductNameSearch(String productName) {
        
        
        TsTemp.clear();
        for(TotalStockDB TS:TsSearchTemp){
//            if((TS.getProductName().equals(productName))){
//                TsTemp.add(TS);
//                
//            }
        }
        TsSearchTemp.setAll(TsTemp);
        TsTable.setItems(TsSearchTemp);
    }

    private void TsGetStorageNameSearch(String storageName) {
        
        
        TsTemp.clear();
        for(TotalStockDB TS:TsSearchTemp){
            if((TS.getStorage().equals(storageName))){
                TsTemp.add(TS);
                
            }
        }
        TsSearchTemp.setAll(TsTemp);
        TsTable.setItems(TsSearchTemp);
        
    }

    

    private void UsGetSupplierNameSearch(String text) {
        UsTemp.clear();
        for(UpcomingStockDB US:UsSearchTemp){
//            if((US.getSupplierName().equals(text))){
//                UsTemp.add(US);
//                
//            }
        }
        UsSearchTemp.setAll(UsTemp);
        UsTable.setItems(UsSearchTemp);
    }

    private void UsGetProductNameSearch(String text) {
        UsTemp.clear();
        for(UpcomingStockDB US:UsSearchTemp){
//            if((US.getProductName().equals(text))){
//                UsTemp.add(US);
//                
//            }
        }
        UsSearchTemp.setAll(UsTemp);
        UsTable.setItems(UsSearchTemp);
    }

    private void PdGetProductNameSearch(String text) {
        PdTemp.clear();
        for(PendingDeliveriesDB PD:PdSearchTemp){
//            if((PD.getProductName().equals(text))){
//                PdTemp.add(PD);
//                
//            }
        }
        PdSearchTemp.setAll(PdTemp);
        PdTable.setItems(PdSearchTemp);
    }

    
    
}
