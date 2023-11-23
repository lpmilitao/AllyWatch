import './header.style.css';

import menu from '../../../assets/icons/menu.svg';
import logout from '../../../assets/icons/logout.svg';
import { iconsMenu } from '../../../assets/arrays/icons-menu.js';
import { UseAuth } from '../../../hooks/auth/useAuth.hook';

export function Header({ icon }) {
  const { handleLogout } = UseAuth();
  return (
    <header className='header-container'>
      <div>
        <img src={menu} />
      </div>
      <img src={iconsMenu[icon].colored} />
      <img src={logout} className='pointer' onClick={handleLogout} />
    </header>
  );
}
