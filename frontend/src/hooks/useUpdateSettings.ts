import { updateSettings } from '@/services/user.service';
import { UpdateSettingsRequest, User } from '@/types/user';
import { useMutation } from '@tanstack/react-query';


export function useUpdateSettings() {
  return useMutation<User, Error, UpdateSettingsRequest>({
    mutationFn: updateSettings,
  });
}