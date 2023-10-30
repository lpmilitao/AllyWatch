import './card.style.css';

import arrow from '../../../assets/icons/short-arrow-pink.svg';
import { cardsContent } from '../../../assets/arrays/presentation-cards.js';
import { useState } from 'react';

const circles = [0, 1, 2, 3, 4, 5];

export function Card() {
  const [index, setIndex] = useState(0);

  function next() {
    if (index + 1 > 5) {
      setIndex(0);
    } else {
      setIndex(index + 1);
    }
  }

  function previous() {
    if (index - 1 < 0) {
      setIndex(5);
    } else {
      setIndex(index - 1);
    }
  }

  return (
    <div className='card'>
      <img
        src={cardsContent.at(index).icon}
        alt={cardsContent.at(index).function}
      />
      <h1>{cardsContent.at(index).function}</h1>
      <div className='text-buttons'>
        <button onClick={previous}>
          <img src={arrow} alt='Pink arrow' />
        </button>
        <p>{cardsContent.at(index).text}</p>
        <button onClick={next}>
          <img src={arrow} alt='Pink arrow' className='mirror' />
        </button>
      </div>
      <div className='circles-holder'>
        {circles.map((c) => {
          return (
            <div className={c === index ? 'circle fullfilled' : 'circle'} />
          );
        })}
      </div>
    </div>
  );
}
