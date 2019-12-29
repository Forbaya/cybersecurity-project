package sec.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "UserRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Role")
    private String role;
}
