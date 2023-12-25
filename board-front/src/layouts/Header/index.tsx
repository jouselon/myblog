//rfc

import React, {useState, ChangeEvent, useRef, KeyboardEvent, useEffect} from 'react'
import './style.css'
import {useNavigate, useParams} from "react-router-dom";
import {AUTH_PATH, MAIN_PATH, SEARCH_PATH, USER_PATH} from 'constants/index';
import * as events from "events";
import {useCookies} from "react-cookie";

//component : 헤더 레이아웃
export default function Header() {

    //state : cookie 상태 -로그인 후 마이페이지버튼으로 변경...
    const [cookies, setCookies] = useCookies();
    //state : 로그인 상태
    const[isLogin, setLogin] = useState<boolean>(false)


    //function : 네비게이트 함수
    const navigate = useNavigate();

    //event handler : 로고 클릭 이벤트 처리 함수
    const onLogoClickHandler = () => {
        navigate(MAIN_PATH());
    }

    //component : 검색 버튼 컴포넌트
    const SearchButton = () => {

        //state : 검색 버튼 요소 참조 상태
        const searchButtonRef = useRef<HTMLDivElement | null>(null);

        //state : 검색 버튼 상태
        const [status, setStatus] = useState<boolean>(false);

        //state : 검색어 상태
        const [word, setWord] = useState<string>('');

        //state : 검색어 state path variable 상태
        const { searchWord } = useParams();



        //event handler : 검색어 변경 이벤트 처리 함수
        const onSearchWordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
            const value = event.target.value;
            setWord(value);
            return;
        }

        // event handler : 검색어 키 이벤트 처리 함수
        const onSearchWordKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
            if (event.key === 'Enter') {
                if (!searchButtonRef.current) return;

                searchButtonRef.current.click();
            }
        };


        // event handler : 검색 아이콘 클릭 이벤트 처리 함수
        const onSearchButtonClickHandler = () => {
            if(!status) {
                setStatus(!status);
                return;
            }
            navigate(SEARCH_PATH(word));
        };


        // event handler : 검색어 path variable 변경될 때마다 실행될 함수, 검색어 유지
        useEffect(() => {
            if (searchWord) {
                setWord(searchWord);
                setStatus(true);
            }
        }, [searchWord]);




        if(!status)
        //render : 검색 버튼 컴포넌트 렌더링 (클릭 false 상태)
        return (
            <div className='icon-button' onClick={onSearchButtonClickHandler}>
            <div className='icon search-light-icon'></div>
        </div>
        );

        //render : 검색 버튼 컴포넌트 렌더링 (클릭 true 상태)
        return (
            <div className='header-search-input-box'>
                <input className='header-search-input' type='text' placeholder='검색어를 입력해주세요.'
                       value={word}
                       onChange={onSearchWordChangeHandler}
                       onKeyDown={onSearchWordKeyDownHandler}/>
                <div ref={searchButtonRef} className='icon-button' onClick={onSearchButtonClickHandler}>
                    <div className='icon search-light-icon'></div>
                </div>
            </div>
        );
    };

    //component : 로그인 또는 마이페이지 버튼 컴포넌트
    const LoginMyPageButton = () => {

        //event handler : 마이페이지 버튼 클릭 이벤트 처리 함수
        const onMyPageButtonClickHandler = () => {
            navigate(USER_PATH(''));
        }

        //event handler : 로그인 버튼 클릭 이벤트 처리 함수
const onSighInButtonClickHandler = () => {
            navigate(AUTH_PATH());
}

        if (isLogin)
            //render: 마이페이지 버튼 컴포넌트 렌더링
            return <div className='white-button' onClick={onMyPageButtonClickHandler}>{'마이페이지'}</div>

        //render : 로그인 버튼 컴포넌트 렌더링
        return <div className='black-button'>{'로그인'}</div>;

    }

    //render : 헤더 레이아웃 렌더링
    return (
        <div id='header'>
            <div className='header-container'>
                <div className='header-left-box' onClick={onLogoClickHandler}>
                    <div className='icon-box' >
                        <div className='icon logo-dark-icon'></div>
                    </div>

                    <div className='header-logo'>{`FILMPORT`}</div>
                </div>
                <div className='header-right-box'>
                    <SearchButton/>
                    <LoginMyPageButton/>
                </div>
            </div>
        </div>
    )
}