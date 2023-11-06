import './sideMenu.style.css';

import { useNavigate } from 'react-router-dom';

import { iconsMenu } from '../../../assets/arrays/icons-menu';

export function SideMenu({ selected }) {
  const navigate = useNavigate();

  return (
    <section className='side-menu'>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'home' ? iconsMenu.home.colored : iconsMenu.home.white
          }
        />
        <h3>Timeline</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'profile'
              ? iconsMenu.profile.colored
              : iconsMenu.profile.white
          }
        />
        <h3>Meu perfil</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'specialists'
              ? iconsMenu.specialist.colored
              : iconsMenu.specialist.white
          }
        />
        <h3>Especialistas</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'chatbot'
              ? iconsMenu.chatbot.colored
              : iconsMenu.chatbot.white
          }
        />
        <h3>Chatbot</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'police'
              ? iconsMenu.police.colored
              : iconsMenu.police.white
          }
        />
        <h3>Denúncia</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'local'
              ? iconsMenu.local.colored
              : iconsMenu.local.white
          }
        />
        <h3>Pontos de ajuda</h3>
      </div>
      <div onClick={() => navigate('/')}>
        <img
          src={
            selected === 'chat' ? iconsMenu.chat.colored : iconsMenu.chat.white
          }
        />
        <h3>Chats</h3>
      </div>
    </section>
  );
}
