import './timeline.style.css';

import { BaseScreen, Post, RightTab } from '../../../components';

const POST = {
  id: 1,
  title: 'Post title',
  body: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quaerat sit voluptas assumenda, aliquid dolores ex optio enim officia animi veniam doloremque autem aut nobis accusantium sint distinctio eligendi culpa accusamus. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quaerat sit voluptas assumenda, aliquid dolores ex optio enim officia animi veniam doloremque autem aut nobis accusantium sint distinctio eligendi culpa accusamus.',
  publicatedAt: '2021-01-01 00:00:00',
  coments: [],
  numberOfLikes: 7,
  mine: false,
  author: 'Luiza',
  icon: 'FEMALE',
  anonymous: false,
  likedByMe: false,
};

const POSTS = [POST, POST, POST, POST, POST, POST, POST, POST, POST, POST];

export function Timeline() {
  return (
    <BaseScreen at='home' rightTab={true}>
      <section className='timeline-container'>
        {POSTS.map((post) => (
          <Post key={post?.id} post={post} />
        ))}
      </section>
      <RightTab></RightTab>
    </BaseScreen>
  );
}
