import {SignInRequestDto, SignUpRequestDto} from "./request/auth";
import axios from "axios";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;
import {ResponseDto} from "./response";
import SignInResponseDto from "./response/auth/sign-in.response.dto";

const DOMAIN = 'http://localhost:4000'

const API_DOMAIN = `${DOMAIN}/api/v1`;

const SIGN_IN_URL=()=>`${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL=()=>`${API_DOMAIN}/auth/sign-up`;

export const signInRequest = async (requestBody: SignInRequestDto) => {
    const result = await axios.post(SIGN_IN_URL(),requestBody)
        .then(response => {
            const responseBody : SignInResponseDto = response.data;
            return responseBody
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody : ResponseDto = error.response.data;
            return responseBody;
        })
    return result;
}

export const signUpRequest = async (requestBody: SignUpRequestDto) => {

}