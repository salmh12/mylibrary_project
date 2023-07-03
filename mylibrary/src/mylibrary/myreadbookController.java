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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author salmh
 */
 
public class myreadbookController implements Initializable  {
   
    @FXML
    private  TextField text; 
    @FXML
    private ListView lvbook;

    @FXML
    private ListView lvpage;

    @FXML
    private ListView lvsdate;

    @FXML
    private ListView lvedate;
    
    @FXML
    private Button delete;
    @FXML
    private Button update;
    
        //ObservableList  book and page and sdate and edate 
        ObservableList<String> obbook = FXCollections.observableArrayList();
        ObservableList<String> obpage = FXCollections.observableArrayList();
        ObservableList<String> obsdate = FXCollections.observableArrayList();
        ObservableList<String> obedate = FXCollections.observableArrayList();
    int index;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
            //add the ObservableList in ListView
            lvbook.setItems(obbook);
            lvpage.setItems(obpage);
            lvsdate.setItems(obsdate);
            lvedate.setItems(obedate);
            
            
            //Add Listeners on lvpage
            lvpage.getSelectionModel().selectedItemProperty().addListener(e-> {
                lvpage.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                
               index = lvpage.getSelectionModel().getSelectedIndex();
                
                lvsdate.getSelectionModel().select(index);
                lvedate.getSelectionModel().select(index);
                lvbook.getSelectionModel().select(index);
                
                text.setText(obpage.get(index));
                
            });
            
//           rtrieve the book
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Book> sList = null;
            String queryStr = "from Book";
            Query query = session.createQuery(queryStr);
            sList = query.list();
            session.close();
            
            for(Book s: sList){
                obbook.add(s.getName());
                obpage.add(s.getPagenumber());
                obsdate.add(s.getStartdate());
                obedate.add(s.getExpirydate());
           }
           
           
    }
                
    // Go to the quotes interface
    @FXML
    void quotes(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
        try{
       Parent pane =  (Pane) FXMLLoader.load(getClass().getResource("quotes.fxml"));
       Scene s = new Scene (pane);
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(s);
       window.show();    }
       catch(IOException e){e.printStackTrace();}
        }
    }
    @FXML
    void home(KeyEvent event) {
        if(event.getCode().equals(KeyCode.BACK_SPACE)){
        try{
       Parent pane =  (Pane) FXMLLoader.load(getClass().getResource("home.fxml"));
       Scene s = new Scene (pane);
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(s);
       window.show();    }
       catch(IOException e){e.printStackTrace();}
        }
    }
   
   
       //delete data from the ObservableList
    @FXML
    void delete(ActionEvent event) {
        //audio reference
      //https://github.com/Raghad-Ghazi/kitchen-in-a-pocket/blob/master/PROJECT/src/project/AddController.java
        Media file = new Media (new File("src\\sound\\successsdelete.mp4").toURI().toString());
                MediaPlayer sound = new MediaPlayer(file);
                 sound.play();
           
        Book b1=new Book();
        obpage.contains(text.getText());
        int m=obpage.indexOf(text.getText());
                       b1.setPagenumber(obpage.get(m));
                       b1.setName(obbook.get(m));
                       b1.setStartdate(obsdate.get(m));
                       b1.setExpirydate(obedate.get(m));
                     
             Session session = HibernateUtil.getSessionFactory().openSession();
                 Transaction tx2 = null;
            tx2 = session.beginTransaction();
            session.delete(b1);
             tx2.commit();
             session.close();
            
            obbook.remove(m);
            obpage.remove(m);
            obsdate.remove(m);
            obedate.remove(m);
            
                }
    @FXML
    void update(ActionEvent event) {
           
       obpage.set(index,text.getText());
       Session session = HibernateUtil.getSessionFactory().openSession();
            List<Book> sList = null;
            String queryStr = "from Book";
            Query query = session.createQuery(queryStr);
            sList = query.list();
            session.close();
            
            for(Book b:sList){
                if(b.getName().equals(obbook.get(index))){
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2.beginTransaction();
            b.setPagenumber(text.getText());
            session2.update(b);
            session2.getTransaction().commit();
            session2.close();
                }
            
            }
        
    }
}