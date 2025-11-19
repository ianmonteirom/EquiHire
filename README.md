# 🤖 EquiHire — API RESTful de Recrutamento Inclusivo (Global Solution 2025)

## 💡 Contexto e Objetivo
A **EquiHire** é uma API RESTful desenvolvida para a Global Solution 2025, com foco no **Futuro do Trabalho** e **Recrutamento Inclusivo**. A plataforma visa conectar profissionais que buscam **upskilling/reskilling** às vagas futuras (que funcionam como "trilhas de competências") através de um sistema baseado em dados.

A solução é implementada em **Java** com **Spring Boot** e segue rigorosamente a arquitetura em três camadas (**Controller → Service → Repository**), implementando dois CRUDs completos e tratamento de exceções robusto.

### 🌍 Conexão com ODS
O projeto adere aos Objetivos de Desenvolvimento Sustentável (ODS) da ONU:
* **ODS 4 (Educação de Qualidade):** Foco em **upskilling** e **reskilling** para preparar profissionais para as demandas de 2030+.
* **ODS 8 (Trabalho Decente e Crescimento Econômico):** Promoção de um mercado de trabalho justo através de processos de seleção que mitigam vieses.
* **ODS 10 (Redução das Desigualdades):** Prioridade em mecanismos de recrutamento inclusivo e análise algorítmica justa.

---

## ⚙️ Requisitos Técnicos
* **Java:** Versão 17+
* **Spring Boot:** 3.3.4 (Versão utilizada)
* **Persistência:** Spring Data JPA
* **Validação:** Jakarta Bean Validation (`@Valid`, `@NotBlank`, etc.)
* **Arquitetura:** MVC (Front-end Thymeleaf opcional para visualização) e **API RESTful** (Core da avaliação).
* **Banco de Dados:** H2 (em memória, com descarte de dados a cada reinício).

---

## ▶️ Como Rodar a Aplicação

1.  **Pré-requisito:** Certifique-se de ter o Maven e o Java 17+ instalados.
2.  **Clone o projeto** e navegue até a pasta raiz.
3.  **Execute a aplicação:**

```bash
mvn clean install
mvn spring-boot:run
```

---

## 💾 Acesso e Persistência (H2)

* **Estratégia de Seeds:** O carregamento inicial dos dados (seeds) é feito através do script `import.sql` (executado pelo Hibernate em modo create-drop).
* **URL de Acesso:** `http://localhost:8080`
* **H2 Console:** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:equihire` (URL usada no application.properties)
    * **User:** `ian` (Credencial definida no properties)
    * **Password:** `123255` (Senha definida no properties)

***

---



## 💻 Endpoints da API RESTful (CRUD)



A API expõe dois recursos principais: `/candidatos` e `/vagas`.



### 1. CRUD: Candidato (Recurso: `/candidatos`)



| Método | Rota | Descrição | Status de Sucesso |

| :--- | :--- | :--- | :--- |

| **GET** | `/candidatos` | Lista todos. | 200 OK |

| **GET** | `/candidatos/{id}` | Busca por ID. | 200 OK |

| **POST** | `/candidatos` | Cria novo registro. | 201 Created |

| **PUT** | `/candidatos/{id}` | Atualiza registro existente. | 200 OK |

| **DELETE** | `/candidatos/{id}` | Remove registro. | 204 No Content |



**Exemplo de Requisição (POST /candidatos):**

```json

{

  "nome": "Carla Silva",

  "email": "carla.s@futuro.com",

  "areaAtuacao": "Recursos Humanos",

  "nivelCarreira": "PLENO",

  "resumoExperiencia": "Foco em People Analytics e IA."

}
```

## 📺 Vídeo de Apresentação do projeto rodando
https://youtu.be/tsHbI-BPvWo
