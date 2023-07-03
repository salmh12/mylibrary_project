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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.hibernate.*;
/**
 *
 * @author salmh
 */
public class quotesController implements Initializable {
    @FXML
    private TextField text_feld1;
    @FXML
    private TextArea text_aerea;
    @FXML
    private Button add;
    @FXML
    private ListView<String> listbook;
    @FXML
    private ListView<String> listquotes;
    @FXML
    private Label lblmsg;
    
    ObservableList <String> obNames_book =FXCollections.observableArrayList();
     ObservableList <String> obNames_quotes =FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listbook.setItems(obNames_book);
        listquotes.setItems(obNames_quotes);
        
        
//        rtrieve the book and MyQuotes
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<MyQuotes> sList = null;
            String queryStr = "from MyQuotes";
            Query query = session.createQuery(queryStr);
            sList = query.list();
            session.close();
            
            for(MyQuotes s: sList){
                obNames_book.add(s.getBookname());
                obNames_quotes.add(s.getQuotes());
                
           }
    }
   
     
    
    @FXML
    private void add(ActionEvent event) {
         //audio reference
      //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/AddController.java
        
        if (text_feld1.getText().isEmpty()|| text_aerea.getText().isEmpty()  ) {
           Media file = new Media (new File("src\\sound\\success2.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            lblmsg.setText("Enter book name and your quotes ");
        }else{
          
                Media file = new Media (new File("src\\sound\\pm4button.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
            obNames_book.add(text_feld1.getText());
            obNames_quotes.add(text_aerea.getText());
            
              MyQuotes q=new MyQuotes();
              q.setBookname(text_feld1.getText());
              q.setQuotes(text_aerea.getText());
        
         Session session = HibernateUtil.getSessionFactory().openSession();
             // session.beginTransaction();
              
              Transaction tx = session.beginTransaction();
              //tx = session.beginTransaction();
                      session.save(q);
               tx.commit();
        session.close();    
        
       
        
        }
        
      
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
    @FXML
    void exsit(MouseEvent event) {
       
        try{
       Parent pane =  (Pane) FXMLLoader.load(getClass().getResource("login.fxml"));
       Scene s = new Scene (pane);
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(s);
       window.show();    }
       catch(IOException e){e.printStackTrace();}
        
    }
}