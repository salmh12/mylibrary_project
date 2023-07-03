/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylibrary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author salmh
 */
public class singupController implements Initializable{
   @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private Button signUp;

    @FXML
    private Label label;
    @FXML
    private Label lsignup;
    
    @FXML
    void btnSignUp(MouseEvent event) {
        String nameLen = name.getText();
        String emailLen = email.getText();
        String passLen = pass.getText();
        Window owner = signUp.getScene().getWindow();
    
        if(name.getText().equals("") && email.getText().equals("") && pass.getText().equals("")){
            //audio reference
      //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/AddController.java
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
          label.setText("Please Enter your information");
    }
        else if(name.getText().equals("")){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            label.setText("Please Enter your name !");
        }
        else if(email.getText().equals("")){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            label.setText("Please Enter your e-mail !");
        }
        else if(pass.getText().equals("")){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            label.setText("Please Enter your password !");
        }
       
        else if(!emailLen.contains("@")){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
        label.setText("Error !\nYour email must be contain '@' ");
        }
        else if(!emailLen.contains(".com")){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
        label.setText("Error !\nYour email must be contain '.com' word");
        }
        else if(passLen.length() != 8){
            Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            label.setText("The length of password must be 8 characters !");
        }
        else{
            //button sound
                Media file = new Media (new File("src\\sound\\pm4button.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
        Registration message = new Registration(name.getText(),email.getText(),pass.getText());
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Rejistration successful!","Welcome " +message.getUsername()+" in My Library Web");
        // to go to login page
        try {
                        Parent pane = (Pane) FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene scene = new Scene(pane);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(scene);  
                        window.show();
                    } catch (IOException ex) {
                        
                    }
        //to save user information in table Registration in database
         Registration regster = new Registration();
              regster.setUsername(name.getText());
              regster.setPassword(pass.getText());
              regster.setEmail(email.getText());
             
              
              Session session = HibernateUtil.getSessionFactory().openSession();
             // session.beginTransaction();
              
              Transaction tx = session.beginTransaction();
              //tx = session.beginTransaction();
                      session.save(regster);
               tx.commit();
        session.close(); 
         
        }
       
    }
     public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/LoginController.java
        lsignup.setText("SignUp");
        lsignup.setVisible(true);  
        FadeTransition fade= new FadeTransition(javafx.util.Duration.seconds(6),lsignup );
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setCycleCount(100);
        fade.play();
        
    }    
    
}