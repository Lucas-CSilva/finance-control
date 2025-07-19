import logger from '@/lib/logger';
import { isPublicRoute, PROTECTED_ROUTE_REDIRECT, PUBLIC_ROUTE_REDIRECT } from '@/lib/routes';
import { getTokenFromRequest } from '@/services/token.service';
import { NextRequest, NextResponse } from 'next/server';

export default function middleware(request: NextRequest) {
  const { pathname } = request.nextUrl;
  const token = getTokenFromRequest(request);
  const isPublic = isPublicRoute(pathname);

  logger.info(`Middleware executing for ${pathname}`);

  if (isPublic && token) {
    logger.info(`Redirecting logged-in user from public route to protected route: ${PROTECTED_ROUTE_REDIRECT}`);
    return NextResponse.redirect(new URL(PROTECTED_ROUTE_REDIRECT, request.url));
  }

  if (!isPublic && !token) {
    logger.info(`Redirecting unauthenticated user from protected route to public route: ${PUBLIC_ROUTE_REDIRECT}`);
    return NextResponse.redirect(new URL(PUBLIC_ROUTE_REDIRECT, request.url));
  }

  logger.info(`Allowing request for ${pathname}`);

  return NextResponse.next();
}

export const config = {
  /*
     * Faz o match de todas as rotas exceto:
     * - /api (rotas de API)
     * - /_next/static (arquivos estáticos)
     * - /_next/image (arquivos de otimização de imagem)
     * - /favicon.ico (ícone de favorito)
    */
  matcher: ['/((?!api|_next/static|_next/image|favicon.ico).*)'],
};