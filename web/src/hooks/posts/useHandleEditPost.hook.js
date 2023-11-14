import { useState } from 'react';
import { toast } from 'react-toastify';

import useGlobalUser from '../../context/user/user.context';
import useGlobalReload from '../../context/reload/reload.context';

import { getPostDetails } from '../../external/server/post/getPostDetails';
import { editPost } from '../../external/server';

export function UseHandleEditPost(postId) {
  const [token] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();
  const [post, setPost] = useState({
    body: '',
    aggressor: '',
    anonymous: false,
  });

  async function getPost() {
    try {
      const response = await getPostDetails(token, postId);
      setPost(response);
    } catch (error) {
      toast.error('Ocorreu um erro ao buscar os detalhes do post.', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  function onChange(event) {
    const { name, value } = event.target;
    setPost({ ...post, [name]: value });
  }

  function onChangeCheckbox(event) {
    const { checked } = event.target;

    if (checked) {
      setPost({
        ...post,
        anonymous: true,
      });
    } else {
      setPost({
        ...post,
        anonymous: false,
      });
    }
  }

  async function handleEditPost() {
    console.log(post);
    try {
      await editPost(token, postId, post);
      toast.success('Post editado com sucesso!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setReload(!reload);
    } catch (error) {
      toast.error('Ocorreu um erro ao editar o post.', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
    }
  }

  return {
    post,
    getPost,
    onChange,
    onChangeCheckbox,
    handleEditPost,
  };
}
