import React from 'react';
import BoardItem from 'components/BoardItem';
import latestBoardListMock from "./mocks/latest-baord-list.mock";
import Top3Item from "./components/Top3Item";
import {commentListMock, top3BoardListMock} from "./mocks";
import CommentItem from "./components/CommentItem";
import './App.css'


function App() {
  return (
    <>
        <div style={{padding: '0 20px', display: 'flex', flexDirection: 'column', gap: '30px' }}>
            {commentListMock.map(commentListItem => <CommentItem commentListItem={commentListItem} />) }
        </div>

</>

  );
}

export default App;
