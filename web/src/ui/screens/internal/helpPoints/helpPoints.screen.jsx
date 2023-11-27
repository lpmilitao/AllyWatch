('use client');

import './helpPoints.style.css';
import '@reach/combobox/styles.css';

import { useEffect, useState } from 'react';

import usePlacesAutocomplete, {
  getGeocode,
  getLatLng,
} from 'use-places-autocomplete';

import {
  Combobox,
  ComboboxInput,
  ComboboxPopover,
  ComboboxList,
  ComboboxOption,
} from '@reach/combobox';

import { useLoadScript, GoogleMap, Marker } from '@react-google-maps/api';
import { BaseScreen, Loader } from '../../../components';

export function HelpPoints() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: 'AIzaSyDUrsRu6J6H9WbwjlsBIm7EEgUta4iTJTI',
    libraries: ['places'],
  });

  return (
    <BaseScreen at={'local'}>{!isLoaded ? <Loader /> : <Map />}</BaseScreen>
  );
}

function Map() {
  const [center, setCenter] = useState({ lat: 43.45, lng: -80.49 });
  const [selected, setSelected] = useState(null);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setCenter({
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          });
        },
        (error) => {
          console.error('Error Code = ' + error.code + ' - ' + error.message);
        },
      );
    }
  }, []);

  return (
    <>
      <div className='places-container'>
        <PlacesAutocomplete
          setSelected={setSelected}
          userLocation={center}
          setCenter={setCenter}
        />
      </div>

      <GoogleMap
        zoom={15}
        center={center}
        mapContainerClassName='map-container'
      >
        {selected && <Marker position={selected} />}
      </GoogleMap>
    </>
  );
}

const PlacesAutocomplete = ({ setSelected, userLocation, setCenter }) => {
  const searchOptions = {
    locationBias: {
      radius: 1000,
      center: { lat: userLocation.lat, lng: userLocation.lng },
    },
  };

  const {
    ready,
    value,
    setValue,
    suggestions: { status, data },
    clearSuggestions,
  } = usePlacesAutocomplete({ requestOptions: searchOptions });

  const handleSelect = async (address) => {
    setValue(address, searchOptions);
    clearSuggestions();

    const results = await getGeocode({ address });
    const { lat, lng } = getLatLng(results[0]);
    setSelected({ lat, lng });
    setCenter({ lat, lng });
  };

  return (
    <Combobox onSelect={handleSelect} className='combobox-container'>
      <div class='group'>
        <svg class='icon' aria-hidden='true' viewBox='0 0 24 24'>
          <g>
            <path d='M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z'></path>
          </g>
        </svg>
        <ComboboxInput
          value={value}
          onChange={(e) => setValue(e.target.value)}
          disabled={!ready}
          className='combobox-input'
          placeholder='Procure por um ponto de ajuda'
        />
      </div>

      <ComboboxPopover>
        <ComboboxList>
          {status === 'OK' &&
            data.map(({ place_id, description }) => (
              <ComboboxOption key={place_id} value={description} />
            ))}
        </ComboboxList>
      </ComboboxPopover>
    </Combobox>
  );
};
