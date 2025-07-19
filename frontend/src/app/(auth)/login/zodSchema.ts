import { z } from 'zod';

export const loginSchema = z.object({
  email: z.email('Email inválido'),
  password: z.string(),
  remember: z.boolean().optional(),
});

export type LoginSchemaType = z.infer<typeof loginSchema>;
