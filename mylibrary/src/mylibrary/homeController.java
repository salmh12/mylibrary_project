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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.hibernate.*;


/**
 *
 * 
 */
public class homeController implements Initializable {
    @FXML
    private  TextField text; 
    @FXML
    private TextField text1;
   
    @FXML
    private DatePicker edate;
    @FXML
    private DatePicker sdate;
    @FXML
    private Button add;
    
    
    @Override
    
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    public void add(ActionEvent event) {
       //audio reference
      //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/AddController.java
                Media file = new Media (new File("src\\sound\\pm4button.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
        
         Book b = new Book();
          b.setName(text.getText());
          b.setPagenumber(text1.getText());
          b.setStartdate(sdate.getValue()+"");
          b.setExpirydate(edate.getValue()+"");
          

              
              Session session = HibernateUtil.getSessionFactory().openSession();
             // session.beginTransaction();
              
              Transaction tx = session.beginTransaction();
              //tx = session.beginTransaction();
                      session.save(b);
               tx.commit();
        session.close();    
        
    }
@FXML
    void myreadbook(MouseEvent event) {
        
        try{
       Parent pane =  (Pane) FXMLLoader.load(getClass().getResource("myreadbook.fxml"));
       Scene s = new Scene (pane);
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(s);
       window.show();    }
       catch(IOException e){e.printStackTrace();}
        
    }
    
    
    
    
    
}
