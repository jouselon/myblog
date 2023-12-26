import React from 'react';
import 'App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Container from 'layouts/Container';
import Footer from './layouts/Footer';
import Main from 'views/Main';
import Authentication from 'views/Authentication';
import BoardDetail from 'views/Board/Detail';
import BoardWrite from 'views/Board/Write';
import BoardUpdate from 'views/Board/Update';
import User from 'views/User';
import Search from 'views/Search';
import {
    AUTH_PATH,
    BOARD_DETAIL_PATH,
    BOARD_PATH,
    BOARD_UPDATE_PATH,
    BOARD_WRITE_PATH,
    MAIN_PATH,
    SEARCH_PATH,
    USER_PATH,
} from 'constant';

// description: 메인화면 : '/' - Main
// description: 로그인 + 회원가입 화면 : '/auth' - Authentication
// description: 검색화면 : '/search/:searchWord' - Search
// description: 게시물 상세보기 : '/board/detail/:boardNumber' - BoardDetail
// description: 게시물 작성하기 : '/board/write' - BoardWrite
// description: 게시물 수정하기 : '/board/update/:boardNumber' - BoardUpdate

function App() {
    return (
        <Routes>
            <Route element={<Container />}>
                <Route path={MAIN_PATH()} element={<Main />} />
                <Route path={AUTH_PATH()} element={<Authentication />} />
                <Route path={SEARCH_PATH(':searchWord')} element={<Search />} />
                <Route path={BOARD_WRITE_PATH()} element={<BoardWrite />} />
                <Route path={BOARD_DETAIL_PATH(':boardNumber')} element={<BoardDetail />} />
                <Route path={BOARD_UPDATE_PATH(':boardNumber')} element={<BoardUpdate />} />
                <Route path={USER_PATH(':searchEmail')} element={<User />} />
                <Route path='*' element={<h1>404 Not Found</h1>} />
            </Route>
        </Routes>
    );
}


export default App;
