# 🌱 Florescer – Sistema de Hábitos e Organização Pessoal
<p>O Florescer é uma API RESTful voltada para auxiliar os usuários na organização de hábitos e tarefas pessoais. O sistema permite o cadastro, acompanhamento e análise de hábitos, possibilitando que o usuário visualize sua evolução e mantenha a motivação para alcançar seus objetivos diários. O projeto tem como foco produtividade e autogestão, oferecendo funcionalidades como criação de hábitos, registro de tarefas, acompanhamento de progresso, filtros, ordenação e cache para otimização de desempenho..</p>

👥 Alunas: Agnes Pinheiro Pereira e Maine Calabrezi de Souza
_____________________________________________________________________________________________________________________________________________________________            
<h2>📝 Descrição do Problema</h2>
<p>Muitas pessoas encontram dificuldade em manter hábitos e organizar tarefas diárias, perdendo controle sobre frequência e progresso. O Florescer oferece uma solução prática e estruturada para gerenciar hábitos, acompanhar tarefas e medir resultados, promovendo produtividade e disciplina pessoal.</p>

<h2>💻 Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>H2</li>
  <li>Insomnia (testes de API)</li>
  <li>GitHub (controle de versão)</li>
</ul>

<h2>💡 Principais Funcionalidades da API</h2>
<ul>
  <li><strong>Cadastro e autenticação de usuários:</strong> criar conta e login com autenticação segura. Armazenar dados como nome, e-mail e senha</li>
  <li><strong>Gerenciamento de hábitos:</strong> criar, listar, editar e excluir hábitos; associar hábitos a categorias; marcar hábitos como concluídos; cache configurável para GET de hábitos.</li>
  <li><strong>Gerenciamento de tarefas:</strong> criar tarefas diárias vinculadas a hábitos, definir prioridade, data e status.</li>
  <li><strong>Acompanhamento de progresso:</strong> visualizar percentual de conclusão de hábitos; consultar histórico por período.</li>
  <li><strong>Filtros e ordenação:</strong> listar hábitos com filtros por categoria, status ou período; paginação e ordenação dos resultados.</li>
</ul>

<h3>📊 Mapeamento das Funcionalidades</h3>
<table>
  <tr><th>Funcionalidade</th><th>Descrição</th><th>Entidades Envolvidas</th></tr>
  <tr><td>Cadastro de Usuário</td><td>Criação de um novo usuário na aplicação</td><td>Usuário</td></tr>
  <tr><td>Criação de Hábito</td><td>Permite ao usuário registrar um novo hábito</td><td>Usuário, Hábito</td></tr>
  <tr><td>Registro de Tarefa</td><td>Criação de tarefas vinculadas a um hábito</td><td>Hábito, Tarefa</td></tr>
  <tr><td>Listagem e Acompanhamento</td><td>Permite visualizar todos os hábitos e tarefas criadas</td><td>Hábito, Tarefa</td></tr>
  <tr><td>Edição e Exclusão</td><td>Atualiza ou remove hábitos e tarefas</td><td>Todas</td></tr>
</table>

<h2>⚙️ Limitações do Projeto</h2>
<p>O sistema é focado apenas em funcionalidades básicas de cadastro e acompanhamento de hábitos e tarefas. Não possui, nesta versão, autenticação de usuários com token JWT nem integração com frontend</p>
<h2>🧱 Entidades do Projeto</h2>
<h3>👤 Usuário</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único do usuário</td></tr>
  <tr><td>nome</td><td>string</td><td>Nome completo do usuário</td></tr>
  <tr><td>email</td><td>string</td><td>E-mail usado para login</td></tr>
  <tr><td>senha</td><td>string</td><td>Senha criptografada</td></tr>
  <tr><td>criado_em</td><td>datetime</td><td>Data de criação do cadastro</td></tr>
</table>

<p><strong>Relacionamentos:</strong><br>
Um usuário possui vários hábitos (1:N com Hábito)<br>
Um usuário possui várias tarefas (1:N com Tarefa)</p>

<hr>

<h3>🌿 Hábito</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único do hábito</td></tr>
  <tr><td>usuario_id</td><td>int</td><td>ID do usuário responsável</td></tr>
  <tr><td>nome</td><td>string</td><td>Nome do hábito</td></tr>
  <tr><td>descricao</td><td>string</td><td>Breve descrição do hábito</td></tr>
  <tr><td>frequencia</td><td>string</td><td>Periodicidade (diário, semanal, etc.)</td></tr>
</table>

<p><strong>Relacionamentos:</strong><br>
Um hábito pertence a um usuário (N:1 com Usuário)<br>
Um hábito possui várias tarefas (1:N com Tarefa)</p>

<hr>

<h3>✅ Tarefa</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único da tarefa</td></tr>
  <tr><td>habito_id</td><td>int</td><td>ID do hábito vinculado</td></tr>
  <tr><td>titulo</td><td>string</td><td>Título da tarefa</td></tr>
  <tr><td>descricao</td><td>string</td><td>Descrição detalhada</td></tr>
  <tr><td>data</td><td>date</td><td>Data de execução da tarefa</td></tr>
  <tr><td>status</td><td>boolean</td><td>Define se a tarefa foi concluída</td></tr>
</table>

<p><strong>Relacionamentos:</strong><br>
Uma tarefa pertence a um hábito (N:1 com Hábito)</p>


<h2>🚀 Rotas da API</h2>
<h4>👤 Usuário</h4>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código de Retorno</th></tr>
  <tr><td>POST</td><td>/usuarios</td><td>Cadastra um novo usuário</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/usuarios</td><td>Lista todos os usuários</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/usuarios/{id}</td><td>Retorna os dados de um usuário específico</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>PUT</td><td>/usuarios/{id}</td><td>Atualiza os dados de um usuário</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>DELETE</td><td>/usuarios/{id}</td><td>Exclui um usuário</td><td>204 No Content / 404 Not Found</td></tr>
</table>

<h4>🌿 Hábito</h4>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código de Retorno</th></tr>
  <tr><td>POST</td><td>/habitos</td><td>Cria um novo hábito vinculado a um usuário</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/habitos</td><td>Lista todos os hábitos cadastrados</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/habitos/{id}</td><td>Retorna um hábito específico</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>PUT</td><td>/habitos/{id}</td><td>Atualiza informações de um hábito</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>DELETE</td><td>/habitos/{id}</td><td>Exclui um hábito</td><td>204 No Content / 404 Not Found</td></tr>
</table>

<h4>✅ Tarefa</h4>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código de Retorno</th></tr>
  <tr><td>POST</td><td>/tarefas</td><td>Cria uma nova tarefa associada a um hábito</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/tarefas</td><td>Lista todas as tarefas registradas</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/tarefas/{id}</td><td>Retorna uma tarefa específica</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>PUT</td><td>/tarefas/{id}</td><td>Atualiza informações de uma tarefa</td><td>200 OK / 404 Not Found</td></tr>
  <tr><td>DELETE</td><td>/tarefas/{id}</td><td>Exclui uma tarefa</td><td>204 No Content / 404 Not Found</td></tr>
</table>
<h2>⚠️ Exemplos de Erros HTTP</h2>

<h2>🧰 Como Executar o Projeto Localmente</h2>

<h2>🧠 Outros Conteúdos Relevantes</h2>
