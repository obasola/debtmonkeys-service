/*
 * Created on 26 Feb 2017 ( Time 16:22:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.domain;

import java.io.Serializable;

import javax.validation.constraints.*;


public class AccountOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    @Size( min = 1, max = 15 )
    private String userName;

    @Size( max = 45 )
    private String password;

    @Size( max = 45 )
    private String emailAddress;

    @Size( max = 45 )
    private String firstName;

    @Size( max = 45 )
    private String lastName;


    private Double netMonthlyIncome;

    @Size( max = 1 )
    private String isadmin;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setUserName( String userName ) {
        this.userName = userName;
    }
    public String getUserName() {
        return this.userName;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setEmailAddress( String emailAddress ) {
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setNetMonthlyIncome( Double netMonthlyIncome ) {
        this.netMonthlyIncome = netMonthlyIncome;
    }
    public Double getNetMonthlyIncome() {
        return this.netMonthlyIncome;
    }

    public void setIsadmin( String isadmin ) {
        this.isadmin = isadmin;
    }
    public String getIsadmin() {
        return this.isadmin;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(userName);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(emailAddress);
        sb.append("|");
        sb.append(firstName);
        sb.append("|");
        sb.append(lastName);
        sb.append("|");
        sb.append(netMonthlyIncome);
        sb.append("|");
        sb.append(isadmin);
        return sb.toString(); 
    } 


}
