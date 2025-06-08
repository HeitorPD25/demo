
# Flood Alert - API de Alertas de Alagamento

API REST desenvolvida em Java com Spring Boot para gerenciamento de alertas de alagamento, usuários e autenticação JWT.  
Banco de dados Oracle e deploy na Railway.

---

## Tecnologias usadas

- Java 17+
- Spring Boot 3.x
- Spring Security com JWT
- Hibernate / JPA
- Oracle Database
- Swagger (OpenAPI) para documentação
- Railway para deploy em nuvem
- Maven para build

---

## Funcionalidades

- Cadastro e login de usuários com segurança JWT
- CRUD completo de alertas de alagamento
- Paginação e filtros em listagem de alertas
- Restrição de acesso baseada no usuário autenticado
- Documentação de API via Swagger UI

---

## Como rodar localmente

### Pré-requisitos

- Java 17 ou superior instalado
- Oracle Database disponível (local ou remoto)
- Maven instalado

### Passos

1. Clone o projeto:
   ```bash
   git clone <url-do-seu-repo>
   cd flood-alert
   ```

2. Configure o arquivo `application.properties` ou `application.yml` com as variáveis do banco Oracle e JWT:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha

   jwt.secret=seu_segredo_jwt
   jwt.expiration=3600000
   ```

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em: `http://localhost:8080`

5. A documentação Swagger está disponível em:  
   `http://localhost:8080/swagger-ui/index.html`

---

## Como testar os endpoints

- Use o Postman ou outra ferramenta para fazer requisições HTTP.
- Exemplo JSON para criar um alerta (POST `/alerts`):
  ```json
  {
    "waterLevelCm": 45,
    "message": "Alerta de enchente na região central",
    "alertStatus": "Ativo",
    "userId": 1
  }
  ```

- Faça login para obter token JWT no endpoint `/auth/login`.
- Envie o token no cabeçalho `Authorization: Bearer <token>` nas requisições protegidas.

---

## Deploy na Railway

- Link do deploy: `https://demo-production-e90a.up.railway.app`
- Variáveis de ambiente configuradas:
  - `SPRING_DATASOURCE_URL`
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`
  - `JWT_SECRET`
  - `JWT_EXPIRATION`

---

## Observações

- A data do alerta é gerada automaticamente pelo servidor.
- Apenas o usuário dono do alerta pode editar ou deletar.
- Use o Swagger para consultar e testar todos os endpoints.

---

## Contato

Heitor Pereira Duarte  
Email: heitorpdte@gmail.com 
GitHub: https://github.com/HeitorPD25
