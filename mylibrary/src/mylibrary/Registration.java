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
@Table(name="REGISTRATION")
public class Registration implements java.io.Serializable {
    @Id
     @Column(name="UserName")
     private String username;
    @Column(name="Email")
     private String email;
     @Column(name="Password")
     private String password;

    public Registration() {
    }

    public Registration(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
}
