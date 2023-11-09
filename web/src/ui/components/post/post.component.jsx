import './post.style.css';

import { toast } from 'react-toastify';

import { avatarList } from '../../../assets/arrays/avatars';
import like from '../../../assets/icons/like.svg';
import comment from '../../../assets/icons/comment.svg';
import report from '../../../assets/icons/report.svg';

import { likeOrDislikePost } from '../../../external/server';
import useGlobalUser from '../../../context/user/user.context';
import useGlobalReload from '../../../context/reload/reload.context';

export function Post({ post, commentPost, reportPost }) {
  const [user] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();

  async function likePost(id) {
    try {
      await likeOrDislikePost(user, id);
      setReload(!reload);
    } catch (error) {
      toast.error('Ocorreu ao interagir com o post.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

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
