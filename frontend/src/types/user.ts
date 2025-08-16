export interface User {
    id: string;
    name: string;
    email: string;
}

export interface UpdateSettingsRequest {
    name: string;
    currentPassword: string | null;
    newPassword: string | null;
}