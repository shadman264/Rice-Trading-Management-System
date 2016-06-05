/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.newSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class SaleDeliveryController implements Initializable {

    @FXML
    private VBox SdCustomerV;
    @FXML
    private VBox SdInstituteV;
    @FXML
    private VBox SdInvoiceNoV;
    @FXML
    private VBox SdBillNoV;
    @FXML
    private TextField SdBillNo;
    @FXML
    private TextField SdTruckNo;
    @FXML
    private TextField SdTruckMobileNo;
    @FXML
    private VBox SdProductV;
    @FXML
    private VBox SdMarkaV;

    @FXML
    private VBox SdQuantityV;
    @FXML
    private TextField SdUnit;
    @FXML
    private MenuButton SdTypeDrop;
    @FXML
    private RadioMenuItem SdTypeKg;
    @FXML
    private RadioMenuItem SdTypeMon;
    @FXML
    private RadioMenuItem SdTypePiece;
    @FXML
    private VBox SdRateKgV;
    @FXML
    private VBox SdRateMonV;
    @FXML
    private VBox SdPriceV;
    @FXML
    private TextField SdLp;
    @FXML
    private TableView<SaleDeliveryDB> SdTable;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdTruckNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdProductCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdQuantityCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateKgCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateMonCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdPriceCol;

    private AutoCompleteTextField SdCustomer;
    private AutoCompleteTextField SdOrderNo;
    private AutoCompleteTextField SdInstitute;
    private AutoCompleteTextField SdMarka;
    private AutoCompleteTextField SdProduct;
    private IntegerTextField SdQuantity;
    private IntegerTextField SdRateKg;
    private IntegerTextField SdRateMon;
    private IntegerTextField SdPrice;

    private IntegerTextField SdTotalPrice;
    private IntegerTextField SdFreightAdvance;
    private IntegerTextField SdCommission;
    private IntegerTextField SdAdvance;
    private IntegerTextField SdPayableTotal;

    ObservableList<SaleDeliveryDB> SdData = FXCollections.observableArrayList();
    ObservableList<SaleDeliveryDB> SdTableData = FXCollections.observableArrayList();

    private int totalAmount = 0;

    SaleDeliveryDB editOb = new SaleDeliveryDB();

    @FXML
    private Button SdEnterDeliveryInfo;
    @FXML
    private Button SdSaveOrderButt;

    SaleDeliveryDB Sdob = new SaleDeliveryDB();
    @FXML
    private Button SdDeleteButt;
    @FXML
    private Button SdEditButt;
    @FXML
    private VBox TotalVbox;

    @FXML
    private HBox SdTotalPriceH;
    @FXML
    private HBox SdFreightAdvanceH;
    @FXML
    private HBox SdCommissionH;
    @FXML
    private HBox SdAdvanceH;
    @FXML
    private HBox SdPayablePriceH;
    private String setbillno = null;
    @FXML
    private Button SdDeleteDeliveryButt;

    Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

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
        ///SALES DELIVERY
        SdCustomer = new AutoCompleteTextField();
        SdCustomer.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        SdCustomer.setMaxSize(150, 25);
        SdCustomer.setPromptText("CUSTOMER");
        SdCustomerV.getChildren().add(SdCustomer);

        SdInstitute = new AutoCompleteTextField();
        SdInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        SdInstitute.setMaxSize(150, 25);
        SdInstitute.setPromptText("INSTITUTE");
        SdInstituteV.getChildren().add(SdInstitute);

        SdOrderNo = new AutoCompleteTextField();
        SdOrderNo.setEntries(database.getSuggestionList("SELECT * FROM SALE_ORDER;", "INVOICE_NO"));
        SdOrderNo.setMaxSize(150, 25);
        SdOrderNo.setPromptText("ORDER NO");
        SdInvoiceNoV.getChildren().add(SdOrderNo);

        SdMarka = new AutoCompleteTextField();
        SdMarka.setEntries(database.getSuggestionList("SELECT * FROM SALE_ORDER;", "MARKA"));
        SdMarka.setMaxSize(150, 25);
        SdMarka.setPromptText("MARKA");
        SdMarkaV.getChildren().add(SdMarka);

        SdProduct = new AutoCompleteTextField();
        SdProduct.setEntries(database.getSuggestionList("SELECT * FROM PURCHASE_ORDER;", "PRODUCT"));
        SdProduct.setEntries(database.getSuggestionList("SELECT * FROM SALE_ORDER;", "PRODUCT"));
        SdProduct.setMaxSize(150, 25);
        SdProduct.setPromptText("PRODUCT");
        SdProductV.getChildren().add(SdProduct);

        SdQuantity = new IntegerTextField();
        SdRateKg = new IntegerTextField();
        SdRateMon = new IntegerTextField();
        SdPrice = new IntegerTextField();
        SdCommission = new IntegerTextField();
        SdTotalPrice = new IntegerTextField();
        SdFreightAdvance = new IntegerTextField();
        SdPayableTotal = new IntegerTextField();
        SdAdvance = new IntegerTextField();

        SdQuantity.setPromptText("QUANTITY");
        SdQuantity.setMaxWidth(150);
        SdRateKg.setPromptText("/KG");
        SdRateKg.setMaxWidth(150);
        SdRateMon.setPromptText("/MON");
        SdRateMon.setMaxWidth(150);
        SdPrice.setPromptText("/PRICE");
        SdPrice.setMaxWidth(150);
        SdTotalPrice.setPromptText("TOTAL PRICE");
        SdTotalPrice.setMaxWidth(150);
        SdFreightAdvance.setPromptText("FREIGHT ADVANCE");
        SdFreightAdvance.setMaxWidth(150);
        SdCommission.setPromptText("COMMISSION");
        SdCommission.setMaxWidth(150);
        SdAdvance.setPromptText("ADVANCE");
        SdAdvance.setMaxWidth(150);
        SdPayableTotal.setPromptText("PAYABLE PRICE");
        SdPayableTotal.setMaxWidth(180);

        SdQuantityV.getChildren().add(SdQuantity);
        SdPriceV.getChildren().add(SdPrice);
        SdRateKgV.getChildren().add(SdRateKg);
        SdRateMonV.getChildren().add(SdRateMon);

        SdTotalPriceH.getChildren().add(SdTotalPrice);
        SdFreightAdvanceH.getChildren().add(SdFreightAdvance);
        SdCommissionH.getChildren().add(SdCommission);
        SdAdvanceH.getChildren().add(SdAdvance);
        SdPayablePriceH.getChildren().add(SdPayableTotal);

        SdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        SdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        SdTruckNoCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));

        dontDeleteDB.closeDatabase();
        database.close_database();

        SdPayableTotal.setEditable(false);

        SdRateKg.setOnAction((ActionEvent evt) -> {
            double rateKg = IntegerTextField.doubleConvert(SdRateKg.getText());
            double rateMon = rateKg * 37.324;
            double totalKg;
            if ("KG".equals(SdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SdQuantity.getText()) * IntegerTextField.doubleConvert(SdUnit.getText());
                SdRateMon.setText(IntegerTextField.numformat(rateMon));
                SdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("MON".equals(SdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SdQuantity.getText()) * IntegerTextField.doubleConvert(SdUnit.getText()) * 37.324;
                SdRateMon.setText(IntegerTextField.numformat(rateMon));
                SdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("PIECE".equals(SdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SdQuantity.getText()) * IntegerTextField.doubleConvert(SdUnit.getText());
                SdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
                SdRateMon.setText(null);
            }

        });

        SdRateMon.setOnAction((ActionEvent evt) -> {
            double rateMon = IntegerTextField.doubleConvert(SdRateMon.getText());
            double rateKg = rateMon / 37.324;
            double totalKg = 0;
            if ("KG".equals(SdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SdQuantity.getText()) * IntegerTextField.doubleConvert(SdUnit.getText());
            } else if ("MON".equals(SdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SdQuantity.getText()) * IntegerTextField.doubleConvert(SdUnit.getText()) * 37.324;
            }
            SdRateKg.setText(IntegerTextField.numformat(rateKg));
            SdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
        });

        SdFreightAdvance.setOnAction((ActionEvent evt) -> {
            setPayableTotal();
        });

        SdCommission.setOnAction((ActionEvent evt) -> {
            setPayableTotal();
        });

        SdAdvance.setOnAction((ActionEvent evt) -> {
            setPayableTotal();
        });

        SdCustomer.setOnAction((ActionEvent evt) -> {
            SdInstitute.setText(getInstitute(SdCustomer.getText()));
            SdInstitute.requestFocus();
            generateBill();
        });

        SdOrderNo.setOnAction((ActionEvent evt) -> {
            setOrder(SdOrderNo.getText());
            generateBill();
        });
        SdEnterDeliveryInfo.disableProperty().bind(
                Bindings.isEmpty(SdBillNo.textProperty())
                .or(Bindings.isEmpty(SdCustomer.textProperty()))
                .or(Bindings.isEmpty(SdInstitute.textProperty()))
                .or(Bindings.isEmpty(SdOrderNo.textProperty()))
                .or(Bindings.isEmpty(SdProduct.textProperty()))
                .or(Bindings.isEmpty(SdQuantity.textProperty()))
                .or(Bindings.isEmpty(SdTruckNo.textProperty()))
                .or(Bindings.isEmpty(SdUnit.textProperty()))
                .or(Bindings.isEmpty(SdPrice.textProperty()))
        );

        if (FrontController.SdBillNo != null) {
            setSaleDelivery(FrontController.SdBillNo);
        }
        else if (CustomerListController.SdBillNo != null) {
            setSaleDelivery(CustomerListController.SdBillNo);
        }
        
        else if (TruckTransController.SendTtBillNo != null) {
            setSaleDeliveryTruck(TruckTransController.SendTtBillNo);
        }

    }

    private void generateBill() {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "select MAX(SALE_DELIVERY_ID) as ID from SALE_DELIVERY;";
        int serial = dontDeleteDB.getAIntDB(query, "ID");
        serial++;
        String sl;
        if(serial<1000){
            sl=String.format("%03d", serial);
        }else sl=String.valueOf(serial);
        
        SdBillNo.setText(sl);

        dontDeleteDB.closeDatabase();
    }

    private void setSaleDelivery(String billno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT * FROM SALE_DELIVERY WHERE BILL_NO='" + billno + "'";
        SdTableData.addAll(dontDeleteDB.getSaleDelivery(sql));
        SdTable.setItems(SdTableData);

        for (SaleDeliveryDB So : SdTableData) {
            SdCustomer.setText(So.getCustomer());
            SdInstitute.setText(So.getInstitute());
            SdOrderNo.setText(So.getInvoiceNo());
            SdBillNo.setText(billno);
            setbillno = billno;
            SdLp.setText(So.getLp());
            SdFreightAdvance.setText(So.getFreight2());
            SdCommission.setText(So.getCommission2());
            SdAdvance.setText(So.getStorage());
            SdTruckMobileNo.setText(So.getTruckMobileNo());
            SdTruckNo.setText(So.getTruckNo());
            dateTimeNow= So.getDate();
            break;
        }

        setTotal();
        setPayableTotal();
        SdDeleteDeliveryButt.setDisable(false);

        SdSaveOrderButt.setText("SAVE");
        dontDeleteDB.closeDatabase();
    }

    
    private void setSaleDeliveryTruck(String billno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT * FROM SALE_DELIVERY WHERE BILL_NO='" + billno + "'";
        SdTableData.addAll(dontDeleteDB.getSaleDelivery(sql));
        SdTable.setItems(SdTableData);

        for (SaleDeliveryDB So : SdTableData) {
            SdCustomer.setText(So.getCustomer());
            SdInstitute.setText(So.getInstitute());
            SdOrderNo.setText(So.getInvoiceNo());
            SdBillNo.setText(billno);
            setbillno = billno;
            SdLp.setText(So.getLp());
            SdFreightAdvance.setText(So.getFreight2());
            SdCommission.setText(So.getCommission2());
            SdAdvance.setText(So.getStorage());
            SdTruckMobileNo.setText(So.getTruckMobileNo());
            SdTruckNo.setText(So.getTruckNo());
            dateTimeNow= So.getDate();
            break;
        }

        setTotal();
        setPayableTotal();

        SdSaveOrderButt.setVisible(false);
        SdDeleteDeliveryButt.setVisible(false);
        SdEditButt.setVisible(false);
        SdEnterDeliveryInfo.setVisible(false);
        SdDeleteButt.setVisible(false);
        dontDeleteDB.closeDatabase();
    }

    private void setPayableTotal() {
        double total = IntegerTextField.doubleConvert(SdTotalPrice.getText())
                + IntegerTextField.doubleConvert(SdFreightAdvance.getText())
                + IntegerTextField.doubleConvert(SdCommission.getText())
                - IntegerTextField.doubleConvert(SdAdvance.getText());

        SdPayableTotal.setText(IntegerTextField.numformat(total));
    }

    private void setTotal() {

        totalAmount = 0;
        for (SaleDeliveryDB Pd : SdTableData) {
            totalAmount += Pd.getPrice();
        }
        SdTotalPrice.setText(IntegerTextField.numformat(totalAmount));
    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");

        dontDeleteDB.closeDatabase();
        return Ins;

    }

    private void setOrder(String order) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT CUSTOMER from SALE_ORDER where INVOICE_NO='" + SdOrderNo.getText() + "'limit 1";
        String sup = dontDeleteDB.getAStringDB(sql, "CUSTOMER");
        SdCustomer.setText(sup);
        SdCustomer.requestFocus();
        sql = "SELECT INSTITUTE from SALE_ORDER where INVOICE_NO='" + SdOrderNo.getText() + "'limit 1";
        String ins = dontDeleteDB.getAStringDB(sql, "INSTITUTE");
        SdInstitute.setText(ins);
        SdInstitute.requestFocus();
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SdEnterDeliveryInfoClick(ActionEvent event) throws IOException {
        if ("SAVE".equals(SdEnterDeliveryInfo.getText())) {
            SdTableData.remove(editOb);
            editOb = null;
            SdEnterDeliveryInfo.setText("ENTRY");
            SdEditButt.setText("EDIT");
        }

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT SALE_ORDER_ID FROM SALE_ORDER WHERE INVOICE_NO= '" + SdOrderNo.getText() + "'";
        int id = dontDeleteDB.getAIntDB(mySql, "SALE_ORDER_ID");
        if (id == 0) {
            FrontController.error = "ORDER NO. not Found!!";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();

            return;

        }
        dontDeleteDB.closeDatabase();

        SaleDeliveryDB ob = new SaleDeliveryDB();
        ob.setBillNo(SdBillNo.getText());
        ob.setCustomer(SdCustomer.getText());
        ob.setDate(dateTimeNow);
        ob.setInstitute(SdInstitute.getText());
        ob.setInvoiceNo(SdOrderNo.getText());
        ob.setLp(SdLp.getText());
        ob.setMarka(SdMarka.getText());
        ob.setPrice(IntegerTextField.doubleConvert(SdPrice.getText()));
        ob.setPrice2(SdPrice.getText());
        ob.setProduct(SdProduct.getText());
        ob.setQuantity(IntegerTextField.doubleConvert(SdQuantity.getText()));
        ob.setQuantity2(SdQuantity.getText());
        ob.setRateKg(IntegerTextField.doubleConvert(SdRateKg.getText()));
        ob.setRateKg2(SdRateKg.getText());
        ob.setRateMon(IntegerTextField.doubleConvert(SdRateMon.getText()));
        ob.setRateMon2(SdRateMon.getText());
        ob.setTruckMobileNo(SdTruckMobileNo.getText());
        ob.setTruckNo(SdTruckNo.getText());
        ob.setUnit(IntegerTextField.doubleConvert(SdUnit.getText()));
        ob.setUnit2(SdUnit.getText() + " " + SdTypeDrop.getText());
        ob.setUnitType(SdTypeDrop.getText());
        ob.setUser("LEFT");

        SdTableData.add(ob);

        SdTable.setItems(SdTableData);
        setTotal();
        setPayableTotal();

        SdPrice.clear();
        SdProduct.clear();
        SdQuantity.clear();
        SdRateKg.clear();
        SdRateMon.clear();
        SdMarka.clear();

        SdUnit.clear();
    }

    @FXML
    private void SdSaveOrderButtClick(ActionEvent event) throws SQLException, IOException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        if ("SAVE".equals(SdSaveOrderButt.getText())) {
            setDeleteAll(setbillno);
        }

        for (SaleDeliveryDB ob : SdTableData) {
            ob.setCustomer(SdCustomer.getText());
            ob.setInstitute(SdInstitute.getText());
            ob.setBillNo(SdBillNo.getText());
            ob.setInvoiceNo(SdOrderNo.getText());
            ob.setFreight(IntegerTextField.doubleConvert(SdFreightAdvance.getText()));
            ob.setCommission(IntegerTextField.doubleConvert(SdCommission.getText()));
            ob.setStorage(SdAdvance.getText());
            insertSaleDelivery(ob);
            //inserting purchasedelivery table

        }
        setCustomer();

        Stage stage = (Stage) SdEditButt.getScene().getWindow();
        stage.close();
        dontDeleteDB.closeDatabase();
    }

    private void insertSaleDelivery(SaleDeliveryDB ob) throws SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //inserting purchase delivery ends here
        dontDeleteDB.insertSaleDelivery(ob);
        //inserting into supplier transaction
        CustomerTransactionDB obs = new CustomerTransactionDB();

        obs.setDate(ob.getDate());
        obs.setType(2);
        obs.setInstitute(SdInstitute.getText());
        obs.setRateKg(ob.getRateKg());
        obs.setLp(ob.getLp());
        obs.setStorage(ob.getStorage());
        obs.setRateMon(ob.getRateMon());
        obs.setDebit(ob.getPrice());
        obs.setProduct(ob.getProduct());
        obs.setQuantity(ob.getQuantity());
        obs.setCustomer(ob.getCustomer());
        obs.setUnit(ob.getUnit());
        obs.setUnitType(ob.getUnitType());
        obs.setBillNo(ob.getBillNo());
        obs.setUser(ob.getUser());

        dontDeleteDB.insertCustomerTransaction(obs);

        dontDeleteDB.closeDatabase();

    }

    private void setCustomer() {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        //starting customer list
        String sqlslID = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + SdCustomer.getText()
                + "' AND INSTITUTE= '" + SdInstitute.getText() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "CUSTOMER_LIST_ID");

        String sqltotalDeilivery = "SELECT TOTAL_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= " + slID + "";

        Double totalDelivery = dontDeleteDB.getADoubleDB(sqltotalDeilivery, "TOTAL_DELIVERY");
        String sqlbalanceDeilivery = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= " + slID + "";

        Double balanceDelivery = dontDeleteDB.getADoubleDB(sqlbalanceDeilivery, "BALANCE_DELIVERY");

        Double totalDeliveryNew = totalDelivery + IntegerTextField.doubleConvert(SdPayableTotal.getText());
        Double balanceDeliveryNew = balanceDelivery + IntegerTextField.doubleConvert(SdPayableTotal.getText());

        String query = "update CUSTOMER_LIST set TOTAL_DELIVERY = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalDeliveryNew),
                SdCustomer.getText(), SdInstitute.getText());
        query = "update CUSTOMER_LIST set BALANCE_DELIVERY = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceDeliveryNew),
                SdCustomer.getText(), SdInstitute.getText());

        //ending supplier list work
    }

    @FXML
    private void SdDeleteButtClick(ActionEvent event) throws IOException {
        SaleDeliveryDB SdOb = SdTable.getSelectionModel().getSelectedItem();
        if (SdOb == null) {
            return;
        }
        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            SdTableData.remove(SdOb);
            newSet = false;
        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        SdTable.setItems(SdTableData);

        dontDeleteDB.closeDatabase();

    }

    private void setDeleteAll(String billno) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        SdData.addAll(dontDeleteDB.getSaleDelivery("SELECT * from SALE_DELIVERY WHERE BILL_NO='"
                + billno + "'"));

        for (SaleDeliveryDB po : SdData) {
            deleteSaleDelivery(po);
        }
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SdEditButtClick(ActionEvent event) throws SQLException {
        if ("CANCEL".equals(SdEditButt.getText())) {
            SdProduct.clear();
            SdMarka.clear();
            SdQuantity.clear();
            SdUnit.clear();
            SdRateKg.clear();
            SdRateKg.clear();
            SdRateMon.clear();
            SdEnterDeliveryInfo.setText("ENTRY");
            SdEditButt.setText("EDIT");

        } else if ("EDIT".equals(SdEditButt.getText())) {
            SaleDeliveryDB SdOb = SdTable.getSelectionModel().getSelectedItem();
            if (SdOb == null) {
                return;
            } else {
                editOb = SdOb;
            }
            SdEditButt.setText("CANCEL");
            SdEnterDeliveryInfo.setText("SAVE");
            SdCustomer.setText(SdOb.getCustomer());
            SdInstitute.setText(SdOb.getInstitute());
            SdOrderNo.setText(SdOb.getInvoiceNo());
            SdLp.setText(SdOb.getLp());
            SdProduct.setText(SdOb.getProduct());
            SdMarka.setText(SdOb.getStorage());
            SdQuantity.setText(SdOb.getQuantity2());
            SdUnit.setText(String.valueOf(SdOb.getUnit()));
            SdTypeDrop.setText(SdOb.getUnitType());
            SdRateKg.setText(SdOb.getRateKg2());
            SdRateMon.setText(SdOb.getRateMon2());
            SdPrice.setText(SdOb.getPrice2());

        }

    }

    private void deleteSaleDelivery(SaleDeliveryDB ob) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM SALE_DELIVERY WHERE SALE_DELIVERY_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        query = "DELETE FROM CUSTOMER_TRANSACTION WHERE BILL_NO = '" + ob.getBillNo() + "'";
        dontDeleteDB.delete(query);

        //starting supplier list
        String sqlslID = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getCustomer()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "CUSTOMER_LIST_ID");

        String sqltotalDeilivery = "SELECT TOTAL_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + slID + "'";

        Double totalDelivery = dontDeleteDB.getADoubleDB(sqltotalDeilivery, "TOTAL_DELIVERY");
        String sqlbalanceDeilivery = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + slID + "'";

        Double balanceDelivery = dontDeleteDB.getADoubleDB(sqlbalanceDeilivery, "BALANCE_DELIVERY");
        Double payable=ob.getPrice()+ob.getFreight()+ob.getCommission()-IntegerTextField.doubleConvert(ob.getStorage());
        Double totalDeliveryNew = totalDelivery - payable;
        Double balanceDeliveryNew = balanceDelivery - payable;

        query = "update CUSTOMER_LIST set TOTAL_DELIVERY = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalDeliveryNew),
                ob.getCustomer(), ob.getInstitute());
        query = "update CUSTOMER_LIST set BALANCE_DELIVERY = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceDeliveryNew),
                ob.getCustomer(), ob.getInstitute());

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SetTextForSaleDeliveryTypeKg(ActionEvent event) {
        SdTypeDrop.setText("KG");
    }

    @FXML
    private void SetTextForSaleDeliveryTypeMon(ActionEvent event) {
        SdTypeDrop.setText("MON");
    }

    @FXML
    private void SetTextForSaleDeliveryTypePiece(ActionEvent event) {
        SdTypeDrop.setText("PIECE");
    }

    @FXML
    private void SdDeleteDeliveryButtClick(ActionEvent event) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            setDeleteAll(setbillno);
            newSet = false;
        }

        dontDeleteDB.closeDatabase();
        stage = (Stage) SdEditButt.getScene().getWindow();
        stage.close();
    }

}
