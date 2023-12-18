import React from 'react'
import './style.css';

//components : Board List Item 컴포넌트
export default function index() {

    // render : Board List Item 컴포넌트 렌더링
    return (
        <div className='board-list-item'>
            <div className='board-list-item-box'>

                <div className='board-list-item-top'>
                    <div className='board-list-item-profile-box'>
                        <div className='board-list-item-profile-image'></div>
                    </div>

                    <div className='board-list-item-write-box'>
                        <div className='board-list-item-nickname'></div>
                        <div className='board-list-item-write-datetime'></div>
                    </div>
                </div>

                <div className='board-list-item-middle'>
                    <div className='board-list-item-title'></div>
                    <div className='board-list-item-content'></div>
                </div>

                <div className='board-list-item-bottom'>
                    <div className='board-list-item-counts'></div>
                </div>
            </div>

            <div className='board-list-item-image-box'>
                <div className='board-list-item-image'></div>
            </div>

        </div>
    )
}
