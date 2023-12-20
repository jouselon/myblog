import React from 'react';
import BoardItem from 'components/BoardItem';
import latestBoardListMock from "./mocks/latest-baord-list.mock";
import Top3Item from "./components/Top3Item";
import {commentListMock, favoriteListMock, top3BoardListMock} from "./mocks";
import CommentItem from "./components/CommentItem";
import './App.css'
import FavoriteItem from "./components/FavoriteItem";


function App() {
  return (
    <>
        <div style={{display: 'flex', rowGap: '20px', columnGap: '30px' }}>
            {favoriteListMock.map(favoriteListItem => <FavoriteItem favoriteListItem={favoriteListItem} />) }
        </div>

</>

  );
}

export default App;
