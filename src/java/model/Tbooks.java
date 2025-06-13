/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cool_
 */
@Entity
@Table(name = "TBOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbooks.findAll", query = "SELECT t FROM Tbooks t"),
    @NamedQuery(name = "Tbooks.findByIsbn", query = "SELECT t FROM Tbooks t WHERE t.isbn = :isbn"),
    @NamedQuery(name = "Tbooks.findByTitle", query = "SELECT t FROM Tbooks t WHERE t.title = :title"),
    @NamedQuery(name = "Tbooks.findByAuthor", query = "SELECT t FROM Tbooks t WHERE t.author = :author"),
    @NamedQuery(name = "Tbooks.findByPrice", query = "SELECT t FROM Tbooks t WHERE t.price = :price")})
public class Tbooks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ISBN")
    private String isbn;
    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 20)
    @Column(name = "AUTHOR")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;

    /**
     *
     */
    public Tbooks() {
    }

    /**
     *
     * @param isbn
     */
    public Tbooks(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @param isbn
     * @param price
     */
    public Tbooks(String isbn, double price) {
        this.isbn = isbn;
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbooks)) {
            return false;
        }
        Tbooks other = (Tbooks) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Title: " + title + "  ";
    }
    
    /**
     *
     * @return
     */
    public String getDollarPrice() {
        DecimalFormat priceFormat = new DecimalFormat("0.00");
        return "$" + priceFormat.format(this.price);
    }
    
}
