'use client';
import { createTheme } from '@mui/material/styles';

import { poppins, inter } from './fonts';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1E3A8A',
    },
    secondary: {
      main: '#3B82F6',
    },
    success: {
      main: '#10B981',
    },
    error: {
      main: '#EF4444',
    },
    warning: {
      main: '#F59E0B',
      dark: '#B45309',
    },
    text: {
      primary: '#1F2937',
      secondary: '#6B7280',
    }
  },
  typography: {
    fontFamily: inter.style.fontFamily,
    h1: { fontFamily: poppins.style.fontFamily, fontWeight: 700 },
    h2: { fontFamily: poppins.style.fontFamily, fontWeight: 600 },
    h3: { fontFamily: poppins.style.fontFamily, fontWeight: 600 },
    h4: { fontFamily: poppins.style.fontFamily, fontWeight: 600 },
    h5: { fontFamily: poppins.style.fontFamily, fontWeight: 500 },
    h6: { fontFamily: poppins.style.fontFamily, fontWeight: 500 },
  },
  components: {
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          borderRadius: '12px',
          '&.Mui-error .MuiOutlinedInput-notchedOutline': {
            borderColor: '#EF4444',
            borderWidth: '2px',
          },
        },
      },
    },
  }
});

export default theme;