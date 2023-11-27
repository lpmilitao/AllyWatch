import { useState } from 'react';
import { toast } from 'react-toastify';

import { states } from '../../assets/arrays/brazil-states';

import { listLawyers, listPsychologists } from '../../external/server';

import useGlobalReload from '../../context/reload/reload.context';
import useGlobalUser from '../../context/user/user.context';
import useGlobalLoading from '../../context/load/loading.context';

export function UseHandleSpecialist() {
  const [token] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();
  const [, setLoading] = useGlobalLoading();
  const [specialistType, setSpecialistType] = useState('lawyers');
  const [page, setPage] = useState(0);
  const [hasNextPage, setHasNextPage] = useState(false);
  const [hasPreviousPage, setHasPreviousPage] = useState(false);
  const [totalPages, setTotalPages] = useState(0);
  const [specialist, setSpecialist] = useState([
    {
      id: 0,
      fullname: '',
      state: '',
      email: '',
      phone: '',
      city: '',
    },
  ]);

  async function getLawyers() {
    setLoading(true);
    try {
      const response = await listLawyers(token, page);

      setSpecialist(response.content);

      setHasNextPage(!response.last);
      setHasPreviousPage(!response.first);
      setTotalPages(response.totalPages);
    } catch (error) {
      toast.error('Erro ao buscar os especialistas.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    } finally {
      setLoading(false);
    }
  }

  async function getPsychologists() {
    setLoading(true);
    try {
      const response = await listPsychologists(token, page);

      setSpecialist(response.content);

      setHasNextPage(!response.last);
      setHasPreviousPage(!response.first);
      setTotalPages(response.totalPages);
    } catch (error) {
      toast.error('Erro ao buscar os especialistas.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    } finally {
      setLoading(false);
    }
  }

  function nextPage() {
    if (!hasNextPage) return;
    if (page + 1 > totalPages) return;

    setPage(page + 1);
    setReload(!reload);
  }

  function previousPage() {
    if (!hasPreviousPage) return;
    if (page - 1 < 0) return;

    setPage(page - 1);
    setReload(!reload);
  }

  return {
    specialistType,
    setSpecialistType,
    specialist,
    page,
    setPage,
    hasNextPage,
    hasPreviousPage,
    getLawyers,
    nextPage,
    previousPage,
    getPsychologists,
  };
}
