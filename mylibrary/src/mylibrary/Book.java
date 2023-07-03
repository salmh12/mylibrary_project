/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylibrary;

import java.util.Date;
import javafx.scene.control.DatePicker;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author salmh
 */
@Entity
@Table(name="BOOK")
public class Book implements java.io.Serializable{
    @Id
     @Column(name="BookName")
     private String name;
    
     @Column(name="PageNumber")
     private String pagenumber;
     @Column(name="StartDate")
     private String startdate;
     @Column(name="ExpiryDate")
     private String expirydate;

    public Book() {
    }

    public Book(String name, String pagenumber, String startdate, String expirydate) {
        this.name = name;
        this.pagenumber = pagenumber;
        this.startdate = startdate;
        this.expirydate = expirydate;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

}
