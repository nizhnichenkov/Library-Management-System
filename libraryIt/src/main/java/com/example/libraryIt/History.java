package com.example.libraryIt;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="histories")
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column 
    private Long itemId;

    @Column
    private String itemTitle;

    @Column
    private String itemType;

    @Column
    private String itemGenre;

    @Column
    private Long userId;

    @Column
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date loanedOn;

    @Column
    @Temporal(TemporalType.DATE)
    private Date returnedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoanedOn() {
        return loanedOn;
    }

    public void setLoanedOn(Date loanedOn) {
        this.loanedOn = loanedOn;
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemGenre() {
        return itemGenre;
    }

    public void setItemGenre(String itemGenre) {
        this.itemGenre = itemGenre;
    }
}