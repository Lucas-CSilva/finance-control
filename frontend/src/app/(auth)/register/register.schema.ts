import { z } from 'zod';

export const registerSchema = z.object({
  email: z.email('Invalid email'),
  name: z.string(),
  password: z.string().min(6, 'Minimum 6 characters'),
  confirmPassword: z.string(),
  acceptTerms: z.boolean().refine((value) => value === true, {
    message: 'You must accept terms'
  }),
}).refine((data) => data.password === data.confirmPassword, {
  message: 'Passwords do not match',
  path: ['confirmPassword'],
});

export type RegisterSchemaType = z.infer<typeof registerSchema>;
