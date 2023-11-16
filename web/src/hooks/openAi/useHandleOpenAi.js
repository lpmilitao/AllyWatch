import { useState } from 'react';
import { toast } from 'react-toastify';

import { sendMessage } from '../../external/openAi/sendMessage';

export function UseHandleOpenAi() {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  async function handleSend() {
    try {
      setMessages([...messages]);

      const response = await sendMessage(newMessage);

      setMessages([...messages, { message: response, isMe: false }]);
      setNewMessage('');
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
