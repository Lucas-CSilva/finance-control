'use client';

import React from 'react';
import { TextField, TextFieldProps, InputAdornment, IconButton } from '@mui/material';
import { Eye, EyeOff } from 'lucide-react';

type PasswordFieldProps = TextFieldProps & {
  showPassword?: boolean;
  setShowPassword?: () => void;
}

export const PasswordField: React.FC<PasswordFieldProps> = ({
  showPassword,
  setShowPassword,
  ...props
}) => {
  return (
    <TextField 
      {...props}
      type={showPassword ? 'text' : 'password'} 
      variant='outlined'
      size='medium'
      margin='normal'
      fullWidth
      sx={{ borderRadius: 3}}
      slotProps={{
        input: {
          endAdornment: (
            <InputAdornment position='end'>
              <IconButton aria-label="toggle password visibility"
                onClick={setShowPassword}
                edge="end">
                {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
              </IconButton>
            </InputAdornment>
          )
        }
      }}
    />
  );
};