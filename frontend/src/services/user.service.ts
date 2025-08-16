import { fetchWithAuth } from '@/lib/fetch';
import { UpdateSettingsRequest, User } from '@/types/user';

export function updateSettings(userId: string, data: UpdateSettingsRequest): Promise<User> {
  return fetchWithAuth({
    url: `/api/users/${userId}/settings`,
    options: {
      method: 'PATCH',
      body: JSON.stringify(data),
    },
  });
}