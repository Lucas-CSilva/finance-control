import { fetchWithAuth } from '@/lib/fetch';
import { UpdateSettingsRequest, User } from '@/types/user';

export function updateSettings(data: UpdateSettingsRequest): Promise<User> {
  return fetchWithAuth({
    url: '/api/users/me/update-settings',
    options: {
      method: 'PATCH',
      body: JSON.stringify(data),
    },
  });
}