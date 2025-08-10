import { fetchWithoutAuth } from '@/lib/fetch';
import { LoginRequest, LoginResponse, RegisterRequest } from '@/types/auth';

export function login(data: LoginRequest): Promise<LoginResponse> {
  return fetchWithoutAuth({
    url: '/api/auth/login',
    options: {
      method: 'POST',
      body: JSON.stringify(data),
    },
  });
}

export function register(data: RegisterRequest): Promise<void> {
  return fetchWithoutAuth({
    url: '/api/auth/register',
    options: {
      method: 'POST',
      body: JSON.stringify(data),
    },
  });
}