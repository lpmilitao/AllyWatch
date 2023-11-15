import './baseScreen.style.css';

import { Header, SideMenu } from '../';
import { ToastContainer } from 'react-toastify';

export function BaseScreen({ children, at, rightTab }) {
  return (
    <section className={rightTab ? 'base-screen right' : 'base-screen'}>
      <ToastContainer />
      <Header icon={at} />
      <SideMenu selected={at} />
      {children}
    </section>
  );
}
