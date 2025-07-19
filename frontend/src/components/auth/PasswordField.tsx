import React from 'react';
import { TextField, Button, TextFieldProps } from '@mui/material';
import { Eye, EyeOff } from 'lucide-react';

interface PasswordFieldProps extends Omit<TextFieldProps, 'type' | 'InputProps'> {
  error?: boolean;
  helperText?: string;
  showPassword: boolean;
  setShowPassword: (show: boolean) => void;
}

export const PasswordField: React.FC<PasswordFieldProps> = ({
  label,
  value,
  onChange,
  error,
  helperText,
  showPassword,
  setShowPassword,
  ...rest
}) => (
  <TextField
    label={label}
    type={showPassword ? 'text' : 'password'}
    value={value}
    onChange={onChange}
    error={error}
    helperText={helperText}
    fullWidth
    margin="normal"
    InputProps={{
      endAdornment: (
        <Button
          size="small"
          onClick={() => setShowPassword(!showPassword)}
          aria-label={showPassword ? 'Hide password' : 'Show password'}
          tabIndex={0}
        >
          {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
        </Button>
      ),
    }}
    {...rest}
  />
);