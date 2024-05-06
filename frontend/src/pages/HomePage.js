import React, { useState } from 'react';
import { Box, Drawer, List, ListItem, ListItemText, ListItemIcon, IconButton } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import HistoryIcon from '@mui/icons-material/History';
import MenuIcon from '@mui/icons-material/Menu';
import ProductList from '../components/ProductList';
import OrderHistory from '../components/OrderHistory';

export default function HomePage() {
  const [open, setOpen] = useState(false);
  const [selectedPage, setSelectedPage] = useState('Products');

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const menuItems = [
    { text: 'Products', icon: <ShoppingCartIcon />, component: <ProductList /> },
    { text: 'Order History', icon: <HistoryIcon />, component: <OrderHistory /> },
  ];

  return (
    <Box sx={{ display: 'flex' }}>
      <Drawer
        variant="permanent"
        onMouseOver={handleDrawerOpen}
        onMouseOut={handleDrawerClose}
        sx={{
          width: open ? 220 : 50,
          flexShrink: 0,
          whiteSpace: 'nowrap',
          [`& .MuiDrawer-paper`]: { width: open ? 220 : 50, boxSizing: 'border-box', overflow: 'hidden', transition: 'width 0.5s ease' },
        }}
      >
        {(
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            sx={{ ml: 1, mt: 1 }}
          >
            <MenuIcon />
          </IconButton>
        )}
        <List>
          {menuItems.map((item, index) => (
            <ListItem button key={index} onClick={() => setSelectedPage(item.text)}>
              <ListItemIcon>{item.icon}</ListItemIcon>
              <ListItemText primary={open ? item.text : ''} />
            </ListItem>
          ))}
        </List>
      </Drawer>
      <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
        {menuItems.find(item => item.text === selectedPage)?.component}
      </Box>
    </Box>
  );
}
