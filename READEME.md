# Task Tracker CLI - Manual do Projeto

Um sistema de gerenciamento de tarefas via linha de comando (CLI) construído totalmente em Java puro. O projeto não utiliza frameworks externos, aplicando conceitos de Orientação a Objetos, manipulação de arquivos (I/O) e conversão manual de dados para o formato JSON. 

## ⚙️ Como o Sistema Funciona
Ao executar a aplicação, o usuário envia argumentos pelo terminal. A classe principal (`TaskCli`) intercepta esses comandos, executa a lógica de negócio (como gerar um novo ID ou alterar o status de uma tarefa) e aciona o `FileManager` para persistir as alterações de forma definitiva no arquivo `tasks.json` localizado na raiz do projeto.

## 📁 Estrutura do Projeto
```text
/task-tracker
├── src/                 # Código-fonte Java (.java)
│   ├── TaskCli.java
│   ├── Task.java
│   ├── FileManager.java
│   └── JsonUtils.java
├── bin/                 # Arquivos compilados (.class) gerados automaticamente
├── tasks.json           # Banco de dados local (criado na primeira execução)
└── README.md            # Manual do projeto
```

## 🚀 Preparando o Ambiente

**1. Compilando o código:**
Antes de usar o sistema, é necessário compilar os arquivos da pasta `src` para a pasta `bin`. No terminal, na raiz do projeto, execute:
```bash
javac -d bin src/*.java
```

---

## 📖 Manual de Uso e Comandos Disponíveis

Para interagir com o sistema, o padrão de execução é sempre `java -cp bin TaskCli <comando> [argumentos]`. Abaixo estão detalhadas todas as operações que o rastreador suporta:

### 1. Adicionar Tarefa (`add`)
Cria uma nova tarefa no banco de dados. O status inicial é sempre definido como `todo` (a fazer) e a data exata de criação é registrada automaticamente.
*   **Formato:** `java -cp bin TaskCli add "Sua descrição aqui"`
*   **Exemplo:** `java -cp bin TaskCli add "Terminar projeto da faculdade"`

### 2. Listar Tarefas (`list`)
Exibe as tarefas salvas de forma organizada no terminal. Pode ser usado sozinho para ver todo o histórico ou acompanhado de um filtro de status (`todo`, `in-progress` ou `done`).
*   **Ver todas as tarefas:** `java -cp bin TaskCli list`
*   **Ver apenas pendentes:** `java -cp bin TaskCli list todo`
*   **Ver em andamento:** `java -cp bin TaskCli list in-progress`
*   **Ver concluídas:** `java -cp bin TaskCli list done`

### 3. Atualizar Descrição (`update`)
Modifica a descrição de uma tarefa existente buscando pelo seu número de ID. Ao fazer isso, a data de atualização (`updatedAt`) é automaticamente atualizada pelo sistema.
*   **Formato:** `java -cp bin TaskCli update <ID> "Nova descrição"`
*   **Exemplo:** `java -cp bin TaskCli update 1 "Terminar projeto da faculdade e revisar código"`

### 4. Atualizar Status (`mark-in-progress` / `mark-done`)
Avança o ciclo de vida da tarefa selecionada, alterando seu status de progresso.
*   **Mover para "Em Andamento":** `java -cp bin TaskCli mark-in-progress <ID>`
*   **Mover para "Concluída":** `java -cp bin TaskCli mark-done <ID>`
*   **Exemplo:** `java -cp bin TaskCli mark-in-progress 1`

### 5. Excluir Tarefa (`delete`)
Busca a tarefa pelo ID e a remove permanentemente do arquivo `tasks.json`.
*   **Formato:** `java -cp bin TaskCli delete <ID>`
*   **Exemplo:** `java -cp bin TaskCli delete 1`