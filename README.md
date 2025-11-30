# 🌱 Florescer – Sistema de Hábitos e Organização Pessoal

<p>O <strong>Florescer</strong> é uma API RESTful voltada para auxiliar os usuários na organização de hábitos e tarefas pessoais. O sistema permite o cadastro, acompanhamento e análise de hábitos, possibilitando que o usuário visualize sua evolução e mantenha a motivação para alcançar seus objetivos diários. O projeto foca em produtividade e autogestão, oferecendo funcionalidades como criação de hábitos, registro de tarefas, acompanhamento de progresso, filtros, ordenação e cache para otimização de desempenho.</p>

<p><strong>👥 Integrantes:</strong> Agnes Pinheiro Pereira e Maine Calabrezi de Souza</p>

<hr>

<h2>📝 Descrição do Problema</h2>
<p>Muitas pessoas têm dificuldade em manter hábitos e organizar tarefas diárias, perdendo controle sobre frequência e progresso. O <strong>Florescer</strong> oferece uma solução prática para gerenciar hábitos, acompanhar tarefas e medir resultados, promovendo produtividade e disciplina pessoal.</p>

<hr>

<h2>💻 Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>H2 Database</li>
  <li>Insomnia (teste de API)</li>
  <li>GitHub</li>
</ul>

<hr>

<h2>💡 Funcionalidades Principais</h2>
<ul>
  <li><strong>Cadastro de usuários:</strong> Criação de contas.</li>
  <li><strong>Gerenciamento de hábitos:</strong> Criar, listar (com filtro por nome), editar e excluir hábitos.</li>
  <li><strong>Gerenciamento de tarefas:</strong> Criar tarefas vinculadas a hábitos, listar com filtros (hábito, status e período), atualizar, concluir e desconcluir tarefas.</li>
  <li><strong>Acompanhamento de progresso:</strong> Percentual de conclusão de hábitos através da rota <code>/habitos/{id}/progresso</code>.</li>
  <li><strong>Filtros e ordenação:</strong> Paginação e ordenação disponíveis nas listagens de hábitos e tarefas.</li>
</ul>

<hr>

<h2>📊 Mapeamento das Funcionalidades</h2>
<table>
  <tr><th>Funcionalidade</th><th>Descrição</th><th>Entidades Envolvidas</th></tr>
  <tr>
    <td>Cadastro de Usuário</td>
    <td>Criar, atualizar e remover usuários do sistema</td>
    <td>Usuario</td>
  </tr>
  <tr>
    <td>Criação de Hábito</td>
    <td>Registrar novos hábitos vinculados a um usuário</td>
    <td>Usuario, Habito</td>
  </tr>
  <tr>
    <td>Registro de Tarefa</td>
    <td>Criar tarefas vinculadas a hábitos, com data e status de conclusão</td>
    <td>Habito, Tarefa</td>
  </tr>
  <tr>
    <td>Listagem e Filtros</td>
    <td>Visualizar hábitos e tarefas com paginação, ordenação e filtros (por nome, status ou período)</td>
    <td>Habito, Tarefa</td>
  </tr>
  <tr>
    <td>Edição e Exclusão</td>
    <td>Atualizar ou remover hábitos e tarefas existentes</td>
    <td>Habito, Tarefa</td>
  </tr>
  <tr>
    <td>Concluir/Desconcluir Tarefa</td>
    <td>Marcar tarefas como concluídas ou reverter o status</td>
    <td>Tarefa</td>
  </tr>
  <tr>
    <td>Acompanhamento de Progresso</td>
    <td>Calcular percentual de conclusão das tarefas de cada hábito</td>
    <td>Habito, Tarefa</td>
  </tr>
</table>

<hr>


<h2>⚙️ Limitações do Projeto</h2>
<p>Nesta versão, a API não possui autenticação JWT nem integração com frontend. Foca apenas em cadastro, acompanhamento e organização de hábitos e tarefas.</p>

<hr>

<h2>🧱 Entidades do Projeto</h2>

