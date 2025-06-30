
package com.example.student_library_management_system.model;

import com.example.student_library_management_system.enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {
    //id, cardStatus, exiprydate, createddate, updateddate

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="card_status", nullable = false)
    @Enumerated(value=EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name="expiry_date", nullable = false)
    private String expiryDate;

    @Column(name="created_date", nullable = false)
    @CreationTimestamp // when a new card is created/issued it will automatically add data and time
    private Date createdDate;

    @Column(name="updated_date", nullable = false)
    @UpdateTimestamp // when a card is updated it will automatically add data and time
    private Date updatedDate;

    @JoinColumn // it joins the primary key student id from student table as a foreign key in card table
    @OneToOne // one card is assigned to one student
    private Student student;

    @OneToMany(mappedBy = "card")
    private List<Book> booksOfCard;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getBooksOfCard() {
        return booksOfCard;
    }

    public void setBooksOfCard(List<Book> booksOfCard) {
        this.booksOfCard = booksOfCard;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
