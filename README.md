# 🌱 Florescer – Sistema de Hábitos e Organização Pessoal
<p>O Florescer é uma API RESTful voltada para auxiliar os usuários na organização de hábitos e tarefas pessoais. O sistema permite o cadastro, acompanhamento e análise de hábitos, possibilitando que o usuário visualize sua evolução e mantenha a motivação para alcançar seus objetivos diários. O projeto tem como foco produtividade e autogestão, oferecendo funcionalidades como criação de hábitos, registro de tarefas, acompanhamento de progresso, filtros, ordenação e cache para otimização de desempenho..</p>

👥 Alunas: Agnes Pinheiro Pereira e Maine Calabrezi de Souza
_____________________________________________________________________________________________________________________________________________________________            
<h2>📝 Descrição do Problema</h2>
<p>Muitas pessoas encontram dificuldade em manter hábitos e organizar tarefas diárias, perdendo controle sobre frequência e progresso. O Florescer oferece uma solução prática e estruturada para gerenciar hábitos, acompanhar tarefas e medir resultados, promovendo produtividade e disciplina pessoal.</p>

<h2>💻 Tecnologias Utilizadas</h2>

<h2>💡 Principais Funcionalidades da API</h2>
<ul>
  <li><strong>Cadastro e autenticação de usuários:</strong> criar conta e login com autenticação segura. Armazenar dados como nome, e-mail e senha criptografada.</li>
  <li><strong>Gerenciamento de hábitos:</strong> criar, listar, editar e excluir hábitos; associar hábitos a categorias; marcar hábitos como concluídos; cache configurável para GET de hábitos.</li>
  <li><strong>Gerenciamento de tarefas:</strong> criar tarefas diárias vinculadas a hábitos, definir prioridade, data e status.</li>
  <li><strong>Acompanhamento de progresso:</strong> visualizar percentual de conclusão de hábitos; consultar histórico por período.</li>
  <li><strong>Filtros e ordenação:</strong> listar hábitos com filtros por categoria, status ou período; paginação e ordenação dos resultados.</li>
</ul>
<h2>⚙️ Limitações do Projeto</h2>

<h2>🧱 Entidades do Projeto</h2>
<h4>1. Usuário</h4>
<p><strong>Campos:</strong></p>
<p>id - int - Identificador único do usuário</p>
<p>nome - string - Nome completo do usuário</p>
<p>email - string - E-mail usado para login</p>
<p>senha - string - Senha criptografada</p>
<p>criado_em - datetime - Data de criação do cadastro</p>

<p><strong>Relacionamentos:</strong></p>
<p>Um usuário possui vários hábitos (1:N com Hábito)</p>
<p>Um usuário possui várias tarefas (1:N com Tarefa)</p>

<h4>2. Hábito</h4>
p><strong>Campos:</strong></p>
<p>id - int - Identificador único do hábito</p>
<p>titulo - string - Nome ou título do hábito</p>
<p>descricao - text - Descrição detalhada</p>
<p>categoria - string - Categoria do hábito (ex.: saúde, estudo, lazer)</p>
<p>frequencia - string - Frequência esperada (diário, semanal)</p>
<p>progresso - float - Percentual de conclusão</p>
<p>criado_em - datetime - Data de criação</p>
<p>atualizado_em - datetime - Data da última atualização</p>
<p>usuario_id - int - ID do usuário criador</p>

<p><strong>Relacionamentos:</strong></p>
<p>Um hábito pode ter várias tarefas (1:N com Tarefa)</p>
<p>Cada hábito pertence a um usuário (N:1 com Usuário)</p>
<p><strong>Cache:</strong> as requisições GET para listar hábitos utilizam cache configurável.</p>

<h4>3. Tarefa</h4>
<p><strong>Campos:</strong></p>
<p>id - int - Identificador único da tarefa</p>
<p>titulo - string - Título da tarefa</p>
<p>descricao - text - Descrição detalhada</p>
<p>data - date - Data planejada para execução</p>
<p>status - string - pendente / concluída</p>
<p>prioridade - string - baixa / média / alta</p>
<p>habito_id - int - ID do hábito associado</p>
<p>usuario_id - int - ID do usuário dono da tarefa</p>

<p><strong>Relacionamentos:</strong></p>
<p>Cada tarefa pertence a um hábito (N:1)</p>
<p>Cada tarefa pertence a um usuário (N:1)</p>


<h2>🚀 Rotas da API</h2>

<h2>⚠️ Exemplos de Erros HTTP</h2>

<h2>🧰 Como Executar o Projeto Localmente</h2>

<h2>🧠 Outros Conteúdos Relevantes</h2>
