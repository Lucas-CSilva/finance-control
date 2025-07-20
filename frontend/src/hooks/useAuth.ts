import { login, register } from "@/services/auth/auth.service";
import { setClientSideToken } from "@/services/auth/token.service";
import { LoginRequest, LoginResponse } from "@/types/auth";
import { useMutation } from "@tanstack/react-query";
import { error } from "console";

export function useLogin() {
    return useMutation<LoginResponse, Error, LoginRequest>({
        mutationFn: login,
        onSuccess: (data) => {
            setClientSideToken(data.token);
        },
    })
}

export function useRegister() {
    return useMutation({
        mutationFn: register,
    })
}