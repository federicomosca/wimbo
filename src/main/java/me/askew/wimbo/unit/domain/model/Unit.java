package me.askew.wimbo.unit.domain.model;

import jakarta.persistence.*;
import lombok.*;
import me.askew.wimbo.user.domain.model.User;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String friendlyName;
    private String address;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

