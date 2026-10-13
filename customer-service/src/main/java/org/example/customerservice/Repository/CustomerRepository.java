package org.example.customerservice.Repository;

// CustomerRepository.java
import org.example.customerservice.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si besoin
    boolean existsByEmail(String email);
}