import './post.style.css';

import { avatarList } from '../../../assets/arrays/avatars';
import like from '../../../assets/icons/like.svg';
import comment from '../../../assets/icons/comment.svg';
import report from '../../../assets/icons/report.svg';

export function Post({ post, likePost, commentPost, reportPost }) {
  return (
    <section className='post'>
      <header>
        <img src={avatarList[post?.icon]} alt='' />
        <h3>{post?.author}</h3>
      </header>
      <p>{post?.body}</p>
      <div className='icons'>
        <img
          src={like}
          className={!post?.likedByMe ? 'not-liked' : null}
          onClick={() => likePost(post?.id)}
        />
        <img src={comment} onClick={commentPost} />
        <img src={report} className='end' onClick={report} />
      </div>
    </section>
  );
}
