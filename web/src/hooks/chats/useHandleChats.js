import { useState } from 'react';
import { toast } from 'react-toastify';

import {
  answerChatSolicitation,
  detailChat,
  listChats,
  listChatsSolicitations,
  sendMessage,
} from '../../external/server';

import useGlobalUser from '../../context/user/user.context';
import useGlobalReload from '../../context/reload/reload.context';

export function UseHandleChats() {
  const [token] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();

  const [listOpened, setListOpened] = useState('chats');
  const [chats, setChats] = useState([{ id: 0, open: true, ally: '' }]);
  const [solicitatons, setSolicitations] = useState([
    { id: 0, status: '', requestedUser: '', requestingUser: '' },
  ]);
  const [chat, setChat] = useState({
    id: 0,
    open: true,
    ally: '',
    messages: [{ id: 0, message: '', sentAt: '', sentByMe: false }],
  });
  const [isChatSelected, setIsChatSelected] = useState(false);
  const [chatSelectedId, setChatSelectedId] = useState(0);
  const [newMessage, setNewMessage] = useState('');

  async function handleListChats() {
    try {
      const response = await listChats(token);
      setChats(response);
    } catch (error) {
      toast.error('Erro ao buscar chats', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  async function handleListSolicitations() {
    try {
      const response = await listChatsSolicitations(token);
      setSolicitations(response);
    } catch (error) {
      toast.error('Erro ao buscar solicitações', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  async function acceptSolicitation(solicitationId) {
    try {
      await answerChatSolicitation(token, true, solicitationId);
      toast.success('Solicitação aceita!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setReload(!reload);
    } catch (error) {
      toast.error('Erro ao aceitar solicitação', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  async function denySolicitation(solicitationId) {
    try {
      await answerChatSolicitation(token, false, solicitationId);
      toast.success('Solicitação negada!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setReload(!reload);
    } catch (error) {
      toast.error('Erro ao recusar solicitação', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  async function handleChatSelection() {
    try {
      const response = await detailChat(token, chatSelectedId);
      setChat(response);
    } catch (error) {
      toast.error('Erro ao buscar as informações do chat', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  function onChange(event) {
    setNewMessage(event.target.value);
  }

  async function handleNewMessage() {
    if (newMessage.trim() === '') return;

    try {
      await sendMessage(token, chatSelectedId, newMessage);
      setReload(!reload);
      setNewMessage('');
    } catch (error) {
      toast.error('Erro ao enviar mensagem', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  return {
    listOpened,
    setListOpened,
    chats,
    solicitatons,
    handleListChats,
    handleListSolicitations,
    acceptSolicitation,
    denySolicitation,
    chat,
    isChatSelected,
    setIsChatSelected,
    handleChatSelection,
    chatSelectedId,
    setChatSelectedId,
    newMessage,
    onChange,
    handleNewMessage,
  };
}
