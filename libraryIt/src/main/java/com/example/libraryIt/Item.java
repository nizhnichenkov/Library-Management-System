package com.example.libraryIt;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="items")
public class Item {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column
private String title;

@Column
private String type;

@Column
private String genre;

@Column
private Boolean onLoan;

@ManyToOne
private User user;

@ManyToOne
private User reservedUser;

@Column
@Temporal(TemporalType.DATE)
private Date dateDue;

@UpdateTimestamp
@Temporal(TemporalType.DATE)
@Column(name="date_loaned")
private Date dateLoaned;

@Column
private Boolean reserved;

@Column
@Temporal(TemporalType.DATE)
private Date dateReservedFrom;

@Column
@Temporal(TemporalType.DATE)
private Date dateReservedUntil;

@Column
private int timesLoaned;



public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}

public Boolean getOnLoan() {
    return onLoan;
}

public void setOnLoan(Boolean onLoan) {
    this.onLoan = onLoan;
}

public Date getDateDue() {
    return dateDue;
}

public void setDateDue(Date dateDue) {
    this.dateDue = dateDue;
}

public Date getDateLoaned() {
    return dateLoaned;
}

public void setDateLoaned(Date dateLoaned) {
    this.dateLoaned = dateLoaned;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

public User getReservedUser() {
    return reservedUser;
}


public void setReservedUser(User reservedUser) {
    this.reservedUser = reservedUser;
}

public Date getDateReservedFrom() {
    return dateReservedFrom;
}

public void setDateReservedFrom(Date dateReservedFrom) {
    this.dateReservedFrom = dateReservedFrom;
}

public Date getDateReservedUntil() {
    return dateReservedUntil;
}

public void setDateReservedUntil(Date dateReservedUntil) {
    this.dateReservedUntil = dateReservedUntil;
}

public Boolean getReserved() {
    return reserved;
}

public void setReserved(Boolean reserved) {
    this.reserved = reserved;
}

public String getGenre() {
    return genre;
}

public void setGenre(String genre) {
    this.genre = genre;
}

public int getTimesLoaned() {
    return timesLoaned;
}

public void setTimesLoaned(int timesLoaned) {
    this.timesLoaned = timesLoaned;
}


}