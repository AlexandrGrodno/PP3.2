package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty( message = "Введите name")
    @Size(min = 2, max = 15, message = "Введите правильное имя")
    @Column(name = "username")
    private String username;

    @Column(name = "lastname")
    @NotEmpty( message = "Введите lastName")
    @Size(min = 2, max = 15, message = "Введите правильное имя")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "возраст нее может меньше  0")
    @Max(value = 120,  message = "возраст нее может больше  120 :)), но это не точно..")
    private int age;

    @Column
    @Min(value = 4, message = "пароль нее может меньше  4 символов")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @Column(name = "roles")
    private Set<Role> roles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, lastName, age);
    }

}
