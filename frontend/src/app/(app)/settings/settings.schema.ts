import { z } from 'zod';

export const settingsSchema = z.object({
  name: z.string().min(1, 'Name is required'),
  email: z.string().optional(),
  currentPassword: z.string().optional(),
  newPassword: z.string().optional(),
  confirmPassword: z.string().optional(),
})
  .refine((data) => {
    if ((data.newPassword || data.confirmPassword)) {
      return !!data.currentPassword;
    }
    return true;
  }, {
    message: 'Current password is required',
    path: ['currentPassword'],
  })
  .refine((data) => {
    if (data.newPassword) {
      return data.newPassword.length >= 6;
    }
    return true;
  }, {
    message: 'New password must be at least 6 characters',
    path: ['newPassword'],
  })
  .refine((data) => {
    if (data.newPassword || data.confirmPassword) {
      return data.newPassword === data.confirmPassword;
    }
    return true;
  }, {
    message: 'Passwords do not match',
    path: ['confirmPassword'],
  });

export type SettingsSchemaType = z.infer<typeof settingsSchema>;