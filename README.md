# 🌱 Florescer – Sistema de Hábitos e Organização Pessoal
<p>O Florescer é uma API RESTful voltada para auxiliar os usuários na organização de hábitos e tarefas pessoais. O sistema permite o cadastro, acompanhamento e análise de hábitos, possibilitando que o usuário visualize sua evolução e mantenha a motivação para alcançar seus objetivos diários. O projeto tem como foco produtividade e autogestão, oferecendo funcionalidades como criação de hábitos, registro de tarefas, acompanhamento de progresso, filtros, ordenação e cache para otimização de desempenho..</p>

👥 Alunas: Agnes Pinheiro Pereira e Maine Calabrezi de Souza
_____________________________________________________________________________________________________________________________________________________________            
<h2>📝 Descrição do Problema</h2>
<p>Muitas pessoas encontram dificuldade em manter hábitos e organizar tarefas diárias, perdendo controle sobre frequência e progresso. O Florescer oferece uma solução prática e estruturada para gerenciar hábitos, acompanhar tarefas e medir resultados, promovendo produtividade e disciplina pessoal.</p>

<h2>💻 Tecnologias Utilizadas</h2>

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

<h2>🧱 Entidades do Projeto</h2>
<h4>1. Usuário</h4>
<p>Campos: id, nome, email, senha, criado_em</p>
<p>Relacionamentos: um usuário possui vários hábitos e tarefas (1:N)</p>

<h4>2. Hábito</h4>
<p>Campos: id, titulo, descricao, categoria, frequencia, progresso, criado_em, atualizado_em, usuario_id</p>
<p>Relacionamentos: um hábito pode ter várias tarefas; cada hábito pertence a um usuário.</p>
<p>Cache: as requisições GET para listar hábitos utilizam cache configurável.</p>

<h4>3. Tarefa</h4>
<p>Campos: id, titulo, descricao, data, status, prioridade, habito_id, usuario_id</p>
<p>Relacionamentos: cada tarefa pertence a um hábito e a um usuário.</p>


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
