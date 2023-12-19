import React from 'react';
import BoardItem from 'components/BoardItem';
import latestBoardListMock from "./mocks/latest-baord-list.mock";

function App() {
  return (
    <>
        {latestBoardListMock.map(boardListItem=>  <BoardItem boardListItem={boardListItem} /> )}


    </>
  );
}

export default App;
