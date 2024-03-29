package lv.fitness_app.core.domain;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "client")
public class User {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "user_name", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "subscription_ends", nullable = false)
    Date endOfSubscriptionDate;
    @Column(name = "subscription", nullable = false)
    @Enumerated(EnumType.STRING)
    Subscription subscription;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.subscription = Subscription.TRAIL;
        endOfSubscriptionDate = Date.valueOf(LocalDate.now().plusDays(30));
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSubscription() {
        return subscription.name().toUpperCase();
    }

    public Date getEndOfSubscriptionDate() {
        return endOfSubscriptionDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubscription(String subscription) {
        this.subscription = Subscription.valueOf(subscription.toUpperCase());
    }

    public void setEndOfSubscriptionDate(Date endOfSubscriptionDate) {
        this.endOfSubscriptionDate = endOfSubscriptionDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(endOfSubscriptionDate, user.endOfSubscriptionDate) && subscription == user.subscription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username, password, endOfSubscriptionDate, subscription);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", endOfSubscriptionDate=" + endOfSubscriptionDate +
                ", subscription=" + subscription +
                '}';
    }
}
