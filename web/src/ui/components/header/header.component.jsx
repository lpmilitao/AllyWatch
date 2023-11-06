import './header.style.css';

import menu from '../../../assets/icons/menu.svg';
import logout from '../../../assets/icons/logout.svg';
import { iconsMenu } from '../../../assets/arrays/icons-menu.js';

export function Header({ icon }) {
  return (
    <header className='header-container'>
      <div>
        <img src={menu} />
      </div>
      <img src={iconsMenu[icon].colored} />
      <img src={logout} />
    </header>
  );
}
