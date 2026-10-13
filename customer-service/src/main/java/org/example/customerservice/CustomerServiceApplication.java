package org.example.customerservice;

import org.example.customerservice.Entity.Customer;
import org.example.customerservice.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            // Insertion de quelques clients au démarrage
            customerRepository.save(new Customer("Alice Dupont", "alice.dupont@example.com", "0612345678"));
            customerRepository.save(new Customer("Bob Martin", "bob.martin@example.com", "0698765432"));
            customerRepository.save(new Customer("Charlie Durand", "charlie.durand@example.com", "0655443322"));

            // Affichage dans la console pour vérifier
            System.out.println("=== Liste des clients initialisés ===");
            customerRepository.findAll().forEach(customer -> {
                System.out.println("ID: " + customer.getId() +
                        " | Nom: " + customer.getName() +
                        " | Email: " + customer.getEmail());
            });
        };
    }
}