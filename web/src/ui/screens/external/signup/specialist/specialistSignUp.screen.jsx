import './specialistSignUp.style.css';

import { useEffect } from 'react';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';

import specialist from '../../../../../assets/images/specialist.png';

import { UseRegisterSpecialist } from '../../../../../hooks';

import { Back, Form, Input, Radio, SendButton } from '../../../../components';

export function SpecialistSignUp() {
  const {
    lawyer,
    psychologist,
    type,
    setType,
    handleRegister,
    onChangeLawyer,
    onChangePsychologist,
    cleanLawyer,
    cleanPsychologist,
  } = UseRegisterSpecialist();

  useEffect(() => {
    cleanLawyer();
    cleanPsychologist();
  }, [type]);

  return (
    <>
      <Back side={'right'} />
      <ToastContainer />
      <section className='specialist-signup-container'>
        {type === 'lawyer' ? (
          <Form>
            <h1>Advogado</h1>
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.fullname}
              title={'Nome Completo'}
              name={'fullname'}
            />
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.oabRegisterNumber}
              title={'Nº de inscrição na OAB'}
              name={'oabRegisterNumber'}
            />
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.state}
              title={'Seccional'}
              name={'state'}
            />
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.city}
              title={'Cidade'}
              name={'city'}
            />
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.email}
              title={'E-mail para contato'}
              name={'email'}
            />
            <Input
              type={'text'}
              onChange={onChangeLawyer}
              value={lawyer.phone}
              title={'Telefone para contato'}
              name={'phone'}
            />
            <SendButton onClick={handleRegister} text={'Oferecer serviços'} />
          </Form>
        ) : type === 'psychologist' ? (
          <Form>
            <h1>Psicólogo</h1>
            <div className='radio-holder'>
              <Radio
                onChange={onChangePsychologist}
                value={'CPF'}
                title={'CPF'}
                name={'type'}
              />
              <Radio
                onChange={onChangePsychologist}
                value={'CNPJ'}
                title={'CNPJ'}
                name={'type'}
              />
            </div>

            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.fullname}
              title={'Nome Completo'}
              name={'fullname'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.registerNumber}
              title={'Nº de registro no CRP'}
              name={'registerNumber'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.cpfOrCpnj}
              title={psychologist.type === 'CNPJ' ? 'CNPJ' : 'CPF'}
              name={'cpfOrCpnj'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.state}
              title={'Estado'}
              name={'state'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.city}
              title={'Cidade'}
              name={'city'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.email}
              title={'E-mail para contato'}
              name={'email'}
            />
            <Input
              type={'text'}
              onChange={onChangePsychologist}
              value={psychologist.phone}
              title={'Telefone para contato'}
              name={'phone'}
            />
            <SendButton onClick={handleRegister} text={'Oferecer serviços'} />
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
