/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylibrary;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author salmh
 */
@Entity
@Table(name="MyQuotes")
public class MyQuotes implements java.io.Serializable{
     @Id
     @Column(name="BookName")
     private String bookname;
     @Column(name="Quotes")
     private String quotes;

    public MyQuotes() {
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

   
     
}
