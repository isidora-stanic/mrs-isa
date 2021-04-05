package isa9.Farmacy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Rating {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Pharmacy pharmacy;
    @ManyToOne
    private Medicine medicine;
    @Column
    private int rating;

    public Rating() {
        super();
    }

    public Rating(User user, Pharmacy pharmacy, Medicine medicine, int rating) {
        super();
        this.user = user;
        this.pharmacy = pharmacy;
        this.medicine = medicine;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating [user=" + user + ", pharmacy=" + pharmacy + ", medicine=" + medicine + ", rating=" + rating
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating && Objects.equals(user, rating1.user) && Objects.equals(pharmacy, rating1.pharmacy) && Objects.equals(medicine, rating1.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, pharmacy, medicine, rating);
    }
}
