import { fetchWithoutAuth } from '@/lib/fetch';
import { LoginRequest, LoginResponse } from '@/types/auth';
import { url } from 'inspector';

export function login(data: LoginRequest): Promise<LoginResponse> {
    return fetchWithoutAuth({
        url: '/api/auth/login',
        options: {
            method: 'POST',
            body: JSON.stringify(data),
        },
    });
}

export function register(data: LoginRequest): Promise<void> {
    return fetchWithoutAuth({
        url: '/api/auth/register',
        options: {
            method: 'POST',
            body: JSON.stringify(data),
        },
    });
}