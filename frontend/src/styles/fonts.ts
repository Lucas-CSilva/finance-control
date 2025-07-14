import { Poppins, Inter } from 'next/font/google';

export const poppins = Poppins({
  variable: '--font-poppins',
  weight: ['500', '600', '700'],
  subsets: ['latin'],
  display: 'swap',
});

export const inter = Inter({
  variable: '--font-inter',
  weight: ['400', '500'],
  subsets: ['latin'],
  display: 'swap',
});