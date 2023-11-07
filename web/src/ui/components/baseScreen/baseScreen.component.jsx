import './baseScreen.style.css';

import { Header, SideMenu } from '../';

export function BaseScreen({ children, at }) {
  return (
    <section className='base-screen'>
      <Header icon={at} />
      <SideMenu selected={at} />
      {children}
    </section>
  );
}
