'use client';

import React, { useState } from 'react';
import Link from 'next/link';
import { Avatar, Box, IconButton, Menu, MenuItem } from '@mui/material';
import { useUser } from '@/contexts/UserProvider';
import { useRouter } from 'next/navigation';


const navLinks = [
  { name: 'Dashboard', href: '/dashboard' },
  { name: 'Accounts', href: '/accounts' },
  { name: 'Transactions', href: '/transactions' },
  { name: 'Objectives', href: '/objectives' },
  { name: 'Reports', href: '/reports' }
];

export default function Header() {

  const { logout } = useUser();
  const router = useRouter();
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const isMenuOpen = Boolean(anchorEl);

  const handleProfileMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };
  
  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logout();
    handleMenuClose();
    router.push('/login');
  };

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
 <Box sx={{ position: 'relative' }}>
            <IconButton
              onClick={handleProfileMenuOpen}
              size="small"
              sx={{ p: 0 }}
              aria-controls={isMenuOpen ? 'account-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={isMenuOpen ? 'true' : undefined}
            >
              <Avatar
                alt="User Avatar"
                src="https://placehold.co/32x32/E0E0E0/333?text=U" 
                sx={{ width: 32, height: 32 }}
              />
            </IconButton>
            <Menu
              id="account-menu"
              anchorEl={anchorEl}
              open={isMenuOpen}
              onClose={handleMenuClose}
              onClick={handleMenuClose} 
              PaperProps={{
                elevation: 0,
                sx: {
                  overflow: 'visible',
                  filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                  mt: 1.5,
                  '& .MuiAvatar-root': {
                    width: 32,
                    height: 32,
                    ml: -0.5,
                    mr: 1,
                  },
                  '&:before': {
                    content: '""',
                    display: 'block',
                    position: 'absolute',
                    top: 0,
                    right: 14,
                    width: 10,
                    height: 10,
                    bgcolor: 'background.paper',
                    transform: 'translateY(-50%) rotate(45deg)',
                    zIndex: 0,
                  },
                },
              }}
              transformOrigin={{ horizontal: 'right', vertical: 'top' }}
              anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
            >
              <MenuItem component="a" href="/settings">
                Settings
              </MenuItem>
              <MenuItem onClick={handleLogout}>
                Logout
              </MenuItem>
            </Menu>
          </Box>

          <div className="md:hidden">
            <IconButton aria-label="open menu">
            </IconButton>
          </div>
        </div>

      </div>
    </header>
  );
}