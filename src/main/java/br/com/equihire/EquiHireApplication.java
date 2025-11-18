package br.com.equihire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EquiHireApplication {
  public static void main(String[] args) {
      SpringApplication.run(EquiHireApplication.class, args);

      System.out.println("\n========================================");
      System.out.println("✅ Aplicação iniciada com sucesso!");
      System.out.println("App:          http://localhost:8080/EquiHire");
      System.out.println("H2 Console:   http://localhost:8080/h2-console");
      System.out.println("JDBC URL:     jdbc:h2:mem:equihire");
      System.out.println("Usuário:      ian");
      System.out.println("Senha:        123255");
      System.out.println("========================================\n");
  }
}
