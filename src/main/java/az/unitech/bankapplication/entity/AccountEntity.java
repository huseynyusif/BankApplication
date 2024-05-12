package az.unitech.bankapplication.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts",schema = "bank_application")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "card_status")
    private String cardStatus;

    private String currency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

}

