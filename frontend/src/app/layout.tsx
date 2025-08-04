import { CssBaseline, ThemeProvider } from '@mui/material';
import { AppRouterCacheProvider } from '@mui/material-nextjs/v15-appRouter';

import { inter, poppins } from '@/styles/fonts';
import theme from '@/styles/theme';

import type { Metadata } from 'next';

import './globals.css';
import QueryProvider from '@/contexts/QueryProvider';
import { Toaster } from 'sonner'; 

export const metadata: Metadata = {
  title: 'Finance Control',
  description: 'Sua ferramenta para um controle financeiro inteligente.',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${inter.variable} ${poppins.variable} antialiased`}
      >
        <AppRouterCacheProvider options={{ enableCssLayer: true }}>
          <ThemeProvider theme={theme}>
            <QueryProvider>
              <CssBaseline />
              <Toaster richColors position="bottom-right" closeButton />
              {children}
            </QueryProvider>
          </ThemeProvider>
        </AppRouterCacheProvider>
      </body>
    </html>
  );
}
