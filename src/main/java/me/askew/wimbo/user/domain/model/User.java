package me.askew.wimbo.user.domain.model;

import jakarta.persistence.*;
import lombok.*;
import me.askew.wimbo.unit.domain.model.Unit;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String status;

    @OneToMany
    private List<Unit> units;
}



