package sec.project.domain;

import javax.persistence.*;

@Entity
@Table(name = "UserRole")
public class UserRole {
    public UserRole() {
    }

    public UserRole(long id, Long userId, String role) {
        this.id = id;
        this.userId = userId;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Role")
    private String role;

    public Long getUserId() {
        return this.userId;
    }

    public String getRole() {
        return this.role;
    }
}
