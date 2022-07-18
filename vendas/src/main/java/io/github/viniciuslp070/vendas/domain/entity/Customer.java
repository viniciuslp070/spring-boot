package io.github.viniciuslp070.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Customer() {
    }

    public Customer(String nome) {
        this.name = nome;
    }

    public Customer(Integer id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf;}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }
}
