import createGlobalState from 'react-create-global-state';

const [useGlobalLoading, Provider] = createGlobalState(false);

export const GlobalLoadingProvider = Provider;

export default useGlobalLoading;
