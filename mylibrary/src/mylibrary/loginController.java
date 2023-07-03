/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylibrary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author salmh
 */
 
public class loginController implements Initializable {
  String user;
     @FXML
    private Label lblmsg;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField txtUser;

      
    @FXML
    private Button signUp;
    @FXML
    private Label llogin;
    
    ObservableList<String> obNames = FXCollections.observableArrayList();
    ListView lvUsername = new ListView(obNames);
    ObservableList<String> obPass = FXCollections.observableArrayList();
    ListView lvPassword = new ListView(obPass);
   
    @FXML
    void login(ActionEvent event) {
        //audio reference
      //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/AddController.java
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Registration> regList = null;
    String queryStr = "from Registration";
      org.hibernate.Query query = session.createQuery(queryStr);
    regList = query.list();
    session.close();
     user = txtUser.getText();
    String pass = password.getText();
    
    if(user.equals("") && pass.equals("")){
        
        lblmsg.setText("Please Enter your username and password!");
        Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
    }
    else if(user.equals("")){
        Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound1 = new MediaPlayer(file);
                 sound1.play();
        lblmsg.setText("Please Enter your username!");
    }
    else if(pass.equals("")){
        Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound2 = new MediaPlayer(file);
                 sound2.play();
        lblmsg.setText("Please Enter your password!");
    }
    
    else if(pass.length() != 8){
        Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound3 = new MediaPlayer(file);
                 sound3.play();
            lblmsg.setText("The length of password must be 8 characters!");
            
            }
   
    else {

        
        for(Registration regist: regList){
            if (regist.getUsername().equals(txtUser.getText())){
                
                try {
                    //button sound
                Media file = new Media (new File("src\\sound\\pm4button.mp4").toURI().toString());
                MediaPlayer sound4 = new MediaPlayer(file);
                 sound4.play();
              
        Parent pane = (Pane) FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(pane);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }    
   catch(IOException e){e.printStackTrace();}
    }
              
            else{
              
         lblmsg.setText("You didn't have an account !\n Please create an account by SignUp");
           
            }   
            
        }
      
        
    }

    }
    @FXML
   public void signUp(MouseEvent event ) throws IOException{
       
        // to go to sign up page
        
      try {
         Parent pane = (Pane) FXMLLoader.load(getClass().getResource("singup.fxml"));
         Scene scene = new Scene(pane);
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(scene);  
                        window.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        
                    }
                }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //animation label login (Code reference from a previous batch project The kitchen in the pocket master  )
        llogin.setText("LogIn");
        llogin.setVisible(true);  
        FadeTransition fade= new FadeTransition(javafx.util.Duration.seconds(4),llogin );
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(100);
        fade.play();
    
}
     
    
}

    