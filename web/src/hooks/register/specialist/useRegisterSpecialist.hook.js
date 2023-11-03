import { useState } from 'react';

import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';

import { states } from '../../../assets/arrays/brazil-states.js';
import { createNewLawyer, createNewPsychology } from '../../../external/server';

export function UseRegisterSpecialist() {
  const [lawyer, setLawyer] = useState({
    fullname: '',
    state: '',
    oabRegisterNumber: '',
    email: '',
    phone: '',
    city: '',
  });

  const [psychologist, setPsychologist] = useState({
    fullname: '',
    state: '',
    registerNumber: '',
    email: '',
    phone: '',
    city: '',
    type: 'CPF',
    cpfOrCpnj: '',
  });
  const [type, setType] = useState('specialist');

  async function handleRegister(event) {
    event.preventDefault();

    if (type === 'lawyer') {
      await registerLawyer();
    } else {
      await registerPsychologist();
    }
  }

  async function registerLawyer() {
    if (
      lawyer.fullname === '' ||
      lawyer.state === '' ||
      lawyer.oabRegisterNumber === '' ||
      lawyer.email === '' ||
      lawyer.phone === '' ||
      lawyer.city === ''
    ) {
      toast.error('Preencha todos os campos', {
        position: 'bottom-right',
      });
      return;
    }

    if (lawyer.oabRegisterNumber.length < 6) {
      toast.error('OAB inválida', {
        position: 'bottom-right',
      });
      return;
    }

    if (states.indexOf(lawyer.state) === -1) {
      toast.error('Estado inválido', {
        position: 'bottom-right',
      });
      return;
    }

    try {
      await createNewLawyer(lawyer);
      toast.success('Serviços oferecidos!', {
        position: 'bottom-right',
      });
    } catch (error) {
      toast.error('Erro ao oferecer serviços', {
        position: 'bottom-right',
      });
    }
  }

  async function registerPsychologist() {
    if (
      psychologist.fullname === '' ||
      psychologist.state === '' ||
      psychologist.registerNumber === '' ||
      psychologist.email === '' ||
      psychologist.phone === '' ||
      psychologist.city === '' ||
      psychologist.cpfOrCpnj === ''
    ) {
      toast.error('Preencha todos os campos', {
        position: 'bottom-right',
      });
      return;
    }

    const regex = /^\d{2}\/\d{6}\-[A-Z]{2}$/;

    if (!regex.test(psychologist.registerNumber)) {
      toast.error('CRP inválido, formate corretamente.', {
        position: 'bottom-right',
      });
      return;
    }

    if (states.indexOf(psychologist.state) === -1) {
      toast.error('Estado inválido', {
        position: 'bottom-right',
      });
      return;
    }

    cleanCpfCnpj();

    try {
      await createNewPsychology(psychologist);
      toast.success('Serviços oferecidos!', {
        position: 'bottom-right',
      });
    } catch (error) {
      toast.error('Erro ao oferecer serviços', {
        position: 'bottom-right',
      });
    }
  }

  function onChangeLawyer(event) {
    setLawyer({
      ...lawyer,
      [event.target.name]: event.target.value,
    });
  }

  function onChangePsychologist(event) {
    setPsychologist({
      ...psychologist,
      [event.target.name]: event.target.value,
    });
  }

  function cleanCpfCnpj() {
    setPsychologist({
      ...psychologist,
      cpfOrCpnj: psychologist.cpfOrCpnj.replace(/\D/g, ''),
    });
  }

  function cleanLawyer() {
    setLawyer({
      fullname: '',
      state: '',
      oabRegisterNumber: '',
      email: '',
      phone: '',
      city: '',
    });
  }

  function cleanPsychologist() {
    setPsychologist({
      fullname: '',
      state: '',
      registerNumber: '',
      email: '',
      phone: '',
      city: '',
      type: 'CPF',
      cpfOrCpnj: '',
    });
  }

  return {
    lawyer,
    psychologist,
    type,
    setType,
    handleRegister,
    onChangeLawyer,
    onChangePsychologist,
    cleanLawyer,
    cleanPsychologist,
  };
}
