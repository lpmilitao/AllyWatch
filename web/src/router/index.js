import { createBrowserRouter } from 'react-router-dom';
import {
  Chatbot,
  Chats,
  ChooseType,
  HelpPoints,
  Home,
  Profile,
  Report,
  SignIn,
  Specialist,
  SpecialistSignUp,
  Timeline,
  UserSignUp,
} from '../ui/screens';
import { PrivateRoute } from './privateRoute';

export const router = createBrowserRouter([
  {
    path: '/home',
    element: <Home />,
  },
  {
    path: '/signin',
    element: <SignIn />,
  },
  {
    path: '/signup',
    element: <ChooseType />,
  },
  {
    path: '/signup-user',
    element: <UserSignUp />,
  },
  {
    path: '/signup-specialist',
    element: <SpecialistSignUp />,
  },
  {
    path: '/',
    element: <PrivateRoute Screen={Timeline} />,
  },
  {
    path: '/me',
    element: <PrivateRoute Screen={Profile} />,
  },
  {
    path: '/specialists',
    element: <PrivateRoute Screen={Specialist} />,
  },
  {
    path: '/chatbot',
    element: <PrivateRoute Screen={Chatbot} />,
  },
  {
    path: '/report',
    element: <PrivateRoute Screen={Report} />,
  },
  {
    path: '/help-points',
    element: <PrivateRoute Screen={HelpPoints} />,
  },
  {
    path: '/chats',
    element: <PrivateRoute Screen={Chats} />,
  },
]);
