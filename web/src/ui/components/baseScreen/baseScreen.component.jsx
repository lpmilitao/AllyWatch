import './baseScreen.style.css';

import { Header, SideMenu } from '../';

export function BaseScreen({ children, at, rightTab, rightTabChildren }) {
  return (
    <section className={rightTab ? 'base-screen right' : 'base-screen'}>
      <Header icon={at} />
      <SideMenu selected={at} />
      {children}
    </section>
  );
}
