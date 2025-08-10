'use client';

import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import React, { useState } from 'react';
import {toast} from 'sonner';

interface QueryProviderProps {
    children: React.ReactNode;
}

export default function QueryProvider({ children } :  QueryProviderProps) {
  const [queryClient] = useState(new QueryClient({
    defaultOptions: {
      mutations: {
        onError: (error) => {
          toast.error(error.message);
        },
      },
    }
  }));

  return ( 
    <QueryClientProvider client={queryClient}>
      {children}
    </QueryClientProvider>
  );
}