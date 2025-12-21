package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "user_account",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_quota_plan",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private Set<QuotaPlanEntity> quotaPlans = new HashSet<>();


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Set<QuotaPlanEntity> getQuotaPlans() { return quotaPlans; }
    public void setQuotaPlans(Set<QuotaPlanEntity> quotaPlans) {
        this.quotaPlans = quotaPlans;
    }
}
