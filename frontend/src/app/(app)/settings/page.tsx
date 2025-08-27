'use client';
import { settingsSchema, SettingsSchemaType } from './settings.schema';
import { useForm, Controller } from 'react-hook-form';
import { Typography, TextField, Button } from '@mui/material';
import { zodResolver } from '@hookform/resolvers/zod';
import { useEffect, useState } from 'react';
import { PasswordField } from '@/components/auth/PasswordField';
import { useUpdateSettings } from '@/hooks/useUpdateSettings';
import { useUser } from '@/contexts/UserProvider';
import { ClientOnly } from '@/components/shared/ClientOnly';

export default function SettingsPage() {
  const { user, setUser } = useUser();

  const {handleSubmit, control, reset, formState: { errors }} = useForm<SettingsSchemaType>({
    resolver: zodResolver(settingsSchema),
    defaultValues: {
      name: user?.name || '',
      email: user?.email || '',
      currentPassword: '',
      newPassword: '',
      confirmPassword: ''
    },
    mode: 'onChange'
  });

  useEffect(() => {
    if (user) {
      reset({
        name: user.name,
        email: user.email,
        currentPassword: '',
        newPassword: '',
        confirmPassword: ''
      });
    }
  }, [user, reset]);

  const [showPassword, setShowPassword] = useState(false);
  const [showNewPassword, setShowNewPassword] = useState(false);
  const [showConfirm, setShowConfirm] = useState(false);

  const updateSettingsMutation = useUpdateSettings();

  const onSubmit = async (data: SettingsSchemaType) => {
    const updatedUser = await updateSettingsMutation.mutateAsync(
      {
        name: data.name,
        currentPassword: data.currentPassword || null,
        newPassword: data.newPassword || null
      });
    setUser(updatedUser);
  };

  return (
    <ClientOnly>
      <div className="max-w-2xl mx-auto px-4 py-8">
        <Typography variant="h3" fontWeight={700} gutterBottom>
          Settings
        </Typography>

        <form onSubmit={handleSubmit(onSubmit)} noValidate>

          <Typography variant="h5" fontWeight={600} sx={{mt: 3, mb: 1}}>
            Personal Information
          </Typography>

          <Controller
            name="name"
            control={control}
            render={({ field }) => (
              <TextField {...field} label="Full Name" fullWidth margin="normal"
                error={!!errors.name} helperText={errors.name?.message} />
            )}
          />

          <Controller
            name="email"
            control={control}
            render={({ field }) => (
              <TextField {...field} label="Email" fullWidth margin="normal"
                disabled error={!!errors.email} helperText={errors.email?.message} />
            )}
          />


          <Typography variant="h5" fontWeight={600} sx={{mt: 3, mb: 1}}>
            Password Management
          </Typography>

          <Controller
            name="currentPassword"
            control={control}
            render={({ field }) => (
              <PasswordField {...field} label="Current Password"
                showPassword={showPassword} setShowPassword={() => setShowPassword(p => !p)}
                error={!!errors.currentPassword} helperText={errors.currentPassword?.message} />
            )}
          />

          <Controller
            name="newPassword"
            control={control}
            render={({ field }) => (
              <PasswordField {...field} label="New Password"
                showPassword={showNewPassword} setShowPassword={() => setShowNewPassword(p => !p)}
                error={!!errors.newPassword} helperText={errors.newPassword?.message} />
            )}
          />

          <Controller
            name="confirmPassword"
            control={control}
            render={({ field }) => (
              <PasswordField {...field} label="Confirm New Password"
                showPassword={showConfirm} setShowPassword={() => setShowConfirm(p => !p)}
                error={!!errors.confirmPassword} helperText={errors.confirmPassword?.message} />
            )}
          />

          <div className="flex justify-end mt-6">
            <Button type="submit" variant="contained" className="rounded-lg px-6 py-2 normal-case">
            Update Settings
            </Button>
          </div>
        </form>
      </div>  
    </ClientOnly>
  );
}