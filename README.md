# 🧠 MentalCheck – Monitoramento de Bem-estar em Trabalho Híbrido

O **MentalCheck** é um sistema desenvolvido para acompanhar, de forma simples e contínua, o estado mental dos colaboradores em ambientes de trabalho híbrido. A partir de **check-ins diários rápidos**, o sistema coleta dados sobre o bem-estar dos usuários e gera **insights para gestores e líderes**, ajudando na identificação de possíveis sinais de burnout ou esgotamento e apoiando a criação de ações preventivas e de melhoria de qualidade de vida e produtividade.

---

## 🎯 Objetivo do Projeto

O projeto tem como principal objetivo:

- Facilitar o **acompanhamento da saúde mental** dos colaboradores;
- Fornecer aos gestores **indicadores e insights** sobre o time;
- Apoiar a detecção precoce de **sinais de burnout e esgotamento**;
- Contribuir para uma **melhor comunicação** entre líderes e equipe;
- Estimular a adoção de **hábitos organizacionais mais saudáveis** com base em dados.

---

## 🏫 Contexto Acadêmico

- **Instituição:** FIAP  
- **Curso:** Análise e Desenvolvimento de Sistemas  
- **Disciplina / Projeto:** Global Solution – Java Advanced  

---

## ⚙️ Funcionalidades Principais

O backend do MentalCheck disponibiliza uma API REST que suporta as seguintes funcionalidades:

- **Cadastro e login de usuário**;
- **Registro de check-ins diários de bem-estar**;
- **Listagem de check-ins por período**;
- **Cadastro e consulta de dicas de bem-estar**;
- **Geração de insights** com base nos dados de check-ins.

As entidades principais modeladas no sistema incluem:

- **Usuário**
- **Checkin**
- **Dica**
- **Insight**

---

## 🗄️ Banco de Dados e Infraestrutura

- **Banco de Dados:** Oracle (ambiente FIAP)  
- **Backend:** Java + Spring Boot  
- **Hospedagem do Backend:** Railway (para acesso remoto e testes via Swagger)

O objetivo do deploy no Railway é permitir que o professor e a banca consigam **acessar e testar a API e a documentação Swagger** sem necessidade de configuração local completa.

---

## ▶️ Execução Local (Ambiente de Desenvolvimento)

Para execução local do projeto (opcional, caso o avaliador deseje):

1. **Pré-requisitos:**
   - JDK 17 instalado;
   - Maven configurado (ou uso do Maven embutido no IntelliJ IDEA);
   - Acesso ao banco Oracle da FIAP.

2. **Importação do projeto:**
   - Clonar ou baixar o repositório;
   - Abrir o projeto em uma IDE como IntelliJ IDEA;
   - Garantir que o projeto esteja configurado com **Java 17**.

3. **Configuração de ambiente:**
   - O arquivo `application-dev.properties` (incluído no pacote enviado na entrega) contém as configurações de acesso ao banco e parâmetros de autenticação.

4. **Execução:**
   - Rodar a classe principal `MentalCheckApplication` pela IDE  
---

## 🌐 Deploy em Produção (Railway)

O backend está publicado no Railway, permitindo acesso remoto à API e à documentação:

- **Swagger UI (documentação da API):**  
  👉 `https://mentalcheck-backend-production.up.railway.app/api/swagger-ui.html`

> Observação: o contexto base da aplicação é `/api`.  
> O acesso deve ser feito pelos endpoints documentados no Swagger.

---

## 👥 Equipe

- **Alexis Ronaldo Quirijota Rondo** – RM: 560384 – Turma: 2TDSPS  
- **Lucas Aurélio de Brito Chicote** – RM: 559366 – Turma: 2TDSPA  
- **Lucas Gomes de Araújo Lopes** – RM: 559607 – Turma: 2TDSPA  

---

## 📌 Observações Finais

- O foco deste projeto é o **backend** em Java, com exposição dos endpoints via Swagger.
- A integração completa com o frontend não foi priorizada devido ao prazo de entrega, mas a API já está preparada para consumo por aplicações web ou mobile, caso seja concluido a integração será enviado link para acesso ao video na entrega.
- O deploy no Railway foi realizado para facilitar a **demonstração prática** do funcionamento da API durante a avaliação.
