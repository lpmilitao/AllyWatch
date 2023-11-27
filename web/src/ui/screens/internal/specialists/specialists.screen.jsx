import './specialist.style.css';

import { useEffect } from 'react';

import arrow from '../../../../assets/icons/short-arrow-white.svg';

import { UseHandleSpecialist } from '../../../../hooks';

import { BaseScreen, Loader } from '../../../components';
import useGlobalLoading from '../../../../context/load/loading.context';

export function Specialist() {
  const [isLoading] = useGlobalLoading();
  const {
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
  } = UseHandleSpecialist();

  useEffect(() => {
    if (specialistType === 'lawyers') {
      getLawyers();
    } else {
      getPsychologists();
    }
  }, [specialistType, page]);

  return (
    <BaseScreen at={'specialists'}>
      {isLoading ? (
        <Loader />
      ) : (
        <>
          <section className='specialists-container'>
            <header className='specialist-header'>
              <button
                onClick={() => {
                  setSpecialistType('lawyers');
                  setPage(0);
                }}
                className={
                  specialistType === 'lawyers'
                    ? 'lawyer specialist-selected'
                    : 'lawyer'
                }
              >
                Advogados
              </button>
              <button
                onClick={() => {
                  setSpecialistType('psychologists');
                  setPage(0);
                }}
                className={
                  specialistType === 'psychologists'
                    ? 'psychologist specialist-selected'
                    : 'psychologist'
                }
              >
                Psicólogos
              </button>
            </header>
            {specialist?.map((specialist) => (
              <details className='info-container'>
                <summary>{specialist.fullname}</summary>
                <p>Email para contato: {specialist.email}</p>
                <p>Telefone para contato: {specialist.phone}</p>
                <p>
                  Atende em {specialist.city} - {specialist.state}
                </p>
              </details>
            ))}

            <div className='specialist-pagination'>
              <img
                src={arrow}
                onClick={hasPreviousPage ? previousPage : null}
              />
              <h3>{page + 1}</h3>
              <img
                src={arrow}
                onClick={hasNextPage ? nextPage : null}
                className='mirror'
              />
            </div>
          </section>
        </>
      )}
    </BaseScreen>
  );
}
