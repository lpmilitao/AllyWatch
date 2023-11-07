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
        <h3 className={selected === 'home' ? 'selected' : null}>Timeline</h3>
      </div>
      <div onClick={() => navigate('/me')}>
        <img
          src={
            selected === 'profile'
              ? iconsMenu.profile.colored
              : iconsMenu.profile.white
          }
        />
        <h3 className={selected === 'profile' ? 'selected' : null}>
          Meu perfil
        </h3>
      </div>
      <div onClick={() => navigate('/specialists')}>
        <img
          src={
            selected === 'specialists'
              ? iconsMenu.specialists.pink
              : iconsMenu.specialists.white
          }
        />
        <h3 className={selected === 'specialists' ? 'selected' : null}>
          Especialistas
        </h3>
      </div>
      <div onClick={() => navigate('/chatbot')}>
        <img
          src={
            selected === 'chatbot'
              ? iconsMenu.chatbot.colored
              : iconsMenu.chatbot.white
          }
        />
        <h3 className={selected === 'chatbot' ? 'selected' : null}>Chatbot</h3>
      </div>
      <div onClick={() => navigate('/report')}>
        <img
          src={
            selected === 'police'
              ? iconsMenu.police.colored
              : iconsMenu.police.white
          }
        />
        <h3 className={selected === 'police' ? 'selected' : null}>Denúncia</h3>
      </div>
      <div onClick={() => navigate('/help-points')}>
        <img
          src={
            selected === 'local'
              ? iconsMenu.local.colored
              : iconsMenu.local.white
          }
        />
        <h3 className={selected === 'local' ? 'selected' : null}>
          Pontos de ajuda
        </h3>
      </div>
      <div onClick={() => navigate('/chats')}>
        <img
          src={selected === 'chat' ? iconsMenu.chat.pink : iconsMenu.chat.white}
        />
        <h3 className={selected === 'chat' ? 'selected' : null}>Chats</h3>
      </div>
    </section>
  );
}
