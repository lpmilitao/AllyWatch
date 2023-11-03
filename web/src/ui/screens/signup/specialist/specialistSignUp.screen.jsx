import './specialistSignUp.style.css';

import { useEffect, useState } from 'react';

import specialist from '../../../../assets/images/specialist.png';

import { Back, Form } from '../../../components';

const lawyer = {
  title: 'Advogado',
};
const psychologist = {
  title: 'Psicólogo',
};

export function SpecialistSignUp() {
  const [type, setType] = useState('specialist');

  useEffect(() => {}, [type]);

  return (
    <>
      <Back side={'right'} />
      <section className='specialist-signup-container'>
        {type === 'lawyer' ? (
          <Form>
            <h1>{lawyer.title}</h1>
          </Form>
        ) : type === 'psychologist' ? (
          <Form>
            <h1>{psychologist.title}</h1>
          </Form>
        ) : (
          <div className='specialist-presentation'>
            <h1>Seja a voz de apoio de quem precisa</h1>
            <span>
              Cadastre-se como psicólogo ou advogado em nossa plataforma e
              ofereça seu auxílio aos que necessitam de orientação e suporte
              diante do assédio moral e violência sexual escolar.
              <br />
              <p>Juntos, fazemos a diferença.</p>
            </span>
            <img src={specialist} alt='' />
          </div>
        )}
        <div className='pick'>
          <h1>Sou um</h1>
          <button onClick={() => setType('lawyer')}>
            <span>Advogado</span>
          </button>
          <button onClick={() => setType('psychologist')}>
            <span>Psicólogo</span>
          </button>
        </div>
      </section>
    </>
  );
}
