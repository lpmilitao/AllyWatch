import { useState } from 'react';
import { toast } from 'react-toastify';

import { sendMessage } from '../../external/openAi/sendMessage';

export function UseHandleOpenAi() {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  async function handleSend() {
    try {
      const messagesRequest = messages.map((m) => {
        return {
          role: m.isMe ? 'user' : 'assistant',
          content: m.message,
        };
      });

      setMessages((currentMessages) => [
        ...currentMessages,
        { message: newMessage, isMe: true },
        { message: 'Digitando ...', isMe: false },
      ]);
      messagesRequest.push({ role: 'user', content: newMessage });
      setNewMessage('');

      const response = await sendMessage(messagesRequest);

      setMessages((currentMessages) => [
        ...currentMessages.filter((m) => m.message !== 'Digitando ...'),
        { message: response, isMe: false },
      ]);
    } catch (error) {
      toast.error('Erro ao enviar a mensagem.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function onChange(event) {
    setNewMessage(event.target.value);
  }

  return {
    messages,
    newMessage,
    handleSend,
    onChange,
  };
}
