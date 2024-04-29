package az.unitech.bankapplication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts",schema = "bank_application")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

