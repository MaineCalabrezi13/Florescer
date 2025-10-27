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
  <li>H2 Database (ou PostgreSQL/MySQL)</li>
  <li>Insomnia (teste de API)</li>
  <li>GitHub (controle de versão)</li>
</ul>

<hr>

<h2>💡 Funcionalidades Principais</h2>
<ul>
  <li><strong>Cadastro de usuários:</strong> Criação de contas.</li>
  <li><strong>Gerenciamento de hábitos:</strong> Criar, listar, editar e excluir hábitos; marcar hábitos como concluídos; cache configurável para GET.</li>
  <li><strong>Gerenciamento de tarefas:</strong> Criar tarefas vinculadas a hábitos, definir prioridade, data e status.</li>
  <li><strong>Acompanhamento de progresso:</strong> Percentual de conclusão de hábitos; histórico por período.</li>
  <li><strong>Filtros e ordenação:</strong> Listagem de hábitos por categoria, status ou período; paginação e ordenação.</li>
</ul>

<hr>

<h2>📊 Mapeamento das Funcionalidades</h2>
<table>
  <tr><th>Funcionalidade</th><th>Descrição</th><th>Entidades Envolvidas</th></tr>
  <tr><td>Cadastro de Usuário</td><td>Criar um novo usuário</td><td>Usuário</td></tr>
  <tr><td>Criação de Hábito</td><td>Registrar um novo hábito</td><td>Usuário, Hábito</td></tr>
  <tr><td>Registro de Tarefa</td><td>Criar tarefas vinculadas a hábitos</td><td>Hábito, Tarefa</td></tr>
  <tr><td>Listagem e Acompanhamento</td><td>Visualizar todos hábitos e tarefas</td><td>Hábito, Tarefa</td></tr>
  <tr><td>Edição e Exclusão</td><td>Atualizar ou remover hábitos e tarefas</td><td>Todas</td></tr>
</table>

<hr>

<h2>⚙️ Limitações do Projeto</h2>
<p>Nesta versão, a API não possui autenticação JWT nem integração com frontend. Foca apenas em cadastro, acompanhamento e organização de hábitos e tarefas.</p>

<hr>

<h2>🧱 Entidades do Projeto</h2>

<h3>👤 Usuário</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único do usuário</td></tr>
  <tr><td>nome</td><td>string</td><td>Nome completo</td></tr>
  <tr><td>email</td><td>string</td><td>E-mail para login</td></tr>
  <tr><td>senha</td><td>string</td><td>Senha criptografada</td></tr>
  <tr><td>criado_em</td><td>datetime</td><td>Data de criação do cadastro</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
1:N com Hábito<br>
1:N com Tarefa</p>

<h3>🌿 Hábito</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único</td></tr>
  <tr><td>usuario_id</td><td>int</td><td>ID do usuário responsável</td></tr>
  <tr><td>nome</td><td>string</td><td>Nome do hábito</td></tr>
  <tr><td>descricao</td><td>string</td><td>Breve descrição</td></tr>
  <tr><td>frequencia</td><td>string</td><td>Periodicidade (diário, semanal, etc.)</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
N:1 com Usuário<br>
1:N com Tarefa</p>

<h3>✅ Tarefa</h3>
<table>
  <tr><th>Campo</th><th>Tipo</th><th>Descrição</th></tr>
  <tr><td>id</td><td>int</td><td>Identificador único</td></tr>
  <tr><td>habito_id</td><td>int</td><td>ID do hábito vinculado</td></tr>
  <tr><td>titulo</td><td>string</td><td>Título da tarefa</td></tr>
  <tr><td>descricao</td><td>string</td><td>Descrição detalhada</td></tr>
  <tr><td>data</td><td>date</td><td>Data de execução</td></tr>
  <tr><td>status</td><td>boolean</td><td>Define se foi concluída</td></tr>
</table>
<p><strong>Relacionamentos:</strong><br>
N:1 com Hábito</p>

<hr>

<h2>🚀 Rotas da API</h2>

<h3>👤 Usuário</h3>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código</th></tr>
  <tr><td>POST</td><td>/usuarios</td><td>Criar novo usuário</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/usuarios</td><td>Listar usuários</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/usuarios/{id}</td><td>Detalhar usuário</td><td>200 OK / 404</td></tr>
  <tr><td>PUT</td><td>/usuarios/{id}</td><td>Atualizar usuário</td><td>200 OK / 404</td></tr>
  <tr><td>DELETE</td><td>/usuarios/{id}</td><td>Remover usuário</td><td>204 / 404</td></tr>
</table>

<h3>🌿 Hábito</h3>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código</th></tr>
  <tr><td>POST</td><td>/habitos</td><td>Criar hábito vinculado</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/habitos</td><td>Listar hábitos</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/habitos/{id}</td><td>Detalhar hábito</td><td>200 OK / 404</td></tr>
  <tr><td>PUT</td><td>/habitos/{id}</td><td>Atualizar hábito</td><td>200 OK / 404</td></tr>
  <tr><td>DELETE</td><td>/habitos/{id}</td><td>Remover hábito</td><td>204 / 404</td></tr>
</table>

<h3>✅ Tarefa</h3>
<table>
  <tr><th>Verbo</th><th>Rota</th><th>Descrição</th><th>Código</th></tr>
  <tr><td>POST</td><td>/tarefas</td><td>Criar tarefa vinculada a hábito</td><td>201 Created</td></tr>
  <tr><td>GET</td><td>/tarefas</td><td>Listar tarefas</td><td>200 OK</td></tr>
  <tr><td>GET</td><td>/tarefas/{id}</td><td>Detalhar tarefa</td><td>200 OK / 404</td></tr>
  <tr><td>PUT</td><td>/tarefas/{id}</td><td>Atualizar tarefa</td><td>200 OK / 404</td></tr>
  <tr><td>DELETE</td><td>/tarefas/{id}</td><td>Remover tarefa</td><td>204 / 404</td></tr>
</table>

<hr>

<h2>⚠️ Exemplos de Erros HTTP</h2>
<!--<table>
  <tr><th>Código</th><th>Mensagem</th><th>Descrição</th></tr>
  <tr><td>400</td><td>Bad Request</td><td>Dados inválidos ou incompletos</td></tr>
  <tr><td>401</td><td>Unauthorized</td><td>Usuário não autenticado ou token inválido</td></tr>
  <tr><td>404</td><td>Not Found</td><td>Recurso não encontrado</td></tr>
  <tr><td>409</td><td>Conflict</td><td>Conflito ao criar ou atualizar registro existente</td></tr>
  <tr><td>500</td><td>Internal Server Error</td><td>Erro interno do servidor</td></tr>
</table>

<hr>

<h2>🧰 Como Executar o Projeto Localmente</h2>

<h2>🧠 Outros Conteúdos Relevantes</h2>

