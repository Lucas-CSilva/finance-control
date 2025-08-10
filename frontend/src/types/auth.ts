import { User } from './user';

export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegisterRequest extends LoginRequest {
    name: string;
}

export interface LoginResponse {
    token: string;
    user: User;
}