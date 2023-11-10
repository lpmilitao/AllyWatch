import { useState } from 'react';
import { commentOnPost } from '../../external/server';
import useGlobalUser from '../../context/user/user.context';
import { toast } from 'react-toastify';
import useGlobalReload from '../../context/reload/reload.context';

export function useHandleComments(postId, postsComments) {
  const [user] = useGlobalUser();
  const [reload, setReaload] = useGlobalReload();

  const [comments, setComments] = useState(postsComments);
  const [openComments, setOpenComments] = useState(false);
  const [newComment, setNewComment] = useState('');

  function openCommentList() {
    setOpenComments(!openComments);
  }

  async function addCommentToPost(event) {
    event.preventDefault();
    try {
      await commentOnPost(user, postId, newComment);
      setReaload(!reload);
      toast.success('Comentário feito com sucesso!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setNewComment('');
    } catch (error) {
      toast.error('Ocorreu um erro ao fazer um comentário.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function onChange(event) {
    setNewComment(event.target.value);
  }

  return {
    comments,
    openComments,
    newComment,
    openCommentList,
    addCommentToPost,
    onChange,
  };
}
