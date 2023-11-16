import './report.style.css';

import { useState } from 'react';

import { states } from '../../../../assets/arrays/brazil-states';
import { links } from '../../../../assets/arrays/policia-civil-links';

import { BaseScreen } from '../../../components';

export function Report() {
  const [stateSelected, setStateSelected] = useState('RS');
  return (
    <BaseScreen at={'police'}>
      <section className='reports-container'>
        <h1>Denúncias online</h1>
        <p>
          Ao invés de denunciar pessoalmente, em uma delegacia ou algum órgão do
          tipo, é possível denunciar de forma remota, online. Para isso, é
          preciso apenas acessar o site da polícia, ou de outro órgão, e
          preencher as informações necessárias. Então, selecione seu estado para
          ser redirecionado para o site da polícia civil correto.
        </p>
        <div>
          <select
            name='state'
            onChange={(event) => setStateSelected(event.target.value)}
          >
            {states.map((state) => (
              <option value={state}>{state}</option>
            ))}
          </select>
          <a href={links[stateSelected]}>
            Faça uma denúncia online na polícia civil do {stateSelected}
          </a>
        </div>
        <h1>Números de telefone que podem ajudar</h1>
        <p>
          Central de Atendimento à Mulher (para vítimas de violência contra a
          mulher): <b>180</b>
        </p>
        <p>
          Ministério dos Direitos Humanos e da Cidadania (denúncias de violações
          de direitos humanos, encaminha o caso para o conselho tutelar mais
          próximo): <b>100</b>
        </p>
        <p>
          Polícia Militar (denúncias urgentes, de potencial risco à vida e de
          emergência): <b>190</b>
        </p>
        <p>
          Polícia Civil (atendimento humanizado, voltado a dar orientações
          gerais a pessoas que de algum modo precisam de ajuda): <b>197</b>
        </p>
        <p>
          Pronto Socorro - SAMU (em caso de emergência médica, como abuso
          sexual): <b>192</b>
        </p>
        <p>
          Guarda Municipal (denúncias, comunicação de ocorrências): <b>153</b>
        </p>
        <h1>Onde pedir ajuda dentro do IFSul?</h1>
        <p>
          <b>NUGEDS</b> (Núcleo de Gênero e Diversidade Sexual) - Órgão
          responsável pelo acompanhamento às questões relacionadas a gênero e
          diversidade sexual, e promove os direitos da mulher e eliminação de
          discriminações dentro do Instituto, auxiliando, também, vítimas de
          violência sexual dentro da escola. <br />
          <b>E-mail: ss-nuged@ifsul.edu.br</b>
        </p>
        <p>
          <b>Setor Pedagógico</b> - Setor com a finalidade de atender às
          demandas apresentadas pela comunidade escolar primando pela boa
          comunicações e realizando intervenções voltadas à melhoria do ensino e
          aprendizagem, podendo oferecer apoio tanto em casos de assédio moral
          quanto em casos de violência sexual dentro da escola.
          <br />
          <b>E-mail: ss-coped@ifsul.edu.br</b>
        </p>
        <p>
          <b>NEABI</b> (Núcleo de Estudos Afro-brasileiros e Indígenas) - Órgão
          responsável pelo acompanhamento de questões relacionadas à esfera
          étnico-racial, podendo auxiliar com casos de assédio moral envolvendo
          preconceitos raciais e étnicos.
          <br />
          <b>E-mail: ss-neabi@ifsul.edu.br</b>
        </p>
        <p>
          <b>Ouvidoria</b> - Serviço que tem por finalidade dar encaminhamentos,
          no âmbito institucional, a denúncias, reclamações, solicitações,
          sugestões e elogios referentes aos serviços da Instituição, podendo
          auxiliar, principalmente, vítimas de assédio moral dentro da escola.
        </p>
        <a href='https://falabr.cgu.gov.br/web/home'>
          Para registrar sua manifestação, acesse o Fala.BR
        </a>

        <h1>Denúncie pessoalmente</h1>
        <p>
          Caso você não queira denunciar de forma online, você pode procurar o
          ponto de ajuda mais próximo de você, através da nossa página de
          <b> Pontos de Ajuda</b>.
        </p>
        <a href='/help-points'>Procure por pontos de ajuda próximos a você</a>
        <h1>Procure um advogado</h1>
        <p>
          O AllyWatch disponibiliza para você uma lista de advogados aprovados
          pela plataforma, para que você possa poupar esforços e se conectar com
          o especialista mais rapidamente. Nessa lista de advogados, você poderá
          escolher algum deles e entrar em contato pelo e-mail que o próprio
          forneceu, para assim, poder prosseguir com um atendimento.
        </p>
        <a href='/specialists'>Encontre um advogado</a>
        <h1>Esclareça dúvidas</h1>
        <p>
          Caso ainda reste alguma dúvida sobre seu caso, se ele é abrangido por
          alguma lei, ou coisas do tipo, você pode perguntar para o AllyBot,
          para que possa tomar as providências após ter certeza totalmente do
          que se trata.
        </p>
        <a href='/chatbot'>Converse com nosso chatbot</a>
      </section>
    </BaseScreen>
  );
}
