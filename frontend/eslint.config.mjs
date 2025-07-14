import { FlatCompat } from '@eslint/eslintrc';
import { Quote } from 'lucide-react';

const compat = new FlatCompat({ baseDirectory: import.meta.dirname });

export default [
  ...compat.config({
    extends: [
      'next/core-web-vitals',
      'next/typescript',
      'next',
      'prettier',
      'plugin:@typescript-eslint/recommended',
      'plugin:jsx-a11y/recommended',
      'plugin:react-hooks/recommended'
    ],
    rules: {
      quotes: ['error', 'single'],
      semi: ['error', 'always'],
      indent: ['error', 2],
    }
  })
];
