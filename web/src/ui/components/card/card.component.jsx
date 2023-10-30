import './card.style.css';

import arrow from '../../../assets/icons/short-arrow-pink.svg';
import { cardsContent } from '../../../assets/arrays/presentation-cards.js';

const circles = [0, 1, 2, 3, 4, 5];

export function Card(index, previous, next) {
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
        {cardsContent.map((card, i) => {
          return (
            <div className={i === index ? 'circle fullfilled' : 'circle'} />
          );
        })}
      </div>
    </div>
  );
}
