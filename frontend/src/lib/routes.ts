
const publicRoutes = [
  '/login',
  '/register',
];

export const PUBLIC_ROUTE_REDIRECT = '/login';
export const PROTECTED_ROUTE_REDIRECT = '/dashboard';

export function isPublicRoute(pathname: string): boolean {
  return publicRoutes.includes(pathname);
}
