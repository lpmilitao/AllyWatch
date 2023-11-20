import { useState } from 'react';
import { listChats, listChatsSolicitations } from '../../external/server';
import useGlobalUser from '../../context/user/user.context';
import { toast } from 'react-toastify';

export function UseHandleChats() {
  const [token] = useGlobalUser();
  const [listOpened, setListOpened] = useState('chats');
  const [chats, setChats] = useState([{ id: 0, open: true, ally: '' }]);
  const [solicitatons, setSolicitations] = useState([
    { id: 0, status: '', requestedUser: '', requestingUser: '' },
  ]);

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

  return {
    listOpened,
    setListOpened,
    chats,
    solicitatons,
    handleListChats,
    handleListSolicitations,
  };
}
