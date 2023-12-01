import './chatbot.style.css';

import sender from '../../../../assets/icons/send-white.svg';

import { UseHandleOpenAi } from '../../../../hooks/openAi/useHandleOpenAi';

import { BaseScreen, Message } from '../../../components';

export function Chatbot() {
  const { messages, newMessage, handleSend, onChange } = UseHandleOpenAi();

  function handleKeyPress(e) {
    if (e.key === 'Enter') {
      handleSend();
    }
  }

  return (
    <BaseScreen at={'chatbot'}>
      <section className='chatbot-container'>
        <span>
          Converse com o AllyBot se você tem alguma dúvida e precisa de uma
          resposta imediata!
          <br />
          Mas lembre-se, o AllyBot é uma inteligência artificial, então ele pode
          estar errado.
        </span>
        {messages
          ? messages.map((message, index) => (
              <Message
                key={index}
                message={message?.message}
                isMe={message?.isMe}
              />
            ))
          : null}
        <div className='send-message'>
          <input
            type='text'
            onChange={onChange}
            value={newMessage}
            onKeyPress={handleKeyPress}
          />
          <button onClick={handleSend}>
            <img src={sender} />
          </button>
        </div>
      </section>
    </BaseScreen>
  );
}
