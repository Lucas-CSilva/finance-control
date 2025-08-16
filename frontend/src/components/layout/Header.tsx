import React from 'react';
import Link from 'next/link';
import { Avatar, IconButton } from '@mui/material';


const navLinks = [
  { name: 'Dashboard', href: '/dashboard' },
  { name: 'Accounts', href: '/accounts' },
  { name: 'Transactions', href: '/transactions' },
  { name: 'Objectives', href: '/objectives' },
  { name: 'Reports', href: '/reports' }
];

export default function Header() {
  return (
    <header>
      <div className="container mx-auto flex items-center justify-between p-4">
        <div className="flex items-center gap-8">
          <Link href="/dashboard" className="text-xl font-bold text-azul-profundo flex items-center gap-2">
            <h1>Finance Control</h1>
          </Link>
        </div>

        <div className="flex items-center gap-4">
          <nav className="hidden md:flex items-center gap-6">
            {navLinks.map((link) => (
              <Link 
                key={link.name} 
                href={link.href} 
                className="text-sm font-medium text-cinza-medio hover:text-azul-profundo transition-colors"
              >
                {link.name}
              </Link>
            ))}
          </nav>
          <Link href="/settings">
            <Avatar 
              alt="User Avatar" 
              src="/path-to-user-avatar.jpg" 
              sx={{ width: 32, height: 32, cursor: 'pointer' }}
            />
          </Link>

          <div className="md:hidden">
            <IconButton aria-label="open menu">
            </IconButton>
          </div>
        </div>

      </div>
    </header>
  );
}