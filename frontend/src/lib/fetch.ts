import { getClientSideToken } from "@/services/auth/token.service";
import { url } from "inspector";

type FetchOptions = Omit<RequestInit, 'headers'> & {
    headers?: Record<string, string>
}

interface BaseFetchParameters {
    url: string;
    options: FetchOptions;
    useAuth: boolean;
}

type FetchParameters = Omit<BaseFetchParameters, 'useAuth'>;

async function baseFetch<T>({ url, options = {}, useAuth }: BaseFetchParameters): Promise<T> {

    const headers: Record<string, string> = {};

    if (useAuth) {
        const token = getClientSideToken();
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }
    }

    const isFormData = options.body instanceof FormData;
    if (isFormData) {
        headers['Content-Type'] = 'application/json';
    }

    const requestOptions: RequestInit = {
        ...options,
        headers: {
            ...headers,
            ...(options.headers || {}),
        },
    };

    const response = await fetch(`${API_BASE_URL}${url}`, requestOptions);

    if (!response.ok) {
        const errorMessage = await extractErrorMessage(response);
        throw new Error(errorMessage);
    }

    if (response.status === 204) {
        return undefined as T;
    }

    return response.json();
}


async function extractErrorMessage(response: Response): Promise<string> {
    try {
        const data = await response.json();
        return data?.message || response.statusText;
    } catch {
        return response.statusText;
    }
}

export function fetchWithAuth<T>({ url, options = {} }: FetchParameters): Promise<T> {
    return baseFetch<T>({ url: url, options: options, useAuth: true })
}

export function fetchWithoutAuth<T>({ url, options = {} }: FetchParameters): Promise<T> {
    return baseFetch<T>({ url: url, options: options, useAuth: false })
}