<h3>👤 Usuário</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>Long</td><td>Identificador único do usuário</td></tr>
  <tr><td>nome</td><td>String</td><td>Nome completo do usuário</td></tr>
  <tr><td>email</td><td>String</td><td>E-mail para login</td></tr>
  <tr><td>senha</td><td>String</td><td>Senha do usuário</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
1:N com Hábito</p>

<h3>🌿 Hábito</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>Long</td><td>Identificador único do hábito</td></tr>
  <tr><td>usuarioId</td><td>Long</td><td>ID do usuário responsável pelo hábito</td></tr>
  <tr><td>nome</td><td>String</td><td>Nome do hábito</td></tr>
  <tr><td>descricao</td><td>String</td><td>Breve descrição do hábito</td></tr>
  <tr><td>frequencia</td><td>String</td><td>Periodicidade (diário, semanal, etc.)</td></tr>
  <tr><td>ativo</td><td>boolean</td><td>Indica se o hábito está ativo</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
N:1 com Usuário<br>
1:N com Tarefa</p>

<h3>✅ Tarefa</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>Long</td><td>Identificador único da tarefa</td></tr>
  <tr><td>habitoId</td><td>Long</td><td>ID do hábito vinculado</td></tr>
  <tr><td>titulo</td><td>String</td><td>Título da tarefa</td></tr>
  <tr><td>descricao</td><td>String</td><td>Descrição detalhada da tarefa</td></tr>
  <tr><td>dataHora</td><td>LocalDateTime</td><td>Data e hora de execução da tarefa</td></tr>
  <tr><td>concluida</td><td>boolean</td><td>Define se a tarefa foi concluída</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
N:1 com Hábito</p>

<hr>

<h2>🚀 Rotas da API</h2>

<h3>👤 Usuário</h3>
<table>
  <tr>
    <th>Descrição</th>
    <th>URI</th>
    <th>Método HTTP</th>
    <th>Corpo</th>
    <th>Resposta Esperada</th>
  </tr>
  <tr>
    <td>Criar novo usuário</td>
    <td><code>/usuarios</code></td>
    <td><code>POST</code></td>
    <td>
<pre>{
  "nome": "João",
  "email": "joao@email.com",
  "senha": "123456"
}</pre>
    </td>
    <td><code>201 Created</code></td>
  </tr>
  <tr>
    <td>Listar usuários</td>
    <td><code>/usuarios</code></td>
    <td><code>GET</code></td>
    <td>Vazio</td>
    <td><code>200 OK</code></td>
  </tr>
  <tr>
    <td>Detalhar usuário</td>
    <td><code>/usuarios/{id}</code></td>
    <td><code>GET</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>
  <tr>
    <td>Atualizar usuário</td>
    <td><code>/usuarios/{id}</code></td>
    <td><code>PUT</code></td>
    <td>
<pre>{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "novaSenha123"
}</pre>
    </td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>
  <tr>
    <td>Remover usuário</td>
    <td><code>/usuarios/{id}</code></td>
    <td><code>DELETE</code></td>
    <td>Vazio</td>
    <td><code>204 No Content / 404 Not Found</code></td>
  </tr>
</table>

<h3>🌿 Hábito</h3>

<table>
  <tr>
    <th>Descrição</th>
    <th>URI</th>
    <th>Método HTTP</th>
    <th>Corpo</th>
    <th>Resposta Esperada</th>
  </tr>

  <tr>
    <td>Criar hábito</td>
    <td><code>/habitos</code></td>
    <td><code>POST</code></td>
    <td>
<pre>{
  "nome": "Beber água",
  "descricao": "Beber 2 litros diariamente",
  "frequencia": "Diário",
  "ativo": true
}</pre>
    </td>
    <td><code>200 OK</code></td>
  </tr>

  <tr>
    <td>Listar hábitos (com filtro e paginação)</td>
    <td><code>/habitos</code></td>
    <td><code>GET</code></td>
    <td>
      Parâmetros opcionais:<br>
      <code>?nome=agua</code><br>
      <code>?page=0&amp;size=10&amp;sort=nome,asc</code>
    </td>
    <td><code>200 OK</code></td>
  </tr>

  <tr>
    <td>Detalhar hábito</td>
    <td><code>/habitos/{id}</code></td>
    <td><code>GET</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Atualizar hábito</td>
    <td><code>/habitos/{id}</code></td>
    <td><code>PUT</code></td>
    <td>
