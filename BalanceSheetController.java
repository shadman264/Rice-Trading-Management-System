/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author strings
 */
public class BalanceSheetController implements Initializable {

    @FXML
    private HBox clockDV;
    @FXML
    private HBox clockMV;
    @FXML
    private Button CloseForTheDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        clockDisplayer();
    }

    private void clockDisplayer() {

        final Label clockd = new Label();
        final Label clockm = new Label();
        final Label clockt = new Label();

        final DateFormat format = new SimpleDateFormat("dd");
        final DateFormat formatm = new SimpleDateFormat("MM");

        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            final Calendar cal = Calendar.getInstance();
            clockd.setText(format.format(cal.getTime()) + "/");
            clockd.setFont(Font.font(70));
            clockm.setText(formatm.format(cal.getTime()));
            clockm.setFont(Font.font(70));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        clockDV.getChildren().add(clockd);
        clockMV.getChildren().add(clockm);

    }

    @FXML
    private void CloseForTheDateButtClick(ActionEvent event) throws IOException {
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

        if (FrontController.newSet == true) {
            FrontController.newSet=false;
            java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            String query = "Select USER_ID FROM USERS where USERNAME='stairsdate'";
            int user = dontDeleteDB.getAIntDB(query, "USER_ID");
            if (user == 0) {
                UsersDB ob = new UsersDB();
                ob.setUsername("stairsdate");
                ob.setDate(timeStamp);
                dontDeleteDB.insertUsers(ob);
            } else {
                query = "delete from USERS where USER_ID='" + user + "'";
                dontDeleteDB.delete(query);
                UsersDB ob = new UsersDB();
                ob.setUsername("stairsdate");
                ob.setDate(timeStamp);
                dontDeleteDB.insertUsers(ob);

            }
        }
        dontDeleteDB.closeDatabase();

    }

}
