'use client';

import React from 'react';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginSchema, LoginSchemaType } from './zodSchema';
import { useForm, Controller } from 'react-hook-form';
import {
  Box,
  Paper,
  Typography,
  TextField,
  Checkbox,
  FormControlLabel,
  Button,
  Divider,
  Link as MuiLink,
} from '@mui/material';
import { PasswordField } from '@/components/auth/PasswordField';
import { useLogin } from '@/hooks/useAuth';
import { useRouter } from 'next/navigation';


export default function LoginPage() {
  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm<LoginSchemaType>({
    resolver: zodResolver(loginSchema),
    defaultValues: {
      email: '',
      password: '',
      remember: false,
    },
    mode: 'onSubmit',
  });
  const router = useRouter();
  const loginMutation = useLogin();
  const [showPassword, setShowPassword] = React.useState(false);
  const [loading, setLoading] = React.useState(false);

  const onSubmit = async (data: LoginSchemaType) => {
    setLoading(true);
    try {
      await loginMutation.mutateAsync({email: data.email, password: data.password});
      router.push('/dashboard');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      minHeight="100vh"
      bgcolor="#f5f5f5"
      px={2}
    >
      <Paper
        elevation={3}
        sx={{
          p: 6,
          width: { xs: '100%', sm: 400 },
          maxWidth: 500,
          borderRadius: 4,
        }}
      >
        <Box textAlign="center" mb={3}>
        </Box>

        {/* Title */}
        <Typography variant="h4" align="center" fontWeight={700} gutterBottom>
          Welcome back
        </Typography>
        <Typography variant="body1" align="center" color="textSecondary" mb={4}>
          Please enter your details to login.
        </Typography>

        {/* Form */}
        <form onSubmit={handleSubmit(onSubmit)} noValidate>
          <Controller
            name="email"
            control={control}
            render={({ field }) => (
              <TextField
                {...field}
                label="Email"
                variant="outlined"
                fullWidth
                size="medium"
                margin="normal"
                error={!!errors.email}
                helperText={errors.email?.message}
              />
            )}
          />

          <Controller
            name="password"
            control={control}
            render={({ field }) => (
              <PasswordField
                {...field}
                label="Senha"
                error={!!errors.password}
                helperText={errors.password?.message}
                showPassword={showPassword}
                setShowPassword={setShowPassword}
              />
            )}
          />

          <Box display="flex" justifyContent="space-between" alignItems="center" mt={2} mb={3}>
            <Controller
              name="remember"
              control={control}
              render={({ field }) => (
                <FormControlLabel
                  control={<Checkbox {...field} checked={field.value} size="small" />}
                  label="Remember me"
                />
              )}
            />
            <MuiLink href="#" underline="none" variant="body2">
              Forgot password?
            </MuiLink>
          </Box>

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 1, py: 1.75, borderRadius: 3 }}
            disabled={loading}
            startIcon={loading ? (
              <span className="loader" style={{ width: 20, height: 20, border: '2px solid #fff', borderRadius: '50%', borderTop: '2px solid #3b82f6', animation: 'spin 1s linear infinite', display: 'inline-block' }} />
            ) : null}
          >
            {loading ? 'Entrando...' : 'LOGIN'}
          </Button>
        </form>

        {/* Or divider */}
        <Divider sx={{ my: 4, borderRadius: 2 }}>OR</Divider>

        {/* Social buttons */}
        <Button
          variant="outlined"
          fullWidth
          sx={{ mb: 2, textTransform: 'none', borderRadius: 3 }}
        >
          Continue with Apple
        </Button>
        <Button
          variant="outlined"
          fullWidth
          sx={{ textTransform: 'none', borderRadius: 3 }}
        >
          Continue with Google
        </Button>

        {/* Register link */}
        <Box textAlign="center" mt={4}>
          <Typography variant="body2">
            Don&apos;t have an account?{' '}
            <MuiLink href="/register" underline="none" fontWeight={600}>
              Register
            </MuiLink>
          </Typography>
        </Box>
      </Paper>
    </Box>
  );
}