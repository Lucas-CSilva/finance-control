import { updateSettings } from '@/services/user.service';
import { UpdateSettingsRequest, User } from '@/types/user';
import { useMutation } from '@tanstack/react-query';

interface UpdateSettingsMutationParams {
    userId: string;
    data: UpdateSettingsRequest;
}


export function useUpdateSettings() {
  return useMutation<User, Error, UpdateSettingsMutationParams>({
    mutationFn: ({ userId, data }) => updateSettings(userId, data),
  });
}