<pre>{
  "nome": "Beber 2L de água",
  "descricao": "Beber água suficiente",
  "frequencia": "Diário",
  "ativo": true
}</pre>
    </td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Remover hábito</td>
    <td><code>/habitos/{id}</code></td>
    <td><code>DELETE</code></td>
    <td>Vazio</td>
    <td><code>204 No Content / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Consultar progresso do hábito</td>
    <td><code>/habitos/{id}/progresso</code></td>
    <td><code>GET</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

</table>

<h3>✅ Tarefas</h3>

<table>
  <tr>
    <th>Descrição</th>
    <th>URI</th>
    <th>Método HTTP</th>
    <th>Corpo</th>
    <th>Resposta Esperada</th>
  </tr>

  <tr>
    <td>Criar tarefa vinculada a um hábito</td>
    <td><code>/tarefas?habitoId=1</code></td>
    <td><code>POST</code></td>
    <td>
<pre>{
  "titulo": "Beber água",
  "descricao": "Beber 2 litros durante o dia",
  "dataHora": "2025-11-02T08:00:00",
  "concluida": false
}</pre>
    </td>
    <td><code>200 OK</code></td>
  </tr>

  <tr>
    <td>Listar tarefas (com filtros, paginação e ordenação)</td>
    <td><code>/tarefas</code></td>
    <td><code>GET</code></td>
    <td>
      Parâmetros opcionais:<br>
      <code>?habitoId=1</code><br>
      <code>?concluida=true</code><br>
      <code>?startDate=2025-10-01T00:00:00</code><br>
      <code>?endDate=2025-11-01T23:59:00</code><br>
      <code>?page=0&amp;size=10&amp;sort=dataHora,asc</code>
    </td>
    <td><code>200 OK</code></td>
  </tr>

  <tr>
    <td>Detalhar tarefa</td>
    <td><code>/tarefas/{id}</code></td>
    <td><code>GET</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Atualizar tarefa</td>
    <td><code>/tarefas/{id}</code></td>
    <td><code>PUT</code></td>
    <td>
<pre>{
  "titulo": "Beber 2L de água",
  "descricao": "Beber água suficiente",
  "dataHora": "2025-11-02T08:00:00",
  "concluida": true
}</pre>
    </td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Remover tarefa</td>
    <td><code>/tarefas/{id}</code></td>
    <td><code>DELETE</code></td>
    <td>Vazio</td>
    <td><code>204 No Content / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Marcar tarefa como concluída</td>
    <td><code>/tarefas/{id}/concluir</code></td>
    <td><code>PUT</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

  <tr>
    <td>Marcar tarefa como NÃO concluída</td>
    <td><code>/tarefas/{id}/desconcluir</code></td>
    <td><code>PUT</code></td>
    <td>Vazio</td>
    <td><code>200 OK / 404 Not Found</code></td>
  </tr>

</table>
 

<hr>

<h2>🧰 Como Executar o Projeto Localmente</h2>
<ol>
  <li>Certifique-se de ter Java e Maven instalados.</li>
  <li>Clone o repositório:
    <pre>git clone https://github.com/seu-usuario/seu-projeto.git</pre>
  </li>
  <li>Entre na pasta do projeto:
    <pre>cd seu-projeto</pre>
  </li>
  <li>Configure o banco de dados em <code>application.properties</code> ou <code>application.yml</code>.</li>
  <li>Execute o projeto:
    <pre>mvn clean spring-boot:run</pre>
  </li>
  <li>Acesse a API em <code>http://localhost:8080</code> e teste as rotas com Insomnia ou Postman.</li>
</ol>



<h2>🃏 Carta-Desafio – Implementação do Cache</h2>
<p>O projeto Florescer implementa um sistema de cache para otimizar o desempenho das requisições de listagem (GET) e reduzir o tempo de resposta ao acessar dados que não mudam com frequência. O cache foi aplicado principalmente na entidade Hábito, já que é uma das rotas mais consultadas no sistema, responsável por armazenar informações sobre os hábitos cadastrados pelos usuários.</p>
