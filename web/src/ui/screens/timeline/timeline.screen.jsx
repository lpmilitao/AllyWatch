import './timeline.style.css';

import { Header, SideMenu } from '../../components';

export function Timeline() {
  return (
    <section className='timeline-container'>
      <Header icon={'home'} />
      <SideMenu selected={'home'} />
    </section>
  );
}
